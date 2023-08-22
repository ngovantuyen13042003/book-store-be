package createBookController;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.CatogoryDAO;
import model.Category;

/**
 * Servlet implementation class getCategory
 */
@WebServlet("/api/v1/category")
@MultipartConfig
public class GetCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public GetCategory() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// set type content and character encoding
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/json");
		
		// get list category from db
		CatogoryDAO cDAO = new CatogoryDAO();
		List<Category> list = cDAO.selectAll();
		
        
		Gson gson = new Gson();
		String listGson = gson.toJson(list);
		response.getWriter().println(listGson);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}












