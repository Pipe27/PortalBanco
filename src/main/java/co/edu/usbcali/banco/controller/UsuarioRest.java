package co.edu.usbcali.banco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.banco.dto.UsuarioDTO;
import co.edu.usbcali.banco.logica.IUsuarioLogica;
import co.edu.usbcali.banco.mapper.IUsuarioMapper;
import co.edu.usbcali.banco.modelo.Usuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioRest {
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private IUsuarioMapper usuarioMapper;
	
	@GetMapping("/consultarUsuarioPorUsuUsuario/{usuUsuario}")
	public UsuarioDTO consultarPorUsuUsuario(@PathVariable("usuUsuario") String usuUsuario) {
		Usuario usuario = usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		UsuarioDTO usuarioDTO = usuarioMapper.usuarioToUsuarioDTO(usuario);
		return usuarioDTO;
	}
	
	@GetMapping("/consultarTodosLosUsuarios/")
	public List<UsuarioDTO> consultarTodos(){
		List<Usuario> listaUsuario = usuarioLogica.consultarTodo();
		List<UsuarioDTO> listaUsuarioDTO = usuarioMapper.listaUsuarioToListaUsuarioDTO(listaUsuario);
		return listaUsuarioDTO;
	}
	
	@PostMapping("/crearUsuario")
	public void crearUsuario(@RequestBody UsuarioDTO usuarioDTO)throws Exception{
		Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
		usuarioLogica.grabar(usuario);
	}
	
	@PutMapping("/modificarUsuario")
	public void modificarUsuario(@RequestBody UsuarioDTO usuarioDTO)throws Exception{
		Usuario usuario = usuarioMapper.usuarioDTOToUsuario(usuarioDTO);
		usuarioLogica.modificar(usuario);
	}
	
	@DeleteMapping("/borrarUsuario/{usuUsuario}")
	public void borrarUsuario(@PathVariable("usuUsuario") String usuUsuario)throws Exception{
		Usuario usuario = usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		usuarioLogica.borrar(usuario);
	}
}
