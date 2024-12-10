package net.javaguides.banking.Service.implementations;

import net.javaguides.banking.Dto.AccountDto;
import net.javaguides.banking.Entity.Account;
import net.javaguides.banking.Mapper.AccountMapper;
import net.javaguides.banking.Repository.AccountRepository;
import net.javaguides.banking.Service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImplementation implements AccountService {


    private AccountRepository accountRepository;

    public AccountServiceImplementation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
Account account =  AccountMapper.mapToAccount(accountDto);
Account savedAccount=accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public AccountDto getAccountById(long id) {
  Account account= accountRepository
          .findById(id)
          .orElseThrow(()-> new RuntimeException("Account Does not Exist"));

        return AccountMapper.mapToAccountDto(account);
    }

    @Override
    public AccountDto deposit(long id, double amount) {

        Account account= accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account Does not Exist"));

     double Total= account.getBalance()+amount;
     account.setBalance(Total);
     Account savedAccount=accountRepository.save(account);
     return AccountMapper.mapToAccountDto(savedAccount);

    }

    @Override
    public AccountDto withdraw(long id, double amount) {

        Account account= accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account Does not Exist"));

        if(account.getBalance()-amount<0){
            throw new RuntimeException("Insufficient Balance");
        }

        double Total= account.getBalance()-amount;
        account.setBalance(Total);
        Account savedAccount=accountRepository.save(account);

        return AccountMapper.mapToAccountDto(savedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> accounts=accountRepository.findAll();
        return  accounts.stream().map((account) ->AccountMapper
                .mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(long id) {

        Account account= accountRepository
                .findById(id)
                .orElseThrow(()-> new RuntimeException("Account Does not Exist"));

        accountRepository.deleteById(id);

    }
}
