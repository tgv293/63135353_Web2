package vn.gvt.HelloSpringBoot.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/")
	public String trangchu(ModelMap model) {
		model.addAttribute("tb","Hello");
		model.addAttribute("hoten", "Giáp Văn Tài");
		return "welcome";
	}
}

