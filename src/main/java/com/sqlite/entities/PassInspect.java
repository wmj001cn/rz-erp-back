/*
 * Decompiled with CFR 0_123.
 * 
 * Could not load the following classes:
 *  javax.persistence.GeneratedValue
 *  javax.persistence.GenerationType
 *  javax.persistence.Id
 *  javax.persistence.Table
 */
package com.sqlite.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="pass_inspects")
public class PassInspect {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private int seq;
    private String tid;
    private String epc;
    private String epcMultiple = "";
    private boolean isLeading;
    
	private String status;
    private String time;
    private String orderNum;

    public boolean isLeading() {
        return this.isLeading;
    }

    public void setLeading(boolean isLeading) {
        this.isLeading = isLeading;
    }

    public String getEpcMultiple() {
        return this.epcMultiple;
    }

    public void setEpcMultiple(String epcMultiple) {
        this.epcMultiple = epcMultiple;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeq() {
        return this.seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public String getTid() {
        return this.tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getEpc() {
        return this.epc;
    }

    public void setEpc(String epc) {
        this.epc = epc;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderNum() {
        return this.orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }
}

