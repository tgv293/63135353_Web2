package vn.gvt.ENote.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

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