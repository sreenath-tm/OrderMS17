package com.infy.OrderNewMS.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.infy.OrderNewMS.dto.OrderDetailsDTO;
import com.infy.OrderNewMS.dto.ProductsOrderedDTO;
import com.infy.OrderNewMS.entity.CompositeKey;
import com.infy.OrderNewMS.service.OrderService;

@RestController
@CrossOrigin
@RequestMapping(value="/api")
public class OrderController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired 
	OrderService orderService;
	
	
	//Place a new order
	@PostMapping(value = "/placeOrder",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createOrder(@RequestBody OrderDetailsDTO orderDTO)
	{
		logger.info("Creation request for new Order {}", orderDTO);
		orderService.createOrder(orderDTO);
		return new ResponseEntity<String>("order addeed successfully!!", HttpStatus.OK);
	}
    //Place products ordered details
	@PostMapping(value = "/productsordered",  consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addProductsOrderedDetails(@RequestBody ProductsOrderedDTO productsOrderedDTO)
	{
		logger.info("Adding products ordered details {}", productsOrderedDTO);
		orderService.addProductsOrdered(productsOrderedDTO);
		return new ResponseEntity<String>("product order added successfully!!", HttpStatus.OK);

	}
	//get specific order
	@GetMapping(value = "/order/{orderid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public OrderDetailsDTO getSpecificOrder(@PathVariable Integer orderid) {
		logger.info("Fetching specific order details {}");
		return orderService.getSpecificOrder(orderid);		
	}
	//get all orders details
	@GetMapping(value = "/allorders", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<OrderDetailsDTO> getAllOrders()
	{
		logger.info("Fetching all orders details {}");
		return orderService.getAllOrderDetails();
	
	}
	
	//get all products details
	@GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductsOrderedDTO> getAllProducts()
	{
		logger.info("Fetching all products details {}");
		return orderService.getAllProducts();
	}
	//delete specific order
	@DeleteMapping(value = "/order/{orderid}")	
	public ResponseEntity<String> deleteSpecificOrder(@PathVariable Integer orderid)
	{
		logger.info(" Deleting specific order");
		ResponseEntity<String> response=orderService.deleteSpecificOrder(orderid);
		return response;

	}

	//change the status of the order
		@PutMapping(value="/change/status")
		public ResponseEntity<String> updateStatus(@RequestBody OrderDetailsDTO order)
		{
			ResponseEntity<String> str = orderService.updateStatusOfOrder(order);
			return str;
		}
		
	//get specific products ordered
	@GetMapping(value = "/products/{orderid}/{prodid}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductsOrderedDTO getSpecificProductDetails(@PathVariable Integer orderid, @PathVariable Integer prodid) {
		logger.info("Fetching specific product details {}");
		CompositeKey comp = new CompositeKey();
		comp.setOrderId(orderid);
		comp.setProdId(prodid);
		return orderService.getSpecificProduct(comp);
	}


	
	//delete specific product
	@DeleteMapping(value = "/order/{orderid}/{prodid}")	
	public ResponseEntity<String> deleteProducts(@PathVariable Integer orderid, @PathVariable Integer prodid)
	{
	
		logger.info("Deleting ordered products  ");
		CompositeKey comp = new CompositeKey();
		comp.setOrderId(orderid);
		comp.setProdId(prodid);
		ResponseEntity<String> response=orderService.deleteOrderedProduct(comp);
		return response;
	}
	//reorder
	@GetMapping(value = "/reorder/{orderId}/{prodId}")
	public ResponseEntity<String> saveFriend(@PathVariable int orderId,@PathVariable int prodId)
	 {	System.out.println("hi");
	 	
		CompositeKey comp = new CompositeKey();
		comp.setOrderId(orderId);
		comp.setProdId(prodId);
		
		orderService.saveProduct(comp);
		return new ResponseEntity<String>("Reorder Succesful", HttpStatus.OK);
	 }

}
