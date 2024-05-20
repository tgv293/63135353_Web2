package vn.gvt.ENote.Controllers;

import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.NoteManagement;
import vn.gvt.ENote.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class UserController {

	private final UserService userService;

	@Autowired
	public UserController(UserService userService, NoteManagement noteManagement) {
		this.userService = userService;
	}

	// Lấy thông tin người dùng hiện tại và truyền vào model
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

	// Xem thông tin người dùng
	@GetMapping(value = "/user-{id}")
	public String viewUser(@PathVariable Integer id, Model model) {
		userService.get(id).ifPresent(user -> model.addAttribute("user", user));
		List<Integer> ages = IntStream.rangeClosed(3, 100).boxed().collect(Collectors.toList());
		model.addAttribute("ages", ages);
		return "user_profile";
	}

	// Cập nhật thông tin người dùng
	@PostMapping(value = "/user-{id}")
	public String updateUser(@PathVariable Integer id, @ModelAttribute User updatedUser, HttpSession session) {
		Optional<User> existingUser = userService.getByEmail(updatedUser.getEmail());
		if (existingUser.isPresent() && existingUser.get().getId() != id) {
			session.setAttribute("msg", "Email đã tồn tại");
			return "redirect:/user-" + id;
		}
		userService.update(id, updatedUser);
		return "redirect:/user-" + id;
	}

	// Hiển thị form thay đổi mật khẩu
	@GetMapping("/changepass-user-{id}")
	public String showChangePasswordForm(@PathVariable Integer id, Model model) {
		userService.get(id).ifPresent(user -> model.addAttribute("user", user));
		model.addAttribute("actionUrl", "/changepass-user-" + id);
		return "change_pass";
	}

	// Thay đổi mật khẩu
	@PostMapping("/changepass-user-{id}")
	public String changePassword(@PathVariable Integer id, @RequestParam("decryptedPassword") String password, RedirectAttributes redirectAttributes) {
		Optional<User> optionalUser = userService.get(id);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();
			userService.changePassword(user, password);
			redirectAttributes.addFlashAttribute("successMessage", "Thay đổi mật khẩu thành công!");
			return "redirect:/changepass-user-" + id;
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy người dùng!");
			return "redirect:/changepass-user-" + id;
		}
	}

	// Xóa người dùng
	@GetMapping("/delete/user-{id}")
	public String deleteUser(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		userService.delete(id);
		return "redirect:/login"; // Chuyển hướng đến trang đăng nhập sau khi đăng xuất
	}
}
