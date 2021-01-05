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
import co.edu.usbcali.banco.modelo.CuentaRegistrada;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestCuentaRegistradaLogica {
	
	private final static Logger log=LoggerFactory.getLogger(TestCuentaRegistradaLogica.class);
	
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private ICuentaRegistradaLogica cuentaRegistradaLogica;
	
	static Long cureId = new Long (20L);
	static BigDecimal clieId = new BigDecimal(802);
	static String cuenId = new String ("2390-2819-1532-8067");
	
	@Test
	@DisplayName("Crear CuentaRegistrada")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() throws Exception{
		assertNotNull(cuentaRegistradaLogica,"La cuentaRegistradaLogica esta null");
		CuentaRegistrada cuentaRegistrada= cuentaRegistradaLogica.consultarPorId(cureId);
		assertNull(cuentaRegistrada,"cuentaRegistrada existente");
		
		cuentaRegistrada = new CuentaRegistrada();
		cuentaRegistrada.setCureId(cureId);
		
		Cliente cliente = clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente,"El cliente es nulo");
		cuentaRegistrada.setCliente(cliente);
		
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta es nulo");
		cuentaRegistrada.setCuenta(cuenta);
		
		cuentaRegistradaLogica.grabar(cuentaRegistrada);
		
	}
	
	@Test
	@DisplayName("Modificar CuentaRegistrada")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() throws Exception{
		assertNotNull(cuentaRegistradaLogica,"El cuentaRegistradaLogica esta nulo");
		CuentaRegistrada cuentaRegistrada= cuentaRegistradaLogica.consultarPorId(cureId);
		assertNotNull(cuentaRegistrada,"La cuentaRegistrada no existe");
		
		Cliente cliente = clienteLogica.consultarPorId(clieId);
		assertNotNull(cliente,"El usuario es nulo");
		cuentaRegistrada.setCliente(cliente);
		
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta es nulo");
		cuentaRegistrada.setCuenta(cuenta);
		
		cuentaRegistradaLogica.modificar(cuentaRegistrada);
	}
	
	@Test
	@DisplayName("Borrar cuentaRegistrada")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() throws Exception{
		assertNotNull(cuentaRegistradaLogica,"El cuentaRegistradaLogica esta nulo");
		CuentaRegistrada cuentaRegistrada= cuentaRegistradaLogica.consultarPorId(cureId);
		assertNotNull(cuentaRegistrada,"La cuentaRegistrada no existe");
		
		cuentaRegistradaLogica.borrar(cuentaRegistrada);
	}
	
	@Test
	@DisplayName("Consultar Transaccion")
	@Transactional(readOnly=true)
	public void btest() {
		assertNotNull(cuentaRegistradaLogica);
		CuentaRegistrada cuentaRegistrada=cuentaRegistradaLogica.consultarPorId(cureId);
		assertNotNull(cuentaRegistrada);
		
		log.info("-------------------------------------------");
		log.info("Nombre Cliente: " + cuentaRegistrada.getCliente().getNombre());
		log.info("Teléfono Cliente: " + cuentaRegistrada.getCliente().getTelefono());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(cuentaRegistradaLogica,"El cuentaRegistradaDAO esta nulo");
		List<CuentaRegistrada> lasCuenticas = cuentaRegistradaLogica.consultarTodo();
		lasCuenticas.forEach(cuentica -> {
			log.info("-------------------------------------------");
			log.info("Nombre: " + cuentica.getCliente().getNombre());
			log.info("Id del Cliente: " + cuentica.getCliente().getClieId());
			log.info("e-mail Cliente: " + cuentica.getCliente().getEmail());
			log.info("Id Cuenta: " + cuentica.getCuenta().getCuenId());
			log.info("Saldo de la Cuenta: " + cuentica.getCuenta().getSaldo());
		});
	}
}