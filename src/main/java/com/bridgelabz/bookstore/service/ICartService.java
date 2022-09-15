package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.entity.CartData;

import java.util.List;

public interface ICartService {
    String insert(CartDTO cartDTO);
    List<CartData> getAllCart(String token);
    CartData getCartById(String token);
    CartData updateCartById(String token,CartDTO cartDTO);
    void deleteCartData(String token);
}

