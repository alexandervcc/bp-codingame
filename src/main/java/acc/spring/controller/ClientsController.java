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

import acc.spring.DTO.ClientDto;
import acc.spring.DTO.ResponseDto;
import acc.spring.exceptions.list.NotFoundException;
import acc.spring.model.Client;
import acc.spring.services.IClientService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/clientes")
@AllArgsConstructor
public class ClientsController {
	private IClientService clienteService;

	@GetMapping(path = "/all")
	public ResponseEntity<ResponseDto> getAllClients() {
		List<Client> listaClientes = clienteService.getAllClients();
		ResponseDto response = new ResponseDto();
		response.dataList = listaClientes;
		response.title = "Lista de clientes";
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@GetMapping(path = "/")
	public ResponseEntity<ResponseDto> getClientByParam(@RequestParam(required = false) Long clientId)
			throws NotFoundException {
		Client client = clienteService.getClientById(clientId);
		ResponseDto response = new ResponseDto();
		response.data = client;
		response.title = "Cliente encontrado";
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@PostMapping(path = "/")
	public ResponseEntity<ResponseDto> createNewClient(@RequestBody ClientDto clientDto) throws Exception {
		Client newClient = clienteService.createNewClient(clientDto);
		ResponseDto response = new ResponseDto();
		response.data = newClient;
		response.title = "Cliente Creado";
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PutMapping("/")
	public ResponseEntity<ResponseDto> updateClient(@RequestParam Long clientId, @RequestBody ClientDto clientDto)
			throws NotFoundException {
		Client client = clienteService.updateClient(clientId, clientDto);
		ResponseDto response = new ResponseDto();
		response.data = client;
		response.title = "Cliente actualizado";
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}

	@DeleteMapping("/")
	public ResponseEntity<ResponseDto> deleteClient(@RequestParam Long clientId) throws NotFoundException {
		clienteService.deleteClientById(clientId);
		ResponseDto response = new ResponseDto();
		response.title = "Cliente eliminado.";
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
}