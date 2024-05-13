package vn.gvt.ENote.Controllers;


import lombok.extern.log4j.Log4j2;
import vn.gvt.ENote.Models.Note;
import vn.gvt.ENote.Models.NoteState;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.NoteManagement;
import vn.gvt.ENote.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Log4j2
@Controller
public class NotesManagementController {

    private final UserService userService;
    private final NoteManagement noteManagement;

    @Autowired
    public NotesManagementController(UserService userService, NoteManagement noteManagement) {
        this.userService = userService;
        this.noteManagement = noteManagement;
    }
    
	@ModelAttribute
	public void getUser(Principal p, Model m) {
	    String email = p.getName();
	    Optional<User> user = userService.getByEmail(email);
	    if (user.isPresent()) {
	        m.addAttribute("user", user.get());
	        m.addAttribute("firstName", user.get().getFirstName());
	    }
	}

	@GetMapping(value = "/new")
	public String createNewNote(Model model) {
	    model.addAttribute("note", new Note());
	    model.addAttribute("states", NoteState.values());
	    return "add_notes";
	}


    @PostMapping(value = "/saveNote")
    public String saveNote(Note note, Principal principal) {
        Optional<User> user = userService.getByEmail(principal.getName());
        if (user.isPresent()) {
            noteManagement.saveNew(note, user.get());
            return "redirect:/viewNotes";
        }

        return "add_notes";
    }

    @PostMapping(value = "/update")
    public String updateNote(Note note, Principal principal) {
        LOGGER.info("Updating a note with id = {}", note.getId());
        Optional<User> user = userService.getByEmail(principal.getName());
        user.ifPresent(value -> noteManagement.update(note, value));
        return "redirect:/note/" + note.getId();
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteNote(@PathVariable Integer id) {
        LOGGER.info("Deleting note with id = {}", id);
        noteManagement.deleteById(id);
        return "redirect:/notesGalleryView";
    }


    @GetMapping("/{id}")
    public String viewNote(@PathVariable Integer id, Model model) {
        noteManagement.getById(id).ifPresent(n -> model.addAttribute("note", n));
        return "viewNotes";
    }

    @GetMapping(value = "/archive/{id}")
    public String archiveNote(@PathVariable Integer id) {
        noteManagement.archive(id);
        return "redirect:/note/" + id;
    }

    @GetMapping(value = "/unarchive/{id}")
    public String unarchiveNote(@PathVariable Integer id) {
        noteManagement.unarchive(id);
        return "redirect:/note/" + id;
    }
}