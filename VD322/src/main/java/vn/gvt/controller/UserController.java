package vn.gvt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

	@RequestMapping(value = "login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(ModelMap model, HttpServletRequest request) {
		String id = request.getParameter("id");
		String pw= request.getParameter("password");
		if (id.equals("fpt") && pw.equals("polytechnic")) {
			model.addAttribute("message", "Đăng nhập thành công!");
		} else {
			model.addAttribute("message", "Sai thông tin đăng nhập!");
		}
		return "login";
	}
}
