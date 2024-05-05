package vn.gvt.ENote.Controllers;

import java.io.Console;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Repositories.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@ModelAttribute
	public void getUser(Principal p, Model m) {
		String email = p.getName();
		User user = userRepo.findUserByEmail(email);
		m.addAttribute("user", user);
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
