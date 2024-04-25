//package vn.gvt.ENote.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfiguration{
//
//    private final DataSource dataSource;
//
//    @Autowired
//    public WebSecurityConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }
//
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                    .requestMatchers("/", "/register").permitAll()
//                    .anyRequest().authenticated()
//                .and()
//                    .formLogin()
//                    .loginPage("/login")
//                    .defaultSuccessUrl("/", true)
//                    .usernameParameter("email")
//                    .permitAll()
//                .and()
//                    .logout()
//                    .permitAll();
//    }
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
//                .usersByUsernameQuery("SELECT email AS username, password, active AS enabled FROM users WHERE email=?")
//                .authoritiesByUsernameQuery("SELECT u.email AS username, ur.role AS authority FROM users u INNER JOIN user_roles ur ON u.role_id = ur.id WHERE u.email=?");
//    }
//
//    public void configure(WebSecurity web) {
//        web.ignoring().requestMatchers("/assets/**", "/static/**", "/webjars/**");
//    }
//}