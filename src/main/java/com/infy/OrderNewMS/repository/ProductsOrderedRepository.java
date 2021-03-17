package com.infy.OrderNewMS.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.OrderNewMS.entity.CompositeKey;
import com.infy.OrderNewMS.entity.ProductsOrdered;

@Repository
public interface ProductsOrderedRepository extends JpaRepository<ProductsOrdered, CompositeKey>{

}
