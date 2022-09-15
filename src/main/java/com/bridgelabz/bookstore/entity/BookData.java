package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Data
@Table(name="bookdata")

public class BookData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private int price;
    private int quantity;

    public BookData(int id, BookDTO bookDTO) {
        this.bookId=id;
        this.bookName=bookDTO.getBookName();
        this.authorName=bookDTO.getAuthorName();
        this.bookDescription=bookDTO.getBookDescription();
        this.bookImg=bookDTO.getBookImg();
        this.price=bookDTO.getPrice();
        this.quantity=bookDTO.getQuantity();
    }

    public BookData(BookDTO bookDTO) {
        this.bookName=bookDTO.getBookName();
        this.authorName=bookDTO.getAuthorName();
        this.bookDescription=bookDTO.getBookDescription();
        this.bookImg=bookDTO.getBookImg();
        this.price=bookDTO.getPrice();
        this.quantity=bookDTO.getQuantity();
    }

    public BookData() {

    }
}