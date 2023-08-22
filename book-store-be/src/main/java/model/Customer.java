	package model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String username, password, gender, address, receiveAddress, email,
			phoneNumber, imagePath;
	private Date dateOfBirth;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReceiveAddress() {
		return receiveAddress;
	}
	public void setReceiveAddress(String receiveAddress) {
		this.receiveAddress = receiveAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public Customer(int id, String username, String password, String gender, String address, String receiveAddress,
			String email, String phoneNumber, String imagePath, Date dateOfBirth) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.address = address;
		this.receiveAddress = receiveAddress;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.imagePath = imagePath;
		this.dateOfBirth = dateOfBirth;
	}
	public Customer() {
		super();
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", username=" + username + ", password=" + password + ", gender=" + gender
				+ ", address=" + address + ", receiveAddress=" + receiveAddress + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", imagePath=" + imagePath + ", dateOfBirth=" + dateOfBirth + "]";
	}

	

}
