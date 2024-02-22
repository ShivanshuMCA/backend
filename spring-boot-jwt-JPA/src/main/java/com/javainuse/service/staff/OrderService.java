package com.javainuse.service.staff;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javainuse.model.staff.Order;
import com.javainuse.repository.staff.OrderRepo;

@Service
public class OrderService {

	@Autowired
	private OrderRepo orederRepo;

	public List<Order> findAll() {
		return orederRepo.findAll();
	}

	public Order save(@Valid Order staff) {
		return orederRepo.save(staff);
	}

}
