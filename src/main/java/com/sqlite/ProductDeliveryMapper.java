package com.sqlite;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductDeliveryMapper {

    @Select("SELECT\n" +
    		"	coalesce(ci.inventory_code, p.name) as inventoryCode,\n" +
    		"  coalesce(ci.description, p.name) as description,\n" +
    		"  oi.qty,\n" +
    		"  p.unit,\n" +
    		"  p.type,\n" +
    		"  p.id\n" +
    		"FROM\n" +
    		"	tbl_order_copy1_test o\n" +
    		"JOIN tbl_order_item_test oi ON o.id = oi.order_id\n" +
    		"JOIN tbl_product p ON oi.product_id = p.id\n" +
    		"LEFT JOIN tbl_client_inventory ci ON p.id = ci.product_id\n" +
    		"AND ci.client_id = o.client_id\n" +
    		"WHERE\n" +
    		"	o.id = #{orderId}")
    List<Map> findProductClientInventoryInfoByOrderId(@Param("orderId") String orderId);
    
    @Select("SELECT\n" +

    		"  p.unit,\n" +
    		"  p.name,\n" +
    		"  oi.qty,\n" +
    		"  coalesce(oi.sale_price, p.sale_price) as salePrice\n" +
    		"FROM\n" +
    		"	tbl_order_copy1_test o\n" +
    		"JOIN tbl_order_item_test oi ON o.id = oi.order_id\n" +
    		"JOIN tbl_product p ON oi.product_id = p.id\n" +
    		"WHERE\n" +
    		"	o.id = #{orderId}")
    List<Map> findInvoiceItemByOrderId(@Param("orderId") String orderId);
    
    @Select("SELECT\n" +
    		"  cc.contact,\n" +
    		"  coalesce(cc.addr, c.addr) as addr,\n" +
    		"  cc.tel,\n" +
    		"  c.tel as clientTel,\n" +
    		"  c.name as clientName,\n" +
    		"  c.addr as cAddr,\n" +
    		"  c.tax_number as taxNumber,\n" +
    		"  c.bank as bank,\n" +
    		"  c.bank_account as bankAccount\n" +
    		"FROM\n" +
    		"	tbl_client_contact cc\n" +
    		"join tbl_client c on cc.client_id = c.id\n" +
    		"WHERE\n" +
    		"	cc.role = '${role}'\n" +
    		"AND cc.client_id = #{clientId}\n" +
    		"AND cc.isDefault = 1")
	Map findDeliveryInfoByClientId(@Param("clientId") String clientId, @Param("role") String role);

    
    @Select("SELECT\n" +
    		"	p.name,\n" +
    		"	p.unit,\n" +
    		"	oi.qty * ps.qty AS totalQty\n" +
    		"FROM\n" +
    		"	tbl_order o\n" +
    		"JOIN tbl_order_item_test oi ON o.id = oi.order_id\n" +
    		"JOIN tbl_product_suit ps ON oi.product_id = ps.parent_product_id\n" +
    		"JOIN tbl_product p ON ps.product_id = p.id\n" +
    		"WHERE\n" +
    		"	o.id = #{orderId}\n" +
    		"AND ps.parent_product_id = #{productId}")
    List<Map> findAllAssociateProduct(@Param("orderId") String orderId, @Param("productId") Integer productId);

}