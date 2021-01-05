package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.Usuario;
;

public interface IUsuarioDAO {
	public void grabar(Usuario usuario);
	public void modificar(Usuario usuario);
	public void borrar(Usuario usuario);
	public Usuario consultarPorUsuUsuario(String usuUsuario);
	public List<Usuario> consultarTodo();
	public Long identificacionUsuario(BigDecimal identificacion);

}
