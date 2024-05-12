package vn.gvt.ENote.Controllers;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.UserService;

@Controller
public class UserController {

	private UserService userService;

	@Autowired
    public UserController(UserService userService) {
        this.userService = userService;
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

	@GetMapping("/addNotes")
	public String addNotes() {
		return "add_notes";
	}

	@GetMapping("/viewNotes")
	public String viewNotes() {
		return "view_notes";
	}

	@GetMapping("/editNotes")
	public String editNotes() {
		return "edit_notes";
	}
}
