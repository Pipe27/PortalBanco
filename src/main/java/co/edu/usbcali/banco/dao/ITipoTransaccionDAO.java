package co.edu.usbcali.banco.dao;


import java.util.List;

import co.edu.usbcali.banco.modelo.TipoTransaccion;

public interface ITipoTransaccionDAO {
	public void grabar(TipoTransaccion tipoTransaccion);
	public void modificar(TipoTransaccion tipoTransaccion);
	public void borrar(TipoTransaccion tipoTransaccion);
	public TipoTransaccion consultarPorTipoTraId(long titrId);
	public List<TipoTransaccion> consultarTodo();

}
