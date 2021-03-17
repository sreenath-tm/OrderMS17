package com.infy.OrderNewMS.entity;


import java.io.Serializable;

//import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "productsordered")
@IdClass(CompositeKey.class)
public class ProductsOrdered implements Serializable{
    private static final long serialVersionUID = 1L;

	@Id
	@Column(name="orderid")
	Integer orderid;
	@Id
	@Column(name = "prodid")
	Integer prodid;
	@Column(name = "sellerid", nullable = false)
	Integer sellerid;
	@Column(nullable = false)
	Integer quantity;
	@Column(nullable = false, length=60)
	String status;
	@Column(precision=10, scale=2)
	Double price;
	public Integer getOrderId() {
		return orderid;
	}
	public void setOrderId(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getProdId() {
		return prodid;
	}
	public void setProdId(Integer prodid) {
		this.prodid = prodid;
	}
	public Integer getSellerId() {
		return sellerid;
	}
	public void setSellerId(Integer sellerid) {
		this.sellerid = sellerid;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
