package acc.spring.controller;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import acc.spring.DTO.MovementDto;
import acc.spring.DTO.ResListMovement;
import acc.spring.model.Movement;
import acc.spring.services.IMovementsService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/movimientos")
@AllArgsConstructor
public class MovementsController {
	private IMovementsService movementsService;

	@GetMapping(path = "/all")
	public ResponseEntity<Iterable<Movement>> getAllMovements() {
		Iterable<Movement> listaMovimientos = movementsService.getAllMovements();
		return ResponseEntity.status(HttpStatus.OK).body(listaMovimientos);
	}

	@GetMapping(path = "/")
	public ResponseEntity<?> getMovementsByAccount(
			@RequestParam Long accountId,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fechaInicio,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fechaFin)
			throws Exception {
		MovementDto movementDto = new MovementDto();
		if (fechaInicio != null)
			movementDto.fechaInicio = new Timestamp(fechaInicio.getTime());
		if (fechaFin != null)
			movementDto.fechaFin = new Timestamp(fechaFin.getTime());

		ResListMovement listMovements = movementsService.getMovementsByAccount(accountId, movementDto);

		return ResponseEntity.status(HttpStatus.OK).body(listMovements);
	}

	@PostMapping(path = "/")
	public ResponseEntity<Movement> createNewMovement(@RequestBody MovementDto movementDto) throws Exception {
		Movement newMovement = movementsService.createNewMovement(movementDto);
		return ResponseEntity.status(HttpStatus.OK).body(newMovement);
	}

	@PutMapping("/")
	public ResponseEntity<Movement> updateMovement(@RequestBody MovementDto movementDto)
			throws Exception {
		Movement movement = movementsService.updateMovement(movementDto);
		return ResponseEntity.status(HttpStatus.OK).body(movement);
	}

	@DeleteMapping("/")
	public ResponseEntity<String> deleteMovement(@RequestParam Long movementId) throws Exception {
		movementsService.deleteMovement(movementId);
		return ResponseEntity.status(HttpStatus.OK).body("Movimiento eliminado");
	}
}
