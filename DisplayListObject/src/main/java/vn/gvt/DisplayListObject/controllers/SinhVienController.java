package vn.gvt.DisplayListObject.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import vn.gvt.DisplayListObject.models.SinhVien;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SinhVienController {
	// hard-code data
	static List<SinhVien> dsSinhViens = new ArrayList<SinhVien>();

	static {
		dsSinhViens.add(new SinhVien("0001", "Mai Cường Thọ"));
		dsSinhViens.add(new SinhVien("0002", "Trần Văn Long"));
		dsSinhViens.add(new SinhVien("0003", "Nguyễn Thị Hương"));
		dsSinhViens.add(new SinhVien("0004", "Lê Anh Dũng"));
		dsSinhViens.add(new SinhVien("0005", "Phạm Thị Bích"));
		dsSinhViens.add(new SinhVien("0006", "Vũ Minh Hiếu"));
		dsSinhViens.add(new SinhVien("0007", "Ngô Thị Lan"));
		dsSinhViens.add(new SinhVien("0008", "Đỗ Văn Tùng"));
		dsSinhViens.add(new SinhVien("0009", "Hoàng Thị Mai"));
		dsSinhViens.add(new SinhVien("0010", "Trần Minh Quân"));
		dsSinhViens.add(new SinhVien("0011", "Nguyễn Thành Nam"));
		dsSinhViens.add(new SinhVien("0012", "Lý Thị Hà"));

	}
	
	@GetMapping("/danhsachSV")
	public String listStudent(ModelMap mm) {
		mm.addAttribute("dsSV", dsSinhViens);
		return "sinhvien_getAll";
	}
	
}
