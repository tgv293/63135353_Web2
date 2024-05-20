package vn.gvt.ENote.Controllers;

import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.UserService;

@Controller
public class HomeController {
	
	private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }
    
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
}
