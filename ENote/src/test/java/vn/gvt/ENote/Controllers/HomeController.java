package vn.gvt.ENote.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
	@GetMapping("/")
	public String trangChu() {
		return "index";
	}
}
