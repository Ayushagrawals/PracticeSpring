package com.PracticeProject.Practice.Entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class Product {
@Id
@GeneratedValue
private int id;
@Column(name="productName")
private String productName;
@Column(name="qty")
private int qty;
@Column(name="price")
private int price;
//@ManyToOne(fetch = FetchType.EAGER, optional = false)
//@JoinColumn(name = "user_id", nullable = false)
//private Book book;
//private User user;
public Product(int id, String productName, int qty, int price) {
	super();
	this.id = id;
	this.productName = productName;
	this.qty = qty;
	this.price = price;
}
public Product()
{
	
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public void setQty(int qty) {
	this.qty = qty;
}
public void setPrice(int price) {
	this.price = price;
}
@Override
public String toString() {
	return "Product [id=" + id + ", productName=" + productName + ", qty=" + qty + ", price=" + price + "]";
}
public String getProductName() {
	return productName;
}
public int getQty() {
	return qty;
}
public int getPrice() {
	return price;
}



}
