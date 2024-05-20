package vn.gvt.ENote.Controllers;

import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.NoteState;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.NoteManagement;
import vn.gvt.ENote.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class NotesManagementController {

	private final UserService userService;
	private final NoteManagement noteManagement;

	@Autowired
	public NotesManagementController(UserService userService, NoteManagement noteManagement) {
		this.userService = userService;
		this.noteManagement = noteManagement;
	}

	// Lấy thông tin người dùng hiện tại và thêm vào model
	@ModelAttribute
	public void getUser(Principal p, Model m) {
		if (p != null) {
			String email = p.getName();
			Optional<User> user = userService.getByEmail(email);
			if (user.isPresent()) {
				m.addAttribute("user", user.get());
				m.addAttribute("firstName", user.get().getFirstName());
			}
		}
	}

	// Trang tạo ghi chú mới
	@GetMapping(value = "/new")
	public String createNewNote(Model model) {
		model.addAttribute("note", new Note());
		model.addAttribute("states", NoteState.values());
		return "add_notes";
	}

	// Lưu ghi chú mới
	@PostMapping(value = "/saveNote")
	public String saveNote(Note note, Principal principal) {
		Optional<User> user = userService.getByEmail(principal.getName());
		if (user.isPresent()) {
			noteManagement.saveNew(note, user.get());
			return "redirect:/viewNotes";
		}

		return "add_notes";
	}

	// Cập nhật ghi chú
	@PostMapping(value = "/update")
	public String updateNote(Note note, Principal principal, @RequestParam("reminder") String reminderTime) {
		Optional<User> user = userService.getByEmail(principal.getName());
		if (!reminderTime.isEmpty()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			LocalDateTime reminder = LocalDateTime.parse(reminderTime, formatter);
			note.setReminderTime(reminder);
		}
		user.ifPresent(value -> noteManagement.update(note, value));
		return "redirect:/" + note.getId();
	}

	// Xóa ghi chú
	@GetMapping(value = "/delete/{id}")
	public String deleteNote(@PathVariable Integer id,
							 @RequestParam(required = false, name = "filterDone") Boolean filterDone,
							 @RequestParam(required = false, name = "filterToday") Boolean filterToday,
							 @RequestParam(required = false, name = "filterLastDays") Boolean filterLastDays,
							 RedirectAttributes redirectAttributes, @RequestParam(required = false, name = "from") String from) {
		noteManagement.deleteById(id);

		if (filterDone != null) {
			redirectAttributes.addAttribute("filterDone", filterDone);
		}
		if (filterToday != null) {
			redirectAttributes.addAttribute("filterToday", filterToday);
		}
		if (filterLastDays != null) {
			redirectAttributes.addAttribute("filterLastDays", filterLastDays);
		}

		switch (from) {
			case "viewNotes":
				return "redirect:/viewNotes";
			case "viewArchivedNotes":
				return "redirect:/viewArchivedNotes";
			default:
				return "redirect:/viewNotes";
		}
	}

	// Xem ghi chú
	@GetMapping("/{id}")
	public String viewNote(@PathVariable Integer id, Model model) {
		noteManagement.getById(id).ifPresent(n -> {
			model.addAttribute("note", n);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
			if (n.getReminderTime() != null) {
				String formattedReminderTime = n.getReminderTime().format(formatter);
				model.addAttribute("formattedReminderTime", formattedReminderTime);
			}
		});
		model.addAttribute("states", NoteState.values());
		return "detail_note";
	}

	// Xem danh sách ghi chú chưa được lưu trữ
	@GetMapping("/viewNotes")
	public String viewUnarchivedNotes(Model model, Principal principal,
									  @RequestParam(required = false, name = "filterDone") Boolean filterDone,
									  @RequestParam(required = false, name = "filterToday") Boolean filterToday,
									  @RequestParam(required = false, name = "filterLastDays") Boolean filterLastDays,
									  @RequestParam(required = false, name = "searchQuery") String searchQuery) {
		Optional<User> user = userService.getByEmail(principal.getName());
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(String.format("Username '%s' not found in DB.", principal.getName()));
		}

		List<Note> notes;
		if (filterDone != null && filterDone) {
			notes = noteManagement.getAllDoneUnarchived(user.get());

		} else if (filterDone != null) {
			notes = noteManagement.getAllNotDoneUnarchived(user.get());
		} else if (filterToday != null && filterToday) {
			notes = noteManagement.getAllUnarchivedByCreated(user.get(), LocalDate.now());
		} else if (filterLastDays != null && filterLastDays) {
			notes = noteManagement.getAllUnarchived(user.get()).stream()
					.filter(note -> note.getCreated().isBefore(LocalDate.now())).collect(Collectors.toList());
		} else {
			notes = noteManagement.getAllUnarchived(user.get());
		}

		// Áp dụng bộ lọc tìm kiếm nếu searchQuery không null
		if (searchQuery != null && !searchQuery.isEmpty()) {
			notes = notes.stream().filter(note -> note.getHeader().toLowerCase().contains(searchQuery.toLowerCase()))
					.collect(Collectors.toList());
		}

		model.addAttribute("currentPage", "viewNotes");
		model.addAttribute("searchQuery", searchQuery);
		model.addAttribute("notes", notes);
		model.addAttribute("states", NoteState.values());
		return "view_notes";
	}

	// Xem danh sách ghi chú đã được lưu trữ
	@GetMapping("/viewArchivedNotes")
	public String viewArchivedNotes(Model model, Principal principal,
									@RequestParam(required = false, name = "searchQuery") String searchQuery) {
		Optional<User> user = userService.getByEmail(principal.getName());
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(String.format("Username '%s' not found in DB.", principal.getName()));
		}

		List<Note> notes = noteManagement.getAllArchived(user.get());

		// Áp dụng bộ lọc tìm kiếm nếu searchQuery không null
		if (searchQuery != null && !searchQuery.isEmpty()) {
			notes = notes.stream().filter(note -> note.getHeader().toLowerCase().contains(searchQuery.toLowerCase()))
					.collect(Collectors.toList());
		}


		model.addAttribute("currentPage", "viewArchivedNotes");
		model.addAttribute("searchQuery", searchQuery);
		model.addAttribute("notes", notes);
		model.addAttribute("states", NoteState.values());
		return "view_archived_notes";
	}

	// Lấy danh sách ghi chú nhắc nhở
	@GetMapping("/reminders")
	public ResponseEntity<List<Note>> getReminders(Principal principal) {
		Optional<User> user = userService.getByEmail(principal.getName());
		if (!user.isPresent()) {
			throw new UsernameNotFoundException(String.format("Username '%s' not found in DB.", principal.getName()));
		}
		List<Note> reminders = noteManagement.getReminders(user.get());
		return ResponseEntity.ok(reminders);
	}

}
