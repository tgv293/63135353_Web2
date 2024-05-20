package vn.gvt.ENote.config;

import java.util.Arrays;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import vn.gvt.ENote.Models.User;

public class CustomUser implements UserDetails {

	private User user;

	public CustomUser(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// Lấy quyền của người dùng từ vai trò của họ
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(user.getRole().getRole());
		return Arrays.asList(authority);
	}

	@Override
	public String getPassword() {
		// Lấy mật khẩu của người dùng
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		// Lấy tên đăng nhập của người dùng
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// Kiểm tra tài khoản người dùng có hết hạn hay không
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Kiểm tra tài khoản người dùng có bị khóa hay không
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Kiểm tra thông tin xác thực của người dùng có hết hạn hay không
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Kiểm tra tài khoản người dùng có được kích hoạt hay không
		return true;
	}

}
