package vn.gvt.FormSubmit_Thymeleaf.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import vn.gvt.FormSubmit_Thymeleaf.models.DTOSinhVien;

@Controller
public class SinhVienController {
	@GetMapping("/themMoiSV")
	public String showForm(Model mm) {
		DTOSinhVien sinhvienNull = new DTOSinhVien();
		mm.addAttribute("svDTO", sinhvienNull);
		return "themSinhVien_form";
	}

	@PostMapping("/themMoiSV")
	public String submitForm(@ModelAttribute("svDTO") DTOSinhVien sv) {
		return "themSinhVien_OK";
	}
}
