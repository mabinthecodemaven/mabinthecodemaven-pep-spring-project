package com.example.service;
import com.example.entity.*;
import com.example.exception.AccountException;
import com.example.repository.AccountRepository;
import org.springframework.stereotype.Service;


@Service
public class AccountService {
    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    
    }

    public Account register(Account account){

        if(account.getUsername().isBlank()){
            throw new AccountException.InvalidAccountException("Username Blank");
        }
        if(account.getPassword().length() < 4){
            throw new AccountException.InvalidAccountException("Password under 4 characters");
        }

        Account existing = accountRepository.findByUsername(account.getUsername());
        if(existing != null){
            throw new AccountException.DuplicateUsernameException(account.getUsername());
        }

        return accountRepository.save(account);  
    }



}
