package com.oop.websignup.POS.dto;

import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Data
public class UserDTO {
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 40, message = "Name must be between 2 and 40 characters")
    private String name;

    @NotBlank(message = "Contact number is mandatory")
    @Size(min = 5, max = 11, message = "Contact number must be between 5 and 11 characters")
    private String contactNumber;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
