package taigiapvan;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.*;
import java.text.DecimalFormat;

/**
 * Servlet implementation class BMICalculate
 */
@WebServlet("/calculate")
public class BMICalculate extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public BMICalculate() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter traVe = response.getWriter();

        traVe.append("<!DOCTYPE html>");
        traVe.append("<html lang=\"vi\">");
        traVe.append("<head>");
        traVe.append("<meta charset=\"UTF-8\">");
        traVe.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        traVe.append("<title>Tính BMI</title>");
        traVe.append("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css\" rel=\"stylesheet\" />");
        traVe.append("<style>");
        traVe.append("body {");
        traVe.append("  background-image: url('https://c8.alamy.com/comp/2GGJTW9/bmi-body-mass-index-and-weight-loss-banner-background-2GGJTW9.jpg');");
        traVe.append("  background-attachment: fixed;");
        traVe.append("  background-position: center;");
        traVe.append("  background-repeat: no-repeat;");
        traVe.append("  background-size: cover;");
        traVe.append("}");
        traVe.append("</style>");
        traVe.append("</head>");
        traVe.append("<body>");
        traVe.append("<div class=\"container py-5\">");
        traVe.append("<div class=\"row justify-content-center\">");
        traVe.append("<div class=\"col-lg-8\">");
        traVe.append("<div class=\"card\">");
        traVe.append("<div class=\"card-body text-center\">");
        traVe.append("<h2 class=\"card-title\">Tính BMI</h2>");
        traVe.append("<form action=\"/BMI/calculate\" method=\"post\">");
        traVe.append("<div class=\"mb-3\">");
        traVe.append("<label for=\"height\" class=\"form-label\">Chiều cao (cm):</label>");
        traVe.append("<input type=\"text\" class=\"form-control\" id=\"height\" name=\"height\" required>");
        traVe.append("</div>");
        traVe.append("<div class=\"mb-3\">");
        traVe.append("<label for=\"weight\" class=\"form-label\">Cân nặng (kg):</label>");
        traVe.append("<input type=\"text\" class=\"form-control\" id=\"weight\" name=\"weight\" required>");
        traVe.append("</div>");
        traVe.append("<button type=\"submit\" class=\"btn btn-primary\">Submit</button>");
        traVe.append("</form>");
        traVe.append("</div>");
        traVe.append("</div>");
        traVe.append("</div>");
        traVe.append("</div>");
        traVe.append("</div>");
        traVe.append("<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js\"></script>");
        traVe.append("</body>");
        traVe.append("</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String heightStr = request.getParameter("height");
        String weightStr = request.getParameter("weight");

        // Convert dữ liệu sang kiểu số
        double height = Double.parseDouble(heightStr);
        double weight = Double.parseDouble(weightStr);

        // Thực hiện tính BMI
        double bmi = calculateBMI(height, weight);

        // Gửi trả kết quả về client
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter traVe = response.getWriter();
        traVe.append("<!DOCTYPE html>");
        traVe.append("<html lang=\"vi\">");
        traVe.append("<head>");
        traVe.append("<meta charset=\"UTF-8\">");
        traVe.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
        traVe.append("<title>Kết quả BMI</title>");
        traVe.append("<link href=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.min.css\" rel=\"stylesheet\" />");
        traVe.append("<style>");
        traVe.append("body {");
        traVe.append("  background-image: url('https://c8.alamy.com/comp/2GGJTW9/bmi-body-mass-index-and-weight-loss-banner-background-2GGJTW9.jpg');");
        traVe.append("  background-attachment: fixed;");
        traVe.append("  background-position: center;");
        traVe.append("  background-repeat: no-repeat;");
        traVe.append("  background-size: cover;");
        traVe.append("}");
        traVe.append("</style>");
        traVe.append("</head>");
        traVe.append("<body>");
        traVe.append("<div class=\"container py-5\">");
        traVe.append("<div class=\"row justify-content-center\">");
        traVe.append("<div class=\"col-lg-8\">");
        traVe.append("<div class=\"card\">");
        traVe.append("<div class=\"card-body text-center\">");
        traVe.append("<h2 class=\"card-title\">Kết quả BMI</h2>");
        
        DecimalFormat df = new DecimalFormat("#.##");
        double roundedBMI = Double.parseDouble(df.format(bmi));

        traVe.append("<p class=\"card-text\">Chỉ số BMI của bạn là: " + roundedBMI + "</p>");
        
        String bmiGroup = getBMIGroup(roundedBMI);
        traVe.append("<p class=\"card-text\">Nhóm BMI của bạn: " + bmiGroup + "</p>");
        
        traVe.append("</div>");
        traVe.append("</div>");
        traVe.append("</div>");
        traVe.append("</div>");
        traVe.append("</div>");
        traVe.append("<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/7.1.0/mdb.umd.min.js\"></script>");
        traVe.append("</body>");
        traVe.append("</html>");
	}

	private double calculateBMI(double height, double weight) {
        // Thực hiện tính BMI
        return weight / ((height / 100) * (height / 100));
    }
	
	private String getBMIGroup(double bmi) {
        if (bmi < 16) {
            return "Gầy độ III";
        } else if (bmi < 17) {
            return "Gầy độ II";
        } else if (bmi < 18.5) {
            return "Gầy độ I";
        } else if (bmi < 25) {
            return "Bình thường";
        } else if (bmi < 30) {
            return "Thừa cân";
        } else if (bmi < 35) {
            return "Béo phì độ I";
        } else if (bmi < 40) {
            return "Béo phì độ II";
        } else {
            return "Béo phì độ III";
        }
    }
}
