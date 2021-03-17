package com.infy.OrderNewMS.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderdetails")
public class OrderDetails{

	@Id
	@Column(name = "orderid",nullable = false)
	
	Integer orderid;
	
	@Column(name = "buyerid",nullable = false)
	Integer buyerid;
	
	@Column(name = "amount",nullable = false,precision=10, scale=2)
	Float amount;

	@Column(name = "date")
	Date date;
	
	@Column(name = "address",nullable = false,length=100)
	String address;
	@Column(name = "status",nullable = false,length=60)
	String status;
	public Integer getOrderId() {
		return orderid;
	}
	public void setOrderId(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getBuyerId() {
		return buyerid;
	}
	public void setBuyerId(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public Float getAmount() {
		return amount;
	}
	public void setAmount(Float amount) {
		this.amount = amount;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public OrderDetails() {
		super();
		
}}
