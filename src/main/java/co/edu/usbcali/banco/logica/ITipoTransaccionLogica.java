package co.edu.usbcali.banco.logica;


import java.util.List;

import co.edu.usbcali.banco.modelo.TipoTransaccion;


public interface ITipoTransaccionLogica {
	
	public void grabar(TipoTransaccion tipoTransaccion) throws Exception;
	public void modificar(TipoTransaccion tipoTransaccion) throws Exception;
	public void borrar(TipoTransaccion tipoTransaccion) throws Exception;
	public TipoTransaccion consultarPorTipoTraId(long titrId);
	public List<TipoTransaccion> consultarTodo();

}
