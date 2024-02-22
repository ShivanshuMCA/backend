package com.javainuse.controller.staff;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javainuse.model.staff.Order;
import com.javainuse.service.staff.OrderService;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/all")
	public List<Order> getAllStaff() {

		return orderService.findAll();
	}
	
	@PostMapping("add")
	public ResponseEntity<?> saveStaff(@Valid @RequestBody Order staff) throws Exception {
		Order SavedStaff = null;
		if(staff.getId() != null && staff.getId() > 0 ) {
			//staff.setDate(new Date());
		} else {
			staff.setDate(new Date());
		}

		SavedStaff = orderService.save(staff);
		return ResponseEntity.ok(SavedStaff);
	}
}
