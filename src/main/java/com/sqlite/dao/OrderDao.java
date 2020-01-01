package com.sqlite.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sqlite.model.Order;

public interface OrderDao extends JpaRepository<Order, Integer> , JpaSpecificationExecutor<Order>{

	Page<Order> findAllByStatusIn(List<Integer> status, Pageable pageable);
	
    


  
	
	


}
