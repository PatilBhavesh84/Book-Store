package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.entity.BookData;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    String insert(BookDTO bookDTO);

    List<BookData> getAllBooks();

    Optional<BookData> getBooksById(int id);

    List<BookData> getBooksByName(String bookName);

    List<BookData> getBooksByAuthorName(String authorName);

    BookData updateBooksById(int id, BookDTO bookDTO);

    BookData updataBooksByQuantity(String token, int quantity);

    List<BookData> sortBookDataAsc();

    List<BookData> sortBookDataDesc();

    void deletebookData(int id);
}