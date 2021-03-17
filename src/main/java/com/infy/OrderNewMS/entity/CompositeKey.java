package com.infy.OrderNewMS.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CompositeKey implements Serializable {
    private static final long serialVersionUID = 1L;

	@Column(name="orderid")
	Integer orderid;
	@Column(name = "prodid")
	Integer prodid;
	/*public CompositeKey(Integer orderid,Integer prodid)
	{
		this.orderid=orderid;
		this.prodid=prodid;
	}*/
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
}
