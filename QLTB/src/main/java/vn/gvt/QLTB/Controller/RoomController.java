package vn.gvt.QLTB.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import vn.gvt.QLTB.Models.Phong;
import vn.gvt.QLTB.Services.LoaiThietBiService;
import vn.gvt.QLTB.Services.PhongService;

@Controller
public class RoomController {
	
	private PhongService phongService;
	private LoaiThietBiService loaiTBService;
	
	@Autowired
	public RoomController(PhongService phongService, LoaiThietBiService loaiTBService) {
		this.phongService = phongService;
		this.loaiTBService = loaiTBService;
	}
	
	@RequestMapping("/room/add")
	public String a(ModelMap model, @ModelAttribute("Phong") Phong phong, @RequestParam("idroom") String idroom ) {
	    Phong room= new Phong();
	    room.setMaPhong(idroom);
	    if (phongService.addRoom(room) != null) {
	        model.addAttribute("message","success");
	        model.addAttribute("action","Thêm Phòng");
	    }
	    else {
	        model.addAttribute("message","error");
	        model.addAttribute("action","Thêm Phòng");
	    }
	    model.addAttribute("listPhong", phongService.getPhong());
	    model.addAttribute("listLoaiTB", loaiTBService.getAllLoaiTB());
	    return "device/device_detail";
	}

}
