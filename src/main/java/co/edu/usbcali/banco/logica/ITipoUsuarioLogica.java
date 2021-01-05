package co.edu.usbcali.banco.logica;

import java.util.List;

import co.edu.usbcali.banco.modelo.TipoUsuario;

public interface ITipoUsuarioLogica {

	public void grabar(TipoUsuario tipoUsuario)throws Exception;
	public void modificar(TipoUsuario tipoUsuario)throws Exception;
	public void borrar(TipoUsuario tipoUsuario)throws Exception;
	public TipoUsuario consultarPorId(long tiusId);
	public List<TipoUsuario>consultarTodo();
	
}