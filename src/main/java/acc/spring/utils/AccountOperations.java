package acc.spring.utils;

import acc.spring.DTO.AccountDto;
import acc.spring.exceptions.list.BlockedAccountException;
import acc.spring.exceptions.list.InvalidParameter;
import acc.spring.model.Account;

import static acc.spring.constants.AppConstants.*;

public class AccountOperations {
    public static void checkInvalidValuesForAccount(AccountDto accountDto) throws Exception {
        if (!ALLOWED_ACCOUNT_TYPES.contains(accountDto.tipoDeCuenta)) {
            throw new InvalidParameter("Tipo de cuenta invalida");
        }
        if (accountDto.saldoInicial < MINIMUN_ACCOUNT_INITIAL_VALUE) {
            throw new InvalidParameter("Valor invalido para saldo");
        }
    }

    public static void checkAccountStatus(Account account) throws BlockedAccountException{
        if (!account.getEstado()) {
			throw new BlockedAccountException("Cuenta esta inactiva");
		}
    }
}
