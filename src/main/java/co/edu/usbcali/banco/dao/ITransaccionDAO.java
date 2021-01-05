package co.edu.usbcali.banco.dao;

import java.util.List;

import co.edu.usbcali.banco.modelo.Transaccion;

public interface ITransaccionDAO {
	public void grabar(Transaccion transaccion);
	public void modificar(Transaccion transaccion);
	public void borrar(Transaccion transaccion);
	public Transaccion consultarPorTransId(long tranId);
	public List<Transaccion> consultarTodo();
	public List<Transaccion> consultarRetiros(String cuenId);
	public List<Transaccion> consultarConsignaciones(String cuenId);
	public List<Transaccion> consultarTransferencias(String cuenId);
	public List<Transaccion> consultarPorUsu(String usuUsuario);

}
