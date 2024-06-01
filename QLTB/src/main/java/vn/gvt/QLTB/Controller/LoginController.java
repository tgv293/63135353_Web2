package vn.gvt.QLTB.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jakarta.servlet.http.HttpSession;
import vn.gvt.QLTB.Models.TaiKhoan;
import vn.gvt.QLTB.Services.LoginService;
import vn.gvt.QLTB.Services.RecaptchaVerification;

@Controller
@SessionAttributes({"acc", "permission", "info", "infoManv"})
public class LoginController {
 
    private RecaptchaVerification RecaptchaVerification;
    private LoginService loginService;
    
    @Autowired
    public LoginController(LoginService loginService, RecaptchaVerification RecaptchaVerification) {
        this.loginService = loginService;
        this.RecaptchaVerification = RecaptchaVerification;
    }
    
    // Trang chủ
    @GetMapping("/")
    public String index(Model model, HttpSession session) {
    	
        if (session.getAttribute("acc") != null) {
            return "redirect:/home";
        }
        else
        {
        	model.addAttribute("taiKhoan", new TaiKhoan());
        	return "index";
        }
    }

    // Xử lý đăng nhập
    @PostMapping("/")
    public String index2(@ModelAttribute("taiKhoan") TaiKhoan taiKhoan, Model model, BindingResult errors,
            @RequestParam("g-recaptcha-response") String gRepcaptchResponse, HttpSession session) throws IOException {
        
    	System.out.println("Post");
    	
        boolean verity = RecaptchaVerification.verify(gRepcaptchResponse);

        if (taiKhoan.getId().trim().isBlank() || taiKhoan.getId().trim().isEmpty()) {
            errors.rejectValue("id", "taiKhoan", "Tên đăng nhập không được trống");
        }
        if (taiKhoan.getPass().trim().isBlank() || taiKhoan.getPass().trim().isEmpty()) {
            errors.rejectValue("pass", "taiKhoan", "Mật khẩu không được trống");
        }

        if (errors.hasErrors() || !verity) {
            if (!verity)
                model.addAttribute("tb", "Vui lòng nhập Captcha!");
            return "index"; 
        } else {

            if (loginService.checkLogin(taiKhoan.getId().trim().toString(), taiKhoan.getPass().trim().toString())) {

                TaiKhoan tk=this.loginService.getTKByID(taiKhoan.getId().trim().toString());
                session.setAttribute("acc",tk.getId());
                if(tk.getTypeacc().equals("admin")) {
                    session.setAttribute("permission","admin");
                    session.setAttribute("info","Admin");
                }
                else {
                    session.setAttribute("permission","user");
                    session.setAttribute("info",tk.getNhanvien().getHoTenNV());
                    session.setAttribute("infoManv",tk.getNhanvien().getMaNV());
                }
                
                return "redirect:/home";
            }
            else {
                session.removeAttribute("acc");
                session.removeAttribute("permission");
                session.removeAttribute("info");
                session.removeAttribute("infoManv");
                model.addAttribute("tb", "Tên đăng nhập hoặc mật khẩu không đúng!");
                return "index"; 
            }
        }
    }
    
    // Đăng xuất
    @GetMapping("/logout")
    public String Logout(HttpSession session, SessionStatus status) {
        status.setComplete();
        session.invalidate();
        return "redirect:/";
    }
}