package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.LoginDTO;
import com.bridgelabz.bookstore.dto.UserRegistrationDTO;
import com.bridgelabz.bookstore.entity.UserRegistrationData;

import java.util.List;
import java.util.Optional;

public interface IUserRegistrationService {
    String createUser(UserRegistrationDTO userRegistrationDTO);

    List<UserRegistrationData> getAllUsers(String token);

    UserRegistrationData getUserById(String token);

    List<UserRegistrationData> getUserByEmail(String token);

    UserRegistrationData updateUser(String token, UserRegistrationDTO userRegistrationDTO);

    Optional<UserRegistrationData> login(LoginDTO loginDTO);
}