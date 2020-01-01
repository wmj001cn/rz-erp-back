package com.ruiziot.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sqlite.ProductDeliveryMapper;
import com.sqlite.RZERPApplication;
import com.sqlite.dao.ProductDao;
import com.sqlite.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RZERPApplication.class, loader = SpringBootContextLoader.class)
public class ProductDaoTest {

	@Autowired
	private ProductDao id;

	
	@Autowired
	private ProductDeliveryMapper cm;
	
	
	@Test
	public void test_findAllByIssueDateBefore() throws Exception {
		Page<Product> products = id.findAllByNameContains("", null);
		for (Product product : products) {
			System.out.println(product.getName());
		}

	}
	
	@Test
	public void testName() throws Exception {
		List<Map> pr = cm.findProductClientInventoryInfoByOrderId("13");
		System.out.println(pr);
	}


}
