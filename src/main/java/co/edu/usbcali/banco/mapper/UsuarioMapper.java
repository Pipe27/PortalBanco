package co.edu.usbcali.banco.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dto.UsuarioDTO;
import co.edu.usbcali.banco.logica.ITipoUsuarioLogica;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

@Component
@Scope("singleton")
public class UsuarioMapper implements IUsuarioMapper {

	@Autowired
	ITipoUsuarioLogica tipoUsuarioLogica;
	
	@Override
	@Transactional(readOnly=true)
	public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setActivo(usuario.getActivo());
		usuarioDTO.setClave(usuario.getClave());
		usuarioDTO.setIdentificacion(usuario.getIdentificacion());
		usuarioDTO.setNombre(usuario.getNombre());
		usuarioDTO.setUsuUsuario(usuario.getUsuUsuario());
		
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(usuario.getTipoUsuario().getTiusId());
		usuarioDTO.setIdTipoUsuario(tipoUsuario.getTiusId());
		usuarioDTO.setNombreTipoUsuario(tipoUsuario.getNombre());
		return usuarioDTO;
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setActivo(usuarioDTO.getActivo());
		usuario.setClave(usuarioDTO.getClave());
		usuario.setIdentificacion(usuarioDTO.getIdentificacion());
		usuario.setNombre(usuarioDTO.getNombre());
		usuario.setUsuUsuario(usuarioDTO.getUsuUsuario());
		
		TipoUsuario tipoUsuario = tipoUsuarioLogica.consultarPorId(usuarioDTO.getIdTipoUsuario());
		usuario.setTipoUsuario(tipoUsuario);
		return usuario;
	}

	@Override
	@Transactional(readOnly=true)
	public List<UsuarioDTO> listaUsuarioToListaUsuarioDTO(List<Usuario> listaUsuario) {
		List<UsuarioDTO> listaUsuarioDTO = new ArrayList<>();
		for (Usuario usuario : listaUsuario) {
			listaUsuarioDTO.add(usuarioToUsuarioDTO(usuario));
		}
		return listaUsuarioDTO;
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> listaUsuarioDTOToListaUsuario(List<UsuarioDTO> listaUsuarioDTO) {
		List<Usuario> listaUsuario = new ArrayList<>();
		for (UsuarioDTO usuarioDTO : listaUsuarioDTO) {
			listaUsuario.add(usuarioDTOToUsuario(usuarioDTO));
		}
		return listaUsuario;
	}

}
