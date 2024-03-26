package thiGK.ntu63135353.giapvantai_QLSinhVien.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import thiGK.ntu63135353.giapvantai_QLSinhVien.models.SinhVien;

@Controller
public class SinhVienController {
	
	@GetMapping("/home")
	public String Home() {
		return "index";
	}
	

	static List<SinhVien> dsSinhViens = new ArrayList<SinhVien>();

	static {
		dsSinhViens.add(new SinhVien("0001", "Mai Cường Thọ",9));
		dsSinhViens.add(new SinhVien("0002", "Trần Văn Long",8));
		dsSinhViens.add(new SinhVien("0003", "Nguyễn Thị Hương",7));
		dsSinhViens.add(new SinhVien("0004", "Lê Anh Dũng",8));
		dsSinhViens.add(new SinhVien("0005", "Phạm Thị Bích",5));
		dsSinhViens.add(new SinhVien("0006", "Vũ Minh Hiếu",9));
		dsSinhViens.add(new SinhVien("0007", "Ngô Thị Lan",8));
		dsSinhViens.add(new SinhVien("0008", "Đỗ Văn Tùng",6));
		dsSinhViens.add(new SinhVien("0009", "Hoàng Thị Mai",5));
		dsSinhViens.add(new SinhVien("0010", "Trần Minh Quân",4));
		dsSinhViens.add(new SinhVien("0011", "Nguyễn Thành Nam",3));
		dsSinhViens.add(new SinhVien("0012", "Lý Thị Hà",2));

	}
	
	@GetMapping("/danhsachSV")
	public String listStudent(ModelMap mm) {
		mm.addAttribute("dsSV", dsSinhViens);
		return "QLSV";
	}

}