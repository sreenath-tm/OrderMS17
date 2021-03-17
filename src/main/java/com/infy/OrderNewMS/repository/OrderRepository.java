package com.infy.OrderNewMS.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infy.OrderNewMS.entity.OrderDetails;

@Repository
public interface OrderRepository extends JpaRepository<OrderDetails, Integer> {

	

}
