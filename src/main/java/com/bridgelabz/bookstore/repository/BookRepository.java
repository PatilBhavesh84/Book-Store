package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.entity.BookData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface BookRepository extends JpaRepository<BookData,Integer> {
    @Query(value = "select * from book_data where book_name= :bookName",nativeQuery=true)
    List<BookData> findBookByName(String bookName);

    @Query(value = "select * from book_data where author_name=author_name",nativeQuery=true)
    List<BookData> findBookByAuthorName(String authorName);
    @Query(value = "select * from book_data ORDER BY price ASC",nativeQuery=true)
    List<BookData> sortBookDataAsc();
    @Query(value = "select * from book_data ORDER BY price DESC ",nativeQuery=true)
    List<BookData> sortBookDataDesc();
}
