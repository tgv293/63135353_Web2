package vn.gvt.QLTB.Controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
	@GetMapping("/")
	public String trangChu() {
		return "index";
	}
}
