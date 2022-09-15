package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.CartDTO;
import com.bridgelabz.bookstore.dto.ResponseDTO;
import com.bridgelabz.bookstore.entity.CartData;
import com.bridgelabz.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private ICartService iCartService;


    @GetMapping("/getall/{token}")
    public ResponseEntity<ResponseDTO> getAllCart(@PathVariable String token) {
        List<CartData> cartData = iCartService.getAllCart(token);
        ResponseDTO responseDTO = new ResponseDTO("Get call Success", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    @GetMapping("give/{token}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable String token) {
        CartData cartData = iCartService.getCartById(token);
        ResponseDTO responseDTO = new ResponseDTO("Get call Success for id successfull", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> addCart(@Valid @RequestBody CartDTO cartDTO) {
        String cartData = iCartService.insert(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("created cart data succesfully", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    @PutMapping("/update/{token}")
    public ResponseEntity<ResponseDTO> updateBooksById(@PathVariable String token, @Valid @RequestBody CartDTO cartDTO) {
        CartData cartData = iCartService.updateCartById(token, cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("updated cart data succesfully", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }


    @DeleteMapping("/cartdelete/{token}")
    public ResponseEntity<ResponseDTO> deleteCartData(@PathVariable String token) {
        iCartService.deleteCartData(token);
        ResponseDTO responseDTO = new ResponseDTO("deleted succesfully", token);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
