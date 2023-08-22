package controllerServlet.cart;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dao.BookDAO;
import dao.CartDAO;
import dao.CatogoryDAO;
import model.Book;
import model.Cart;
import model.Image;
import util.Decompress;

@WebServlet("/api/v1/cart")
@MultipartConfig
public class AddToCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddToCartController() {
		super();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// Thiết lập loại dữ liệu trả về và bộ mã hóa ký tự
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String bookId = request.getParameter("bookId");
		String bookName = request.getParameter("name");
		String customerId = request.getParameter("customerId");
		String author = request.getParameter("author");
		String price = request.getParameter("price");
		String categoryId = request.getParameter("categoryId");
		String amount = request.getParameter("amount");

		Cart cart = new Cart();
		cart.setAmount(Integer.parseInt(amount));
		cart.setAuthor(author);
		cart.setBookName(bookName);
		cart.setId_book(Integer.parseInt(bookId));
		cart.setPrice(Double.parseDouble(price));
		cart.setCustomerId(Integer.parseInt(customerId));
		cart.setCategoryId(Integer.parseInt(categoryId));

		CartDAO cDAo = new CartDAO();
		cDAo.insert(cart);

		Gson gson = new Gson();
		String cartJson = gson.toJson(cart);
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().println(cartJson);

	}




	
	

}
