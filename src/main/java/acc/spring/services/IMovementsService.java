package acc.spring.services;


import java.util.List;

import org.springframework.core.io.FileSystemResource;

import acc.spring.DTO.MovementDto;
import acc.spring.DTO.ResListMovement;
import acc.spring.exceptions.NotFoundException;
import acc.spring.model.Movement;

public interface IMovementsService {
    public Movement createNewMovement(MovementDto movementDto) throws Exception;

    public ResListMovement getMovementsByClient(Long clientId, MovementDto movementDto) throws Exception;

    public List<Movement> getAllMovements();

    public FileSystemResource createMovementPDFReport(Long clientId, MovementDto movementDto) throws Exception ;

    public Movement updateMovement(MovementDto movementDto) throws Exception;

    public void deleteMovement(Long movementId) throws NotFoundException;
}
