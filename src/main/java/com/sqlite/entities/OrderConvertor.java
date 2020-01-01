package com.sqlite.entities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.Gson;
import com.sqlite.model.Order;
import com.sqlite.model.OrderVo;

public class OrderConvertor{
	
	public static boolean isJavaLang(Class clz) {
	    return clz.getName().startsWith("java.lang");
	}
	
	public static Order toDo(OrderVo vo) {
		Order order = new Order();
		order.setTaxRate(vo.getTaxRate());
		order.setOrderNum(vo.getOrderNum());
		order.setSummary(vo.getSummary());
		order.setTotal(vo.getTotal());
		order.setDate(vo.getDate());
		order.setStatus(vo.getStatus());
		order.setClientId(vo.getClientId());
		
		java.util.Set<OrderItem> orderItems = new java.util.HashSet<>();
		vo.getOrderItems().stream().forEach(s->{
			OrderItem orderItem = new OrderItem();
			orderItem.setName(s.getName());
			orderItem.setQty(s.getQty());
			orderItem.setProductId(s.getProductId());
			orderItem.setOrder(order);
			orderItems.add(orderItem);
			//orderItem.setOrderId(s.getOrderId());
		});
		
		order.setOrderItems(orderItems);
		
		return order;
	}
	
	public Object toDo(Object vo, Class<?> b, String v) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		
		Object bstd = b.getConstructor().newInstance();
		Class<? extends Object> aclz = vo.getClass();
		Field[] fields = aclz.getDeclaredFields();
		String simpleName = b.getSimpleName();
		String instanceName = StringUtils.uncapitalize(simpleName);
		System.out.println("" + simpleName + " " + instanceName + " = new " + simpleName + "();");
		
		String mname = "vo";
		for (Field field : fields) {
			String name = field.getName();
			String capitalize = StringUtils.capitalize(name);
			if(true || isJavaLang(field.getType())) {
				
				String string = instanceName + ".set" + capitalize + "(" + v + ".get" + capitalize + "());";
				System.out.println(string);
				/*Method method = b.getMethod(string, field.getType());
				field.setAccessible(true);
				method.invoke(bstd, field.get(vo));*/
			} else {
				String substring = capitalize.substring(0, capitalize.length()-1);
				String string = instanceName + ".add" + substring + "(" + v + ".get" + capitalize + "());";
				System.out.println(string);
				//toDo(b, field.getType());
			}
		}
		
		return bstd;
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		OrderConvertor orderConvertor = new OrderConvertor();
		//OrderVo ov = new OrderVo();//new Gson().fromJson("{orderNum: \"test3423423423\", orderItems: [{name: \"英频杰阅读器\", price: 11400, qty: 4, productId: 26}]}", OrderVo.class);
		orderConvertor.toDo(new OrderItemVo(), OrderItem.class, "s");
	}
}
