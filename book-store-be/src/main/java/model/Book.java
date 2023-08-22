package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String author;
	
	@JoinColumn(name = "id_category")
	@ManyToOne
	private Category category;
	
	private String publisher;
	private String name,description;
	private Double  price;
	private int  amount,pagesNumber;
	private String language;
	
	@OneToMany(mappedBy = "books")
	private List<Image> images;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Double getSalePrice() {
		return price;
	}
	public void setSalePrice(Double salePrice) {
		this.price = salePrice;
	}
	

	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPagesNumber() {
		return pagesNumber;
	}
	public void setPagesNumber(int pagesNumber) {
		this.pagesNumber = pagesNumber;
	}
	
	
	
	
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Book(int id, String author, Category category, String publisher, String name, String description,
			Double price, int amount, int pagesNumber, List<Image> images) {
		super();
		this.id = id;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.pagesNumber = pagesNumber;
		this.images = images;
	}
	

	public Book(int id, String author, Category category, String publisher, String name, String description,
			Double price, int amount, int pagesNumber, String language) {
		super();
		this.id = id;
		this.author = author;
		this.category = category;
		this.publisher = publisher;
		this.name = name;
		this.description = description;
		this.price = price;
		this.amount = amount;
		this.pagesNumber = pagesNumber;
		this.language = language;
	}
	public Book() {
		super();
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", category=" + category + ", publisher=" + publisher
				+ ", name=" + name + ", description=" + description + ", price=" + price + ", amount=" + amount
				+ ", pagesNumber=" + pagesNumber + ", language=" + language + ", images=" + images + "]";
	}
	
	
	
}
