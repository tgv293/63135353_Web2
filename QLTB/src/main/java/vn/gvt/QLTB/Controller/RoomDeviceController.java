package vn.gvt.QLTB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.gvt.QLTB.Models.LoaiThietBi;
import vn.gvt.QLTB.Models.Phong;
import vn.gvt.QLTB.Models.ThietBi;
import vn.gvt.QLTB.Services.LoaiThietBiService;
import vn.gvt.QLTB.Services.PhongService;
import vn.gvt.QLTB.Services.ThietBiService;

@Controller
public class RoomDeviceController {
	
	private PhongService phongService;
	private LoaiThietBiService loaiThietBiService;
	private ThietBiService thietBiService;
	
	@Autowired
	public RoomDeviceController(PhongService phongService, LoaiThietBiService loaiThietBiService,
			ThietBiService thietBiService) {
		this.phongService = phongService;
		this.loaiThietBiService = loaiThietBiService;
		this.thietBiService = thietBiService;
	}
	
	// Lấy danh sách phòng
	@ModelAttribute("listPhong")
	public List<Phong> getPhong() {
		return phongService.getPhong();
	}
	
	// Lấy danh sách loại thiết bị chưa có thiết bị
	@ModelAttribute("listTypeTB")
	public List<LoaiThietBi> getTypeTB() {
		return loaiThietBiService.getDistinctByThietBiIsNull();
	}
	
	// Trang chủ
	@GetMapping(value = "/device_detail")
	public String index(Model model, HttpServletRequest request, HttpSession session, @ModelAttribute("Phong") Phong phg) {
		model.addAttribute("navigation", "device_detail");
		model.addAttribute("listPhong", phongService.getPhong());
		model.addAttribute("listTB", thietBiService.getThietBiWithNullPhong());
		model.addAttribute("permission", session.getAttribute("permission"));
		model.addAttribute("info", session.getAttribute("info"));
		model.addAttribute("acc", session.getAttribute("acc"));
		
		// Toast
		model.addAttribute("action", session.getAttribute("action"));
		model.addAttribute("message", session.getAttribute("message"));
		session.removeAttribute("action");
		session.removeAttribute("message");
		return "device_detail";
	}
	
	// Thêm thiết bị vào phòng
	@RequestMapping(value = "/device_detail", params = "btnadd", method = RequestMethod.POST)
	public String addsp(ModelMap model, HttpServletRequest rq, @ModelAttribute("Phong") Phong phg, HttpSession ss) {
		for (ThietBi tb : thietBiService.getThietBiWithNullPhong()) {
			String maThietBiAsString = tb.getMaThietBi().toString();

			if (rq.getParameter(maThietBiAsString) != null) {
				int tmp = thietBiService.addTBToRoom(phg.getMaPhong(), tb.getMaThietBi());
				
				ss.setAttribute("action", "Thêm " + tb.getTenThietBi());
				if (tmp != 0) {
					ss.setAttribute("message", "success");
				} else {
					ss.setAttribute("message", "error");
				}
			}
		}

		model.addAttribute("listPhong", phongService.getPhong());
		model.addAttribute("listTB", thietBiService.getThietBiWithNullPhong());
		return "redirect:/device_detail";
	}
	
	// Hoàn tất bảo trì thiết bị
	@RequestMapping("/device_detail/repair_device_id={id}")
	public String hoanTatBaoTri(@PathVariable("id") Integer id, ModelMap model, @ModelAttribute("CTThietBi") ThietBi CTThietBi) {
		thietBiService.callSpBaoTriThietBi(id);
		model.addAttribute("listPhong", phongService.getPhong());
		return "redirect:/device_detail";
	}
	
	// Báo mất thiết bị
	@RequestMapping("/device_detail/lost_device_id={id}")
	public String baoMatThietBi(@PathVariable("id") Integer id, ModelMap model, @ModelAttribute("CTThietBi") ThietBi CTThietBi) {
		thietBiService.callSpBaoMatThietBi(id);
		model.addAttribute("listPhong", phongService.getPhong());
		return "redirect:/device_detail";
	}
	
	// Hoàn thành bảo trì thiết bị
	@RequestMapping("/device_detail/cpl_repair_device_id={id}")
	public String hoanthanhBTThietBi(@PathVariable("id") Integer id, ModelMap model, @ModelAttribute("CTThietBi") ThietBi CTThietBi) {
		thietBiService.callSpHoanThanhBTThietBi(id);
		model.addAttribute("listPhong", phongService.getPhong());
		return "redirect:/device_detail";
	}

}
