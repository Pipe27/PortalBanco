package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import antlr.collections.List;

import javax.persistence.Query;



import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;



class UsuarioJPATest {
	private final static Logger log=LoggerFactory.getLogger(UsuarioJPATest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	static String  usuId= new String("esfgtyjuvrk");
	
	
	
	@Test
	@DisplayName("CrearUsuario")
	public void aTest() {
		BigDecimal identificacion = new BigDecimal(123456);
		assertNotNull(entityManager,"El entityManager esta nulo");
		Usuario usuario=entityManager.find(Usuario.class,usuId);
		assertNull(usuario,"El usuario ya existe");
		
		usuario=new Usuario();
		
		usuario.setActivo('S');
		usuario.setUsuUsuario(usuId);
		usuario.setClave("asd123");
		usuario.setIdentificacion(identificacion);
		usuario.setNombre("Elkin");
		TipoUsuario tipoUsu=entityManager.find(TipoUsuario.class, 1L);
		assertNotNull(tipoUsu,"El tipo de usuario es Nulo o no Existe");
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ModificarUsuario")
	public void cTest() {
		
		BigDecimal identificacion = new BigDecimal(123456);
		assertNotNull(entityManager,"El entityManager esta nulo");
		Usuario usuario=entityManager.find(Usuario.class,usuId);
		assertNotNull(usuario,"El usuario no existe");
		
		usuario = new Usuario();
		usuario.setActivo('S');
		usuario.setUsuUsuario(usuId);
		usuario.setClave("asd123");
		usuario.setIdentificacion(identificacion);
		usuario.setNombre("Elkin");
		TipoUsuario tipoUsu=entityManager.find(TipoUsuario.class, 2L);
		assertNotNull(tipoUsu,"El tipo de usuario es Nulo o no Existe");
		

		
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("BorrarUsuario")
	public void dTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		Usuario usuario=entityManager.find(Usuario.class,usuId);
		assertNotNull(usuario,"El usuario no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ConsultarUsuario")
	public void bTest() {
		assertNotNull(entityManager);
		Usuario usuario=entityManager.find(Usuario.class,usuId);
		assertNotNull(usuario);
		log.info(usuario.getNombre());
		log.info(usuario.getClave());
		
	}
	@Test
	@DisplayName("consulta todos")
	public void etest() {
		assertNotNull(entityManager);
		java.util.List<Usuario> usuarios=entityManager.createQuery("Select usu from Usuario usu").getResultList();
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
