package co.edu.usbcali.banco.dao;


import java.util.List;

import co.edu.usbcali.banco.modelo.Cuenta;

public interface ICuentaDAO {
	
	public void grabar(Cuenta cuenta);
	public void modificar(Cuenta cuenta);
	public void borrar(Cuenta cuenta);
	public Cuenta consultarPorId(String cuenId);
	public List<Cuenta> consultarTodo();
}
