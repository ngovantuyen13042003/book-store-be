package controllerServlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CartDAO;

/**
 * Servlet implementation class CartDelete
 */
@WebServlet("/api/v1/cart-delete")
@MultipartConfig
public class CartDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartDelete() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Thiết lập loại dữ liệu trả về và bộ mã hóa ký tự
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String customerId = request.getParameter("customerId");
		String bookId = request.getParameter("bookId");

		CartDAO cDAO = new CartDAO();
		System.out.println("cusId "+customerId + "bookId "+ bookId);

		if (cDAO.removeCart(Integer.parseInt(customerId) , Integer.parseInt(bookId)) ) {
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("seccusss");
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			response.getWriter().write("failed");
		}
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
