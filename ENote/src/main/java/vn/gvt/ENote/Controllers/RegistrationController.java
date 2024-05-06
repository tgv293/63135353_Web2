package vn.gvt.ENote.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.UserService;

@Controller
public class RegistrationController {
	
	private static final String REGISTRATION_PAGE = "register";
	
	private UserService userService;
	
	@Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/register")
    public String showRegisterForm(Model model) {
        List<Integer> ages = IntStream.rangeClosed(3, 100).boxed().collect(Collectors.toList());
        model.addAttribute("ages", ages);
        model.addAttribute("user", new User());
        return REGISTRATION_PAGE;
    }
	
	@PostMapping("/register")
	public String addUser(@ModelAttribute User user, HttpSession session, Model model) {
	    boolean f = userService.getByEmail(user.getEmail()).isPresent();

	    if (f) {
	        session.setAttribute("msg", "Email đã tồn tại");
	        List<Integer> ages = IntStream.rangeClosed(3, 100).boxed().collect(Collectors.toList());
	        model.addAttribute("ages", ages);
	        model.addAttribute("user", user);
	        return REGISTRATION_PAGE;
	    }

	    user.setPassword(passwordEncoder.encode(user.getDecryptedPassword()));
	    userService.defaultSave(user);
	    return "redirect:/login";
	}
	
}
