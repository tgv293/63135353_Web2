package vn.gvt.ENote.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.gvt.ENote.Models.MailStructure;
import vn.gvt.ENote.Models.PasswordResetToken;
import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Repositories.PasswordResetTokenRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

	private PasswordResetTokenRepository passwordResetTokenRepository;
	private UserService userService;
	private JavaMailSender mailSender;

	@Autowired
	public PasswordResetTokenServiceImpl(PasswordResetTokenRepository passwordResetTokenRepository,
			UserService userService, JavaMailSender mailSender) {
		this.passwordResetTokenRepository = passwordResetTokenRepository;
		this.userService = userService;
		this.mailSender = mailSender;
	}
	
	@Value("${spring.mail.username}")
	private String from;

	/**
	 * Tạo một token để đặt lại mật khẩu cho người dùng.
	 * 
	 * @param email Địa chỉ email của người dùng
	 * @return PasswordResetToken Đối tượng PasswordResetToken đã được tạo
	 * @throws UsernameNotFoundException Nếu không tìm thấy người dùng với email đã cho
	 */
	@Override
	public PasswordResetToken createPasswordResetToken(String email) {
		// Tạo một token duy nhất. Trong ví dụ này, chúng ta sử dụng UUID.
		String token = UUID.randomUUID().toString();

		// Tìm người dùng bằng email
		Optional<User> userOptional = userService.getByEmail(email);

		if (!userOptional.isPresent()) {
			// Xử lý trường hợp không tìm thấy người dùng
			throw new UsernameNotFoundException("Không tìm thấy người dùng với email: " + email);
		}
		
		// Nếu đã tồn tại token cho người dùng này, vô hiệu hóa nó
		PasswordResetToken existingToken = passwordResetTokenRepository.findByUserAndIsActive(userOptional.get(), true);
		if (existingToken != null) {
			existingToken.setActive(false);
			passwordResetTokenRepository.save(existingToken);
		}

		// Tạo một đối tượng PasswordResetToken mới
		PasswordResetToken passwordResetToken = new PasswordResetToken();
		passwordResetToken.setToken(token);
		passwordResetToken.setUser(userOptional.get()); // Lấy đối tượng User từ Optional<User>
		LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(10); // Đặt ngày hết hạn là 10 phút từ bây giờ
		passwordResetToken.setExpiryDate(expiryDate);
		passwordResetToken.setActive(true); // Đặt token mới là hoạt động

		// Lưu token vào cơ sở dữ liệu
		passwordResetTokenRepository.save(passwordResetToken);

		// Trả về token
		return passwordResetToken;
	}

	/**
	 * Tìm kiếm PasswordResetToken bằng token.
	 * 
	 * @param token Token cần tìm kiếm
	 * @return PasswordResetToken Đối tượng PasswordResetToken tìm thấy
	 */
	@Override
	public PasswordResetToken findByToken(String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	/**
	 * Gửi email đến địa chỉ đã cho.
	 * 
	 * @param email          Địa chỉ email người nhận
	 * @param mailStructure Cấu trúc email (bao gồm tiêu đề và nội dung)
	 */
	@Override
	public void sendMail(String email, MailStructure mailStructure) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom(from);
		mailMessage.setTo(email);
		mailMessage.setSubject(mailStructure.getSubject());
		mailMessage.setText(mailStructure.getBody());
		mailSender.send(mailMessage);
		
	}
	
	/**
	 * Lấy người dùng từ PasswordResetToken.
	 * 
	 * @param token Token cần tìm kiếm
	 * @return User Người dùng tương ứng với token
	 */
	@Override
	public User getUserByPasswordResetToken(String token) {
		// Tìm PasswordResetToken
		PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);

		// Nếu không tìm thấy token, trả về null
		if (passwordResetToken == null) {
			return null;
		}

		// Nếu token đã hết hạn hoặc không hoạt động, trả về null
		LocalDateTime now = LocalDateTime.now();
		if (passwordResetToken.getExpiryDate().isBefore(now) || !passwordResetToken.isActive()) {
			return null;
		}

		// Nếu token hợp lệ, trả về người dùng tương ứng
		return passwordResetToken.getUser();
	}
	
	/**
	 * Xóa các token đã hết hạn.
	 */
	@Scheduled(cron = "0 0 12 * * ?") // Chạy nhiệm vụ hàng ngày lúc 12 giờ trưa
	public void removeExpiredTokens() {
		List<PasswordResetToken> tokens = passwordResetTokenRepository.findAll();

		for (PasswordResetToken token : tokens) {
			if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
				passwordResetTokenRepository.delete(token);
			}
		}
	}
}