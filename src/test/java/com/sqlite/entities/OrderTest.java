package com.sqlite.entities;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;

import com.google.gson.Gson;
import com.ruiziot.test.base.DaoTestBase;
import com.sqlite.dao.OrderDao;
import com.sqlite.model.Order;
import com.sqlite.utils.OrderFilter;
import com.sqlite.utils.OrderPredicateBuilder;

public class OrderTest extends DaoTestBase{
    
    @Autowired
    private OrderDao orderDao;
    
    @Test
    public void test() {
        
        OrderFilter of = new Gson().fromJson(
        		"{pageSize:5, pageNum:0, date:'2019-07-12', fieldBySortDir:{'DESC':'date'}, status:[100]}", 
        		OrderFilter.class);
        //of.setOrderNum("153082");
        int pageSize = 20;
        int pageNum = 0;
    	of.sort(Direction.DESC.name(), "date");
        
        Specification<Order> spec = new OrderPredicateBuilder().toSpec(of);
		PageRequest pageRequest = new PageRequest(pageNum, pageSize, of.buildSort());
        Page<Order> orders = orderDao.findAll(spec, pageRequest);
        
        List<Order> content = orders.getContent();
        content.stream().forEach(s->{
            System.out.println(s.getOrderNum() + "\t" + s.getDate());
            
        });
    }
    
    @Test
    public void test_save_cascadingly() throws Exception {
        Order order = new Order();
        order.setClientId("1");
        
        order.addOrderItem(new OrderItem());
        order.addOrderItem(new OrderItem());
        order.addOrderItem(new OrderItem());
        
        orderDao.save(order);
        
    }

}
