/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  javax.persistence.Entity
 *  javax.persistence.GeneratedValue
 *  javax.persistence.GenerationType
 *  javax.persistence.Id
 *  javax.persistence.Table
 */
package com.sqlite.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sqlite.entities.OrderItem;

@Entity
@Table(name="tbl_order_copy1_test")
public class Order implements Serializable{
	
    
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;
    private Integer taxRate;
    
    private String orderNum;

	private String summary;

	private long total;
    private String date;
    
    private Integer status;
    
    @Column
    private String clientId;
    
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JsonIgnore
    Set<OrderItem> orderItems = new HashSet<>();
    
    public void addOrderItem(OrderItem orderItem) {
        orderItem.setOrder(this);
        this.orderItems.add(orderItem);
    }
    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Set<OrderItem> orderItems) {
        orderItems.stream().forEach(s->{
            s.setOrder(this);
        });
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

