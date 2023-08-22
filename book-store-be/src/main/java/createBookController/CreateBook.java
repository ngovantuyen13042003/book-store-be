package createBookController;

import java.io.IOException;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import dao.BookDAO;
import dao.CatogoryDAO;
import dao.ImageDAO;
import model.Book;
import model.Category;
import model.Image;
import util.Compress;
import util.Decompress;

@WebServlet("/api/v1/books")
@MultipartConfig
public class CreateBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// set type content and character encoding
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		BookDAO bDAO = new BookDAO();
		List<Book> listBook = bDAO.selectAll();

		JsonArray results = new JsonArray();
		for (Book b : listBook) {
			JsonObject bookObj = new JsonObject();
			bookObj.addProperty("id", b.getId());
			bookObj.addProperty("name", b.getName());
			bookObj.addProperty("author", b.getAuthor());
			bookObj.addProperty("languages", b.getLanguage());
			bookObj.addProperty("length", b.getPagesNumber());
			bookObj.addProperty("price", b.getPrice());
			bookObj.addProperty("publisher", b.getPublisher());
		
			
			JsonObject categoryObj = new JsonObject();
			categoryObj.addProperty("id", b.getCategory().getId());
			categoryObj.addProperty("name", CatogoryDAO.getNameById(b.getCategory().getId()));

			bookObj.add("category", categoryObj);
			
			bookObj.addProperty("description", b.getDescription());
			bookObj.addProperty("amount", b.getAmount());

			List<Image> listImg = b.getImages();

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
			
			JsonObject totalObj = new JsonObject();
			totalObj.addProperty("total", BookDAO.getTotalBook());
			
			results.add(bookObj);
			results.add(totalObj);
			
		}

		response.getWriter().print(results);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// set the response content type and character encoding
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String name = request.getParameter("name");
		String author = request.getParameter("author");
		String category = request.getParameter("category");
		String publisher = request.getParameter("publisher");
		String price = request.getParameter("price");
		String languages = request.getParameter("languages");
		String amount = request.getParameter("amount");
		String length = request.getParameter("length");
		String description = request.getParameter("description");

		// xử lý riêng cho model Image
		Collection<Part> listImg = request.getParts();

		Book b = new Book();
		b.setAmount(Integer.parseInt(amount));
		b.setAuthor(author);
		b.setCategory(new Category(Integer.parseInt(category)));
		b.setDescription(description);
		b.setLanguage(languages);
		b.setName(name);
		b.setPagesNumber(Integer.parseInt(length));
		b.setPrice(Double.parseDouble(price));
		b.setPublisher(publisher);

		// insert book into db
		BookDAO bDAO = new BookDAO();
		bDAO.insert(b);

		// add book into bookObj
		JsonObject bookObj = new JsonObject();
		bookObj.addProperty("id", b.getId());
		bookObj.addProperty("name", b.getName());
		bookObj.addProperty("author", b.getAuthor());
		bookObj.addProperty("languages", b.getLanguage());
		bookObj.addProperty("length", b.getPagesNumber());
		bookObj.addProperty("price", b.getPrice());
		bookObj.addProperty("publisher", b.getPublisher());
		bookObj.addProperty("description", b.getDescription());
		bookObj.addProperty("amount", b.getAmount());

		JsonObject categoryObj = new JsonObject();
		categoryObj.addProperty("id", b.getCategory().getId());
		categoryObj.addProperty("name", CatogoryDAO.getNameById(b.getCategory().getId()));

		bookObj.add("category", categoryObj);

		ImageDAO iDAO = new ImageDAO();

		JsonArray arrayImg = new JsonArray();
		for (Part part : listImg) {
			if (part.getName().equals("images")) {
				JsonObject imgObject = new JsonObject();

				Image image = new Image();
				image.setBooks(b);
				image.setNameImage(part.getSubmittedFileName());
				image.setTypeImage(part.getContentType());
				byte[] imageData = Compress.compressImage(part.getInputStream().readAllBytes());
				image.setDataImage(imageData);

				// insert Image compressed into db
				iDAO.insert(image);

				// add image into bookObj
//				imgObject.addProperty("id", image.getId());

				String base64StringImg = Base64.getEncoder()
						.encodeToString(Decompress.decompressImage(image.getDataImage()));
				imgObject.addProperty("id", image.getId());
				imgObject.addProperty("data", base64StringImg);
				imgObject.addProperty("type", image.getTypeImage());
				imgObject.addProperty("name", image.getNameImage());

				arrayImg.add(imgObject);
			}
		}
		bookObj.add("images", arrayImg);

		// response for client
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.getWriter().print(bookObj);

	}

}
