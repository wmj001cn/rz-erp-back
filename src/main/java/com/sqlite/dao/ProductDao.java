package com.sqlite.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sqlite.model.Invoice;
import com.sqlite.model.Product;

public interface ProductDao extends PagingAndSortingRepository<Product, Integer> {

	Page<Product> findAllByNameContains(String name, Pageable pageable);

	List<Product> findAllByNameContaining(String name);
	
}

