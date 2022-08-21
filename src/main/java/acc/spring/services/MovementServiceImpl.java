package acc.spring.services;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;


import acc.spring.DTO.MovementDto;
import acc.spring.DTO.ResListMovement;
import acc.spring.exceptions.DateException;
import acc.spring.exceptions.NotFoundException;
import acc.spring.model.Account;
import acc.spring.model.Movement;
import acc.spring.repository.AccountRepository;
import acc.spring.repository.MovementRepository;
import acc.spring.utils.AccountOperations;
import acc.spring.utils.MovementOperations;
import acc.spring.utils.PDFGenerator;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class MovementServiceImpl implements IMovementsService {
	private MovementRepository movementsRepository;
	private AccountRepository accountRepository;
	private PDFGenerator pdfGenerator;

	@Override
	public List<Movement> getAllMovements() {
		return movementsRepository.findAll();
	}

	@Override
	public Movement createNewMovement(MovementDto movementDto) throws Exception {

		MovementOperations.checkInvalidValuesForMovement(movementDto);

		Account account = accountRepository.findById(movementDto.cuentaOrigen)
				.orElseThrow(() -> new NotFoundException("Cuenta Origen no Encontrada"));

		AccountOperations.checkAccountStatus(account);

		Long newOriginFunds = MovementOperations.calculateNewAccountFunds(movementDto, account.getSaldoInicial());

		account.setSaldoInicial(newOriginFunds);
		Timestamp movementDate = new Timestamp(new Date().getTime());

		Movement newMovement = Movement.builder()
				.id(null)
				.cuenta(account)
				.fecha(movementDate)
				.saldo(newOriginFunds)
				.tipoMovimiento(movementDto.tipoMovimiento)
				.valor(movementDto.valor)
				.build();

		accountRepository.save(account);

		return movementsRepository.save(newMovement);
	}

	@Override
	public ResListMovement getMovementsByAccount(Long accountId, MovementDto movementDto)
			throws Exception {

		Account account = accountRepository.findByIdAndFetchClientEagerly(accountId)
				.orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));

		List<Movement> listMovements;

		if (movementDto.fechaInicio != null && movementDto.fechaFin != null) {
			if (movementDto.fechaInicio.after(movementDto.fechaFin)) {
				throw new DateException("Fecha inicial despues de la final");
			}
			listMovements = movementsRepository.findAllByCuentaAndFechaBetweenOrderByFechaDesc(account,
					movementDto.fechaInicio,
					movementDto.fechaFin);
		} else if (movementDto.fechaInicio != null && movementDto.fechaFin == null) {
			Timestamp fechaActual = new Timestamp(new Date().getTime());
			listMovements = movementsRepository.findAllByCuentaAndFechaBetweenOrderByFechaDesc(account,
					movementDto.fechaInicio,
					fechaActual);
		} else {
			listMovements = movementsRepository.findByCuentaOrderByFechaDesc(account);
		}
		ResListMovement response = new ResListMovement();
		response.cuenta = account;
		response.movimientos = listMovements;
		response.cliente = account.getCliente();
		return response;
	}

	@Override
	public FileSystemResource createMovementPDFReport(Long accountId, MovementDto movementDto) throws Exception {
		Account account = accountRepository.findByIdAndFetchClientEagerly(accountId)
				.orElseThrow(() -> new NotFoundException("Cuenta no encontrada"));

		List<Movement> listMovements;

		if (movementDto.fechaInicio != null && movementDto.fechaFin != null) {
			if (movementDto.fechaInicio.after(movementDto.fechaFin)) {
				throw new DateException("Fecha inicial despues de la final");
			}
			listMovements = movementsRepository.findAllByCuentaAndFechaBetweenOrderByFechaDesc(account,
					movementDto.fechaInicio,
					movementDto.fechaFin);
		} else if (movementDto.fechaInicio != null && movementDto.fechaFin == null) {
			Timestamp fechaActual = new Timestamp(new Date().getTime());
			listMovements = movementsRepository.findAllByCuentaAndFechaBetweenOrderByFechaDesc(account,
					movementDto.fechaInicio,
					fechaActual);
		} else {
			listMovements = movementsRepository.findByCuentaOrderByFechaDesc(account);
		}

		ResListMovement movementReport = new ResListMovement();
		movementReport.cuenta = account;
		movementReport.movimientos = listMovements;
		movementReport.cliente = account.getCliente();

		pdfGenerator.createReport(movementReport);

		return new FileSystemResource("./reporte.pdf");
	}

	@Override
	public Movement updateMovement(MovementDto movementDto) throws Exception {
		Movement movement = movementsRepository.findById(movementDto.movementId)
				.orElseThrow(() -> new NotFoundException("Movimiento no Encontrado"));

		if (movementDto.tipoMovimiento != null)
			movement.setTipoMovimiento(movementDto.tipoMovimiento);
		if (movementDto.valor != null)
			movement.setValor(movementDto.valor);

		return movementsRepository.save(movement);
	}

	@Override
	public void deleteMovement(Long movementId) throws NotFoundException {
		Movement movement = movementsRepository.findById(movementId)
				.orElseThrow(() -> new NotFoundException("Movimiento no Encontrado"));
		movementsRepository.delete(movement);
	}

}
