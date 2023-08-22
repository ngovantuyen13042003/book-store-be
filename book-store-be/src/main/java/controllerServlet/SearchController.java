package controllerServlet;

import java.io.IOException;
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
import model.Book;
import model.Image;
import util.Decompress;

@WebServlet("/api/v1/search")
@MultipartConfig
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		// Thiết lập loại dữ liệu trả về và bộ mã hóa ký tự
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		
		String searchContent = request.getParameter("searchContent");
		System.out.println("searchContent: " + searchContent);
		
		List<Book> list = BookDAO.search(searchContent.trim());
		if(list == null) {
			System.out.println("dfghjkmdfff null");
		}
		JsonArray result = new JsonArray();
		for (Book book : list) {
			JsonObject bookObj = new JsonObject();
			bookObj.addProperty("name", book.getName());
			bookObj.addProperty("id", book.getId());
			bookObj.addProperty("author", book.getAuthor());
			bookObj.addProperty("language", book.getLanguage());
			bookObj.addProperty("price", book.getPrice());
			bookObj.addProperty("description", book.getDescription());
			bookObj.addProperty("publisher", book.getPublisher());
			bookObj.addProperty("amount", book.getAmount());
			
			JsonObject categoryObj = new JsonObject();
			categoryObj.addProperty("id", book.getCategory().getId());
			categoryObj.addProperty("name", book.getCategory().getName());
			
			bookObj.add("category", categoryObj);
			bookObj.addProperty("pageNumber", book.getPagesNumber());
			
			
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
			bookObj.add("images", arrayImg);
			
			result.add(bookObj);
		}
		
		
		
		response.getWriter().println(result);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}













