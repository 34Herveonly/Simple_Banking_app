package net.javaguides.banking.Service;

import net.javaguides.banking.Dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(long id);

    AccountDto deposit(long id,double amount);

    AccountDto withdraw(long id,double amount);

    List<AccountDto> getAllAccounts();

    void deleteAccount(long id);
}
