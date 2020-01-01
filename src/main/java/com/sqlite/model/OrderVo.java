package com.sqlite.model;

import java.util.HashSet;
import java.util.Set;

import com.sqlite.entities.OrderItemVo;

public class OrderVo {

    private Integer id;
    private Integer taxRate;
    
    private String orderNum;

	private String summary;

	private long total;
    private String date;
    
    private Integer status;
    
    private String clientId;
    
    Set<OrderItemVo> orderItems = new HashSet<>();
    
    
    public Set<OrderItemVo> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Set<OrderItemVo> orderItems) {
        this.orderItems = orderItems;
    }
    public String getClientId() {
		return clientId;
	}
    public String getDate() {
		return date;
	}

	public Integer getId() {
		return id;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public Integer getStatus() {
		return status;
	}

	public String getSummary() {
		return summary;
	}

	public Integer getTaxRate() {
		return taxRate;
	}

	public long getTotal() {
		return total;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public void setTaxRate(Integer taxRate) {
		this.taxRate = taxRate;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}

