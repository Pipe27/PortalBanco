package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;
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
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTransaccionDAO {
	
	private final static Logger log=LoggerFactory.getLogger(TestTransaccionDAO.class);
	
	@Autowired
	private ITipoTransaccionDAO tipoTransaccionDAO;
	
	@Autowired
	private ITransaccionDAO transaccionDAO;
	
	@Autowired
	private ICuentaDAO cuentaDAO;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	static Long tranId = new Long (3001);
	static Long tranId2 = new Long (345);
	static String cuenId = new String ("0311-0015-1322-6639");
	static Long titrId = new Long (3L);
	static String usuUsuario = new String ("ssawlci");
	static Date fecha = new Date("12/12/2017");
	
	
	@Test
	@DisplayName("Crear Transaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(transaccionDAO,"El transaccionDAO esta nulo");
		Transaccion transaccion= transaccionDAO.consultarPorTransId(tranId);
		assertNull(transaccion,"Transaccion existente");
		
		transaccion=new Transaccion();
		transaccion.setTranId(tranId);
		
		Cuenta cuenta = cuentaDAO.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta es nulo");
		
		TipoTransaccion tipoTransaccion = tipoTransaccionDAO.consultarPorTipoTraId(titrId);
		assertNotNull(tipoTransaccion,"La transaccion es nula");
		transaccion.setTipoTransaccion(tipoTransaccion);
		
		Usuario usuario = usuarioDAO.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario,"El usuario es nulo");
		transaccion.setUsuario(usuario);
		
		transaccion.setValor(new BigDecimal(1000000));

		transaccion.setFecha(fecha);
		
		transaccionDAO.grabar(transaccion);
	}
	
	@Test
	@DisplayName("Modificar Transaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(transaccionDAO,"El transaccionDAO esta nulo");
		Transaccion transaccion= transaccionDAO.consultarPorTransId(tranId);
		assertNotNull(transaccion,"La transaccion no existe");
		
		
		Cuenta cuenta = cuentaDAO.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta es nulo");

		transaccion.setFecha(fecha);

		TipoTransaccion tipoTransaccion = tipoTransaccionDAO.consultarPorTipoTraId(titrId);
		assertNotNull(tipoTransaccion,"La transaccion es nula");
		transaccion.setTipoTransaccion(tipoTransaccion);
		
		Usuario usuario = usuarioDAO.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario,"El usuario es nulo");
		transaccion.setUsuario(usuario);
		
		transaccion.setValor(new BigDecimal(57395762.0000));
		
		transaccionDAO.modificar(transaccion);
	}
	
	@Test
	@DisplayName("Borrar Transaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(transaccionDAO,"El transaccionDAO esta nulo");
		Transaccion transaccion= transaccionDAO.consultarPorTransId(tranId);
		assertNotNull(transaccion,"La transaccion no existe");
		
		transaccionDAO.borrar(transaccion);
	}
	
	@Test
	@DisplayName("Consultar Transaccion")
	@Transactional(readOnly=true)
	public void btest() {
		assertNotNull(transaccionDAO);
		Transaccion transaccion=transaccionDAO.consultarPorTransId(tranId2);
		assertNotNull(transaccion);
		
		log.info("-------------------------------------------");
		log.info("Id de la Cuenta: " + transaccion.getCuenta().getCuenId());
		log.info("Fecha Transacción: " + transaccion.getFecha());
		log.info("Tipo de Transacción Realizada: " + transaccion.getTipoTransaccion());
		log.info("Valor de Transacción: " + transaccion.getValor());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(transaccionDAO,"El transaccionDAO esta nulo");
		List<Transaccion> lasTransacciones = transaccionDAO.consultarTodo();
		lasTransacciones.forEach(transaccion -> {
			log.info("-------------------------------------------");
			log.info("Nombre: " + transaccion.getUsuario().getNombre());
			log.info("Id Cuenta: " + transaccion.getCuenta().getCuenId());
			log.info("Identificación: " + transaccion.getUsuario().getIdentificacion());
			log.info("Valor de la Transacción: " + transaccion.getValor());
			log.info("Tipo de Transacción: " + transaccion.getTipoTransaccion().getNombre());
		});
	}
	
}