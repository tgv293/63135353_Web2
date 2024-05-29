package vn.gvt.QLTB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;
import vn.gvt.QLTB.Models.LoaiThietBi;
import vn.gvt.QLTB.Services.LoaiThietBiService;

@Controller
public class DeviceTypeController {
	
	private LoaiThietBiService loaiThietBiService;
	
	@Autowired
	public DeviceTypeController(LoaiThietBiService loaiThietBiService) {
		this.loaiThietBiService = loaiThietBiService;
	}

	// Trang chủ của loại thiết bị
	@RequestMapping(value = "/device_type", method = RequestMethod.GET)
	public String index(ModelMap model, @ModelAttribute("LoaiTB") LoaiThietBi loaitb, HttpSession ss) {
		List<LoaiThietBi> listLoaiTB = loaiThietBiService.getAllLoaiTB();
		model.addAttribute("DsLoaiTB", listLoaiTB);
		model.addAttribute("action", ss.getAttribute("action"));
		model.addAttribute("message", ss.getAttribute("message"));
		ss.removeAttribute("action");
		ss.removeAttribute("message");
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		model.addAttribute("navigation","device_type");
		return "device_type";

	}
	
	// Thêm loại thiết bị
	@RequestMapping(value = "/device_type", params = "btnadd", method = RequestMethod.POST)
	public String addsp(Model model, @ModelAttribute("LoaiTB") LoaiThietBi loaitb, HttpSession ss) {
		Integer tmp = loaiThietBiService.add(loaitb);
		ss.setAttribute("action", "Thêm");

		if (tmp != 0) {
			ss.setAttribute("message", "success");
		} else {
			ss.setAttribute("message", "error");
		}
		List<LoaiThietBi> listLoaiTB = loaiThietBiService.getAllLoaiTB();
		ss.setAttribute("DsLoaiTB", listLoaiTB);

		return "redirect:/device_type";
	}
	
	// Xóa loại thiết bị
	@RequestMapping(value = "/device_type/{id}", params = "linkDelete")
	public String delete_SP(Model model, @PathVariable("id") Integer id, HttpSession session) {
		Integer tmp = loaiThietBiService.deleteTb(loaiThietBiService.getLoaiTB_ID(id).orElse(null));
		session.setAttribute("action", "Xóa");

		if (tmp != 0) {
			session.setAttribute("message", "success");
		} else {
			session.setAttribute("message", "error");
		}
		return "redirect:/device_type";
	}
	
	// Sửa loại thiết bị
	@RequestMapping(value = "/device_type", params = "btn-save-edit", method = RequestMethod.POST)
	public String edit_SP(Model model, @ModelAttribute("LoaiTB") LoaiThietBi loaitb, HttpSession session) {
		Integer tmp = loaiThietBiService.editTb(loaitb);
		session.setAttribute("action", "Sửa");

		if (tmp != 0) {
			session.setAttribute("message", "success");
		} else {
			session.setAttribute("message", "error");
		}
		List<LoaiThietBi> listLoaiTB = loaiThietBiService.getAllLoaiTB();
		model.addAttribute("DsLoaiTB", listLoaiTB);
		return "redirect:/device_type";
	}
	
}
