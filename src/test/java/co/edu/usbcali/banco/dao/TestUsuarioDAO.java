package co.edu.usbcali.banco.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
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
import co.edu.usbcali.banco.modelo.Usuario;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestUsuarioDAO {
	
	private final static Logger log=LoggerFactory.getLogger(TestUsuarioDAO.class);
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	static String usuUsuario = new String ("jahelTo");
	static BigDecimal identificacion = new BigDecimal(1461);
	
	@Test
	@DisplayName("CrearUsuario")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void aTest() {
		assertNotNull(usuarioDAO,"El usuarioDAO esta nulo");
		Usuario usuario= usuarioDAO.consultarPorUsuUsuario(usuUsuario);
		assertNull(usuario,"Usuario existente");
		
		usuario=new Usuario();
		usuario.setActivo('S');
		usuario.setUsuUsuario(usuUsuario);
		usuario.setClave("pipei27");
		usuario.setIdentificacion(identificacion);
		usuario.setNombre("Felipe Ibarra");
		
		TipoUsuario tipoUsuario=tipoUsuarioDAO.consultarPorId(2L);
		assertNotNull(tipoUsuario,"El tipo de usuario es Nulo o no Existe");
		usuario.setTipoUsuario(tipoUsuario);
		
		usuarioDAO.grabar(usuario);
	}
	
	@Test
	@DisplayName("ModificarUsuario")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void cTest() {
		assertNotNull(usuarioDAO,"El usuarioDAO esta nulo");
		Usuario usuario= usuarioDAO.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario,"El usuario no existe");
		
		
		usuario.setActivo('S');
		usuario.setClave("appEmpresariales");
		usuario.setIdentificacion(identificacion);
		usuario.setNombre("Valeria Carrillo");
		
		TipoUsuario tipoUsuario=tipoUsuarioDAO.consultarPorId(3L);
		assertNotNull(tipoUsuario,"El tipo de usuario es Nulo o no Existe");
		usuario.setTipoUsuario(tipoUsuario);
		
		usuarioDAO.modificar(usuario);
	}
	
	@Test
	@DisplayName("BorrarUsuario")
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED)
	public void dTest() {
		assertNotNull(usuarioDAO,"El usuarioDAO esta nulo");
		Usuario usuario= usuarioDAO.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario,"El cliente no existe");
		
		usuarioDAO.borrar(usuario);
	}
	
	@Test
	@DisplayName("ConsultarUsuario")
	@Transactional(readOnly=true)
	public void btest() {
		assertNotNull(usuarioDAO);
		Usuario usuario=usuarioDAO.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario);
		
		log.info(usuario.getUsuUsuario());
		log.info(usuario.getNombre());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	@Transactional(readOnly=true)
	public void eTest() {
		assertNotNull(usuarioDAO,"El usuarioDAO esta nulo");
		List<Usuario> losUsuarios = usuarioDAO.consultarTodo();
		losUsuarios.forEach(usuario -> {
			log.info("-------------------------------------------");
			log.info("Nombre: " + usuario.getNombre());
			log.info("Nombre Usuario: " + usuario.getUsuUsuario());
			log.info("Identificación: " + usuario.getIdentificacion());
			log.info("Clave Usuario: " + usuario.getClave());
		});
	}

}