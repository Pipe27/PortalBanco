package co.edu.usbcali.banco.dao;

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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)

class TestClienteDAO {
	
	private final static Logger log = LoggerFactory.getLogger(TestClienteDAO.class);

	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	static BigDecimal clieId = new BigDecimal(142020);

	@Test
	@DisplayName("Crear Cliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(clienteDAO,"El clienteDAO esta nulo");
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNull(cliente,"Cliente existente");
		
		cliente=new Cliente();
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Cra. 53 #13 E - 31");
		cliente.setEmail("felipea.97@hotmail.com");
		cliente.setNombre("Luis Ibarra");
		cliente.setTelefono("12345");
		TipoDocumento tipoDocumento = tipoDocumentoDAO.consultarPorId(2L);
		assertNotNull(tipoDocumento, "El tipo de documento es nulo o no existe");
		cliente.setTipoDocumento(tipoDocumento);
				
		clienteDAO.grabar(cliente);
	}
	
	@Test
	@DisplayName("Modificar Cliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(clienteDAO,"El clienteDAO esta nulo");
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		
		cliente.setActivo('S');
		cliente.setDireccion("Cra. 53 #13 E - 31");
		cliente.setEmail("pipe@hotmail.com");
		cliente.setNombre("Felipe Ibarra");
		cliente.setTelefono("3162269809");
		TipoDocumento tipoDocumento = tipoDocumentoDAO.consultarPorId(3L);
		assertNotNull(tipoDocumento, "El tipo de documento es nulo o no existe");
		cliente.setTipoDocumento(tipoDocumento);
				
		clienteDAO.modificar(cliente);
	}
	
	@Test
	@DisplayName("Borrar Cliente")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(clienteDAO,"El clienteDAO esta nulo");
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		clienteDAO.borrar(cliente);
	}
	
	@Test
	@DisplayName("ConsultarCliente")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(clienteDAO);
		Cliente cliente=clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente);
		
		log.info(cliente.getNombre());
		log.info(cliente.getEmail());
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(clienteDAO,"El clienteDAO esta nulo");
		List<Cliente> losClientes = clienteDAO.consultarTodo();
		losClientes.forEach(cliente -> {
			log.info("-------------------------------------------");
			log.info("Id: " + cliente.getClieId());
			log.info("Nombre: " + cliente.getNombre());
			log.info("e-mail: " +cliente.getEmail());
		});
	}

}