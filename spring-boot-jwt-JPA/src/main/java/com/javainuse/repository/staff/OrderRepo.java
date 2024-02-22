package com.javainuse.repository.staff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.javainuse.model.staff.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

}
