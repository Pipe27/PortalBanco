package co.edu.usbcali.banco.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import co.edu.usbcali.banco.dto.ClienteDTO;

class TestClienteRest {

	private final static Logger log = LoggerFactory.getLogger(TestClienteRest.class);
	BigDecimal clieId = new BigDecimal("1111111");
	
	@Test
	@DisplayName("Crear")
	void aTest() {
		String url ="http://localhost:8080/banco-web/controller/cliente/crear";
		RestTemplate restTemplate = new RestTemplate();
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setActivo('S');
		clienteDTO.setClieId(clieId);
		clienteDTO.setDireccion("Elkin Gay");
		clienteDTO.setEmail("elkin@adriana.com");
		clienteDTO.setIdTipoDocumento(1L);
		clienteDTO.setNombreTipoDocumento("CEDULA");
		clienteDTO.setNombre("Elk Dani");
		clienteDTO.setTelefono("341-456-7235");
		
		restTemplate.postForLocation(url, clienteDTO);
	}
	
	@Test
	@DisplayName("Modificar")
	void bTest() {
		String url ="http://localhost:8080/banco-web/controller/cliente/consultarClientePorId/1111111";
		String urlModi ="http://localhost:8080/banco-web/controller/cliente/modificar";
		RestTemplate restTemplate = new RestTemplate();
		
		ClienteDTO clienteDTO = restTemplate.getForObject(url, ClienteDTO.class);
		assertNotNull(clienteDTO, "El cliente no existe");
		
		clienteDTO.setNombre("Elk Hpta");
		
		restTemplate.put(urlModi, clienteDTO);
	}
	
	@Test
	@DisplayName("ConsultarClientesPorId")
	void cTtest() {
		String url ="http://localhost:8080/banco-web/controller/cliente/consultarClientePorId/1";
		RestTemplate restTemplate = new RestTemplate();
		ClienteDTO clienteDTO = restTemplate.getForObject(url, ClienteDTO.class);
		
		assertNotNull(clienteDTO);
		
		log.info("Id Cliente: " + clienteDTO.getClieId());
		log.info("Nombre: " + clienteDTO.getNombre());
		log.info("Direccion: " + clienteDTO.getDireccion());
		log.info("Teléfono: " + clienteDTO.getTelefono());
	}
	
	@Test
	@DisplayName("Borrar")
	void dTest() {
		String url ="http://localhost:8080/banco-web/controller/cliente/borrar/"+clieId;
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.delete(url);
	}
}
