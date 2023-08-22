package controller.authentication;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.CustomerDAO;
import model.Customer;
import util.SendEmail;
import util.VerifyCode;

@WebServlet("/api/v1/register")
@MultipartConfig
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegisterController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// create verify code
	String verifyCode = VerifyCode.getVerifyCode();
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set the response content type and character encoding
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		
		
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.println(email + "    "+ username+"     "+password);
		// check email có tồn tại chưa
		CustomerDAO cDAO = new CustomerDAO();
		List<Customer> list = cDAO.selectAll();
		for (Customer c : list) {
			if (c.getEmail().equals(email)) {
				// response khi email tồn tại
				response.setStatus(HttpServletResponse.SC_CONFLICT);
				response.getWriter().println("{\"error\": \"Email đã được sử dụng vui lòng sử dụng email khác.\"}");
				return;
			}
		}

		// Gửi email xác thực
		try {
			SendEmail.sendEmail(email, "Verification account", getContent(username));
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		Customer cusTemp = new Customer();
		cusTemp.setUsername(username);
		cusTemp.setEmail(email);
		cusTemp.setPassword(password);
		
//		HttpSession session = request.getSession();	
//		session.setAttribute("customer", cusTemp);
		
//		session.setAttribute("verifyCode", verifyCode);
		ServletContext context = getServletContext();
		context.setAttribute("customer", cusTemp);
		context.setAttribute("verifyCode", verifyCode);
		System.out.println("register "+verifyCode);
//		RequestDispatcher dispatcher = context.getRequestDispatcher("/api/v1/verify-info");
//		dispatcher.forward(request, response);
//		
//		response.setContentType("text/plain;charset=UTF-8");
//		response.getWriter().write("Đăng ký thành công");

	}

	public String getContent(String username) {

		String content = "<!DOCTYPE html>\r\n" + "<html lang=en>\r\n" + "<head>\r\n" + "<meta charset=\"UTF-8\">\r\n"
				+ "<meta http-equiv=X-UA-Compatible content=IE=edge>\r\n"
				+ "<meta name=viewport content=width=device-width, initial-scale=1.0>\r\n"
				+ "<title>Document</title>\r\n" + "</head>\r\n" + "<body>\r\n" + "	<p>\r\n"
				+ "		Xin chào <strong>" + username + "</strong>\r\n" + "	</p>\r\n"
				+ "	<p>Đây là email xác thực được gửi tự động từ hệ thống, bạn không\r\n"
				+ "		được feedback cho email này.</p>\r\n" + "	<p>\r\n" + "		Mã xác thực của bạn là <strong>"
				+ verifyCode + "</strong>\r\n" + "	</p>\r\n"
				+ "	<p>Bạn không được chia sẽ mã xác thực này cho bất kỳ ai.</p>\r\n" + "	<br>\r\n" + "	<p>\r\n"
				+ "		Cảm ơn bạn đã lựa chọn <strong>Our Book Store!</strong>\r\n" + "	</p>\r\n"
				+ "</body>\r\n" + "</html>";

		return content;
	}

}
