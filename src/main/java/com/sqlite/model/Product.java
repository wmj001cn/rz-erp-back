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

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="tbl_product")
@EntityListeners(AuditingEntityListener.class)
public class Product {
	
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;
    
    private String name;
    private Long salePrice = 0l;
    private Long costPrice = 0l;
    private Long loweastPrice = 0l;
    private String spec;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(long salePrice) {
		this.salePrice = salePrice;
	}
	public long getCostPrice() {
		return costPrice;
	}
	public void setCostPrice(long costPrice) {
		this.costPrice = costPrice;
	}
	public long getLoweastPrice() {
		return loweastPrice;
	}
	public void setLoweastPrice(long loweastPrice) {
		this.loweastPrice = loweastPrice;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
    
    
    
}

