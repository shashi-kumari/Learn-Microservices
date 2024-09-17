package com.eazybytes.acounts.service;

import com.eazybytes.acounts.DTO.CustomerDTO;
import org.springframework.stereotype.Service;

/**
 * Service interface for account operations.
 */
public interface IAccountsService {

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDTO the customer data transfer object
     */
    void createAccount(CustomerDTO customerDTO);

    /**
     * Fetches account details for the given mobile number.
     *
     * @param mobileNumber the mobile number
     * @return the customer data transfer object
     */
    CustomerDTO fetchAccountDetails(String mobileNumber);

    Boolean updateAccount(CustomerDTO customerDTO);

    Boolean deleteAccount(String mobileNumber);
}
