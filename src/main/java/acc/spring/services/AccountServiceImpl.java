package acc.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.DTO.AccountDto;
import acc.spring.exceptions.NotFoundException;
import acc.spring.model.Account;
import acc.spring.model.Client;
import acc.spring.repository.AccountRepository;
import acc.spring.repository.ClientRepository;
import acc.spring.utils.AccountValidation;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
  private AccountRepository accountRepository;
  private ClientRepository clientRepository;

  @Override
  public List<Account> getAllAcounts() {
    return accountRepository.findAll();
  }

  @Override
  public Account createNewAccount(AccountDto accountDto) throws Exception {

    AccountValidation.checkInvalidValuesForAccount(accountDto);

    Client client = clientRepository.findById(accountDto.clienteId)
        .orElseThrow(() -> new NotFoundException("Cliente no encontrado"));

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
  public Account getAccountById(Long accountId) throws NotFoundException {
    return accountRepository.findById(accountId).orElseThrow(() -> new NotFoundException("Cuenta no Encontrada"));
  }

  @Override
  public Account updateAccount(AccountDto accountDto, Long accountId) throws Exception {
    AccountValidation.checkInvalidValuesForAccount(accountDto);

    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new NotFoundException("Cuenta no Encontrada"));

    if (accountDto.estado != null)
      account.setEstado(accountDto.estado);
    if (accountDto.saldoInicial != null)
      account.setSaldoInicial(accountDto.saldoInicial);
    if (accountDto.tipoDeCuenta != null)
      account.setTipoDeCuenta(accountDto.tipoDeCuenta);

    return accountRepository.save(account);
  }

  @Override
  public void deleteAccountById(Long accountId) throws NotFoundException {
    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new NotFoundException("Cuenta no Encontrada"));
        
    accountRepository.delete(account);
  }

}
