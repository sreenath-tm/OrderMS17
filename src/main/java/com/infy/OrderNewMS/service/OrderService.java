package com.infy.OrderNewMS.service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.infy.OrderNewMS.dto.OrderDetailsDTO;
import com.infy.OrderNewMS.dto.ProductDTO;
import com.infy.OrderNewMS.dto.ProductsOrderedDTO;
import com.infy.OrderNewMS.entity.CompositeKey;
import com.infy.OrderNewMS.entity.OrderDetails;
import com.infy.OrderNewMS.entity.ProductsOrdered;
import com.infy.OrderNewMS.repository.OrderRepository;
import com.infy.OrderNewMS.repository.ProductsOrderedRepository;

@Service
public class OrderService {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	OrderRepository orderRepo;
	@Autowired
	ProductsOrderedRepository productsRepo;
	
	public void createOrder(OrderDetailsDTO orderDetailsDTO)
	{
		logger.info("Creation request for order {}", orderDetailsDTO);
		String regex = "^[A-Za-z0-9, .-;]{0,100}";
		if(orderDetailsDTO.getAddress().matches(regex)) {
		OrderDetails orderDetails = orderDetailsDTO.createEntity();
		List<ProductsOrderedDTO> dtos=orderDetailsDTO.getProductsOrdered();
		
		for(int i=0;i<dtos.size();i++) {
			this.addProductsOrdered(dtos.get(i));
			System.out.println(dtos.get(i).getQuantity());
		}
		orderRepo.save(orderDetails);
	}}
	public void addProductsOrdered(ProductsOrderedDTO productsOrderedDTO)
	{
		logger.info("Adding request for productsordered {}", productsOrderedDTO);
		int quantity=productsOrderedDTO.getQuantity();
		System.out.println(quantity);
		String getUrl="http://localhost:8300/products/"+productsOrderedDTO.getProdId();
		ProductDTO productdto=new RestTemplate().getForEntity(getUrl,ProductDTO.class).getBody();
		System.out.println(productdto.getStock());
		if((productdto.getStock()-quantity)>10){
			System.out.println(productsOrderedDTO.getProdId());
			
			
			String url = "http://localhost:8300//api/stock/"+productsOrderedDTO.getProdId()+"/"+quantity;
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.put(url,boolean.class);
			ProductsOrdered products = productsOrderedDTO.createEntity();
			productsRepo.save(products);
		}
		else {
			ResponseEntity<String> response = new ResponseEntity<String>("Insufficient Stock", HttpStatus.BAD_REQUEST);
		}
		
	
	}
	
	
	public OrderDetailsDTO getSpecificOrder(Integer orderid)
	{
		logger.info("Specific order details");
		OrderDetailsDTO orders = null; 
		Optional<OrderDetails> q = orderRepo.findById(orderid);
		if(q.isPresent())
		{	
			OrderDetails order = q.get();
			
			orders = OrderDetailsDTO.valueOf(order); 
			
			List<ProductsOrdered> products = productsRepo.findAll();
			List<ProductsOrderedDTO> productsDTO = new ArrayList<>();
			for(ProductsOrdered p : products)
			{	if(p.getOrderId()==orders.getOrderid()) {
				
				ProductsOrderedDTO productDTO = ProductsOrderedDTO.valueOf(p);
				productsDTO.add(productDTO);
				
				}
			}
			orders.setProductsOrdered(productsDTO);
			
		}
	    return orders; 	
	}
	public List<ProductsOrderedDTO> getAllProducts()
	{
		logger.info("All products details");
		
		//String url = "http://localhost:8200/products";
		//RestTemplate restTemplate = new RestTemplate();
		//ProductDTO[] productssDTO = restTemplate.getForObject(url, ProductDTO[].class);
		//System.out.println(productssDTO[0]);
		//System.out.println(productssDTO.length);
		//System.out.println(productssDTO[5].getBrand());
		
		
		
		List<ProductsOrdered> products = productsRepo.findAll();
		List<ProductsOrderedDTO> productsDTO = new ArrayList<>();
		for(ProductsOrdered p : products)
		{
			ProductsOrderedDTO productDTO = ProductsOrderedDTO.valueOf(p);
			productsDTO.add(productDTO);
		}
		return productsDTO;
	}
	public List<OrderDetailsDTO> getAllOrderDetails()
	{
		logger.info("All orders details");
		List<OrderDetails> orders = orderRepo.findAll();
		List<OrderDetailsDTO> orderDTO = new ArrayList<>();
		
		for(OrderDetails o : orders)
		{	
					
			OrderDetailsDTO allOrders = OrderDetailsDTO.valueOf(o);
			
			List<ProductsOrdered> products = productsRepo.findAll();
			List<ProductsOrderedDTO> productsDTO = new ArrayList<>();
			for(ProductsOrdered p : products)
			{	System.out.println("ORderId of prod:"+(p.getOrderId()+" "+o.getOrderId()));
			
				if((p.getOrderId()).equals(o.getOrderId())) {
				
				ProductsOrderedDTO productDTO = ProductsOrderedDTO.valueOf(p);
				productsDTO.add(productDTO);
				
				}
			}
			allOrders.setProductsOrdered(productsDTO);
			orderDTO.add(allOrders);
		}
		return orderDTO;
	}
	
	public ResponseEntity<String> updateStatusOfOrder(OrderDetailsDTO orderDTO)
	{
		ResponseEntity<String> response = null;
		Optional<OrderDetails> order = orderRepo.findById(orderDTO.getOrderid());
		List<ProductsOrdered> products = productsRepo.findAll();
		if(order.isPresent())
		{
			OrderDetails o = order.get();
			o.setStatus(orderDTO.getStatus());
			orderRepo.save(o);
			for(ProductsOrdered p:products)
			{
				if(p.getOrderId()==orderDTO.getOrderid())
				{
					p.setStatus(orderDTO.getStatus());
					productsRepo.save(p);
				}
			}
			String successMessage = "Order Status updated successfully";
			response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
		}
		else
		{
			String failureMessage = "Order not found for updation";
			response = new ResponseEntity<String>(failureMessage, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	public ResponseEntity<String> deleteSpecificOrder(Integer orderid)
	{
		logger.info("delete specific order");
		Optional<OrderDetails> order = orderRepo.findById(orderid);
		
		
		
		if(order.isPresent())
		{		String successMessage = "Order deleted succssfully";
				ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
					
				orderRepo.deleteById(orderid);
		
				List<ProductsOrdered> products = productsRepo.findAll();
				List<ProductsOrderedDTO> productsDTO = new ArrayList<>();
				for(ProductsOrdered p : products)
				{	if(p.getOrderId()==orderid) {
			
					ProductsOrderedDTO productDTO = ProductsOrderedDTO.valueOf(p);
					productsDTO.add(productDTO);
					
					CompositeKey comp = new CompositeKey();
					comp.setOrderId(orderid);
					comp.setProdId(p.getProdId());
					this.deleteOrderedProduct(comp);	
					}
				}
		
				return response;
		}else {
			
			String successMessage = "Order Not Present";
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.BAD_REQUEST);
			return response;
		}
	}
	//reorder
	 public void saveProduct(CompositeKey comp) 
	  {
		 Date date=new Date();
		 
		 logger.info("Creation request for customer {} with data {}");
		 Optional<OrderDetails> order=orderRepo.findById(comp.getOrderId());
		 Optional<ProductsOrdered> products=productsRepo.findById(comp);
		 if(order.isPresent()) {
			 
			 OrderDetails entity=order.get();
			 OrderDetailsDTO orderDTO=OrderDetailsDTO.valueOf(entity);
			 int orderIDTest=orderDTO.getOrderid();
			 int orderIDNew=this.OrderIDGenerator();
			 List<ProductsOrdered> productsTest = productsRepo.findAll();
			 
			 for(ProductsOrdered p:productsTest ) {
				 
				 ProductsOrderedDTO productsOrderedDTO=ProductsOrderedDTO.valueOf(p);
				 
				 if(productsOrderedDTO.getOrderId()==orderIDTest) {
					 productsOrderedDTO.setOrderId(orderIDNew);
					 ProductsOrdered productsNewAdd = productsOrderedDTO.createEntity();
					 productsRepo.save(productsNewAdd);
				 }
			 }
			 
			 if(comp.getOrderId()==orderDTO.getOrderid()) {
				 OrderDetails orderEntity=new OrderDetails();
				 orderEntity.setBuyerId(orderDTO.getBuyerid());
				 orderEntity.setOrderId(orderIDNew);
				 orderEntity.setDate(date);
				 orderEntity.setAddress(orderDTO.getAddress());
				 orderEntity.setAmount(orderDTO.getAmount());
				 orderEntity.setStatus("ORDER PLACED");
				 
				 orderRepo.save(orderEntity);
				 
				 OrderDetailsDTO reorderDTO=orderDTO.valueOf(orderEntity);
				 
			 }
		 }
		
		   	  
	  }
	 
	 public int OrderIDGenerator() {
		 List<OrderDetailsDTO> orders=this.getAllOrderDetails();
		 int orderID=orders.get(orders.size()-1).getOrderid()+1;
		 System.out.println(orderID);
		 return orderID;
	 }
	public ProductsOrderedDTO getSpecificProduct(CompositeKey comp)
	{
		logger.info("Specific product details");
		ProductsOrderedDTO products = null;
		Optional<ProductsOrdered> p = productsRepo.findById(comp);
		if(p.isPresent())
		{
			ProductsOrdered product = p.get();
			products = ProductsOrderedDTO.valueOf(product);
		
		}
		return products;
	}
	
	public ResponseEntity<String> deleteOrderedProduct(CompositeKey comp)
	{
		logger.info("Deleting request for specific product");
		
		ProductsOrdered products = productsRepo.findById(comp).orElse(null);
		if(products != null)
		{	String successMessage = "Subscribed Product deleted succssfully";
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.OK);
			productsRepo.deleteById(comp);
			return response;
		}
		else {
			String successMessage = "Subscribed Product not present ";
			ResponseEntity<String> response = new ResponseEntity<String>(successMessage, HttpStatus.BAD_REQUEST);
			return response;
		}
		
	}
}
