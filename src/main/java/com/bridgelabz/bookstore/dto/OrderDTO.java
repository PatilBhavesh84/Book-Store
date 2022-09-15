package com.bridgelabz.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
@Data
public class OrderDTO {
    private int quantity;
    @NotEmpty(message="address should not be empty")
    private String address;
    private int userId;
    private int bookId;
    private boolean cancel;
    private int price;
}
