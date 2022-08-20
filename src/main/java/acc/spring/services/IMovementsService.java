package acc.spring.services;

import acc.spring.DTO.MovementDto;
import acc.spring.model.Movement;

public interface IMovementsService {
    public Movement createNewMovement(MovementDto movementDto) throws Exception;

    public Movement getMovementsByAccount();

    public Movement getMovementByAccountAndDate();

    public Movement updatMovement(MovementDto movementDto);

    public void deleteMovement(Long movementId);
}
