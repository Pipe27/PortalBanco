package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

public interface IDelegadoDeNegocio {
	
	public void grabarCliente(Cliente cliente) throws Exception;	
	public void modificarCliente(Cliente cliente) throws Exception;	
	public void borrarCliente(Cliente cliente)  throws Exception;	
	public Cliente consultarClientePorId(BigDecimal clieId);	
	public List<Cliente> consultarClienteTodo();
	public List<Cuenta> buscarLasCuentasCliente(BigDecimal clieId);
	public List<Cuenta> buscarLasCuentasRegistradas(BigDecimal clieId);
	
	public void grabarTipoDocumento(TipoDocumento tipoDocumento) throws Exception;	
	public void modificarTipoDocumento(TipoDocumento tipoDocumento) throws Exception;	
	public void borrarTipoDocumento(TipoDocumento tipoDocumento)  throws Exception;	
	public TipoDocumento consultarTipoDocumentoPorId(long tdocId);
	public List<TipoDocumento> consultarTipoDocumentoTodo();
	
	public void grabarUsuario(Usuario usuario)throws Exception;
	public void modificarUsuario(Usuario usuario)throws Exception;
	public void borrarUsuario(Usuario usuario)throws Exception;
	public Usuario consultarUsuarioPorId(String usuUsuario);
	public List<Usuario>consultarUsuarioTodo();
	public Long identificacionUsuario(BigDecimal identificacion);
	
	public void grabarTransaccion(Transaccion transaccion) throws Exception;
	public void modificarTransaccion(Transaccion transaccion)  throws Exception;
	public void borrarTransaccion(Transaccion transaccion)  throws Exception;
	public Transaccion consultarTransaccionPorId(long tranId);
	public List<Transaccion> consultarTransaccionTodo();
	public List<Transaccion> consultarRetiros(String cuenId);
	public List<Transaccion> consultarConsignaciones(String cuenId);
	public List<Transaccion> consultarTransferencias(String cuenId);
	public List<Transaccion> consultarPorUsu(String usuUsuario);
	
	public void grabarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception;
	public void modificarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception;
	public void borrarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception;
	public TipoTransaccion consultarTipoTransaccionPorId(long titrId);
	public List<TipoTransaccion> consultarTipoTransaccionTodo();
	
	public void grabarTipoUsuario(TipoUsuario tipoUsuario)throws Exception;
	public void modificarTipoUsuario(TipoUsuario tipoUsuario)throws Exception;
	public void borrarTipoUsuario(TipoUsuario tipoUsuario)throws Exception;
	public TipoUsuario consultarTipoUsuarioPorId(long tiusId);
	public List<TipoUsuario>consultarTipoUsuarioTodo();
	
	public void grabarCuenta(Cuenta cuenta) throws Exception;
	public void modificarCuenta(Cuenta cuenta) throws Exception;
	public void borrarCuenta(Cuenta cuenta) throws Exception;
	public Cuenta consultarCuentaPorId(String cuenId);
	public List<Cuenta> consultarCuentaTodo();
	
	public void grabarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception;
	public void modificarCuentaRegistrada(CuentaRegistrada cuentaRegistrada)throws Exception;
	public void borrarCuentaRegistrada(CuentaRegistrada cuentaRegistrada)throws Exception;
	public CuentaRegistrada consultarCuentaRegistradaPorId(long cureId);
	public Long consultarCuenExistente(String cuenId, BigDecimal clieId);
	public List<CuentaRegistrada> consultarCuentaRegistradaTodo();

	public void retirar(String cuenId, BigDecimal valorRetiro, String usuUsuario) throws Exception;
	public void consignar(String cuenId, BigDecimal valorConsig, String usuUsuario) throws Exception;
	public void transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valorTrans, String usuUsuario) throws Exception;
	public void transferenciaAlmacenaBD(String cuenId, BigDecimal valor, String usuUsuario) throws Exception;
	
}
