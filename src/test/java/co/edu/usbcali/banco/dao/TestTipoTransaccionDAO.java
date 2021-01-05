package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

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


import co.edu.usbcali.banco.modelo.TipoTransaccion;



@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTipoTransaccionDAO {
	
	private final static Logger log=LoggerFactory.getLogger(TestTipoTransaccionDAO.class);
	
	@Autowired
	private ITipoTransaccionDAO tipoTransaccionDAO;
	
	static Long titrId = new Long (4L);
	static Long titrIdMod = new Long (3L);
	
	@Test
	@DisplayName("Crear TipoTransaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(tipoTransaccionDAO,"El tipoTransaccionDAO esta nulo");
		TipoTransaccion tipoTransaccion= tipoTransaccionDAO.consultarPorTipoTraId(titrId);
		assertNull(tipoTransaccion,"tipoTransaccion existente");
		
		tipoTransaccion=new TipoTransaccion();
		tipoTransaccion.setActivo('S');
		tipoTransaccion.setNombre("GIRO");
		tipoTransaccion.setTitrId(titrId);
					
		tipoTransaccionDAO.grabar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Modificar tipoTransaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(tipoTransaccionDAO,"El tipoTransaccionDAO esta nulo");
		TipoTransaccion tipoTransaccion= tipoTransaccionDAO.consultarPorTipoTraId(titrIdMod);
		assertNotNull(tipoTransaccion,"El tipoTransaccion no existe");
		
		tipoTransaccion.setTitrId(titrIdMod);
		tipoTransaccion.setNombre("EFECTIVO");
		
		tipoTransaccionDAO.modificar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Borrar tipoTransaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(tipoTransaccionDAO,"El tipoTransaccionDAO esta nulo");
		TipoTransaccion tipoTransaccion= tipoTransaccionDAO.consultarPorTipoTraId(titrId);
		assertNotNull(tipoTransaccion,"El tipoDocumento no existe");
		
		tipoTransaccionDAO.borrar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Consultar tipoTransaccion")
	@Transactional(readOnly=true)
	public void btest() {
		assertNotNull(tipoTransaccionDAO);
		TipoTransaccion tipoTransaccion=tipoTransaccionDAO.consultarPorTipoTraId(1L);
		assertNotNull(tipoTransaccion);
		
		log.info("Nombre de Transacción: " + tipoTransaccion.getNombre());
		log.info("Id Tipo: " + tipoTransaccion.getTitrId());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(tipoTransaccionDAO,"El tipoTransaccionDAO esta nulo");
		List<TipoTransaccion> tiposTransaccion = tipoTransaccionDAO.consultarTodo();
		tiposTransaccion.forEach(tipos -> {
			log.info("-------------------------------------------");
			log.info("Nombre de Transacción: " + tipos.getNombre());
			log.info("Estado del Tipo Transacción: " + tipos.getActivo());
			log.info("Id del Tipo Transacción: " + tipos.getTitrId());
		});
	}
}