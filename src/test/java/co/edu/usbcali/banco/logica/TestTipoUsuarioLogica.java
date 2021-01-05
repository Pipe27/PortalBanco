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

import co.edu.usbcali.banco.modelo.TipoUsuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTipoUsuarioLogica {
	private final static Logger log=LoggerFactory.getLogger(TestTipoUsuarioLogica.class);

	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	static Long tiusId = new Long (4L);
	static Long tiusIdMod = new Long (3L);
	
	@Test
	@DisplayName("Crear TipoUsuario")
	public void aTest() throws Exception{
		assertNotNull(tipoUsuarioLogica,"El tipoUsuarioLogica esta nulo");
		TipoUsuario tipoUsuario= tipoUsuarioLogica.consultarPorId(tiusId);
		assertNull(tipoUsuario,"Usuario existente");
		
		tipoUsuario=new TipoUsuario();
		tipoUsuario.setActivo('S');
		tipoUsuario.setNombre("SUPERVISOR");
		tipoUsuario.setTiusId(tiusId);
					
		tipoUsuarioLogica.grabar(tipoUsuario);
	}
	
	@Test
	@DisplayName("Modificar TipoUsuario")
	public void cTest() throws Exception{
		assertNotNull(tipoUsuarioLogica,"El tipoUsuarioLogica esta nulo");
		TipoUsuario tipoUsuario= tipoUsuarioLogica.consultarPorId(tiusIdMod);
		assertNotNull(tipoUsuario,"El tipoUsuario no existe");
		
		tipoUsuario.setTiusId(tiusIdMod);
		tipoUsuario.setNombre("JÉFE DE AREA");
		
		tipoUsuarioLogica.modificar(tipoUsuario);
	}
	
	@Test
	@DisplayName("Borrar tipoUsuario")
	public void dTest() throws Exception{
		assertNotNull(tipoUsuarioLogica,"El tipoUsuarioLogica esta nulo");
		TipoUsuario tipoUsuario= tipoUsuarioLogica.consultarPorId(tiusId);
		assertNotNull(tipoUsuario,"El tipoUsuario no existe");
		
		tipoUsuarioLogica.borrar(tipoUsuario);
	}
	
	@Test
	@DisplayName("Consultar TipoUsuario")
	public void btest() {
		assertNotNull(tipoUsuarioLogica);
		TipoUsuario tipoUsuario=tipoUsuarioLogica.consultarPorId(2L);
		assertNotNull(tipoUsuario);
		
		log.info("Cargo Usuario: " + tipoUsuario.getNombre());
		log.info("Id Cargo: " + tipoUsuario.getTiusId());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	public void eTest() {
		assertNotNull(tipoUsuarioLogica,"El tipoUsuarioDAO esta nulo");
		List<TipoUsuario> tiposUsuario = tipoUsuarioLogica.consultarTodo();
		tiposUsuario.forEach(tipos -> {
			log.info("-------------------------------------------");
			log.info("Nombre del Tipo Usuario: " + tipos.getNombre());
			log.info("Estado del Cargo: " + tipos.getActivo());
			log.info("Id del Tipo Usuario: " + tipos.getTiusId());

		});
	}

}