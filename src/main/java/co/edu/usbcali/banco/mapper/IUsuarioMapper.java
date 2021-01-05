package co.edu.usbcali.banco.mapper;

import java.util.List;

import co.edu.usbcali.banco.dto.UsuarioDTO;
import co.edu.usbcali.banco.modelo.Usuario;

public interface IUsuarioMapper {
	public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
	public Usuario usuarioDTOToUsuario(UsuarioDTO usuarioDTO);
	
	public List<UsuarioDTO> listaUsuarioToListaUsuarioDTO(List<Usuario> listaUsuario);
	public List<Usuario> listaUsuarioDTOToListaUsuario(List<UsuarioDTO> listaUsuarioDTO);
}
