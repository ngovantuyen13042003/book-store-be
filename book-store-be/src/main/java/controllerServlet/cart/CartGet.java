package controllerServlet.cart;

import java.io.IOException;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dao.BookDAO;
import dao.CartDAO;
import dao.CatogoryDAO;
import model.Book;
import model.Cart;
import model.Category;
import model.Image;
import util.Decompress;

@WebServlet("/api/v1/cart-get")
@MultipartConfig
public class CartGet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartGet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");

		String customerId = request.getParameter("id");
		
		Cart cart = new Cart();
		cart.setCustomerId(Integer.parseInt(customerId));
		// add cart
		CartDAO cDAO = new CartDAO();
		List<Cart> listCart = cDAO.getCartByCustomer(Integer.parseInt(customerId));
		
		CatogoryDAO categoryDAO = new CatogoryDAO();
		
		
		JsonArray results = new JsonArray();
		for (Cart cart2 : listCart) {
			System.out.println(cart2);
			JsonObject cartObj = new JsonObject();
			cartObj.addProperty("author", cart2.getAuthor());
			cartObj.addProperty("bookName", cart2.getBookName());
			cartObj.addProperty("price", cart2.getPrice());
			cartObj.addProperty("amount", cart2.getAmount());
			cartObj.addProperty("customerId", cart2.getCustomerId());
			cartObj.addProperty("id", cart2.getId_book());
			cartObj.addProperty("cartId", cart2.getId_cart());

//			System.out.println("category: " + cart.getCategoryId());
//			CatogoryDAO categoryDAO = new CatogoryDAO();
//			Category category = categoryDAO.selectById(cart.getId_book());

//			Category category = BookDAO.getCategory(cart.getId_book());
			Category category = categoryDAO.selectById(cart2.getCategoryId());
			System.out.println(category.getName());
			JsonObject categoryObj = new JsonObject();
			
			categoryObj.addProperty("id",category.getId());
			categoryObj.addProperty("name",category.getName());
			
			cartObj.add("category",categoryObj);

			BookDAO bDAO = new BookDAO();
			Book book = bDAO.getCart(cart2.getId_book());

			List<Image> listImg = book.getImages();

			JsonArray imgObj = new JsonArray();
			for (Image image : listImg) {
				JsonObject imgObject = new JsonObject();
				String base64StringImg = Base64.getEncoder()
						.encodeToString(Decompress.decompressImage(image.getDataImage()));
				imgObject.addProperty("id", image.getId());
				imgObject.addProperty("data", base64StringImg);
				imgObject.addProperty("type", image.getTypeImage());
				imgObject.addProperty("name", image.getNameImage());

				imgObj.add(imgObject);
			}
			cartObj.add("images", imgObj);
			
			results.add(cartObj);
		}
		
		

		response.getWriter().print(results);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
}
