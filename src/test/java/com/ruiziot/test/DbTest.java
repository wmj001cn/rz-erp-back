package com.ruiziot.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sqlite.RZERPApplication;
import com.sqlite.dao.InvoiceDao;
import com.sqlite.model.Invoice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RZERPApplication.class, loader = SpringBootContextLoader.class)
public class DbTest {

	@Autowired
	private InvoiceDao id;

	public static void main(String[] args) {
		System.out.println("ok");
	}

	@Test
	public void test_findAllByIssueDateBefore() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = "2019-03-08";
		Date date = sdf.parse(dateInString);;
		List<Invoice> ll = id.findAllByIssueDateBefore("2019-03-18", null);
		System.out.println(ll);

	}

	@Test
	public void testName() throws Exception {
		Invoice findOne = id.findOne(1);
		System.out.println(findOne.getNumber());

	}

}
