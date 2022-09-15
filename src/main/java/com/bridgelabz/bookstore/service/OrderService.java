package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDTO;
import com.bridgelabz.bookstore.entity.BookData;
import com.bridgelabz.bookstore.entity.OrderData;
import com.bridgelabz.bookstore.entity.UserRegistrationData;
import com.bridgelabz.bookstore.exception.BookStoreException;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRegistrationRepository;
import com.bridgelabz.bookstore.util.EmailSenderService;
import com.bridgelabz.bookstore.util.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private TokenUtil util;
    @Autowired
    private EmailSenderService mailService;


    @Override
    public String insert(OrderDTO orderDTO) {
        Optional<BookData> book = bookRepository.findById(orderDTO.getBookId());
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(orderDTO.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (orderDTO.getQuantity() <= book.get().getQuantity()) {
                int quantity = book.get().getQuantity() - orderDTO.getQuantity();
                book.get().setQuantity(quantity);
                bookRepository.save(book.get());
                int totalPrice=book.get().getPrice()*orderDTO.getQuantity();
                OrderData newOrder = new OrderData(totalPrice, orderDTO.getQuantity(), orderDTO.getAddress(), book.get(), user.get(), orderDTO.isCancel());
                orderRepository.save(newOrder);
                String token = util.createToken(newOrder.getOrderId());
                mailService.sendEmail(newOrder.getUserRegistrationData().getEmail(), "Order Confirmed", "Order Created SuccessFully "
                        +newOrder.getOrderId()+ "Please Click here to get data-> ");
                log.info("Order record inserted successfully");
                return token;
            } else {
                throw new BookStoreException("Requested quantity is out of stock");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }


    @Override
    public List<OrderData> getAllOrder(String token) {
        Integer id=util.decodeToken(token);
        Optional<OrderData> orderData=orderRepository.findById(id);
        if(orderData.isPresent()) {
            List<OrderData> listOrderData=orderRepository.findAll();
            log.info("ALL order records retrieved successfully");
            mailService.sendEmail("bhaveshpatil8448@gmail.com", "Email", "Get your data with this token "
                    +orderData.get().getUserRegistrationData().getEmail()+"Please Click here to get all data-> "
                    +"http://localhost:8080/order/get/"+token);
            return listOrderData;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }


    @Override
    public OrderData getOrderById(String token) {
        Integer id=util.decodeToken(token);
        Optional<OrderData> orderData = orderRepository.findById(id);
        if (orderData.isPresent()) {
            log.info("Order record retrieved successfully for id " + id);
            mailService.sendEmail("bhaveshpatil8448@gmail.com", " Email", "Get your data with this token "
                    +orderData.get().getUserRegistrationData().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8080/order/get/"+token);
            return orderData.get();
        } else {
            throw new BookStoreException("Order doesn't exists");
        }
    }


    @Override
    public OrderData cancelOrderById(String token, int userId) {

        Integer id=util.decodeToken(token);
        Optional<OrderData> order = orderRepository.findById(id);
        Optional<UserRegistrationData> user = userRegistrationRepository.findById(userId);
        if (order.isPresent() && user.isPresent()) {
            order.get().setCancel(true);
            orderRepository.save(order.get());
            mailService.sendEmail(order.get().getUserRegistrationData().getEmail(), "Test Email", "canceled order SuccessFully "
                    +order.get().getOrderId()+"Please Click here to get data of updated id-> "
                    +"http://localhost:8080/order/cancel/"+token);
            return order.get();
        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }
}

