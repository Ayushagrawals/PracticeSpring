package com.PracticeProject.Practice.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;

@Entity
@Table(name="User")
public class Users {
@Id
@GeneratedValue
private int id;
@Column(name="username")
private String username;
@Column(name="password")
private String password;
@Column(name="role")
private String role;
@Column(name="age")
private int age;
@Column(name="salary")
private double salary;
@OneToMany(cascade=CascadeType.ALL)
private List<Product> products=new ArrayList<>();


  @OneToMany(cascade=CascadeType.ALL,mappedBy="users") private List<Devices>
  devices=new ArrayList<>();
 

//(mappedBy = "user", fetch = FetchType.EAGER,
//cascade = CascadeType.ALL)
//@OneToMany(targetEntity=Product.class,cascade=CascadeType.ALL)
//@JoinColumn(name="cp_fk",columnDefinition="id")
//private Product product;



 public List<Product> getProducts() { 
	return products; 
	} 
public void
  setProducts(List<Product> products) { this.products = products; }
 
public void setId(int id) {
	this.id = id;
}
public void setuserName(String username) {
	this.username = username;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
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
public void setAge(int age) {
	this.age = age;
}
public void setSalary(double salary) {
	this.salary = salary;
}

/*
 * public Product getProduct() { return product; } public void
 * setProduct(Product product) { this.product = product;
 * 
 * }
 */
 


/*
 * public User(int Id, String name, int age, double salary,Product product) {
 * super(); this.id = Id; this.name = name; this.age = age; this.salary =salary;
 * this.product=product; }
 */
 



 
 
/*
 * public User(int Id, String name, int age, double salary) { super(); this.id =
 * Id; this.name = name; this.age = age; this.salary = salary;
 * 
 * }
 */
public int getId() {
	return id;
}

public Users(int id, String username, String password,String role, int age, double salary, List<Product> products) {
	super();
	this.id = id;
	this.username = username;
	this.password = password;
	this.age = age;
	this.salary = salary;
	this.products = products;
	this.role=role;
}
public Users()
{
	
}
public String getUserName() {
	return username;
}

public int getAge() {
	return age;
}

public double getSalary() {
	return salary;
}



}
