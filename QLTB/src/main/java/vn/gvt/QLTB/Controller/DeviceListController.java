package vn.gvt.QLTB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.gvt.QLTB.Models.LoaiThietBi;
import vn.gvt.QLTB.Models.ThietBi;
import vn.gvt.QLTB.Services.LoaiThietBiService;
import vn.gvt.QLTB.Services.ThietBiService;

@Controller
public class DeviceListController {
	
	private LoaiThietBiService loaiThietBiService;
	private ThietBiService thietBiService;
	
	@Autowired
	public DeviceListController(LoaiThietBiService loaiThietBiService, ThietBiService thietBiService) {
		this.loaiThietBiService = loaiThietBiService;
		this.thietBiService = thietBiService;
	}
	
	// Lấy danh sách loại thiết bị
	@ModelAttribute("listLoaiTB")
	public List<LoaiThietBi> getLoaiTB() {
		return loaiThietBiService.getAllLoaiTB();
	}
	
	// Trang chủ danh sách thiết bị
	@RequestMapping(value = "/device_list", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request, HttpSession ss, @ModelAttribute("CTThietBi") ThietBi TB) {
		model.addAttribute("navigation", "device_list");
		model.addAttribute("action", ss.getAttribute("action"));
		model.addAttribute("message", ss.getAttribute("message"));
		ss.removeAttribute("action");
		ss.removeAttribute("message");
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));

		List<ThietBi> listCTThietBi = thietBiService.getListTB();
		PagedListHolder<ThietBi> pagedListHolder = new PagedListHolder<ThietBi>(listCTThietBi);
		model.addAttribute("pagedListHolder", pagedListHolder);
		
		int page = ServletRequestUtils.getIntParameter(request, "p", 0);

		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(8);
		pagedListHolder.setMaxLinkedPages(3);

		// Thêm đường dẫn phân trang
		String pagedLink = "/device_list?p={page}";
		model.addAttribute("pagedLink", pagedLink);

		return "device";
	}
	
	
	// Thêm thiết bị
	@RequestMapping(value = "/device_list", params = "btnadd", method = RequestMethod.POST)
	public String addsp(Model model, HttpServletRequest request, @ModelAttribute("CTThietBi") ThietBi TB, @ModelAttribute("soLuong") Integer soluong, HttpSession ss) {
		for (int i = 0; i < soluong; i++) {	    
			ThietBi newDevice = new ThietBi(TB);
			ThietBi savedDevice = thietBiService.add(newDevice);
			ss.setAttribute("action", "Thêm");
			if (savedDevice != null) {
				ss.setAttribute("message", "success");
			} else {
				ss.setAttribute("message", "error");
			}
		}
		
		return "redirect:/device_list";
	}
	
	// Xóa thiết bị
	@RequestMapping(value = "/device_list/{id}", params = "linkDelete")
	public String delete_SP(HttpServletRequest request, ModelMap model, @ModelAttribute("CTThietBi") ThietBi tb,
			@PathVariable("id") Integer id, HttpSession ss) {
		Integer tmp = thietBiService.deleteTb(id);
		ss.setAttribute("action", "Xóa");

		if (tmp != 0) {
			ss.setAttribute("message", "success");
		} else {
			ss.setAttribute("message", "error");
		}
		return "redirect:/device_list";
	}

}
