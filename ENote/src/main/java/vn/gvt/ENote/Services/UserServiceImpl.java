package vn.gvt.ENote.Services;

import vn.gvt.ENote.Models.User;
import vn.gvt.ENote.Models.UserRole;
import vn.gvt.ENote.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    private NoteService noteService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, NoteService noteService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.noteService = noteService;
    }

    /**
     * Lưu thông tin người dùng vào cơ sở dữ liệu.
     * @param user Thông tin người dùng cần lưu.
     * @throws IllegalArgumentException Nếu user là null.
     */
    @Override
    public void save(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        userRepository.save(user);
    }

    /**
     * Xóa người dùng khỏi cơ sở dữ liệu.
     * @param user Người dùng cần xóa.
     * @return 1 nếu thao tác thành công.
     */
    @Override
    public int delete(User user) {
        noteService.deleteAllByUserId(user.getId());
        userRepository.delete(user);
        return 1; // Trả về 1 nếu thao tác thành công
    }

    /**
     * Xóa người dùng khỏi cơ sở dữ liệu dựa trên id.
     * @param id Id của người dùng cần xóa.
     * @return 1 nếu thao tác thành công.
     */
    @Override
    public int delete(int id) {
        noteService.deleteAllByUserId(id);
        userRepository.deleteById(id);
        return 1; // Trả về 1 nếu thao tác thành công
    }

    /**
     * Lấy thông tin người dùng dựa trên id.
     * @param id Id của người dùng cần lấy thông tin.
     * @return Optional chứa thông tin người dùng nếu tìm thấy, nếu không trả về Optional rỗng.
     * @throws IllegalArgumentException Nếu id nhỏ hơn 1.
     */
    @Override
    public Optional<User> get(int id) {
        if (id < 1) {
            throw new IllegalArgumentException();
        }

        return userRepository.findById(id);
    }

    /**
     * Cập nhật thông tin người dùng.
     * @param id Id của người dùng cần cập nhật.
     * @param user Thông tin người dùng mới.
     * @throws IllegalArgumentException Nếu user hoặc id là null hoặc id nhỏ hơn 1.
     */
    @Override
    public void update(Integer id, User user) {
        if (user == null || id == null || id < 1) {
            throw new IllegalArgumentException(String.format("Không tìm thấy người dùng với id bằng null hoặc nhỏ hơn 1, cập nhật bị từ chối. Id = %d", id));
        }
        
        user.setId(id);
        User existingUser = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Id người dùng không hợp lệ:" + id));
        user.setPassword(existingUser.getPassword());
        user.setRegistration(existingUser.getRegistration());
        user.setRole(existingUser.getRole());
        userRepository.save(user);
    }
    
    /**
     * Thay đổi mật khẩu của người dùng.
     * @param user Người dùng cần thay đổi mật khẩu.
     * @param password Mật khẩu mới.
     */
    @Override
    public void changePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    /**
     * Lấy danh sách tất cả người dùng.
     * @return Danh sách người dùng.
     */
    @Override
    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Lưu thông tin người dùng mặc định.
     * @param user Thông tin người dùng cần lưu.
     * @throws IllegalArgumentException Nếu user là null.
     */
    @Override
    public void defaultSave(User user) {
        if (user == null) {
            throw new IllegalArgumentException();
        }

        UserRole userRole = new UserRole();
        userRole.setId(1);
        userRole.setRole("User");

        user.setRole(userRole);
        user.setRegistration(LocalDate.now());

        userRepository.save(user);
    }

    /**
     * Lấy thông tin người dùng dựa trên email.
     * @param email Email của người dùng cần lấy thông tin.
     * @return Optional chứa thông tin người dùng nếu tìm thấy, nếu không trả về Optional rỗng.
     * @throws IllegalArgumentException Nếu email là null.
     */
    @Override
    public Optional<User> getByEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException();
        }

        return userRepository.findUserByEmail(email);
    }
    
    /**
     * Xóa thông báo trong session.
     */
    public void removeSessionMessage() {
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
                .getSession();

        session.removeAttribute("msg");
    }
}