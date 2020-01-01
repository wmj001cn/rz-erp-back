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

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="tbl_invoice")
@EntityListeners(AuditingEntityListener.class)
public class Invoice {
	
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;
    
    private String typeCode;
    private String type;
    private String category;
    private long priceTotalWithTax;
    private long taxTotal;
    

    private String issueDate;
 

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	private String submitDate;
    
    
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public long getPriceTotalWithTax() {
		return priceTotalWithTax;
	}

	public void setPriceTotalWithTax(long priceTotalWithTax) {
		this.priceTotalWithTax = priceTotalWithTax;
	}

	public long getTaxTotal() {
		return taxTotal;
	}

	public void setTaxTotal(long taxTotal) {
		this.taxTotal = taxTotal;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	private String number;
    
    @OneToOne
    private Proof proof;

	public Proof getProof() {
		return proof;
	}

	public void setProof(Proof proof) {
		this.proof = proof;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	} 
    
  
}

