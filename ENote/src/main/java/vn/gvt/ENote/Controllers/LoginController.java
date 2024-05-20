package vn.gvt.ENote.Controllers;

import java.security.Principal;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/current-user")
	public ResponseEntity<User> currentUser(Principal p) {
	    if (p != null) {
	        String email = p.getName();
	        Optional<User> user = userService.getByEmail(email);
	        if (user.isPresent()) {
	            return ResponseEntity.ok(user.get());
	        }
	    }
	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
