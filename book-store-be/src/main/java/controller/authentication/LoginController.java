package controller.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import dao.CustomerDAO;
import model.Customer;
import util.EncodePassword;


@WebServlet("/api/v1/login")
@MultipartConfig
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set the response content type and character encoding
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println("input: " + email + password);

		// check account exist in database
		CustomerDAO cDAO = new CustomerDAO();
		Customer c = new Customer();
		c.setEmail(email);
		c.setPassword(EncodePassword.encodePassword(password));
		c = cDAO.checkAccount(c);

//		System.out.println(c.toString());
//		
		if (c != null) {
			// chuyển dữ liệu customer thành json
			Gson gson = new Gson();
			String customerJson = gson.toJson(c);
			
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().println(customerJson);
		} else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().println("{\"error\": \"Email hoặc mật khẩu không chính xác.\"}");
		}

	}

	

}
