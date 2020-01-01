package com.sqlite.utils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.sqlite.model.Order;

public class OrderPredicateBuilder extends  BasePredicateBuilder<Order>{

    public OrderPredicateBuilder(CriteriaBuilder cb) {
        super(cb);
    }
    
    public OrderPredicateBuilder() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public BasePredicateBuilder<Order> getConditions(Root<Order> root, CriteriaBuilder cb,
            Object filter) {
        OrderFilter orderFilter = (OrderFilter)filter;
        
        String orderNum = orderFilter.getOrderNum();
		if(orderNum != null && !orderNum.isEmpty()) {
            this.likeOpenBothSides(root.get("orderNum"), orderNum);
        }
        
        if(!orderFilter.getStatus().isEmpty()) {
            this.in(root.get("status"), orderFilter.getStatus());
        }
        
        String dateStart = orderFilter.getDateStart();
        String dateEnd = orderFilter.getDateEnd();
		if(dateStart!= null || dateEnd != null) {
        	
			Predicate predictate = null;
			if (dateStart!= null && dateEnd != null) {
				predictate = criteriaBuilder.between(root.get("date"), dateStart, dateEnd);
			} else if (dateStart!= null){
	        	predictate = criteriaBuilder.lessThanOrEqualTo(root.get("date"), dateStart);
			} else if (dateEnd!= null){
	        	predictate = criteriaBuilder.lessThanOrEqualTo(root.get("date"), dateEnd);
			}
			
			predicateList.add(predictate);
			
            //this.(root.get("date"), orderFilter.getOrderNum());
        }
        
        return this;
    }
    

}
