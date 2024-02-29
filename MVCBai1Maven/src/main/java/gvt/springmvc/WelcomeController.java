package gvt.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WelcomeController {
	@RequestMapping("/welcome")
	public String sayHello(ModelMap map) {
		map.addAttribute("msg", "Hello Maven");
		return "welcome";
	}
}
