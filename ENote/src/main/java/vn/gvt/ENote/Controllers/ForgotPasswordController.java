package vn.gvt.ENote.Controllers;

import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.gvt.ENote.Models.MailStructure;
import vn.gvt.ENote.Models.PasswordResetToken;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Services.PasswordResetTokenService;
import vn.gvt.ENote.Services.UserService;

@Controller
public class ForgotPasswordController {

	private UserService userService;
	private PasswordResetTokenService passwordResetTokenService;

	// Inject JavaMailSender
	public ForgotPasswordController(UserService userService, PasswordResetTokenService passwordResetTokenService) {
		this.userService = userService;
		this.passwordResetTokenService = passwordResetTokenService;
	}

	@GetMapping("/forgot-password")
	public String forgotpassword() {
		return "forgot_pass";
	}

	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String email, RedirectAttributes redirectAttributes) {
		// Kiểm tra xem email có tồn tại trong cơ sở dữ liệu hay không
		Optional<User> userOptional = userService.getByEmail(email);

		boolean f2 = userOptional.isPresent();

		if (!f2) {
			// Nếu không tồn tại, chuyển hướng đến trang quên mật khẩu với thông báo lỗi
			redirectAttributes.addFlashAttribute("errorMessage", "Email không tồn tại trong hệ thống.");
			return "redirect:/forgot-password";
		} else {
			// Nếu tồn tại, tạo mã thông báo và gửi email
			PasswordResetToken passwordResetToken = passwordResetTokenService.createPasswordResetToken(email);
			String token = passwordResetToken.getToken();

			String firstName = userOptional.get().getFirstName();
			String lastName = userOptional.get().getLastName();

			MailStructure mailStructure = new MailStructure();
			mailStructure.setSubject("Yêu cầu khôi phục mật khẩu");
			mailStructure.setBody("Kính gửi: " + firstName + " " + lastName + ",\n\n"
					+ "ENote xin chân thành cảm ơn đến Quý khách đã tin tưởng và sử dụng dịch vụ của chúng tôi.\n"
					+ "Hệ thống đã tiếp nhận yêu cầu cấp lại mật khẩu cho tài khoản quản lý dịch vụ từ Quý khách.\n"
					+ "Để khôi phục mật khẩu, quý khách vui lòng nhấn vào đường dẫn dưới đây:\n"
					+ "http://localhost:8080/reset-password?token=" + token + "\n"
					+ "Đường dẫn chỉ có hiệu lực trong vòng 10 phút.\n\n");

			passwordResetTokenService.sendMail(email, mailStructure);

			redirectAttributes.addFlashAttribute("successMessage", "Yêu cầu đã được tiếp nhận. Quý khách vui lòng kiểm tra email.");

			return "redirect:/forgot-password";
		}
	}

	@GetMapping("/reset-password")
	public String showResetPasswordForm(@RequestParam("token") String token, Model model, RedirectAttributes redirectAttributes) {
		// Tìm người dùng liên kết với mã thông báo khôi phục mật khẩu
		User user = passwordResetTokenService.getUserByPasswordResetToken(token);

		// Nếu không tìm thấy hoặc mã thông báo không hợp lệ, chuyển hướng đến trang quên mật khẩu với thông báo lỗi
		if (user == null) {
			redirectAttributes.addFlashAttribute("errorMessage", "Đường dẫn không hợp lệ hoặc quá hạn.");
			return "redirect:/forgot-password";
		}

		// Nếu mã thông báo hợp lệ, thêm người dùng vào model và hiển thị form đặt lại mật khẩu
		model.addAttribute("user", user);
		model.addAttribute("actionUrl", "/reset-password");
		return "change_pass";
	}

	@PostMapping("/reset-password")
	public String handlePasswordReset(@RequestParam("id") Integer id, @RequestParam("decryptedPassword") String password, RedirectAttributes redirectAttributes) {
		// Lấy người dùng theo ID
		Optional<User> optionalUser = userService.get(id);

		if (optionalUser.isPresent()) {
			// Cập nhật mật khẩu của người dùng
			userService.changePassword(optionalUser.get(), password);

			// Thêm thông báo thành công
			redirectAttributes.addFlashAttribute("successMessage", "Mật khẩu của bạn đổi thành công.");

			// Chuyển hướng đến trang đăng nhập
			return "redirect:/login";
		} else {
			// Xử lý trường hợp không tìm thấy người dùng
			redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy người dùng.");
			return "redirect:/forgot-password";
		}
	}
}
