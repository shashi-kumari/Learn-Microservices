package com.eazybytes.acounts.controller;

import com.eazybytes.acounts.DTO.CustomerDTO;
import com.eazybytes.acounts.DTO.ErrorResponseDTO;
import com.eazybytes.acounts.DTO.ResponseDTO;
import com.eazybytes.acounts.constants.AccountsConstants;
import com.eazybytes.acounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Accounts Management API", description = "This APIs are used to perform CRUD operations on customer accounts")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    /**
     * Create a new account.
     *
     * @param customerDTO the customer data transfer object
     * @return ResponseEntity containing ResponseDTO
     */
    @ApiResponse(responseCode = "201", description = "HTTP status code created")
    @Operation(summary = "Rest API for creating a new account", description = "This API is used to create a new account for a customer")
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    /**
     * Fetch account details based on mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return ResponseEntity containing CustomerDTO
     */
    @ApiResponse(responseCode = "200", description = "HTTP status code OK")
    @Operation(summary = "Rest API for fetching account details", description = "This API is used to fetch account details based on the customer's mobile number")
    @GetMapping(value = "/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
        return ResponseEntity.status(HttpStatus.OK).body(iAccountsService.fetchAccountDetails(mobileNumber));
    }

    /**
     * Update account details.
     *
     * @param customerDTO the customer data transfer object
     * @return ResponseEntity containing ResponseDTO
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "HTTP status code OK"), @ApiResponse(responseCode = "417", description = "HTTP status code Update Operation Failed", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @Operation(summary = "Rest API for updating account details", description = "This API is used to update account details based on the customer's mobile number")
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        if (iAccountsService.updateAccount(customerDTO)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    /**
     * Delete account based on mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return ResponseEntity containing ResponseDTO
     */
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "HTTP status code OK"), @ApiResponse(responseCode = "417", description = "HTTP status code Delete Operation Failed", content = @Content(schema = @Schema(implementation = ErrorResponseDTO.class)))})
    @Operation(summary = "Rest API for deleting an account", description = "This API is used to delete an account based on the customer's mobile number")
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits") String mobileNumber) {
        if (iAccountsService.deleteAccount(mobileNumber)) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDTO(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }
}
