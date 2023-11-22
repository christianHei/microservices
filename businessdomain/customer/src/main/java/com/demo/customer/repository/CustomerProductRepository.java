package com.demo.customer.repository;

import com.demo.customer.entity.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProductRepository extends JpaRepository<CustomerProduct, Long> {
}