package acc.spring.services;


import org.springframework.core.io.FileSystemResource;

import acc.spring.DTO.MovementDto;
import acc.spring.DTO.ResListMovement;
import acc.spring.DTO.ResMovementDto;
import acc.spring.exceptions.list.NotFoundException;
import acc.spring.model.Movement;

public interface IMovementsService {
    public ResMovementDto createNewMovement(MovementDto movementDto) throws Exception;

    public ResListMovement getMovementsByClient(Long clientId, MovementDto movementDto) throws Exception;

    public ResListMovement getAllMovements();

    public FileSystemResource createMovementPDFReport(Long clientId, MovementDto movementDto) throws Exception ;

    public Movement updateMovement(MovementDto movementDto) throws Exception;

    public void deleteMovement(Long movementId) throws NotFoundException;
}
