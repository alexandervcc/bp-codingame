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
import acc.spring.DTO.ResMovementDto;
import acc.spring.DTO.ResponseDto;
import acc.spring.model.Movement;
import acc.spring.services.IMovementsService;
import lombok.AllArgsConstructor;

import static acc.spring.constants.AppConstants.CONTENT_TYPE_APP_JSON;

@RestController
@RequestMapping(path = "api/movimientos", produces = CONTENT_TYPE_APP_JSON)
@AllArgsConstructor
public class MovementsController {
	private IMovementsService movementsService;

	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto> getAllMovements() {
		ResListMovement listaMovimientos = movementsService.getAllMovements();
		ResponseDto response = new ResponseDto();
		response.data = listaMovimientos;
		response.title = "Todos los movimientos";
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(path = "/")
	public ResponseEntity<ResponseDto> getMovementsByAccount(
			@RequestParam String clientName,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fechaInicio,
			@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") Date fechaFin)
			throws Exception {
		MovementDto movementDto = new MovementDto();
		if (fechaInicio != null)
			movementDto.fechaInicio = new Timestamp(fechaInicio.getTime());
		if (fechaFin != null)
			movementDto.fechaFin = new Timestamp(fechaFin.getTime());

		ResListMovement listMovements = movementsService.getMovementsByClient(clientName, movementDto);
		ResponseDto response = new ResponseDto();
		response.data = listMovements;
		response.title = "Movimientos Encontrados.";
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(path = "/")
	public ResponseEntity<ResponseDto> createNewMovement(@RequestBody MovementDto movementDto) throws Exception {
		ResMovementDto newMovement = movementsService.createNewMovement(movementDto);
		ResponseDto response = new ResponseDto();
		response.title = "Movimiento Creado.";
		response.data = newMovement;
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateMovement(@RequestBody MovementDto movementDto)
			throws Exception {
		Movement movement = movementsService.updateMovement(movementDto);
		ResponseDto response = new ResponseDto();
		response.title = "Movimiento Actualizado.";
		response.data = movement;
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/")
	public ResponseEntity<ResponseDto> deleteMovement(@RequestParam Long movementId) throws Exception {
		movementsService.deleteMovement(movementId);
		ResponseDto response = new ResponseDto();
		response.title = "Movimiento eliminado.";
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}
