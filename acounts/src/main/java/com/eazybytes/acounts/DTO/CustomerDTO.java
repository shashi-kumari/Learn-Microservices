package com.eazybytes.acounts.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min=5, max = 30, message = "Name should be between 5 and 30 characters")
    private String name;
    @Email(message = "Email address should be valid")
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    private String mobileNumber;
    private AccountsDTO accountsDTO;
}
