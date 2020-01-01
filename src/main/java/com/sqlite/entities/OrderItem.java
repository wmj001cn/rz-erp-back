package com.sqlite.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.sqlite.model.Order;


@Entity
@Table(name="tbl_order_item_test")
public class OrderItem implements Serializable{
	
	private static final long serialVersionUID = 1L;
     
     public static long getSerialversionuid() {
        return serialVersionUID;
    }
     /**
     * 
     */
    @Column
    private String desc;
     /*@Column(name = "order_id", insertable = false, updatable = false)
     private int orderId;*/
    @Column
    private String customerItemNo;
     
    @Column
    private int productId;


    @Column
    private long costPrice;



    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private Integer id;


    @Column
    private Long salePrice;


    @Column
    private String name;


    @Column
    private int qty;



    @ManyToOne
	@JoinColumn(name = "order_id", nullable = false)
	private Order order;



    public long getCostPrice() {
        return costPrice;
    }



    public String getCustomerItemNo() {
        return customerItemNo;
    }



    public String getDesc() {
        return desc;
    }



    public Integer getId() {
        return id;
    }



    public String getName() {
		return name;
	}



    public Order getOrder() {
        return order;
    }



    public int getProductId() {
        return productId;
    }

    public int getQty() {
		return qty;
	}

    public long getSalePrice() {
		return salePrice;
	}
	
	public void setCostPrice(long costPrice) {
        this.costPrice = costPrice;
    }

	public void setCustomerItemNo(String customerItemNo) {
        this.customerItemNo = customerItemNo;
    }

	public void setDesc(String desc) {
        this.desc = desc;
    }

	/*private String orderId;*/
	
	public void setId(Integer id) {
        this.id = id;
    }
	
	public void setName(String name) {
		this.name = name;
	}

	

	public void setOrder(Order order) {
        this.order = order;
    }
	
	
	public void setProductId(int productId) {
        this.productId = productId;
    }


	public void setQty(int qty) {
		this.qty = qty;
	}

	public void setSalePrice(long salePrice) {
		this.salePrice = salePrice;
	}
	

}
