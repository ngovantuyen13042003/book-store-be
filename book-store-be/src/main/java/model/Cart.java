package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_cart ;
	private int id_book;
	private String bookName;
	private String author;
	private Double price;
	private int amount;
	private int customerId;
	private int categoryId;
	
	public int getId_cart() {
		return id_cart;
	}
	public void setId_cart(int id_cart) {
		this.id_cart = id_cart;
	}
	public int getId_book() {
		return id_book;
	}
	public void setId_book(int id_book) {
		this.id_book = id_book;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public Cart() {
		super();
	}
	
	public Cart(int id_cart, int id_book, String bookName, String author, Double price, int amount, int customerId,
			int categoryId) {
		super();
		this.id_cart = id_cart;
		this.id_book = id_book;
		this.bookName = bookName;
		this.author = author;
		this.price = price;
		this.amount = amount;
		this.customerId = customerId;
		this.categoryId = categoryId;
	}
	@Override
	public String toString() {
		return "Cart [id_cart=" + id_cart + ", id_book=" + id_book + ", bookName=" + bookName + ", author=" + author
				+ ", price=" + price + ", amount=" + amount + ", customerId=" + customerId + "]";
	}
	
}
