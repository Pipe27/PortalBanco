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

import co.edu.usbcali.banco.modelo.TipoUsuario;



class TipoUsuarioJPATest {
private final static Logger log=LoggerFactory.getLogger(TipoUsuarioJPATest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	static Long tiusId=new Long(4);
	
	@Test
	@DisplayName("CrearTipoUsuario")
	public void aTest() {
		
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoUsuario tipoUsuario=entityManager.find(TipoUsuario.class,tiusId);
		assertNull(tipoUsuario,"El tipo usuario ya existe");
		
		tipoUsuario=new TipoUsuario();
		
		tipoUsuario.setActivo('S');
		tipoUsuario.setNombre("CONSULTOR");
		tipoUsuario.setTiusId(tiusId);
		
		
		
		entityManager.getTransaction().begin();
		entityManager.persist(tipoUsuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ModificarTipoUsuario")
	public void cTest() {
		
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoUsuario tipoUsuario=entityManager.find(TipoUsuario.class,tiusId);
		assertNotNull(tipoUsuario,"El tipo de usuario no existe");
		
		tipoUsuario = new TipoUsuario();
		tipoUsuario.setActivo('S');
		tipoUsuario.setNombre("CONSULTOR");
		tipoUsuario.setTiusId(tiusId);
		
		entityManager.getTransaction().begin();
		entityManager.merge(tipoUsuario);
		entityManager.getTransaction().commit();
	}

	
	@Test
	@DisplayName("BorrarTipoUsuario")
	public void dTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		TipoUsuario tipoUsuario=entityManager.find(TipoUsuario.class,tiusId);
		assertNotNull(tipoUsuario,"El tipo de usuario no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(tipoUsuario);
		entityManager.getTransaction().commit();
	}

	@Test
	@DisplayName("ConsultarUsuario")
	public void bTest() {
		assertNotNull(entityManager);
		TipoUsuario  tipoUsuario=entityManager.find(TipoUsuario.class,tiusId);
		assertNotNull(tipoUsuario);
		log.info(tipoUsuario.getNombre());
		
		
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
