package acc.spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "clientes")
public class ClientesController {
	
	@GetMapping(path = {"","/"})
	public String getAllCliens() {
		return "xd";
	}

}
