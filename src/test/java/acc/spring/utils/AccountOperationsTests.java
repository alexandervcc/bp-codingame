package acc.spring.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import acc.spring.DTO.AccountDto;
import acc.spring.exceptions.BlockedAccountException;
import acc.spring.exceptions.InvalidParameter;
import acc.spring.model.Account;

public class AccountOperationsTests {
    AccountDto accountDto = new AccountDto();
    Account account = new Account();

    @Test
    void checkInvalidAccountData() {
        accountDto.tipoDeCuenta = "AHORROSS";
        Exception ex = assertThrows(InvalidParameter.class, () -> {
            AccountOperations.checkInvalidValuesForAccount(accountDto);
        });
        assertTrue(ex.getMessage().equals("Tipo de cuenta invalida"));
    }

    @Test
    void checkInvalidFundsData() {
        accountDto.tipoDeCuenta = "AHORROS";
        accountDto.saldoInicial = -1L;

        Exception ex = assertThrows(InvalidParameter.class, () -> {
            AccountOperations.checkInvalidValuesForAccount(accountDto);
        });
        assertTrue(ex.getMessage().equals("Valor invalido para saldo"));
    }


    @Test
    void checkBlockedAccountData() {
        account.setEstado(false);

        Exception ex = assertThrows(BlockedAccountException.class, () -> {
            AccountOperations.checkAccountStatus(account);
        });
        assertTrue(ex.getMessage().equals("Cuenta esta inactiva"));
    }

}
