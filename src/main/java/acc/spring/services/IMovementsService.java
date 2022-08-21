package acc.spring.services;


import java.util.List;


import acc.spring.DTO.MovementDto;
import acc.spring.exceptions.NotFoundException;
import acc.spring.model.Movement;

public interface IMovementsService {
    public Movement createNewMovement(MovementDto movementDto) throws Exception;

    public List<Movement> getMovementsByAccount(Long accountId, MovementDto movementDto) throws Exception;

    public List<Movement> getAllMovements();

    public Movement updateMovement(MovementDto movementDto) throws Exception;

    public void deleteMovement(Long movementId) throws NotFoundException;
}
