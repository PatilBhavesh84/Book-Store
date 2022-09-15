package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.BookData;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService iBookService;

    @Autowired
    private BookRepository bookRepository;


    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getAllBooks() {
        List<BookData> bookData = iBookService.getAllBooks();
        ResponseDTO responseDTO = new ResponseDTO("Get call Success", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("id")
    public <id> ResponseEntity<ResponseDTO> getBookById(@PathVariable int id) {
        Optional<BookData> bookData = iBookService.getBooksById(id);
        ResponseDTO responseDTO = new ResponseDTO("Get call Success for id successfull", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/name/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable String bookName) {
        List<BookData> bookData = bookRepository.findBookByName(bookName);
        ResponseDTO responseDTO = new ResponseDTO("Get call Success for bookname successfull", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/authorName/{authorName}")
    public ResponseEntity<ResponseDTO> getBookByAuthorName(@PathVariable String authorName) {
        List<BookData> bookData = iBookService.getBooksByAuthorName(authorName);
        ResponseDTO responseDTO = new ResponseDTO("Get call Success for authorname successfull", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addBooks(@Valid @RequestBody BookDTO bookDTO) {
        String bookData = iBookService.insert(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("created book data succesfully", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateBooksById(@PathVariable int id, @Valid @RequestBody BookDTO bookDTO) {
        BookData bookData = iBookService.updateBooksById(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("updated book data succesfully", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{token}/{quantity}")
    public ResponseEntity<ResponseDTO> updateBooksByQuantity(@PathVariable String token, @PathVariable int quantity) {
        BookData bookData = iBookService.updataBooksByQuantity(token, quantity);
        ResponseDTO responseDTO = new ResponseDTO("updated book data succesfully", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{token}")
    public ResponseEntity<ResponseDTO> deleteBookData(@PathVariable int id) {
        iBookService.deletebookData(id);
        ResponseDTO responseDTO = new ResponseDTO("deleted succesfully", id);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/ascsort")
    public ResponseEntity<ResponseDTO> sortBookDataAsc() {
        List<BookData> bookData = iBookService.sortBookDataAsc();
        ResponseDTO responseDTO = new ResponseDTO("Book data in ascending order:", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/dessort")
    public ResponseEntity<ResponseDTO> sortBookDataDesc() {
        List<BookData> bookData = iBookService.sortBookDataDesc();
        ResponseDTO responseDTO = new ResponseDTO("Book data in descending order:", bookData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}

