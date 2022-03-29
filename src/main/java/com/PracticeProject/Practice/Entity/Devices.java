package com.PracticeProject.Practice.Entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Devices")
public class Devices {

@Id
@GeneratedValue
private int id;
@Column(name="deviceName")
private String deviceName;
@Column(name="qty")
private int qty;
@Column(name="price")
private double price;
@ManyToOne(cascade=CascadeType.ALL)
@JoinColumn(name="user_id")
private Users users;

public Devices(int id, String deviceName, int qty, double price, Users users) {
	super();
	this.id = id;
	this.deviceName = deviceName;
	this.qty = qty;
	this.price = price;
	this.users = users;
}
public Users getUsers() {
	return users;
}
public void setUsers(Users users) {
	this.users = users;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDeviceName() {
	return deviceName;
}
public void setDeviceName(String deviceName) {
	this.deviceName = deviceName;
}
public int getQty() {
	return qty;
}
public void setQty(int qty) {
	this.qty = qty;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}

}
