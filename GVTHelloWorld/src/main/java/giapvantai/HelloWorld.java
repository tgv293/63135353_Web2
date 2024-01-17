package giapvantai;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

@WebServlet("/hello")
public class HelloWorld extends HttpServlet {
	private String message;

	public void init() throws ServletException {
		message = "Hello World";
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");
		out.println("<head>");
		out.println("<meta charset='UTF-8'>");
		out.println("<meta name='viewport' content='width=device-width, initial-scale=1, shrink-to-fit=no'>");
		out.println("<title>Hello World</title>");
		out.println(
				"<link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css\" rel=\"stylesheet\" />");
		out.println("</head>");
		out.println("<body class='container mt-5'>");
		out.println("<div class='text-center'>");
		out.println("<h1 class='display-4'>" + message + "</h1>");
		out.println(
				"<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js\"></script>");
		out.println("</body>");
		out.println("</html>");
	}
}
