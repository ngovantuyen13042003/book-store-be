package test;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import dao.BookDAO;
import dao.CartDAO;
import dao.CatogoryDAO;
import dao.CustomerDAO;
import dao.ImageDAO;
import model.Book;
import model.Cart;
import model.Category;
import model.Customer;
import model.Image;

public class Test {
	public static void main(String[] args) {
//		CustomerDAO cDAO = new CustomerDAO();
//		List<Customer> list = cDAO.selectAll();
//		for (Customer c : list) {
//			System.out.println(c);
//		}
		
//		Category c = new Category();
//		c.setName("Tây tiến");
//		CatogoryDAO cDAO = new CatogoryDAO();
//		
//		cDAO.insert(c);
//		Book b = new Book();
//		b.setName("Tây tiến");
//		BookDAO bDAO = new BookDAO();
//		bDAO.insert(b);
//
//		Image i = new Image();
//		i.setNameImage("Tây tiến");
//		ImageDAO iDAO = new ImageDAO();
//		iDAO.insert(i);
////		
//		BookDAO bd = new BookDAO();
//		List<Book> list = bd.selectAll();
//		
//		
//		for (Book book : list) {
//			List<Image> listImg = book.getImages();
//			System.out.println(book.getName());
//			for (Image image : listImg) {
//				System.out.println("------------" + image.getNameImage());
//			}
//		}
		
		
//		List<Book> list = BookDAO.paging();
//		for (Book book : list) {
//			System.out.println(book);
//		}
		
		Cart cart = new Cart();
		cart.setBookName("Kinh sám hối");
		CartDAO c = new CartDAO();
		c.insert(cart);
	}
}


















