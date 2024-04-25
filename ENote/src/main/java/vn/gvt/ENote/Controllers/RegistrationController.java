package vn.gvt.ENote.Controllers;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping("/register")
    public String showRegisterForm(Model model) {
        List<Integer> ages = IntStream.rangeClosed(3, 100).boxed().collect(Collectors.toList());
        model.addAttribute("ages", ages);
        model.addAttribute("user", new User());
        return REGISTRATION_PAGE;
    }
	
	@PostMapping("/register")
    public String addUser(User user, Model model) {
        if (userService.getByEmail(user.getEmail()).isPresent()) {
            model.addAttribute("user", user);
            model.addAttribute("isExisting", true);
            return REGISTRATION_PAGE;
        }

        user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getDecryptedPassword()));
        userService.defaultSave(user);
        return "redirect:/login";
    }
}
