package acc.spring.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import acc.spring.DTO.MovementDto;
import acc.spring.DTO.ResListMovement;
import acc.spring.DTO.ResMovementDto;
import acc.spring.exceptions.list.DateExceptionException;
import acc.spring.exceptions.list.NotFoundException;
import acc.spring.model.Account;
import acc.spring.model.Client;
import acc.spring.model.Movement;
import acc.spring.repository.AccountRepository;
import acc.spring.repository.ClientRepository;
import acc.spring.repository.MovementRepository;
import acc.spring.utils.AccountOperations;
import acc.spring.utils.MovementOperations;
import acc.spring.utils.PDFGenerator;
import lombok.AllArgsConstructor;

import static acc.spring.constants.AppConstants.*;

@Transactional
@Service
@AllArgsConstructor
public class MovementServiceImpl implements IMovementsService {
	private MovementRepository movementsRepository;
	private AccountRepository accountRepository;
	private ClientRepository clientRepository;
	private MovementOperations movementOperations;
	private AccountOperations accountOperations;
	private PDFGenerator pdfGenerator;

	@Override
	public ResListMovement getAllMovements() {
		List<Movement> allMovements = movementsRepository.findAll();
		ResListMovement listMovement = new ResListMovement();
		allMovements.forEach(movement -> {
			ResMovementDto movementDtoResponse = new ResMovementDto();
			movementDtoResponse.id = movement.getId();
			movementDtoResponse.cuentaOrigen = movement.getCuenta().getNumeroDeCuenta();
			movementDtoResponse.fecha = movement.getFecha();
			movementDtoResponse.tipoCuentaOrigen = movement.getCuenta().getTipoDeCuenta();
			movementDtoResponse.saldoInicial = movement.getSaldo() - movement.getValor();
			movementDtoResponse.valor = movement.getValor();
			movementDtoResponse.saldoDisponible = movement.getSaldo();
			movementDtoResponse.tipoMovimiento = movement.getTipoMovimiento();
			movementDtoResponse.estado = movement.getCuenta().getEstado();
			movementDtoResponse.cliente = movement.getCuenta().getCliente().getNombre();
			listMovement.movimientos.add(movementDtoResponse);
		});

		return listMovement;
	}

	@Override
	public ResMovementDto createNewMovement(MovementDto movementDto) throws Exception {
		ResMovementDto resMovementDto = new ResMovementDto();
		movementOperations.checkInvalidValuesForMovement(movementDto);

		Account account = accountRepository.findById(movementDto.cuentaOrigen)
				.orElseThrow(() -> new NotFoundException("Cuenta Origen no Encontrada"));

		accountOperations.checkAccountStatus(account);
		resMovementDto.saldoInicial = account.getSaldoInicial();
		Long newOriginFunds = movementOperations.calculateNewAccountFunds(movementDto, account.getSaldoInicial());

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
		newMovement = movementsRepository.save(newMovement);

		resMovementDto.cliente = newMovement.getCuenta().getCliente().getNombre();
		resMovementDto.cuentaOrigen = newMovement.getCuenta().getNumeroDeCuenta();
		resMovementDto.fecha = newMovement.getFecha();
		resMovementDto.saldoDisponible = newMovement.getSaldo();
		resMovementDto.tipoCuentaOrigen = newMovement.getCuenta().getTipoDeCuenta();
		resMovementDto.valor = newMovement.getValor();
		resMovementDto.tipoMovimiento = newMovement.getTipoMovimiento();

		return resMovementDto;
	}

	@Override
	public ResListMovement getMovementsByClient(String clientName, MovementDto movementDto)
			throws Exception {
		List<Client> listClients = clientRepository.findClientsWithPartOfName(clientName);
		if (listClients.size() == 0) {
			throw new NotFoundException("Cliente no encontrado");
		}

		Client client = listClients.get(0);

		List<Account> listAccount = accountRepository.findAllAccountsByClientId(client.getId());
		if (listAccount.size() == 0) {
			throw new NotFoundException("No hay cuentas para el usuario indicado.");
		}

		Boolean useDatesForFiltering;
		if (movementDto.fechaInicio != null && movementDto.fechaFin != null) {
			if (movementDto.fechaInicio.after(movementDto.fechaFin)) {
				throw new DateExceptionException("Fecha inicial despues de la final");
			}
			useDatesForFiltering = true;
		} else if (movementDto.fechaInicio != null && movementDto.fechaFin == null) {
			Timestamp fechaActual = new Timestamp(new Date().getTime());
			movementDto.fechaFin = fechaActual;
			useDatesForFiltering = true;
		} else {
			useDatesForFiltering = false;
		}

		List<Movement> allAccountsMovements = new ArrayList<Movement>();

		listAccount.forEach(account -> {
			List<Movement> accountListMovements;
			if (useDatesForFiltering) {
				accountListMovements = movementsRepository.findAllByCuentaAndFechaBetweenOrderByFechaDesc(account,
						movementDto.fechaInicio,
						movementDto.fechaFin);
			} else {
				accountListMovements = movementsRepository.findByCuentaOrderByFechaDesc(account);
			}
			allAccountsMovements.addAll(accountListMovements);
		});

		ResListMovement response = new ResListMovement();
		allAccountsMovements.forEach(movement -> {
			ResMovementDto movementDtoResponse = new ResMovementDto();
			movementDtoResponse.cuentaOrigen = movement.getCuenta().getNumeroDeCuenta();
			movementDtoResponse.fecha = movement.getFecha();
			movementDtoResponse.tipoCuentaOrigen = movement.getCuenta().getTipoDeCuenta();
			movementDtoResponse.saldoInicial = movement.getSaldo() - movement.getValor();
			movementDtoResponse.valor = movement.getValor();
			movementDtoResponse.saldoDisponible = movement.getSaldo();
			movementDtoResponse.tipoMovimiento = movement.getTipoMovimiento();
			movementDtoResponse.estado = movement.getCuenta().getEstado();
			response.movimientos.add(movementDtoResponse);
		});

		response.cliente.nombre = client.getNombre();
		response.cliente.identificacion = client.getIdentificacion();
		response.cliente.direccion = client.getDireccion();

		return response;
	}

	@Override
	public FileSystemResource createMovementPDFReport(String clientName, MovementDto movementDto) throws Exception {

		ResListMovement movementReport = getMovementsByClient(clientName, movementDto);

		pdfGenerator.createReport(movementReport);

		return new FileSystemResource(REPORT_URL_NAME);
	}

	@Override
	public Movement updateMovement(MovementDto movementDto) throws Exception {
		Movement movement = movementsRepository.findById(movementDto.movementId)
				.orElseThrow(() -> new NotFoundException("Movimiento no Encontrado"));
		Long accountId = movement.getCuenta().getNumeroDeCuenta();
		Account account = accountRepository.findById(accountId)
				.orElseThrow(() -> new NotFoundException("Cuenta no Encontrada"));
		Long newFundsValue = account.getSaldoInicial() - movement.getValor();
		account.setSaldoInicial(newFundsValue);

		if (movementDto.tipoMovimiento != null)
			movement.setTipoMovimiento(movementDto.tipoMovimiento);
		if (movementDto.valor != null)
			movement.setValor(movementDto.valor);

		accountRepository.save(account);
		return movementsRepository.save(movement);
	}

	@Override
	public void deleteMovement(Long movementId) throws NotFoundException {
		Movement movement = movementsRepository.findById(movementId)
				.orElseThrow(() -> new NotFoundException("Movimiento no Encontrado"));
		Account account = accountRepository.findById(movement.getCuenta().getNumeroDeCuenta())
				.orElseThrow(() -> new NotFoundException("Cuenta de movimiento no Encontrado"));
		Long newFunds = account.getSaldoInicial() - movement.getValor();
		account.setSaldoInicial(newFunds);
		accountRepository.save(account);
		movementsRepository.delete(movement);
	}

}
