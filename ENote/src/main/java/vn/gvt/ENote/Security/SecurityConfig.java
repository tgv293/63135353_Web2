package vn.gvt.ENote.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

	@Autowired
	private UserDetailsService userDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public LoginFilter loginFilter() {
	    return new LoginFilter();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService);
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    RequestMatcher matcher = new OrRequestMatcher(
	        new AntPathRequestMatcher("/login"),
	        new AntPathRequestMatcher("/register"),
	        new AntPathRequestMatcher("/"),
	        new AntPathRequestMatcher("/about"),
	        new AntPathRequestMatcher("/contact"),
	        new AntPathRequestMatcher("/assets/**"),
	        new AntPathRequestMatcher("/static/**"),
	        new AntPathRequestMatcher("/webjars/**")
	    );
	    
	    RequestMatcher protectedUrls = new OrRequestMatcher(
	    	new AntPathRequestMatcher("/user/**")
	    );
	    
	    http
	        .csrf().disable()
	        .addFilterBefore(new LoginFilter(), UsernamePasswordAuthenticationFilter.class)
	        .authorizeRequests()
	            .requestMatchers(matcher).permitAll()
	            .requestMatchers(protectedUrls).hasAuthority("User")
	            .anyRequest().authenticated()
	            .and()
	        .authenticationProvider(daoAuthenticationProvider())
				.httpBasic()
				.and()
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

}