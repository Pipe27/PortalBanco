package co.edu.usbcali.banco.dao;


import java.util.List;

import co.edu.usbcali.banco.modelo.TipoUsuario;

public interface ITipoUsuarioDAO {
	public void grabar(TipoUsuario tipoUsuario);
	public void modificar(TipoUsuario tipoUsuario);
	public void borrar(TipoUsuario tipoUsuario);
	public TipoUsuario consultarPorId(long tiusId);
	public List<TipoUsuario> consultarTodo();
}
