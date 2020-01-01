package com.sqlite.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.sql.ForUpdateFragment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqlite.ProductDeliveryMapper;
import com.sqlite.dao.InvoiceDao;
import com.sqlite.dao.ProductDao;
import com.sqlite.model.Invoice;
import com.sqlite.model.Product;


@CrossOrigin(origins="*")
@RestController
public class InvoiceController {
	
	@Autowired
	private InvoiceDao id;
	@Autowired
	private ProductDao pd;
	
	@Autowired
	private ProductDeliveryMapper cm;
	
   /* @GetMapping("/products")
    public Page<Product> products(
    		@RequestParam(required=false) Integer start, 
    		@RequestParam(required=false) Integer size) throws Exception {
        System.out.println(start);
        System.out.println(size);
        
        Pageable pr = new PageRequest(start, size);
    	Page<Product> invoices = pd.findAllByNameContains("", pr);
    	return invoices;
    }*/
    @GetMapping("/deliveryItem")
    public Map deliveryItem(String orderId, String clientId) throws Exception {
    	List<Map> pr = cm.findProductClientInventoryInfoByOrderId(orderId);
    	Map di = cm.findDeliveryInfoByClientId(clientId, "CONSIGNER");
    	
    	Map resl = new HashMap<>();
    	Map resl2 =  null;
    	for (Map map : pr) {
			Object object = map.get("type");
			Object object2 = map.get("inventoryCode");
			if(object != null && (Integer)object == 2) {
			    if(resl2 == null) {
					resl2 = new HashMap<>();
				}
			    
				Integer productId = (Integer) map.get("id");
				List<Map> associateProducts = cm.findAllAssociateProduct(orderId, productId);
				resl2.put(object2, associateProducts);
			} else {
				if(resl2 == null) {
					resl2 = new HashMap<>();
				}
				
				
				
				Map resl3 = new HashMap<>();
				resl3.put("name", map.get("description"));
				resl3.put("totalQty", map.get("qty"));
				resl3.put("unit", map.get("unit"));
				
				
				if(pr.size() > 1){
					object2 = "SEP";
					
					List<Map> plist = (List<Map>) resl2.get(object2);
					
					if(plist != null) {
						plist.add(resl3);
					} else {
						List<Map> ll = new ArrayList<>();
						ll.add(resl3);
						resl2.put(object2, ll);
					}
					
				}else {
					List<Map> ll = new ArrayList<>();
					ll.add(resl3);
					resl2.put(object2, ll);
				}
			}
		}
    	
    	
    	resl.put("deliveryProducts", pr);
    	resl.put("deliveryInfo", di);
    	resl.put("associateProducts", resl2);
    	
    	return resl;
    }
    
    @GetMapping("/invoiceItem")
    public Map invoiceItem(String orderId, String clientId) throws Exception {
    	List<Map> pr = cm.findInvoiceItemByOrderId(orderId);
    	Map di = cm.findDeliveryInfoByClientId(clientId, "INVOICE");
    	
    	Map resl = new HashMap<>();

    	
    	
    	
    	resl.put("deliveryProducts", pr);
    	resl.put("deliveryInfo", di);
    
    	
    	return resl;
    }
    
    @GetMapping("/invoices")
    public Page<Invoice> invoices(
    		@RequestParam(required=false) Integer start, 
    		@RequestParam(required=false) Integer size) throws Exception {
    	System.out.println(start);
    	System.out.println(size);
    	
    	PageRequest pr = new PageRequest(0, 1);
    	Page<Invoice> invoices = (Page<Invoice>) id.findAll(pr);
    	return invoices;
    }
    
    @PostMapping("/invoices/searches")
    public List<Invoice> searches(@RequestBody Map<String, Object> searches) throws Exception {
    	Map<String, Object> issueDate = (Map<String, Object>) searches.get("issueDate");
    	String issueDateV = (String) issueDate.get("value");
    	String operator = (String) issueDate.get("operator");
    	
    	
    	List<Invoice> invoices = new ArrayList<Invoice>();
    			
    	PageRequest pr = new PageRequest(0, 1);
    	if("lt".equals(operator)){
    		invoices = (List<Invoice>) id.findAllByIssueDateBefore(issueDateV, pr);
    	}
    	if("gt".equals(operator)){
    		invoices = (List<Invoice>) id.findAllByIssueDateAfter(issueDateV, pr);
    	}
    	if("eq".equals(operator)){
    		invoices = (List<Invoice>) id.findAllByIssueDate(issueDateV, pr);
    	}
    	
    	
    	return invoices;
    }
    
    

}

