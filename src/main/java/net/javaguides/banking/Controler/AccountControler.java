package net.javaguides.banking.Controler;

import net.javaguides.banking.Dto.AccountDto;
import net.javaguides.banking.Entity.Account;
import net.javaguides.banking.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accounts")
public class AccountControler  {

    public AccountService accountService;

    public AccountControler(AccountService accountService) {
        this.accountService = accountService;
    }
        //Add Account Rest API

@PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto) {

        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }

    // Get Account Rest API
@GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return  ResponseEntity.ok(accountDto);
    }

    //Deposit Rest API
@PutMapping("/{id}/deposit")
    public ResponseEntity<AccountDto> getAccounts(@PathVariable long id,
                                                 @RequestBody Map<String,Double> request) {
        accountService.deposit(id, request.get("amount"));
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    //Withdraw Rest Api
    @PutMapping("/{id}/withdraw")
    public ResponseEntity<AccountDto> withdraw(@PathVariable long id,
                                               @RequestBody Map<String,Double> request) {
        double amount=request.get("amount");
        AccountDto accountDto = accountService.withdraw(id,amount);
        return ResponseEntity.ok(accountDto);

    }

    //Get All Accounts Rest API
    @GetMapping
public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
}

// Delete Account Rest API
 @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted Successfully");

    }


}

