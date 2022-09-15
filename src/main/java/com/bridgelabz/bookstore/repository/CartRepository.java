package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.CartData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartData, Integer> {
    }
