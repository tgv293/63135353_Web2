package vn.gvt.ENote.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
	@GetMapping("/")
	public String trangChu() {
		return "index";
	}
}
