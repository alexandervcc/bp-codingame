package acc.spring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acc.spring.DTO.AccountDto;
import acc.spring.DTO.ResponseDto;
import acc.spring.exceptions.list.NotFoundException;
import acc.spring.model.Account;
import acc.spring.services.IAccountService;
import lombok.AllArgsConstructor;


import static acc.spring.constants.AppConstants.CONTENT_TYPE_APP_JSON;

@RestController
@RequestMapping(path = "api/cuentas/", produces = CONTENT_TYPE_APP_JSON)
@AllArgsConstructor
public class AccountController {
    private IAccountService accountService;

    @GetMapping()
    public ResponseEntity<ResponseDto> getAccountById(@RequestParam Long accountId) throws NotFoundException {
        Account account = accountService.getAccountById(accountId);
        ResponseDto response = new ResponseDto();
        response.data = account;
        response.title = "Cuenta encontrada.";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<ResponseDto> getAllAccount() {
        List<Account> listAccounts = accountService.getAllAcounts();
        ResponseDto response = new ResponseDto();
        response.dataList = listAccounts;
        response.title = "Cuentas encontrados";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping()
    public ResponseEntity<ResponseDto> createNewAccount(@RequestBody AccountDto accountDto)
            throws Exception {
        Account account = accountService.createNewAccount(accountDto);
        ResponseDto response = new ResponseDto();
        response.data = account;
        response.title = "Cuenta creada.";
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping()
    public ResponseEntity<ResponseDto> updateAccount(@RequestBody AccountDto accountDto, @RequestParam Long accountId)
            throws Exception {
        Account account = accountService.updateAccount(accountDto, accountId);
        ResponseDto response = new ResponseDto();
        response.data = account;
        response.title = "Cuenta actualizada.";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping()
    public ResponseEntity<ResponseDto> deleteAccount(@RequestParam Long accountId) throws NotFoundException {
        accountService.deleteAccountById(accountId);
        ResponseDto response = new ResponseDto();
        response.title = "Cuenta eliminada.";
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
