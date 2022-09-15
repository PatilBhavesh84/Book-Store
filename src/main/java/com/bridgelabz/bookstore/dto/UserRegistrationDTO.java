package com.bridgelabz.bookstore.dto;

import lombok.Data;

import javax.validation.constraints.Pattern;
@Data
public class UserRegistrationDTO {
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}", message = "Invalid firstname")
    private String firstName;
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}", message = "Invalid lastname")
    private String lastName;
    private String email;
    private String address;
    private String password;

}