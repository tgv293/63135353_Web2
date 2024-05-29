package vn.gvt.QLTB.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.gvt.QLTB.Models.NhanVien;
import vn.gvt.QLTB.Models.TaiKhoan;
import vn.gvt.QLTB.Services.NhanVienService;
import vn.gvt.QLTB.Services.TaiKhoanService;

@Controller
public class AdminController {

	private NhanVienService nhanVienService;
	private TaiKhoanService taiKhoanService;

	@Autowired
	public AdminController(NhanVienService nhanVienService, TaiKhoanService taiKhoanService) {
		this.nhanVienService = nhanVienService;
		this.taiKhoanService = taiKhoanService;
	}

	@RequestMapping(value = "/administration", method = RequestMethod.GET)
	public String index(ModelMap model, HttpSession ss, @ModelAttribute("NhanVien") NhanVien nv) {
		ss.setAttribute("navigation", "administration");
		model.addAttribute("listNV", nhanVienService.getListNV());
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		return "admin";
	}

	@RequestMapping(value = "/administration", params = "btnadd", method = RequestMethod.POST)
	public String add(ModelMap model, @ModelAttribute("NhanVien") NhanVien nv, HttpSession ss) {
		ss.setAttribute("navigation", "administration");
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
	    if (taiKhoanService.checkTKByID(nv.getTaiKhoan().getId())) {
	        model.addAttribute("message", "error");
	        model.addAttribute("action", "Tài khoản đã được sủ dụng");

	    } else {

	        TaiKhoan tk = new TaiKhoan(nv.getTaiKhoan().getId());
	        tk.setPass("1");
	        tk.setTypeacc("user");
	        nv.setTaiKhoan(tk);
	        System.out.println(nv.getTaiKhoan().getId());
	        System.out.println(nv);
	        if (nhanVienService.addNV(nv) != 0) {
	            model.addAttribute("message", "success");
	            model.addAttribute("action", "Thêm nhân viên thành công");

	        } else {
	            model.addAttribute("message", "error");
	            model.addAttribute("action", "Thêm nhân viên thất bại");

	        }

	    }

	    model.addAttribute("listNV", nhanVienService.getListNV());
	    return "admin";
	}

	@RequestMapping(value = "/administration/resetpass_id={id}")
	public String resetpass(@PathVariable("id") String id, ModelMap model, @ModelAttribute("NhanVien") NhanVien nv, HttpSession ss) {
		nhanVienService.resetPassword(id);

		model.addAttribute("message", "success");
		model.addAttribute("action", "Sửa mật khẩu thành công");
		model.addAttribute("listNV", nhanVienService.getListNV());
		model.addAttribute("permission", ss.getAttribute("permission"));
		model.addAttribute("info", ss.getAttribute("info"));
		model.addAttribute("acc", ss.getAttribute("acc"));
		return "admin";
	}

	@RequestMapping(value = "/administration/changepass")
	public String resetpass(HttpServletRequest rq, HttpSession ss, @RequestParam("currentpass") String currentpass,
			@RequestParam("newpass") String newpass, @RequestParam("retypepass") String retypepass) {
		if (taiKhoanService.checkLogin(ss.getAttribute("acc").toString(), currentpass.trim().toString()) == true) {
			if (newpass.trim().toString().equals(retypepass.trim().toString())) {
				Boolean tmp = taiKhoanService.changePassword(ss.getAttribute("acc").toString(), currentpass.trim().toString(),
						newpass.trim().toString());
				if (tmp == true) {
					return "redirect:/logout";
				} else {
					System.out.println("fail");
					ss.setAttribute("typeToast", "error");
					ss.setAttribute("action", "Đổi mật khẩu");
					return "redirect:/home";
				}

			} else {
				System.out.println("fail2");
				ss.setAttribute("typeToast", "error");
				ss.setAttribute("action", "Mật khẩu mới không khớp, ");
				return "redirect:/home";
			}

		} else {
			System.out.println("fail3");
			ss.setAttribute("typeToast", "error");
			ss.setAttribute("action", "Mật khẩu cũ bạn nhập không đúng, ");
			return "redirect:/home";

		}
	}

}
