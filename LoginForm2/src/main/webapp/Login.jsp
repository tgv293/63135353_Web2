<%@page import="org.apache.catalina.tribes.ChannelSender"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <%
        // Lấy dữ liệu gửi từ người dùng
        String tenDN = request.getParameter("uname");
        String mk = request.getParameter("upass");
        // Xử lý
        if (tenDN.equals("ABC") && mk.equals("MNK")) {
            // Đến trang UserProfile nếu đăng nhập thành công
            session.setAttribute("username", tenDN);
            response.sendRedirect("Index.jsp");
        } else {
            // Đến trang Login nếu đăng nhập thất bại
            response.sendRedirect("Login.html");
        }
    %>
</body>
</html>
