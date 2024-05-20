package vn.gvt.ENote.Security;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Kiểm tra xem người dùng đã được xác thực và đăng nhập hay chưa
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Nếu người dùng đã xác thực và đăng nhập, và đang truy cập vào trang "/login" hoặc "/register"
        if (authentication != null && authentication.isAuthenticated() && 
            ("/login".equals(request.getRequestURI()) || "/register".equals(request.getRequestURI()))) {
            response.sendRedirect("/viewNotes"); // hoặc chuyển hướng đến trang khác theo mong muốn
            return;
        }
        
        // Nếu không thỏa mãn điều kiện trên, tiếp tục chạy các filter khác trong chuỗi filter
        filterChain.doFilter(request, response);
    }
}
