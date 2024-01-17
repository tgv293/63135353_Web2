package giapvantai;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

@WebServlet("/subpage")
public class SubPage extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String value1 = request.getParameter("param1");
		String value2 = request.getParameter("param2");

		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter traVe = response.getWriter();
		traVe.append("<!DOCTYPE html>");
		traVe.append("<html lang='en'>");
		traVe.append("<head>");
		traVe.append("<meta charset='UTF-8'>");
		traVe.append("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
		traVe.append("<title>Sub Page</title>");
		traVe.append(
				"<link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css\" rel=\"stylesheet\" />");
		traVe.append("</head>");
		traVe.append("<body class='container mt-5'>");
		traVe.append("<div class='text-center'>");
		traVe.append("<h1 class='display-4'>Giá trị tham số param1 = " + value1 + "</h1>");
		traVe.append("<h1 class='display-4'>Giá trị tham số param2 = " + value2 + "</h1>");
		traVe.append("</div>");
		traVe.append(
				"<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js\"></script>");
		traVe.append("</body>");
		traVe.append("</html>");
	}
}
