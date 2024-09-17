package com.eazybytes.acounts.mapper;

import com.eazybytes.acounts.DTO.AccountsDTO;
import com.eazybytes.acounts.entity.Account;

public class AccountsMapper {

    public static AccountsDTO mapToAccountDTO(Account account, AccountsDTO accountsDTO) {
        accountsDTO.setAccountNumber(account.getAccountNumber());
        accountsDTO.setAccountType(account.getAccountType());
        accountsDTO.setBranchAddress(account.getBranchAddress());
        return accountsDTO;
    }
    public static Account mapToAccount(AccountsDTO accountsDTO,Account account) {
        account.setAccountNumber(accountsDTO.getAccountNumber());
        account.setAccountType(accountsDTO.getAccountType());
        account.setBranchAddress(accountsDTO.getBranchAddress());
        return account;
    }


}
