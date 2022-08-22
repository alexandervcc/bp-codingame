package acc.spring.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import acc.spring.model.Client;
import acc.spring.services.IClientService;

@WebMvcTest(ClientsController.class)
public class ClientsControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IClientService clientService;

    @Test
    public void getAllClients() throws Exception {
        List<Client> listaClientes = new ArrayList();
        when(clientService.getAllClients()).thenReturn(listaClientes);
        this.mockMvc
                .perform(get("/api/clientes/all"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getClientById() throws Exception {
        Client client = getRealClient();
        when(clientService.getClientById(1L)).thenReturn(client);
        this.mockMvc
                .perform(
                        get("/api/clientes/")
                                .param("clientId", "1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createNewClient() throws Exception {
        Client client = getRealClient();

        when(clientService.createNewClient(any())).thenReturn(client);

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(client);
       
        this.mockMvc
                .perform(
                        post("/api/clientes/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(body))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value(client.getNombre()));
    }

    @Test
    public void updateClient() throws Exception {
        Client client = getRealClient();

        when(clientService.createNewClient(any())).thenReturn(client);

        ObjectMapper mapper = new ObjectMapper();
        String body = mapper.writeValueAsString(client);
       
        this.mockMvc
                .perform(
                        put("/api/clientes/")
                                .contentType(MediaType.APPLICATION_JSON)
                                .param("clientId", "1")
                                .content(body)
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void deleteClientById() throws Exception {
        Client client = getRealClient();
        when(clientService.getClientById(1L)).thenReturn(client);
        this.mockMvc
                .perform(
                        delete("/api/clientes/")
                                .param("clientId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente eliminado."));
    }

    private Client getRealClient() {
        Client client = new Client();
        client.setId(1L);
        client.setContrasena("123456789");
        client.setDireccion("El Comite del Pueblo");
        client.setEdad(25);
        client.setNombre("Alex Charco");
        client.setGenero("M");
        client.setTelefono("0987654321");
        return client;
    }

}
