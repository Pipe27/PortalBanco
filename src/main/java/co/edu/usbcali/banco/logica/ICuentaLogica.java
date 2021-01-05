package co.edu.usbcali.banco.logica;

import java.util.List;

import co.edu.usbcali.banco.modelo.Cuenta;

public interface ICuentaLogica {
	
	public void grabar(Cuenta cuenta) throws Exception;
	public void modificar(Cuenta cuenta) throws Exception;
	public void borrar(Cuenta cuenta) throws Exception;
	public Cuenta consultarPorId(String cuenId);
	public List<Cuenta> consultarTodo();
	
}
