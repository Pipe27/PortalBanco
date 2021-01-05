package co.edu.usbcali.banco.logica;

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
class TestTipoTransaccionLogica {
	
	private final static Logger log=LoggerFactory.getLogger(TestTipoTransaccionLogica.class);
	
	@Autowired
	private ITipoTransaccionLogica tipoTransaccionLogica;
	
	static Long titrId = new Long (4L);
	static Long titrIdMod = new Long (3L);
	
	@Test
	@DisplayName("Crear TipoTransaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() throws Exception{
		assertNotNull(tipoTransaccionLogica,"El tipoTransaccionLogica esta nulo");
		TipoTransaccion tipoTransaccion= tipoTransaccionLogica.consultarPorTipoTraId(titrId);
		assertNull(tipoTransaccion,"tipoTransaccion existente");
		
		tipoTransaccion=new TipoTransaccion();
		tipoTransaccion.setActivo('S');
		tipoTransaccion.setNombre("GIRO");
		tipoTransaccion.setTitrId(titrId);
					
		tipoTransaccionLogica.grabar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Modificar tipoTransaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() throws Exception{
		assertNotNull(tipoTransaccionLogica,"El tipoTransaccionLogica esta nulo");
		TipoTransaccion tipoTransaccion= tipoTransaccionLogica.consultarPorTipoTraId(titrIdMod);
		assertNotNull(tipoTransaccion,"El tipoTransaccion no existe");
		
		tipoTransaccion.setTitrId(titrIdMod);
		tipoTransaccion.setNombre("EFECTIVO");
		
		tipoTransaccionLogica.modificar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Borrar tipoTransaccion")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() throws Exception{
		assertNotNull(tipoTransaccionLogica,"El tipoTransaccionLogica esta nulo");
		TipoTransaccion tipoTransaccion= tipoTransaccionLogica.consultarPorTipoTraId(titrId);
		assertNotNull(tipoTransaccion,"El tipoDocumento no existe");
		
		tipoTransaccionLogica.borrar(tipoTransaccion);
	}
	
	@Test
	@DisplayName("Consultar tipoTransaccion")
	@Transactional(readOnly=true)
	public void btest() {
		assertNotNull(tipoTransaccionLogica);
		TipoTransaccion tipoTransaccion=tipoTransaccionLogica.consultarPorTipoTraId(1L);
		assertNotNull(tipoTransaccion);
		
		log.info("Nombre de Transacción: " + tipoTransaccion.getNombre());
		log.info("Id Tipo: " + tipoTransaccion.getTitrId());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(tipoTransaccionLogica,"El tipoTransaccionLogica esta nulo");
		List<TipoTransaccion> tiposTransaccion = tipoTransaccionLogica.consultarTodo();
		tiposTransaccion.forEach(tipos -> {
			log.info("-------------------------------------------");
			log.info("Nombre de Transacción: " + tipos.getNombre());
			log.info("Estado del Tipo Transacción: " + tipos.getActivo());
			log.info("Id del Tipo Transacción: " + tipos.getTitrId());
		});
	}
}