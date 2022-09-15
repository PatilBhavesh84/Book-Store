package com.bridgelabz.bookstore.entity;

import com.bridgelabz.bookstore.dto.UserRegistrationDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name="userdata")

public class UserRegistrationData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;

    public UserRegistrationData(UserRegistrationDTO userRegistrationDTO) {
        this.firstName = userRegistrationDTO.getFirstName();
        this.lastName = userRegistrationDTO.getLastName();
        this.email = userRegistrationDTO.getEmail();
        this.address = userRegistrationDTO.getAddress();
        this.password = userRegistrationDTO.getPassword();
    }

    public UserRegistrationData(int id, UserRegistrationDTO userRegistrationDTO) {
        this.userId=id;
        this.firstName = userRegistrationDTO.getFirstName();
        this.lastName = userRegistrationDTO.getLastName();
        this.email = userRegistrationDTO.getEmail();
        this.address = userRegistrationDTO.getAddress();
        this.password = userRegistrationDTO.getPassword();

    }

    public UserRegistrationData() {

    }
}
