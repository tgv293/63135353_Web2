package vn.gvt.QLBH_ABC.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	@GetMapping("/")
	public String trangChu(Model m) {
		m.addAttribute("title", "Trang chủ");
		return "index";
	}
	
	@GetMapping("/sbadmin")
	public String trangChuSB(Model m) {
		return "indexSB";
	}
}