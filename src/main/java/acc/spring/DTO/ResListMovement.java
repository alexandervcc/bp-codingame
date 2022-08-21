package acc.spring.DTO;

import java.util.List;

import acc.spring.model.Account;
import acc.spring.model.Client;
import acc.spring.model.Movement;

public class ResListMovement {
    public Account cuenta;
    public Client cliente;
    public List<Movement> movimientos;
}
