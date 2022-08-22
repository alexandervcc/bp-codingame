package acc.spring.utils;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;

import acc.spring.DTO.MovementDto;
import acc.spring.exceptions.list.InvalidParameter;

public class MovementOperationsTests {
    MovementDto movementDto = new MovementDto();
    Long accountFunds;


    @Test
    void checkInvalidValueAccountForMovement(){
        movementDto.cuentaOrigen = null;
        Exception ex = assertThrows(InvalidParameter.class, () -> {
            MovementOperations.checkInvalidValuesForMovement(movementDto);
        });
        assertTrue(ex.getMessage().equals("Ingrese una cuenta de operacion"));
    }

    @Test
    void checkInvalidMovementTypeForMovement(){
        movementDto.cuentaOrigen = 1L;
        movementDto.tipoMovimiento = "";
        Exception ex = assertThrows(InvalidParameter.class, () -> {
            MovementOperations.checkInvalidValuesForMovement(movementDto);
        });
        assertTrue(ex.getMessage().equals("Tipo de movimiento invalido"));
    }

    
    @Test
    void checkInvalidValueForMovement(){
        movementDto.cuentaOrigen = 1L;
        movementDto.tipoMovimiento = "DEBITO";
        movementDto.valor = ThreadLocalRandom.current().nextLong(-9999,0);;
        Exception ex = assertThrows(InvalidParameter.class, () -> {
            MovementOperations.checkInvalidValuesForMovement(movementDto);
        });
        assertTrue(ex.getMessage().equals("Valor de movimiento invalido"));
    }

    
}
