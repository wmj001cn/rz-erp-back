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
@Table(name="tbl_proof")
@EntityListeners(AuditingEntityListener.class)
public class Proof {
	
    
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;
    
    private String path;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

  
}

