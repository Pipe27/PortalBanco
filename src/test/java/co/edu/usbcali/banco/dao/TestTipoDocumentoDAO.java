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


import co.edu.usbcali.banco.modelo.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTipoDocumentoDAO {
	
	private final static Logger log=LoggerFactory.getLogger(TestTipoDocumentoDAO.class);

	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	static Long tdocId = new Long (4L);
	static Long tdocIdMod = new Long (3L);
	
	@Test
	@DisplayName("Crear TipoUsuario")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(tipoDocumentoDAO,"El tipoDocumentoDAO esta nulo");
		TipoDocumento tipoDocumento= tipoDocumentoDAO.consultarPorId(tdocId);
		assertNull(tipoDocumento,"tipoDocumento existente");
		
		tipoDocumento=new TipoDocumento();
		tipoDocumento.setActivo('S');
		tipoDocumento.setNombre("PASAPORTE");
		tipoDocumento.setTdocId(tdocId);
					
		tipoDocumentoDAO.grabar(tipoDocumento);
	}
	
	@Test
	@DisplayName("Modificar tipoDocumento")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(tipoDocumentoDAO,"El tipoDocumentoDAO esta nulo");
		TipoDocumento tipoDocumento= tipoDocumentoDAO.consultarPorId(tdocIdMod);
		assertNotNull(tipoDocumento,"El tipoUsuario no existe");
		
		tipoDocumento.setTdocId(tdocIdMod);
		tipoDocumento.setNombre("REGISTRO CIVIL");
		
		tipoDocumentoDAO.modificar(tipoDocumento);
	}
	
	@Test
	@DisplayName("Borrar tipoDocumento")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(tipoDocumentoDAO,"El tipoDocumentoDAO esta nulo");
		TipoDocumento tipoDocumento= tipoDocumentoDAO.consultarPorId(tdocId);
		assertNotNull(tipoDocumento,"El tipoDocumento no existe");
		
		tipoDocumentoDAO.borrar(tipoDocumento);
	}
	
	@Test
	@DisplayName("Consultar TipoDocumento")
	@Transactional(readOnly=true)
	public void btest() {
		assertNotNull(tipoDocumentoDAO);
		TipoDocumento tipoDocumento=tipoDocumentoDAO.consultarPorId(1L);
		assertNotNull(tipoDocumento);
		
		log.info("Documento de Identificación: " + tipoDocumento.getNombre());
		log.info("Id Tipo: " + tipoDocumento.getTdocId());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(tipoDocumentoDAO,"El tipoDocumentoDAO esta nulo");
		List<TipoDocumento> tiposDocumento = tipoDocumentoDAO.consultarTodo();
		tiposDocumento.forEach(tipos -> {
			log.info("-------------------------------------------");
			log.info("Documento de Identificación: " + tipos.getNombre());
			log.info("Estado del Tipo Documento: " + tipos.getActivo());
			log.info("Id del Tipo Usuario: " + tipos.getTdocId());

		});
	}

}
