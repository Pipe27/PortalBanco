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

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;

class ClienteJPATest {
	
	private final static Logger log=LoggerFactory.getLogger(ClienteJPATest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	static BigDecimal clieId=new BigDecimal(142020);
	
	@Test
	@DisplayName("CrearCliente")
	public void aTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		Cliente cliente=entityManager.find(Cliente.class,clieId);
		assertNull(cliente,"El cliente ya existe");
		
		cliente=new Cliente();
		cliente.setActivo('S');
		cliente.setClieId(clieId);
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homer J Simpson");
		cliente.setTelefono("555-5555-555");
		TipoDocumento tipoDocumento=entityManager.find(TipoDocumento.class, 3L);
		assertNotNull(tipoDocumento,"El tipo de documento es Nulo o no Existe");
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.getTransaction().begin();
			entityManager.persist(cliente);
		entityManager.getTransaction().commit();
	}
	
	@Test
	@DisplayName("ModificarCliente")
	public void cTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		Cliente cliente=entityManager.find(Cliente.class,clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		cliente.setActivo('S');
		cliente.setDireccion("Avenida Siempre Viva 123");
		cliente.setEmail("hsimpson@gmail.com");
		cliente.setNombre("Homer J Simpson M");
		cliente.setTelefono("555-5555-555");
		TipoDocumento tipoDocumento=entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento,"El tipo de documento es Nulo o no Existe");
		cliente.setTipoDocumento(tipoDocumento);
		
		entityManager.getTransaction().begin();
			entityManager.merge(cliente);
		entityManager.getTransaction().commit();
	}
	
	
	@Test
	@DisplayName("BorrarCliente")
	public void dTest() {
		assertNotNull(entityManager,"El entityManager esta nulo");
		Cliente cliente=entityManager.find(Cliente.class,clieId);
		assertNotNull(cliente,"El cliente no existe");
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();
	}
	@Test
	@DisplayName("ConsultarCliente")
	public void bTest() {
		assertNotNull(entityManager);
		Cliente cliente=entityManager.find(Cliente.class,clieId);
		assertNotNull(cliente);
		log.info(cliente.getNombre());
		log.info(cliente.getEmail());
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
