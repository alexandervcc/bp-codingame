package acc.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import acc.spring.controller.AccountController;
import acc.spring.controller.ClientsController;
import acc.spring.controller.MovementsController;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DemoApplicationTests {
	@Autowired
	private ClientsController clientsController;
	@Autowired
	private AccountController accountController;
	@Autowired
	private MovementsController movementsController;
	
	
	@Test
	void contextLoads()throws Exception {
		assertThat(clientsController).isNotNull();
		assertThat(accountController).isNotNull();
		assertThat(movementsController).isNotNull();
	}


}
