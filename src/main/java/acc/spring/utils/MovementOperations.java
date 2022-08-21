package acc.spring.utils;

import acc.spring.DTO.MovementDto;
import acc.spring.exceptions.InsufficientFundsException;
import acc.spring.exceptions.InvalidParameter;

import static acc.spring.constants.AppConstants.*;

public class MovementOperations {
    public static void checkInvalidValuesForMovement(MovementDto movementDto) throws Exception {
        if (movementDto.cuentaOrigen == null) {
            throw new InvalidParameter("Ingrese una cuenta de operacion");
        }
        if (!ALLOWED_MOVEMENT_TYPES.contains(movementDto.tipoMovimiento)) {
            throw new InvalidParameter("Tipo de movimiento invalido");
        }
        if (movementDto.valor == null || movementDto.valor < MINIMUN_MOVEMENT_VALUE) {
            throw new InvalidParameter("Valor de movimiento invalido");
        }
    }

    public static Long calculateNewAccountFunds(MovementDto movementDto, Long accountFunds) throws Exception {
        Long newOriginFunds;
        if (movementDto.tipoMovimiento.equals("DEBITO")) {
            if (accountFunds < movementDto.valor) {
                throw new InsufficientFundsException("Saldo no disponible");
            }
            newOriginFunds = accountFunds - movementDto.valor;
        } else {
            newOriginFunds = accountFunds + movementDto.valor;
        }
        return newOriginFunds;
    }
}