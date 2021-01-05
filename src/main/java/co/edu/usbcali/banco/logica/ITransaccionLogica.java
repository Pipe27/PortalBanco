package co.edu.usbcali.banco.logica;

import java.util.List;


import co.edu.usbcali.banco.modelo.Transaccion;

public interface ITransaccionLogica {
	
	public void grabar(Transaccion transaccion) throws Exception;
	public void modificar(Transaccion transaccion) throws Exception;
	public void borrar(Transaccion transaccion) throws Exception;
	public Transaccion consultarPorTransId(long tranId);
	public List<Transaccion> consultarTodo();
	public List<Transaccion> consultarRetiros(String cuenId);
	public List<Transaccion> consultarConsignaciones(String cuenId);
	public List<Transaccion> consultarTransferencias(String cuenId);
	public List<Transaccion> consultarPorUsu(String usuUsuario);

}
