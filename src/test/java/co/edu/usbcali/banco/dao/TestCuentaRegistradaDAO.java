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
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestCuentaRegistradaDAO {
	
	private final static Logger log=LoggerFactory.getLogger(TestCuentaRegistradaDAO.class);
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ICuentaDAO cuentaDAO;
	
	@Autowired
	private ICuentaRegistradaDAO cuentaRegistradaDAO;
	
	static Long cureId = new Long (20L);
	static Long cureId2 = new Long (5L);
	static Long cureIdMod = new Long (8L);
	static BigDecimal clieId = new BigDecimal(802);
	static String cuenId = new String ("2390-2819-1532-8067");
	
	@Test
	@DisplayName("Crear CuentaRegistrada")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(cuentaRegistradaDAO,"La cuentaRegistradaDAO esta null");
		CuentaRegistrada cuentaRegistrada= cuentaRegistradaDAO.consultarPorId(cureId);
		assertNull(cuentaRegistrada,"cuentaRegistrada existente");
		
		cuentaRegistrada = new CuentaRegistrada();
		cuentaRegistrada.setCureId(cureId);
		
		Cliente cliente = clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El usuario es nulo");
		cuentaRegistrada.setCliente(cliente);
		
		Cuenta cuenta = cuentaDAO.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta es nulo");
		cuentaRegistrada.setCuenta(cuenta);
		
		cuentaRegistradaDAO.grabar(cuentaRegistrada);
		
	}
	
	@Test
	@DisplayName("Modificar CuentaRegistrada")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(cuentaRegistradaDAO,"El cuentaRegistradaDAO esta nulo");
		CuentaRegistrada cuentaRegistrada= cuentaRegistradaDAO.consultarPorId(cureIdMod);
		assertNotNull(cuentaRegistrada,"La cuentaRegistrada no existe");
		
		Cliente cliente = clienteDAO.consultarPorId(clieId);
		assertNotNull(cliente,"El usuario es nulo");
		cuentaRegistrada.setCliente(cliente);
		
		Cuenta cuenta = cuentaDAO.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta es nulo");
		cuentaRegistrada.setCuenta(cuenta);
		
		cuentaRegistradaDAO.modificar(cuentaRegistrada);
	}
	
	@Test
	@DisplayName("Borrar cuentaRegistrada")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(cuentaRegistradaDAO,"El cuentaRegistradaDAO esta nulo");
		CuentaRegistrada cuentaRegistrada= cuentaRegistradaDAO.consultarPorId(cureId);
		assertNotNull(cuentaRegistrada,"La cuentaRegistrada no existe");
		
		cuentaRegistradaDAO.borrar(cuentaRegistrada);
	}
	
	@Test
	@DisplayName("Consultar Transaccion")
	@Transactional(readOnly=true)
	public void btest() {
		assertNotNull(cuentaRegistradaDAO);
		CuentaRegistrada cuentaRegistrada=cuentaRegistradaDAO.consultarPorId(cureId2);
		assertNotNull(cuentaRegistrada);
		
		log.info("-------------------------------------------");
		log.info("Nombre Cliente: " + cuentaRegistrada.getCliente().getNombre());
		log.info("Teléfono Cliente: " + cuentaRegistrada.getCliente().getTelefono());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(cuentaRegistradaDAO,"El cuentaRegistradaDAO esta nulo");
		List<CuentaRegistrada> lasCuenticas = cuentaRegistradaDAO.consultarTodo();
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
