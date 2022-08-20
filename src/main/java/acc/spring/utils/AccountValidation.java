package acc.spring.utils;

import acc.spring.DTO.AccountDto;
import acc.spring.exceptions.InvalidParameter;

import static acc.spring.constants.AppConstants.*;

public class AccountValidation {
    public static void checkInvalidValuesForAccount(AccountDto accountDto) throws Exception {
        if (!ALLOWED_ACCOUNT_TYPES.contains(accountDto.tipoDeCuenta)) {
            throw new InvalidParameter("Tipo de cuenta invalida");
        }
        if (accountDto.saldoInicial < MINIMUN_ACCOUNT_INITIAL_VALUE) {
            throw new InvalidParameter("Valor invalido para saldo");
        }
    }
}
