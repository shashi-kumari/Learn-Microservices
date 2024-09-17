package com.eazybytes.acounts.controller;

import com.eazybytes.acounts.DTO.CustomerDTO;
import com.eazybytes.acounts.DTO.ResponseDTO;
import com.eazybytes.acounts.constants.AccountsConstants;
import com.eazybytes.acounts.service.IAccountsService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;
    @PostMapping(value = "/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        iAccountsService.createAccount(customerDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }
    /**
     * Fetch account details based on mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return ResponseEntity containing CustomerDTO
     */
    @GetMapping(value = "/fetch")
    public ResponseEntity<CustomerDTO> fetchAccountDetails(@RequestParam String mobileNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(iAccountsService.fetchAccountDetails(mobileNumber));
    }

    /**
     * Update account details.
     *
     * @param customerDTO the customer data transfer object
     * @return ResponseEntity containing ResponseDTO
     */
    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateAccount(@Valid @RequestBody CustomerDTO customerDTO) {
        if (iAccountsService.updateAccount(customerDTO)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }

    /**
     * Delete account based on mobile number.
     *
     * @param mobileNumber the mobile number of the customer
     * @return ResponseEntity containing ResponseDTO
     */
    @DeleteMapping(value = "/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam String mobileNumber) {
        if (iAccountsService.deleteAccount(mobileNumber)) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new ResponseDTO(AccountsConstants.STATUS_500, AccountsConstants.MESSAGE_500));
        }
    }
}
