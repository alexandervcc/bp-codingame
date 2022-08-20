package acc.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acc.spring.DTO.AccountDto;
import acc.spring.exceptions.NotFoundException;
import acc.spring.model.Account;
import acc.spring.services.IAccountService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/cuentas/")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class AccountController {
    private IAccountService accountService;

    @GetMapping()
    public ResponseEntity<Account> getAccountById(@RequestParam Long accountId) throws NotFoundException {
        Account account = accountService.getAccountById(accountId);
        return ResponseEntity.status(HttpStatus.FOUND).body(account);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Account>> getAllAccount() {
        List<Account> listaCuentas = accountService.getAllAcounts();
        return ResponseEntity.status(HttpStatus.OK).body(listaCuentas);
    }

    @PostMapping()
    public ResponseEntity<Account> createNewAccount(@RequestBody AccountDto accountDto)
            throws Exception {
        Account account = accountService.createNewAccount(accountDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PutMapping()
    public ResponseEntity<Account> updateAccount(@RequestBody AccountDto accountDto, @RequestParam Long accountId)
            throws Exception {
        Account account = accountService.updateAccount(accountDto, accountId);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAccount(@RequestParam Long accountId) throws NotFoundException {
        accountService.deleteAccountById(accountId);
        return ResponseEntity.status(HttpStatus.OK).body("Cuenta eliminada");
    }
}
