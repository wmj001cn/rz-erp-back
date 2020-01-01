package com.sqlite.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sqlite.dao.ClientDao;
import com.sqlite.dao.InvoiceDao;
import com.sqlite.dao.OrderDao;
import com.sqlite.dao.OrderItemDao;
import com.sqlite.dao.ProductDao;
import com.sqlite.entities.OrderConvertor;
import com.sqlite.entities.OrderItem;
import com.sqlite.entities.Result;
import com.sqlite.model.Order;
import com.sqlite.model.OrderVo;
import com.sqlite.model.Product;
import com.sqlite.utils.JavaBeanCopier;
import com.sqlite.utils.OrderFilter;
import com.sqlite.utils.OrderPredicateBuilder;

@CrossOrigin(origins = "*")
@RestController("/api")
public class ApiController {

	@Autowired
	private InvoiceDao id;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderItemDao oid;

	@GetMapping("/echo")
	public String echo(String msg) {
		System.out.println(msg);
		return msg;
	}
	
	@PostMapping("/orders")
	public Page<Order> pageOrdersFiltered(@RequestBody OrderFilter of) {
		 
		Specification<Order> spec = new OrderPredicateBuilder().toSpec(of);
		PageRequest pageRequest = new PageRequest(of.getPageNum(), of.getPageSize(), of.buildSort());
		Page<Order> orders = orderDao.findAll(spec, pageRequest);
		
		return orders;
	}

/*	@GetMapping("/orders")
	public Page<Order> retrieveOrders(@RequestParam(required = false) Integer page,
			@RequestParam(required = false) Integer size,
			@RequestParam(required = false) String searchName,
			@RequestParam(required = false) List<Integer> status)
			throws Exception {

		if (page > 0) {
			page = page - 1;
		}
		Pageable pageable = new PageRequest(page, size, new Sort(
				Sort.Direction.DESC, "date"));
		Page<Order> invoices = pd.findAllByStatusIn(status, pageable);

		invoices.getContent().stream().forEach((it) -> {
			System.out.println(it.getId());
		});
		
		
		
		
		OrderFilter of = new OrderFilter();
		//of.setOrderNum("153082");
		of.setPageNum(0);
		of.setPageSize(20);
		of.sort(Direction.ASC.name(), "date");
		
		Page<Order> orders = null;
		try {
			
			Specification<Order> spec = new OrderPredicateBuilder().toSpec(of);
			PageRequest pageRequest = new PageRequest(of.getPageNum(), of.getPageSize(), of.buildSort());
			orders = pd.findAll(spec, pageRequest);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return orders;
	}*/
	
	@PostMapping("/order")
	public Result saveOrder(@RequestBody OrderVo example) {

		Result result = new Result();
		Order od = OrderConvertor.toDo(example);
		
		od.setDate(new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		od.setStatus(100);
		
		orderDao.save(od);
		
		return result;
	}
	
	@PostMapping("/order/{id}")
	public Result replaceOrder(@RequestBody Order example) {

		Result result = new Result();
		
		try {
			int orderId = example.getId();
			Order oder = orderDao.findOne(orderId);
			if(oder != null) {
				oder.setStatus(example.getStatus());
				orderDao.save(oder);
			} else {
				result.setResultCode(500);
				result.setMessage("No such order with id - " + orderId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	@GetMapping("/orderItems")
	public List<OrderItem> products(

	@RequestParam(required = false) String orderId) throws Exception {
		List<OrderItem> invoices = null;
		try {

			invoices = oid.findByOrderId(orderId);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return invoices;
	}
	

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ProductDao productDao;
    @GetMapping("/clients")
    public List<Client> retrieveClients(@RequestParam String name) {
        return clientDao.findAllByNameContaining(name);
    }
    
    @GetMapping("/products")
    public List<Product> retrieveProducts(@RequestParam String name) {
        return productDao.findAllByNameContaining(name);
    }


}
