package acc.spring.services;

import java.util.List;

import acc.spring.DTO.AccountDto;
import acc.spring.model.Account;
public interface IAccountService {
    public Account createNewAccount(AccountDto accountDto);
    public Account updateAccount(AccountDto accountDto);
    public Account getAccountById(Long accountId);
    public List<Account> getAllAcounts();
    public void deleteAccountById(Long accountId);
}
   