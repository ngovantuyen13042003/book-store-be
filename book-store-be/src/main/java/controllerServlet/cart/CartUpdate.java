package controllerServlet.cart;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CartDAO;
import model.Cart;

/**
 * Servlet implementation class CartUpdate
 */
@WebServlet("/api/v1/cart-update")
@MultipartConfig
public class CartUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartUpdate() {
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
//		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//		response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
//		response.setHeader("Access-Control-Allow-Headers",
//				"Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
//		response.setHeader("Access-Control-Allow-Credentials", "true");
		request.setCharacterEncoding("UTF-8");
		// Thiết lập loại dữ liệu trả về và bộ mã hóa ký tự
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

//		String cartId = request.getParameter("cartId");
		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("name");
		String customerId = request.getParameter("customerId");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		String amount = request.getParameter("amount");

		Cart cart = new Cart();
//		cart.setId_cart(Integer.parseInt(cartId));
		cart.setAmount(Integer.parseInt(amount));
		cart.setAuthor(author);
		cart.setBookName(bookName);
		cart.setId_book(Integer.parseInt(bookId));
		cart.setPrice(Double.parseDouble(price));
		cart.setCustomerId(Integer.parseInt(customerId));

//			System.out.println("id  "+ customerId + "amount   "+ amount + "name  " + bookName);

		CartDAO cDAo = new CartDAO();
		cDAo.updateCartByBookId(cart);

		Gson gson = new Gson();
		String cartJson = gson.toJson(cart);
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println(cartJson);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doOptions(HttpServletRequest, HttpServletResponse)
	 */
	protected void doOptions(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
