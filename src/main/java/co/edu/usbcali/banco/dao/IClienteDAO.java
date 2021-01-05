package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;

public interface IClienteDAO {
	
	public void grabar(Cliente cliente);
	public void modificar(Cliente cliente);
	public void borrar(Cliente cliente);
	public Cliente consultarPorId(BigDecimal clieId);
	public List<Cliente> consultarTodo();
	public List<Cuenta> buscarLasCuentasCliente(BigDecimal clieId);
	public List<Cuenta> buscarLasCuentasRegistradas(BigDecimal clieId);
	

}
