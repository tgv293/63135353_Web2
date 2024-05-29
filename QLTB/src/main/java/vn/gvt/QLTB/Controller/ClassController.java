package vn.gvt.QLTB.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.gvt.QLTB.Models.Lop;
import vn.gvt.QLTB.Services.LopService;

@Controller
public class ClassController {

	private LopService lopService;

	@Autowired
	public ClassController(LopService lopService) {
		this.lopService = lopService;
	}

	// Trang danh sách lớp
	@RequestMapping(value = "/class", method = RequestMethod.GET)
	public String index(ModelMap model, HttpServletRequest request, HttpSession ss, @ModelAttribute("Lop") Lop lop) {
		model.addAttribute("navigation", "class");
		model.addAttribute("action", ss.getAttribute("action"));
		model.addAttribute("message", ss.getAttribute("message"));
		ss.removeAttribute("action");
		ss.removeAttribute("message");
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));

		// Lấy danh sách lớp từ service
		List<Lop> listLop = lopService.getAllListLop();
		PagedListHolder<Lop> pagedListHolder = new PagedListHolder<Lop>(listLop);
		model.addAttribute("pagedListHolder", pagedListHolder);

		int page = ServletRequestUtils.getIntParameter(request, "p", 0);

		pagedListHolder.setPage(page);
		pagedListHolder.setPageSize(8);
		pagedListHolder.setMaxLinkedPages(3);

		// Đường dẫn phân trang
		String pagedLink = "/class?p={page}";
		model.addAttribute("pagedLink", pagedLink);

		return "class";
	}

	// Thêm lớp
	@RequestMapping(value = "/class", params = "btnadd", method = RequestMethod.POST)
	public String addsp(HttpSession ss, ModelMap model, @ModelAttribute("Lop") Lop lop) {
		// Xóa dấu phẩy đầu tiên trong maLop trước khi kiểm tra
		lop.setMaLop(lop.getMaLop().replaceFirst("^,", ""));

		// Kiểm tra mã lớp đã tồn tại chưa
		Lop existingLop = lopService.getLopByID(lop.getMaLop());
		if (existingLop != null) {
			ss.setAttribute("message", "error");
			ss.setAttribute("action", "Mã lớp đã tồn tại nên");
			return "redirect:/class";
		}

		// Thêm lớp mới
		Boolean tmp = lopService.add(lop);
		ss.setAttribute("action", "Thêm Lớp");
		if (tmp) {
			ss.setAttribute("message", "success");
		} else {
			ss.setAttribute("message", "error");
		}

		return "redirect:/class";
	}
}
