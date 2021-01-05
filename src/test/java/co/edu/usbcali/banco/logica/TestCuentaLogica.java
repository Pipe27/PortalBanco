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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;



@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestCuentaLogica {
	
	private final static Logger log=LoggerFactory.getLogger(TestCuentaLogica.class);
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	static BigDecimal clieId = new BigDecimal (234);
	static String cuenId = new String ("0311-0015-1322-6039");
	
	@Test
	@DisplayName("Crear Cuenta")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() throws Exception{
		assertNotNull(cuentaLogica,"El cuentaLogica esta nulo");
		Cuenta cuenta=cuentaLogica.consultarPorId(cuenId);
		assertNull(cuenta,"Cuenta existente");
		
		cuenta=new Cuenta();
		cuenta.setCuenId(cuenId);
		Cliente cliente = clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente esta nulo");
		cuenta.setCliente(cliente);
		cuenta.setSaldo(new BigDecimal(8596573.000000));
		cuenta.setActiva('S');
		cuenta.setClave("hYwiUEeQt");		
		
		cuentaLogica.grabar(cuenta);
	}
	
	@Test
	@DisplayName("Modificar Cuenta")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() throws Exception{
		assertNotNull(cuentaLogica,"El cuentaLogica esta nulo");
		Cuenta cuenta=cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta no existe");
		
		Cliente cliente = clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente esta nulo");
		cuenta.setCliente(cliente);
		cuenta.setSaldo(new BigDecimal(859436573.000000));
		cuenta.setActiva('S');
		cuenta.setClave("hYiUEQt");	
				
		cuentaLogica.modificar(cuenta);
	}
	
	@Test
	@DisplayName("Borrar Cuenta")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() throws Exception{
		assertNotNull(cuentaLogica,"El cuentaDAO esta nulo");
		Cuenta cuenta=cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta,"El cliente no existe");
		
		cuentaLogica.borrar(cuenta);
	}
	
	@Test
	@DisplayName("Consultar Cuenta")
	@Transactional(readOnly=true)
	public void bTest() {
		assertNotNull(cuentaLogica);
		Cuenta cuenta=cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta);
		
		log.info(cuenta.getCliente().getNombre());
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(cuentaLogica,"El cuentaDAO esta nulo");
		List<Cuenta> lasCuentas = cuentaLogica.consultarTodo();
		lasCuentas.forEach(cuenta -> {
			log.info("-------------------------------------------");
			log.info("Id Cuenta: " + cuenta.getCuenId());
			log.info("Nombre Cliente: " + cuenta.getCliente().getNombre());
			log.info("e-mail: " + cuenta.getCliente().getEmail());
			log.info("Saldo Cuenta: " + cuenta.getSaldo());
		});
	}

}