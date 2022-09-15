package com.bridgelabz.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cartdata")
public class CartData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserRegistrationData userRegistrationData;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private BookData bookData;
    private int quantity;

    public CartData() {
    }

    public CartData(int cartId,UserRegistrationData userRegistrationData, BookData bookData, int quantity) {
        this.cartId=cartId;
        this.userRegistrationData = userRegistrationData;
        this.bookData = bookData;
        this.quantity = quantity;
    }

    public CartData(UserRegistrationData userRegistrationData, BookData bookData, int quantity) {
        this.userRegistrationData = userRegistrationData;
        this.bookData = bookData;
        this.quantity = quantity;
    }
}