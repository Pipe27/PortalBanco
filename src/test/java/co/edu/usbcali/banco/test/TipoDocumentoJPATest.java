package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.modelo.TipoDocumento;




class TipoDocumentoJPATest {

private final static Logger log=LoggerFactory.getLogger(TipoDocumentoJPATest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	static Long tdocId=new Long(4);
	
	
	
	@Test
	@DisplayName("CrearTipoDocumento")
	public void aTest() {
		
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoDocumento tipoDocumento=entityManager.find(TipoDocumento.class,tdocId);
		assertNull(tipoDocumento,"El tipo de documento ya existe");
		
		tipoDocumento=new TipoDocumento();
		
		tipoDocumento.setActivo('S');
		tipoDocumento.setNombre("VISA");
		tipoDocumento.setTdocId(tdocId);
		
		
		
		entityManager.getTransaction().begin();
		entityManager.persist(tipoDocumento);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ModificarTipoDocumento")
	public void cTest() {
		
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoDocumento tipoDocumento=entityManager.find(TipoDocumento.class,tdocId);
		assertNotNull(tipoDocumento,"El tipo de documento no existe");
		
		tipoDocumento = new TipoDocumento();
		tipoDocumento.setActivo('S');
		tipoDocumento.setNombre("VISA");
		tipoDocumento.setTdocId(tdocId);
		
		entityManager.getTransaction().begin();
		entityManager.merge(tipoDocumento);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarTipoDocumento")
	public void dTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoDocumento tipoDocumento=entityManager.find(TipoDocumento.class,tdocId);
		assertNotNull(tipoDocumento,"El tipo de documentacion no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(tipoDocumento);
		entityManager.getTransaction().commit();
	}
	
	
	@Test
	@DisplayName("ConsultaTipoDocumento")
	public void bTest() {
		assertNotNull(entityManager);
		TipoDocumento tipoDocumento=entityManager.find(TipoDocumento.class,tdocId);
		assertNotNull(tipoDocumento);
		log.info(tipoDocumento.getNombre());
		
	}
	
	@BeforeAll
	public static void inicializarTodo() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persitencia");
		entityManager=entityManagerFactory.createEntityManager();
	}	
	@AfterAll
	public static void cerrarTodo() {
		entityManager.close();
		entityManagerFactory.close();
	}
}
