package com.sqlite.entities;

public class OrderItemVo {

	private Integer id;

	private String name;

	private int qty;

	private String orderId;
	
	private int productId;
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public String getOrderId() {
		return orderId;
	}

	public int getQty() {
		return qty;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

}
