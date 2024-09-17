package com.eazybytes.acounts.service;

import com.eazybytes.acounts.DTO.CustomerDTO;
import com.eazybytes.acounts.DTO.AccountsDTO;
import com.eazybytes.acounts.constants.AccountsConstants;
import com.eazybytes.acounts.entity.Account;
import com.eazybytes.acounts.entity.Customer;
import com.eazybytes.acounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.acounts.exception.ResponseNotFoundException;
import com.eazybytes.acounts.mapper.AccountsMapper;
import com.eazybytes.acounts.mapper.CustomerMapper;
import com.eazybytes.acounts.repository.AccountsRepository;
import com.eazybytes.acounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

/**
 * Service class for account operations.
 */
@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * Creates a new account for the given customer.
     *
     * @param customerDTO the customer data transfer object
     */
    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Optional<Customer> existingCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (existingCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with mobile number " + customerDTO.getMobileNumber() + " already exists.");
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    /**
     * Creates a new account entity for the given customer.
     *
     * @param customer the customer entity
     * @return the created account entity
     */
    private Account createNewAccount(Customer customer) {
        Account newAccount = new Account();

        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);

        newAccount.setCustomerId(customer.getCustomerId());
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        return newAccount;
    }


    /**
     * Fetches account details for the given mobile number.
     *
     * @param mobileNumber the mobile number
     * @return the customer data transfer object
     */
    @Override
    public CustomerDTO fetchAccountDetails(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResponseNotFoundException("Customer", "mobileNumber", mobileNumber)
                );
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResponseNotFoundException("Account", "customerId", customer.getCustomerId().toString())
                );
        CustomerDTO customerDTO = CustomerMapper.mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountDTO(account, new AccountsDTO()));
        return customerDTO;
    }
    @Override
     public Boolean updateAccount(CustomerDTO customerDTO) {
        Boolean isUpdated = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if(accountsDTO != null){
            Account account = accountsRepository.findById(accountsDTO.getAccountNumber())
                    .orElseThrow(
                            () -> new ResponseNotFoundException("Account", "accountNumber", accountsDTO.getAccountNumber().toString())
                    );
            account.setBranchAddress(accountsDTO.getBranchAddress());
            account.setAccountType(accountsDTO.getAccountType());
            accountsRepository.save(account);
            Customer customer = customerRepository.findById(account.getCustomerId()).orElseThrow(
                    () -> new ResponseNotFoundException("Customer", "customerId", account.getCustomerId().toString())
            );
            customer.setName(customerDTO.getName());
            customer.setEmail(customerDTO.getEmail());
            customer.setMobileNumber(customerDTO.getMobileNumber());
            customerRepository.save(customer);
            isUpdated = true;
        }
        return isUpdated;
    }

    /**
     * Deletes the account for the given mobile number.
     *
     * @param mobileNumber the mobile number
     * @return true if the account is deleted, false otherwise
     */
    @Override
    public Boolean deleteAccount(String mobileNumber) {
        Boolean isDeleted = false;
        Customer customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(
                        () -> new ResponseNotFoundException("Customer", "mobileNumber", mobileNumber)
                );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);
        isDeleted = true;
        return isDeleted;
    }
}
