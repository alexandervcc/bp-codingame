package acc.spring.controller;

import java.util.List;

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

import acc.spring.DTO.ClienteDto;
import acc.spring.model.Cliente;
import acc.spring.services.IClienteService;


@RestController
@RequestMapping(path = "api/clientes")
public class ClientesController {
	private IClienteService clienteService;

	@GetMapping(path = "/all")
	public ResponseEntity<List<?>> getAllClients() {
		List<Cliente> listaClientes = clienteService.getAllClients();
		return ResponseEntity.status(HttpStatus.OK).body(listaClientes);
	}
	
	@GetMapping(path = "/")
	public ResponseEntity<Cliente> searchClientById(@RequestParam Long clientId) {
		Cliente client = clienteService.getClientById(clientId);
		if(client==null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.FOUND).body(client);
	}
	
	@PostMapping(path="/")
	public ResponseEntity<?> createNewClient(@RequestBody ClienteDto clientDto){
		Cliente newClient = clienteService.createNewClient(clientDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newClient);
	}

	@PutMapping("/")
	public ResponseEntity<?> updateClient(@RequestParam Long clientId,@RequestBody ClienteDto clientDto){
		Cliente client = clienteService.updateClient(clientId, clientDto);
		return ResponseEntity.status(HttpStatus.OK).body(client);
	}

	@DeleteMapping("/")
	public ResponseEntity<?> deleteClient(@RequestParam Long clientId) {
		clienteService.deleteClientById(clientId);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
}