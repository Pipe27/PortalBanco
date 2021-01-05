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


import co.edu.usbcali.banco.modelo.TipoDocumento;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTipoDocumentoLogica {
	
	private final static Logger log=LoggerFactory.getLogger(TestTipoDocumentoLogica.class);
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	static Long tdocId = new Long (4L);
	static Long tdocIdMod = new Long (3L);
	
	@Test
	@DisplayName("Crear TipoUsuario")
	public void aTest() throws Exception{
		assertNotNull(tipoDocumentoLogica,"El tipoDocumentoLogica esta nulo");
		TipoDocumento tipoDocumento= tipoDocumentoLogica.consultarPorId(tdocId);
		assertNull(tipoDocumento,"tipoDocumento existente");
		
		tipoDocumento=new TipoDocumento();
		tipoDocumento.setActivo('S');
		tipoDocumento.setNombre("PASAPORTE");
		tipoDocumento.setTdocId(tdocId);
					
		tipoDocumentoLogica.grabar(tipoDocumento);
	}
	
	@Test
	@DisplayName("Modificar tipoDocumento")
	public void cTest() throws Exception{
		assertNotNull(tipoDocumentoLogica,"El tipoDocumentoLogica esta nulo");
		TipoDocumento tipoDocumento= tipoDocumentoLogica.consultarPorId(tdocIdMod);
		assertNotNull(tipoDocumento,"El tipoUsuario no existe");
		
		tipoDocumento.setTdocId(tdocIdMod);
		tipoDocumento.setNombre("REGISTRO CIVIL");
		
		tipoDocumentoLogica.modificar(tipoDocumento);
	}
	
	@Test
	@DisplayName("Borrar tipoDocumento")
	public void dTest() throws Exception{
		assertNotNull(tipoDocumentoLogica,"El tipoDocumentoLogica esta nulo");
		TipoDocumento tipoDocumento= tipoDocumentoLogica.consultarPorId(tdocId);
		assertNotNull(tipoDocumento,"El tipoDocumento no existe");
		
		tipoDocumentoLogica.borrar(tipoDocumento);
	}
	
	@Test
	@DisplayName("Consultar TipoDocumento")
	public void btest() {
		assertNotNull(tipoDocumentoLogica);
		TipoDocumento tipoDocumento=tipoDocumentoLogica.consultarPorId(1L);
		assertNotNull(tipoDocumento);
		
		log.info("Documento de Identificación: " + tipoDocumento.getNombre());
		log.info("Id Tipo: " + tipoDocumento.getTdocId());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	public void eTest() {
		assertNotNull(tipoDocumentoLogica,"El tipoDocumentoLogica esta nulo");
		List<TipoDocumento> tiposDocumento = tipoDocumentoLogica.consultarTodo();
		tiposDocumento.forEach(tipos -> {
			log.info("-------------------------------------------");
			log.info("Documento de Identificación: " + tipos.getNombre());
			log.info("Estado del Tipo Documento: " + tipos.getActivo());
			log.info("Id del Tipo Usuario: " + tipos.getTdocId());

		});
	}

}