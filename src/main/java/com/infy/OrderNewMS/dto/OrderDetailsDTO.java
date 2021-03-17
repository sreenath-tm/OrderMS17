package com.infy.OrderNewMS.dto;

import java.util.Date;
import java.util.List;

import com.infy.OrderNewMS.entity.OrderDetails;

public class OrderDetailsDTO {
	Integer orderid;
	Integer buyerid;
	float amount;
	Date date;
	String address;
	String status;
	List<ProductsOrderedDTO> productsOrdered;
	public OrderDetailsDTO() {
		super();
		
	}
	public OrderDetailsDTO(Integer orderid, Integer buyerid, float amount, Date date, String address, String status) {
		super();
		this.orderid = orderid;
		this.buyerid = buyerid;
		this.amount = amount;
		this.date = date;
		this.address = address;
		this.status = status;
	}
	public List<ProductsOrderedDTO> getProductsOrdered()
	{
		return productsOrdered;
	}
	public void setProductsOrdered(List<ProductsOrderedDTO> productsOrdered)
	{
		this.productsOrdered=productsOrdered;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getBuyerid() {
		return buyerid;
	}
	public void setBuyerid(Integer buyerid) {
		this.buyerid = buyerid;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
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
	// Converts Entity into DTO
		public static OrderDetailsDTO valueOf(OrderDetails orderDetails) {
			OrderDetailsDTO orderdetailsDto = new OrderDetailsDTO();
			orderdetailsDto.setOrderid(orderDetails.getOrderId());
			orderdetailsDto.setBuyerid(orderDetails.getBuyerId());
			orderdetailsDto.setAmount(orderDetails.getAmount());
			orderdetailsDto.setDate(orderDetails.getDate());
			orderdetailsDto.setAddress(orderDetails.getAddress());
			orderdetailsDto.setStatus(orderDetails.getStatus());
			orderdetailsDto.setProductsOrdered(null);
			return orderdetailsDto;
		}
		//converts DTO into Entity
		public OrderDetails createEntity()
		{
			OrderDetails orderdetails = new OrderDetails();
			orderdetails.setOrderId(this.getOrderid());
			orderdetails.setBuyerId(this.getBuyerid());
			orderdetails.setAmount(this.getAmount());
			orderdetails.setDate(this.getDate());
			orderdetails.setAddress(this.getAddress());
			orderdetails.setStatus(this.getStatus());
			return orderdetails;
		}
		
		@Override
		public String toString() {
			return "OrderDetailsDTO [orderid=" + orderid + ", buyerid=" + buyerid + ", amount=" + amount
					+", date=" + date +", address=" + address + ", status=" + status + "]";
		}

}
