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


import co.edu.usbcali.banco.modelo.TipoUsuario;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTipoUsuarioDAO {

private final static Logger log=LoggerFactory.getLogger(TestTipoUsuarioDAO.class);
	
@Autowired
private ITipoUsuarioDAO tipoUsuarioDAO;

static Long tiusId = new Long (4L);
static Long tiusIdMod = new Long (3L);

@Test
@DisplayName("Crear TipoUsuario")
@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
public void aTest() {
	assertNotNull(tipoUsuarioDAO,"El tipoUsuarioDAO esta nulo");
	TipoUsuario tipoUsuario= tipoUsuarioDAO.consultarPorId(tiusId);
	assertNull(tipoUsuario,"Usuario existente");
	
	tipoUsuario=new TipoUsuario();
	tipoUsuario.setActivo('S');
	tipoUsuario.setNombre("SUPERVISOR");
	tipoUsuario.setTiusId(tiusId);
				
	tipoUsuarioDAO.grabar(tipoUsuario);
}

@Test
@DisplayName("Modificar TipoUsuario")
@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
public void cTest() {
	assertNotNull(tipoUsuarioDAO,"El tipoUsuarioDAO esta nulo");
	TipoUsuario tipoUsuario= tipoUsuarioDAO.consultarPorId(tiusIdMod);
	assertNotNull(tipoUsuario,"El tipoUsuario no existe");
	
	tipoUsuario.setTiusId(tiusIdMod);
	tipoUsuario.setNombre("JÉFE DE AREA");
	
	tipoUsuarioDAO.modificar(tipoUsuario);
}

@Test
@DisplayName("Borrar tipoUsuario")
@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
public void dTest() {
	assertNotNull(tipoUsuarioDAO,"El tipoUsuarioDAO esta nulo");
	TipoUsuario tipoUsuario= tipoUsuarioDAO.consultarPorId(tiusId);
	assertNotNull(tipoUsuario,"El tipoUsuario no existe");
	
	tipoUsuarioDAO.borrar(tipoUsuario);
}

@Test
@DisplayName("Consultar TipoUsuario")
@Transactional(readOnly=true)
public void btest() {
	assertNotNull(tipoUsuarioDAO);
	TipoUsuario tipoUsuario=tipoUsuarioDAO.consultarPorId(2L);
	assertNotNull(tipoUsuario);
	
	log.info("Cargo Usuario: " + tipoUsuario.getNombre());
	log.info("Id Cargo: " + tipoUsuario.getTiusId());
	
}

@Test
@DisplayName("Consultar Todos")
@Transactional(readOnly=true)
public void eTest() {
	assertNotNull(tipoUsuarioDAO,"El tipoUsuarioDAO esta nulo");
	List<TipoUsuario> tiposUsuario = tipoUsuarioDAO.consultarTodo();
	tiposUsuario.forEach(tipos -> {
		log.info("-------------------------------------------");
		log.info("Nombre del Tipo Usuario: " + tipos.getNombre());
		log.info("Estado del Cargo: " + tipos.getActivo());
		log.info("Id del Tipo Usuario: " + tipos.getTiusId());

	});
}

}