package controllerServlet;

import java.io.IOException;
import java.io.OutputStream;
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
import dao.CatogoryDAO;
import model.Book;
import model.Image;
import util.Decompress;

@WebServlet("/api/v1/books/pagination")
@MultipartConfig
public class PaginationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public PaginationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			int currentPage = Integer.parseInt(request.getParameter("page"));
			int pageSize = Integer.parseInt(request.getParameter("size"));
			System.out.println("page: "+ currentPage + "size : "+ pageSize);

			List<Book> items = BookDAO.getItemsByPage(currentPage, pageSize);

			int totalCount = BookDAO.getTotalCount();

			JsonObject result = new JsonObject();
			
			// Tạo một đối tượng JsonObject để đóng gói dữ liệu phân trang
			JsonObject pagination = new JsonObject();
			pagination.addProperty("bookAmount", BookDAO.getTotalBook());
			pagination.addProperty("page", currentPage);
			pagination.addProperty("size", pageSize);
			
			result.add("pagination", pagination);

			JsonArray itemsArray = new JsonArray();
			for (Book book : items) {
				JsonObject itemObject = new JsonObject();
				itemObject.addProperty("id", book.getId());
				itemObject.addProperty("name", book.getName());
				itemObject.addProperty("amount", book.getAmount());
				itemObject.addProperty("author", book.getAuthor());
				itemObject.addProperty("description", book.getDescription());
				itemObject.addProperty("price", book.getPrice());
				itemObject.addProperty("length", book.getPagesNumber());
				itemObject.addProperty("language", book.getLanguage());
				itemObject.addProperty("publisher", book.getPublisher());
				
				// add category
				JsonObject categoryObj = new JsonObject();
				categoryObj.addProperty("id", book.getCategory().getId());
				categoryObj.addProperty("name", CatogoryDAO.getNameById(book.getCategory().getId()));

				itemObject.add("category", categoryObj);
				
				// add image
				List<Image> listImg = book.getImages();
				
				JsonArray arrayImg = new JsonArray();
				for (Image image : listImg) {
					JsonObject imgObject = new JsonObject();
					String base64StringImg = Base64.getEncoder()
							.encodeToString(Decompress.decompressImage(image.getDataImage()));
					imgObject.addProperty("id", image.getId());
					imgObject.addProperty("data", base64StringImg);
					imgObject.addProperty("type", image.getTypeImage());
					imgObject.addProperty("name", image.getNameImage());

					arrayImg.add(imgObject);
				}
				itemObject.add("images", arrayImg);
				
				itemsArray.add(itemObject);
			}
			
			result.add("data", itemsArray);

			// Thiết lập loại dữ liệu trả về và bộ mã hóa ký tự
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			
			response.getWriter().print(result);

			// Ghi dữ liệu phân trang vào OutputStream của response
//			OutputStream out = response.getOutputStream();
//			out.write(result.toString().getBytes("UTF-8"));
//			out.flush();
//			out.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
