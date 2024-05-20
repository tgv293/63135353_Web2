package vn.gvt.ENote.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private LoginFilter loginFilter;

	// Mã hóa mật khẩu sử dụng BCrypt
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// Cung cấp cách xác thực người dùng thông qua UserDetailsService và passwordEncoder
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	// Cấu hình bộ lọc bảo mật
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// Các URL không yêu cầu xác thực
		RequestMatcher matcher = new OrRequestMatcher(
			new AntPathRequestMatcher("/login"),
			new AntPathRequestMatcher("/register"),
			new AntPathRequestMatcher("/forgot-password"),
			new AntPathRequestMatcher("/reset-password"),
			new AntPathRequestMatcher("/"),
			new AntPathRequestMatcher("/about"),
			new AntPathRequestMatcher("/contact"),
			new AntPathRequestMatcher("/assets/**"),
			new AntPathRequestMatcher("/static/**"),
			new AntPathRequestMatcher("/webjars/**")
		);

		// Các URL yêu cầu xác thực
		RequestMatcher protectedUrls = new OrRequestMatcher(
			new AntPathRequestMatcher("/**")
		);

		http
			.csrf().disable()
			.addFilterBefore(loginFilter, UsernamePasswordAuthenticationFilter.class)
			.authorizeRequests()
				.requestMatchers(matcher).permitAll()
				.requestMatchers(protectedUrls).hasAuthority("User")
				.anyRequest().authenticated()
				.and()
			.authenticationProvider(daoAuthenticationProvider())
			.formLogin()
				.loginPage("/login")
				.defaultSuccessUrl("/viewNotes", true)
				.permitAll()
				.and()
			.logout()
				.permitAll()
				.and()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		return http.build();
	}

	// Đăng ký bộ lọc đăng nhập
	@Bean
	public FilterRegistrationBean<LoginFilter> loggingFilter() {
		FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();

		registrationBean.setFilter(new LoginFilter());
		registrationBean.addUrlPatterns("/**");

		return registrationBean;
	}

}
