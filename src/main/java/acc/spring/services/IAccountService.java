package acc.spring.services;

import java.util.List;

import acc.spring.DTO.AccountDto;
import acc.spring.exceptions.NotFoundException;
import acc.spring.model.Account;

public interface IAccountService {
    public Account createNewAccount(AccountDto accountDto) throws Exception;

    public Account updateAccount(AccountDto accountDto, Long accountId) throws Exception;

    public Account getAccountById(Long accountId) throws NotFoundException;

    public List<Account> getAllAcounts();

    public void deleteAccountById(Long accountId) throws NotFoundException;
}
