package thiGK.ntu63135353.giapvantai_QLSinhVien.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SinhVienController {
	
	@GetMapping("/home")
	public String Home() {
		return "index";
	}

}