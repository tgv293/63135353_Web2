package gvt;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;

/**
 * Servlet implementation class AboutMe
 */
@WebServlet("/aboutme")
public class AboutMe extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AboutMe() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Thông Tin Cá Nhân</title>");
        // Thêm link Bootstrap CSS
        out.println("<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">");
        out.println("</head>");
        out.println("<body class=\"container\">");
        out.println("<div class=\"jumbotron mt-4\">");
        out.println("<h1 class=\"display-4\">Thông Tin Cá Nhân</h1>");
        out.println("<p class=\"lead\">Xin chào, tôi là [Họ và Tên].</p>");
        out.println("<p class=\"lead\">Nghề nghiệp của tôi là [Nghề nghiệp].</p>");
        // Thêm thông tin cá nhân khác nếu cần
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
	}

}
