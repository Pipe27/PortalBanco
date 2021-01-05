package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;

public interface IClienteLogica {
	
	public void grabar(Cliente cliente)throws Exception;
	public void modificar(Cliente cliente)throws Exception;
	public void borrar(Cliente cliente)throws Exception;
	
	public Cliente consultarPorId(BigDecimal clieId);
	public List<Cliente> consultarTodo();
	public List<Cuenta> buscarLasCuentasCliente(BigDecimal clieId);
	public List<Cuenta> buscarLasCuentasRegistradas(BigDecimal clieId);

}
