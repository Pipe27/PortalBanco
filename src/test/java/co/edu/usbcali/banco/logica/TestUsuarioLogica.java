package co.edu.usbcali.banco.logica;

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

import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestUsuarioLogica {

	private final static Logger log=LoggerFactory.getLogger(TestUsuarioLogica.class);
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITipoUsuarioLogica tipoUsuarioLogica;
	
	static String usuUsuario = new String ("jahelTo");
	static BigDecimal identificacion = new BigDecimal(1461);
	
	@Test
	@DisplayName("CrearUsuario")
	public void aTest() throws Exception{
		
		assertNotNull(usuarioLogica,"El usuarioLogica esta nulo");
		Usuario usuario= usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		assertNull(usuario,"Usuario existente");
		
		usuario=new Usuario();
		usuario.setActivo('S');
		usuario.setUsuUsuario(usuUsuario);
		usuario.setClave("pipei27");
		usuario.setIdentificacion(identificacion);
		usuario.setNombre("Felipe Ibarra");
		
		TipoUsuario tipoUsuario=tipoUsuarioLogica.consultarPorId(3L);
		assertNotNull(tipoUsuario,"El tipo de usuario es Nulo o no Existe");
		usuario.setTipoUsuario(tipoUsuario);
		
		usuarioLogica.grabar(usuario);
	}
	
	@Test
	@DisplayName("ModificarUsuario")
	public void cTest() throws Exception{
		assertNotNull(usuarioLogica,"El usuarioLogica esta nulo");
		Usuario usuario= usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario,"El usuario no existe");
		
		
		usuario.setActivo('S');
		usuario.setClave("appEmpresariales");
		usuario.setIdentificacion(identificacion);
		usuario.setNombre("Valeria Carrillo");
		
		TipoUsuario tipoUsuario=tipoUsuarioLogica.consultarPorId(3L);
		assertNotNull(tipoUsuario,"El tipo de usuario es Nulo o no Existe");
		usuario.setTipoUsuario(tipoUsuario);
		
		usuarioLogica.modificar(usuario);
	}
	
	@Test
	@DisplayName("BorrarUsuario")
	public void dTest() throws Exception{
		assertNotNull(usuarioLogica,"El usuarioLogica esta nulo");
		Usuario usuario= usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario,"El cliente no existe");
		
		usuarioLogica.borrar(usuario);
	}
	
	@Test
	@DisplayName("ConsultarUsuario")
	public void btest() {
		assertNotNull(usuarioLogica);
		Usuario usuario=usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		assertNotNull(usuario);
		
		log.info(usuario.getUsuUsuario());
		log.info(usuario.getNombre());
		
	}
	
	@Test
	@DisplayName("Consultar Todos")
	public void eTest() {
		assertNotNull(usuarioLogica,"El usuarioLogica esta nulo");
		List<Usuario> losUsuarios = usuarioLogica.consultarTodo();
		losUsuarios.forEach(usuario -> {
			log.info("-------------------------------------------");
			log.info("Nombre: " + usuario.getNombre());
			log.info("Nombre Usuario: " + usuario.getUsuUsuario());
			log.info("Identificación: " + usuario.getIdentificacion());
			log.info("Clave Usuario: " + usuario.getClave());
		});
	}

}