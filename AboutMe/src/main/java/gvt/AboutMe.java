package gvt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

@WebServlet("/aboutme")
public class AboutMe extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter traVe = response.getWriter();
		traVe.append("<!DOCTYPE html>");
		traVe.append("<html lang='en'>");
		traVe.append("<head>");
		traVe.append("<meta charset='UTF-8'>");
		traVe.append("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
		traVe.append("<title>About Me</title>");
		traVe.append(
				"<link href='https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css' rel='stylesheet' />");
		traVe.append("<style>");
		traVe.append("body {");
		traVe.append(
				"  background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://inkythuatso.com/uploads/thumbnails/800/2021/12/logo-dai-hoc-nha-trang-inkythuatso-1-02-14-45-38.jpg') no-repeat center center fixed;"); // Đổi
																																																										// background
		traVe.append("  background-size: cover;");
		traVe.append("}");
		traVe.append(".info {");
		traVe.append("  color: white;");
		traVe.append("  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.5);");
		traVe.append("}");
		traVe.append(".card {");
		traVe.append("  background-color: rgba(255, 255, 255, 0.8);");
		traVe.append("  border-radius: 15px;");
		traVe.append("  padding: 20px;");
		traVe.append("  margin: auto;");
		traVe.append("  max-width: 500px;");
		traVe.append("}");
		traVe.append("</style>");
		traVe.append("</head>");
		traVe.append("<body class='container mt-5'>");
		traVe.append("<div class='text-center info'>");
		traVe.append("<img src='https://avatars.githubusercontent.com/u/102977775?s=400&u=ca17e11e27dc5fa98e004bcaf1e6efe27eaa8751&v=4' alt='Avatar' class='mb-4 rounded-circle' style='width: 150px;'>");
		traVe.append("<div class='card'>");
		traVe.append("<h1 class='display-4'>About Me</h1>");
		traVe.append("<p class='lead'>Giáp Văn Tài</p>");
		traVe.append("<p><strong>MSSV:</strong> 63135353</p>");
		traVe.append("<p><strong>Lớp:</strong> 63.CNTT-CLC</p>");
		traVe.append("<p><strong>Trường:</strong> Đại học Nha Trang</p>");
		traVe.append("<p><strong>Ngành:</strong> Công nghệ thông tin</p>");
		traVe.append("</div>");
		traVe.append("</div>");
		traVe.append(
				"<script type='text/javascript' src='https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js'></script>");
		traVe.append("</body>");
		traVe.append("</html>");
	}
}
