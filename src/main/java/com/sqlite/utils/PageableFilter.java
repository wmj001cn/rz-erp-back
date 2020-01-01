package com.sqlite.utils;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import java.io.Serializable; 
public class PageableFilter implements Serializable{
	
	protected int pageSize;
	protected int pageNum;
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		
		
		this.pageSize = pageSize;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	
	public void setPageNum(int pageNum) {
		
		if (pageNum > 0) {
			pageNum = pageNum - 1;
		}
		
		this.pageNum = pageNum;
	}
	
	public void sort(String dir, String field) {
		SortSpec sortSpec = new SortSpec();
		sortSpec.setDirection(dir);
		sortSpec.setField(field);
		sortSpecs.add(sortSpec);
	}
	
	java.util.List<SortSpec> sortSpecs = new java.util.ArrayList<SortSpec>();
	
	public java.util.List<SortSpec> getSortSpecs() {
		return sortSpecs;
	}
	public void setSortSpecs(java.util.List<SortSpec> sortSpecs) {
		this.sortSpecs = sortSpecs;
	}
	public Sort buildSort(){
		
		
		java.util.List<Sort.Order> orders = new java.util.ArrayList<>();
		if(sortSpecs.isEmpty()) {
			return null;
		}
		
		for (SortSpec entry: sortSpecs) {
			orders.add(new Sort.Order(Direction.valueOf(entry.getDirection()), entry.getField()));
		}
		
		Sort sort = new Sort(orders);
		return sort;
	}
}
