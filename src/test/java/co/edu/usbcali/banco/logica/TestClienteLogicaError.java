package co.edu.usbcali.banco.logica;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestClienteLogicaError {

	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	static BigDecimal clieId = new BigDecimal(142023);

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
		cliente.setTelefono("123-456-2427");
		TipoDocumento tipoDocumento = tipoDocumentoLogica.consultarPorId(2L);
		assertNotNull(tipoDocumento, "El tipo de documento es nulo o no existe");
		cliente.setTipoDocumento(tipoDocumento);
				
		clienteLogica.grabar(cliente);
	}

}