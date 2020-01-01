package com.sqlite.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sqlite.entities.OrderItem;

public interface OrderItemDao extends JpaRepository<OrderItem, String> {
  
	
	@Query(value="SELECT\n" +
			"	p.name,\n" +
			"	COALESCE(oi.sale_price, p.sale_price) as sale_price,\n" +
			"	oi.id,\n" +
			"	oi.qty,\n" +
			"	oi.cost_price,\n" +
			"	oi.customer_item_no,\n" +
			"	oi.desc,\n" +
			"	oi.product_id,\n" +
			"	oi.order_id\n" +
			"FROM\n" +
			"	tbl_order_item_test oi\n" +
			"JOIN tbl_order_copy1_test o ON oi.order_id = o.id\n" +
			"JOIN tbl_product p ON oi.product_id = p.id\n" +
			"WHERE\n" +
			"	o.id = ?1", nativeQuery=true)
	
	List<OrderItem> findByOrderId(String orderId);
	
}

