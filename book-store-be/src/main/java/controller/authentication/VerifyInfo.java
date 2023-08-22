package controller.authentication;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import dao.CustomerDAO;
import model.Customer;
import util.EncodePassword;
import util.SendEmail;
import util.VerifyCode;

@WebServlet("/api/v1/verify")
@MultipartConfig
public class VerifyInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VerifyInfo() {
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
		
		HttpSession session = request.getSession();
		
//		String verifyCode = (String) session.getAttribute("verifyCode");
		ServletContext context = getServletContext();
		String verifyCode = (String) context.getAttribute("verifyCode");
		System.out.println( "info"+verifyCode);
		String code  = request.getParameter("code");
		System.out.println("input"+code);
		if(code.equals(verifyCode)) {
			
			Customer customerTemp = (Customer) context.getAttribute("customer");
			
			String password = customerTemp.getPassword();
			String username = customerTemp.getUsername();
			String email = customerTemp.getEmail();
			
			System.out.println(customerTemp.getEmail() + "    Ngo van tuyen    " + customerTemp.getPassword() + "      deng duy    " + customerTemp.getUsername()+ " " + verifyCode);

			

			// encode password
			password = EncodePassword.encodePassword(password);
			
			Customer customer = new Customer();
			customer.setUsername(username);
			customer.setPassword(password);
			customer.setEmail(email);
			
			// insert into database
			CustomerDAO cDAO = new CustomerDAO();
			cDAO.insert(customer);// insert xuống database +  save info into customer
//			
//			// chuyển dữ liệu customer  thành json
//			Gson gson = new Gson();
////			response.setStatus(HttpServletResponse.SC_CREATED);
//			response.setContentType("text/plain;charset=UTF-8");
//			String customerJson = gson.toJson(customer);
			response.setStatus(HttpServletResponse.SC_CREATED);
			response.getWriter().println("{\"success\": \"Đăng ký thành công. Vui lòng đăng nhập để sử dụng.\"}");
//			session. 
			
		}else {
			response.setContentType("text/plain;charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().println("{\"error\": \"Mã xác thực không chính xác.\"}");
		}
		
		
		
		
		
		
		

	}

	
}
