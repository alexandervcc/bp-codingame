package acc.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.DTO.AccountDto;
import acc.spring.model.Account;
import acc.spring.model.Client;
import acc.spring.repository.AccountRepository;
import acc.spring.repository.ClientRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService{
  private AccountRepository accountRepository;
  private ClientRepository clientRepository;

  @Override
  public Account createNewAccount(AccountDto accountDto) {
    Client client = clientRepository.findById(accountDto.clienteId).orElseThrow();
    Account newAccount = Account.builder()
      .numeroDeCuenta(null)
      .cliente(client)
      .estado(accountDto.estado)
      .saldoInicial(accountDto.saldoInicial)
      .tipoDeCuenta(accountDto.tipoDeCuenta)
      .build();
    return accountRepository.save(newAccount);
  }

  @Override
  public void deleteAccountById(Long accountId) {
      Account account = accountRepository.findById(accountId).orElseThrow();
      accountRepository.delete(account);
  }

  @Override
  public Account getAccountById(Long accountId) {
    return accountRepository.findById(accountId).orElseThrow();
  }

  @Override
  public List<Account> getAllAcounts() {
    return accountRepository.findAll();
  }

  @Override
  public Account updateAccount(AccountDto accountDto) {
    Account account = accountRepository.findById(accountDto.numeroDeCuenta).orElseThrow();
    if(accountDto.estado!=null)account.setEstado(accountDto.estado);
    if(accountDto.saldoInicial!=null)account.setSaldoInicial(accountDto.saldoInicial);
    if(accountDto.tipoDeCuenta!=null)account.setTipoDeCuenta(accountDto.tipoDeCuenta);
    return accountRepository.save(account);
  }



}
 