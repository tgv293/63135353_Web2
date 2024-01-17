package giapvantai;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

@WebServlet("/testdoPost")
public class TestDoPost extends HttpServlet {
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
		traVe.append("<title>Test Do Post</title>");
		traVe.append(
				"<link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css\" rel=\"stylesheet\" />");
		traVe.append("</head>");
		traVe.append("<body class='container mt-5'>");
		traVe.append("<div class='text-center'>");
		traVe.append("<h1 class='display-0'>Bạn vừa gửi Yêu cầu dạng GET, đây là đáp ứng của tôi</h1>");
		traVe.append("</div>");
		traVe.append("<form method='POST' action='/GVTHelloWorld/testdoPost' class='text-center'>");
		traVe.append("<div class='form-outline mb-4' data-mdb-input-init>");
		traVe.append("<input type='text' id='fname' name='fname' class='form-control' aria-describedby='textExample1' />");
		traVe.append("<label class='form-label' for='fname'>Họ:</label>");
		traVe.append("</div>");
		traVe.append("<div class='form-outline mb-4' data-mdb-input-init>");
		traVe.append("<input type='text' id='lname' name='lname' class='form-control' aria-describedby='textExample2' />");
		traVe.append("<label class='form-label' for='lname'>Tên:</label>");
		traVe.append("</div>");
		traVe.append("<button type='submit' class='btn btn-primary mb-4'>Gửi đi</button>");
		traVe.append("</form>");
		traVe.append(
				"<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js\"></script>");
		traVe.append("</body>");
		traVe.append("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		String value1 = request.getParameter("fname");
		String value2 = request.getParameter("lname");
		PrintWriter traVe = response.getWriter();
		traVe.append("<!DOCTYPE html>");
		traVe.append("<html lang='en'>");
		traVe.append("<head>");
		traVe.append("<meta charset='UTF-8'>");
		traVe.append("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
		traVe.append("<title>Test Do Post</title>");
		traVe.append(
				"<link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css\" rel=\"stylesheet\" />");
		traVe.append("</head>");
		traVe.append("<body class='container mt-5'>");
		traVe.append("<div class='text-center'>");
		traVe.append("<h1 class='display-0'>Bạn vừa gửi Yêu cầu dạng POST, đây là đáp ứng của tôi</h1>");
		traVe.append("<div class='form-outline mb-4' data-mdb-input-init>");
		traVe.append("<label class='form-label' for='fname'>Họ:</label>");
		traVe.append("<div class='form-text fw-bold'>" + value1 + "</div>");
		traVe.append("</div>");
		traVe.append("<div class='form-outline mb-4' data-mdb-input-init>");
		traVe.append("<label class='form-label' for='lname'>Tên:</label>");
		traVe.append("<div class='form-text fw-bold'>" + value2 + "</div>");
		traVe.append("</div>");
		traVe.append("</div>");
		traVe.append(
				"<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js\"></script>");
		traVe.append("</body>");
		traVe.append("</html>");
	}
}
