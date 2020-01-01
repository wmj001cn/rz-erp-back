package com.sqlite.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.sqlite.model.Invoice;

public interface InvoiceDao extends PagingAndSortingRepository<Invoice, Integer> {

	List<Invoice> findAllByIssueDateBefore(String string, Pageable pageable);
	List<Invoice> findAllByIssueDateAfter(String string, Pageable pageable);
	List<Invoice> findAllByIssueDate(String string, Pageable pageable);
	
}

