package com.eazybytes.acounts.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "AccountsDTO", description = "Data transfer object for accounts")
public class AccountsDTO {
    @Schema(description = "Account number of the customer", example = "1234567890")
    @NotEmpty(message = "Account number should not be empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be 10 digits")
    private Long accountNumber;

    @Schema(description = "Type of the account", example = "Savings")
    @NotEmpty(message = "Account type should not be empty")
    private String accountType;

    @Schema(description = "Branch address of the account", example = "123 Main St, Springfield")
    @NotEmpty(message = "Branch address should not be empty")
    private String branchAddress;
}

