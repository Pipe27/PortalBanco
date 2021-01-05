package co.edu.usbcali.banco.logica;

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

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTransaccionLogica {
	
	private final static Logger log=LoggerFactory.getLogger(TestTransaccionLogica.class);
	
	@Autowired
	private ITipoTransaccionLogica tipoTransaccionLogica;
	
	@Autowired
	private ITransaccionLogica transaccionLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	static Long tranId = new Long (345L);
	static String cuenId = new String ("3546-4779-2181-9597");
	static Long titrId = new Long (3L);
	static String usuUsuario = new String ("ssawlci");
	static Date fecha = new Date();
	
	@Test
	@DisplayName("Crear Transaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() throws Exception{
		assertNotNull(transaccionLogica,"El transaccionLogica esta nulo");
		Transaccion transaccion = transaccionLogica.consultarPorTransId(tranId);
		assertNull(transaccion,"Transaccion existente");
		
		transaccion=new Transaccion();
		transaccion.setTranId(tranId);
		
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta es nulo");
		
		TipoTransaccion tipoTransaccion = tipoTransaccionLogica.consultarPorTipoTraId(titrId);
		assertNotNull(tipoTransaccion,"La transaccion es nula");
		transaccion.setTipoTransaccion(tipoTransaccion);
		
		Usuario usuario = usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario,"El usuario es nulo");
		transaccion.setUsuario(usuario);
		
		transaccion.setValor(new BigDecimal(1000000));
		transaccion.setFecha(fecha);
		
		transaccionLogica.grabar(transaccion);
	}
	
	@Test
	@DisplayName("Modificar Transaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() throws Exception{
		assertNotNull(transaccionLogica,"El transaccionLogica esta nulo");
		Transaccion transaccion= transaccionLogica.consultarPorTransId(tranId);
		assertNotNull(transaccion,"La transaccion no existe");
		
		
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		assertNotNull(cuenta,"La cuenta es nulo");
		
		transaccion.setFecha(fecha);

		TipoTransaccion tipoTransaccion = tipoTransaccionLogica.consultarPorTipoTraId(titrId);
		assertNotNull(tipoTransaccion,"La transaccion es nula");
		transaccion.setTipoTransaccion(tipoTransaccion);
		
		Usuario usuario = usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario,"El usuario es nulo");
		transaccion.setUsuario(usuario);
		
		transaccion.setValor(new BigDecimal(57395762.0000));
		
		transaccionLogica.modificar(transaccion);
	}
	
	@Test
	@DisplayName("Borrar Transaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() throws Exception{
		assertNotNull(transaccionLogica,"El transaccionLogica esta nulo");
		Transaccion transaccion= transaccionLogica.consultarPorTransId(tranId);
		assertNotNull(transaccion,"La transaccion no existe");
		
		transaccionLogica.borrar(transaccion);
	}
	
	@Test
	@DisplayName("Consultar Transaccion")
	@Transactional(readOnly=true)
	public void btest() {
		assertNotNull(transaccionLogica);
		Transaccion transaccion=transaccionLogica.consultarPorTransId(tranId);
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
		assertNotNull(transaccionLogica,"El transaccionLogica esta nulo");
		List<Transaccion> lasTransacciones = transaccionLogica.consultarTodo();
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