package vn.gvt.ENote.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

	@GetMapping("/register")
    public String showRegisterForm(Model model) {
        List<Integer> ages = IntStream.rangeClosed(4, 99).boxed().collect(Collectors.toList());
        model.addAttribute("ages", ages);
        return "register";
    }

	@GetMapping("/login")
	public String login() {
		return "login";
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
