package com.eazybytes.acounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "CustomerDTO", description = "Data transfer object for customer")
public class CustomerDTO {
    @Schema(description = "Name of the customer", example = "Jaspreet Singh")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max = 30, message = "Name should be between 5 and 30 characters")
    private String name;
    @Schema(description = "Email address of the customer", example = "jaspreet@email.com")
    @Email(message = "Email address should be valid")
    @NotEmpty(message = "Email should not be empty")
    private String email;
    @Schema(description = "Mobile number of the customer", example = "9876543210")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    private String mobileNumber;
    @Schema(description = "Accounts data transfer object", implementation = AccountsDTO.class)
    private AccountsDTO accountsDTO;
}
