package co.edu.usbcali.banco.logica;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestClienteLogica {
	
	private final static Logger log=LoggerFactory.getLogger(TestClienteLogica.class);
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	static BigDecimal clieId = new BigDecimal(14203);

	@Test
	@DisplayName("Crear Cliente")
	public void aTest() throws Exception {
		assertNotNull(clienteLogica,"El clienteLogica esta nulo");
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNull(cliente,"Cliente existente");
		
		cliente=new Cliente();
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Cra. 53 #13 E - 31");
		cliente.setEmail("felipea.97@hotmail.com");
		cliente.setNombre("Luis Ibarra");
		cliente.setTelefono("316-226-9809");
		TipoDocumento tipoDocumento = tipoDocumentoLogica.consultarPorId(2L);
		assertNotNull(tipoDocumento, "El tipo de documento es nulo o no existe");
		cliente.setTipoDocumento(tipoDocumento);
				
		clienteLogica.grabar(cliente);
	}
	
	@Test
	@DisplayName("Borrar Cliente")
	public void dTest() throws Exception {
		assertNotNull(clienteLogica,"El clienteLogica esta nulo");
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		clienteLogica.borrar(cliente);
	}
	
	@Test
	@DisplayName("Modificar Cliente")
	public void cTest() throws Exception {
		assertNotNull(clienteLogica,"El clienteLogica esta nulo");
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		
		cliente.setActivo('S');
		cliente.setDireccion("Cra. 53 #13 E - 31");
		cliente.setEmail("pipe@hotmail.com");
		cliente.setNombre("Felipe Ibarra");
		cliente.setTelefono("316-226-6809");
		TipoDocumento tipoDocumento = tipoDocumentoLogica.consultarPorId(3L);
		assertNotNull(tipoDocumento, "El tipo de documento es nulo o no existe");
		cliente.setTipoDocumento(tipoDocumento);
				
		clienteLogica.modificar(cliente);
	}
	
	@Test
	@DisplayName("ConsultarCliente")
	public void bTest() {
		assertNotNull(clienteLogica);
		Cliente cliente=clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente);
		
		log.info(cliente.getNombre());
		log.info(cliente.getEmail());
	}
	
	@Test
	@DisplayName("Consultar Todos")
	public void eTest() {
		assertNotNull(clienteLogica,"El clienteLogica esta nulo");
		List<Cliente> losClientes = clienteLogica.consultarTodo();
		losClientes.forEach(cliente -> {
			log.info("-------------------------------------------");
			log.info("Id: " + cliente.getClieId());
			log.info("Nombre: " + cliente.getNombre());
			log.info("e-mail: " +cliente.getEmail());
		});
	}

}