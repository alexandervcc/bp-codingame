package acc.spring.services;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import acc.spring.DTO.MovementDto;
import acc.spring.exceptions.NotFoundException;
import acc.spring.exceptions.InsufficientFundsException;
import acc.spring.exceptions.InvalidMovementException;
import acc.spring.model.Account;
import acc.spring.model.Movement;
import acc.spring.repository.AccountRepository;
import acc.spring.repository.MovementRepository;
import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class MovementServiceImpl implements IMovementsService {
	private MovementRepository movementsRepository;
	private AccountRepository accountRepository;

	@Override
	public Movement createNewMovement(MovementDto movementDto) throws Exception {
		Account account = accountRepository.findById(movementDto.cuentaId)
				.orElseThrow();

		Long nuevoSaldo;
		if (movementDto.tipoMovimiento.equals("DEBITO")) {
			if (account.getSaldoInicial() < movementDto.valor) {
				throw new InsufficientFundsException();
			}
			nuevoSaldo = account.getSaldoInicial() - movementDto.valor;
		} else if (movementDto.tipoMovimiento.equals("CREDITO")) {
			nuevoSaldo = account.getSaldoInicial() + movementDto.valor;
		} else {
			throw new InvalidMovementException();
		}

		Movement movement = Movement.builder()
				.id(null)
				.cuenta(account)
				.fecha(new Timestamp(new Date().getTime()))
				.saldo(nuevoSaldo)
				.tipoMovimiento(movementDto.tipoMovimiento)
				.build();
		return movementsRepository.save(movement);
	}

	@Override
	public void deleteMovement(Long movementId) {
		Movement movement = movementsRepository.findById(movementId).orElseThrow();
		movementsRepository.delete(movement);
	}

	@Override
	public Movement getMovementByAccountAndDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movement getMovementsByAccount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movement updatMovement(MovementDto movementDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
