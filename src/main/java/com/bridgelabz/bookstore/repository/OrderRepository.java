package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderData,Integer> {
}
