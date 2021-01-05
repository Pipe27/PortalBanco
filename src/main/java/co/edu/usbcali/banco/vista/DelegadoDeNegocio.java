package co.edu.usbcali.banco.vista;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import co.edu.usbcali.banco.logica.IClienteLogica;
import co.edu.usbcali.banco.logica.ICuentaLogica;
import co.edu.usbcali.banco.logica.ICuentaRegistradaLogica;
import co.edu.usbcali.banco.logica.ITipoDocumentoLogica;
import co.edu.usbcali.banco.logica.ITipoTransaccionLogica;
import co.edu.usbcali.banco.logica.ITipoUsuarioLogica;
import co.edu.usbcali.banco.logica.ITransaccionBancariasLogica;
import co.edu.usbcali.banco.logica.ITransaccionLogica;
import co.edu.usbcali.banco.logica.IUsuarioLogica;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

@Component
@Scope("singleton")
public class DelegadoDeNegocio implements IDelegadoDeNegocio {
	@Autowired
	private IClienteLogica clienteLogica;
	
	@Autowired
	private ITipoDocumentoLogica tipoDocumentoLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITransaccionLogica transaccionLogica;
	
	@Autowired
	private ITipoTransaccionLogica tipoTransaccionLogica;
	
	@Autowired
	private ITipoUsuarioLogica  tipoUsuarioLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private ICuentaRegistradaLogica cuentaRegistradaLogica;
	
	@Autowired
	private ITransaccionBancariasLogica transaccionBancariaLogica;	
	
	@Override
	public void grabarCliente(Cliente cliente) throws Exception {
		clienteLogica.grabar(cliente);		
	}

	@Override
	public void modificarCliente(Cliente cliente) throws Exception {
		clienteLogica.modificar(cliente);		
	}

	@Override
	public void borrarCliente(Cliente cliente) throws Exception {
		clienteLogica.borrar(cliente);		
	}

	@Override
	public Cliente consultarClientePorId(BigDecimal clieId) {
		return clienteLogica.consultarPorId(clieId);
	}

	@Override
	public List<Cliente> consultarClienteTodo() {
		return clienteLogica.consultarTodo();
	}

	@Override
	public void grabarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.grabar(tipoDocumento);			
	}

	@Override
	public void modificarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.modificar(tipoDocumento);		
	}

	@Override
	public void borrarTipoDocumento(TipoDocumento tipoDocumento) throws Exception {
		tipoDocumentoLogica.borrar(tipoDocumento);		
	}

	@Override
	public TipoDocumento consultarTipoDocumentoPorId(long tdocId) {
		return tipoDocumentoLogica.consultarPorId(tdocId);
	}

	@Override
	public List<TipoDocumento> consultarTipoDocumentoTodo() {
		return tipoDocumentoLogica.consultarTodo();
	}

	@Override
	public List<Usuario> consultarUsuarioTodo() {
		return usuarioLogica.consultarTodo();
	}
	
	@Override
	public Long identificacionUsuario(BigDecimal identificacion) {
		return usuarioLogica.identificacionUsuario(identificacion);
	}

	@Override
	public Transaccion consultarTransaccionPorId(long tranId) {
		return transaccionLogica.consultarPorTransId(tranId);
	}

	@Override
	public List<Transaccion> consultarTransaccionTodo() {
		return transaccionLogica.consultarTodo();
	}

	@Override
	public void grabarTransaccion(Transaccion transaccion) throws Exception {
		transaccionLogica.grabar(transaccion);
	}

	@Override
	public void modificarTransaccion(Transaccion transaccion) throws Exception {
		transaccionLogica.modificar(transaccion);
	}

	@Override
	public void borrarTransaccion(Transaccion transaccion) throws Exception {
		transaccionLogica.modificar(transaccion);
	}

	@Override
	public void grabarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception {
		tipoTransaccionLogica.grabar(tipoTransaccion);
	}

	@Override
	public void modificarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception {
		tipoTransaccionLogica.modificar(tipoTransaccion);
	}

	@Override
	public void borrarTipoTransaccion(TipoTransaccion tipoTransaccion) throws Exception {
		tipoTransaccionLogica.borrar(tipoTransaccion);
	}

	@Override
	public TipoTransaccion consultarTipoTransaccionPorId(long titrId) {
		return tipoTransaccionLogica.consultarPorTipoTraId(titrId);
	}

	@Override
	public List<TipoTransaccion> consultarTipoTransaccionTodo() {
		return tipoTransaccionLogica.consultarTodo();
	}

	@Override
	public void grabarUsuario(Usuario usuario) throws Exception {
		usuarioLogica.grabar(usuario);
	}

	@Override
	public void modificarUsuario(Usuario usuario) throws Exception {
		usuarioLogica.modificar(usuario);
	}

	@Override
	public void borrarUsuario(Usuario usuario) throws Exception {
		usuarioLogica.borrar(usuario);
	}

	@Override
	public Usuario consultarUsuarioPorId(String usuUsuario) {
		return usuarioLogica.consultarPorUsuUsuario(usuUsuario);
	}

	@Override
	public void grabarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.grabar(tipoUsuario);
	}

	@Override
	public void modificarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.modificar(tipoUsuario);
	}

	@Override
	public void borrarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
		tipoUsuarioLogica.borrar(tipoUsuario);
	}

	@Override
	public TipoUsuario consultarTipoUsuarioPorId(long tiusId) {
		return tipoUsuarioLogica.consultarPorId(tiusId);
	}

	@Override
	public List<TipoUsuario> consultarTipoUsuarioTodo() {		
		return tipoUsuarioLogica.consultarTodo();
	}

	@Override
	public void grabarCuenta(Cuenta cuenta) throws Exception {
		cuentaLogica.grabar(cuenta);
	}

	@Override
	public void modificarCuenta(Cuenta cuenta) throws Exception {
		cuentaLogica.modificar(cuenta);
	}

	@Override
	public void borrarCuenta(Cuenta cuenta) throws Exception {
		cuentaLogica.borrar(cuenta);
	}

	@Override
	public Cuenta consultarCuentaPorId(String cuenId) {
		return cuentaLogica.consultarPorId(cuenId);
	}

	@Override
	public List<Cuenta> consultarCuentaTodo() {
		return cuentaLogica.consultarTodo();
	}

	@Override
	public void grabarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception {
		cuentaRegistradaLogica.grabar(cuentaRegistrada);
	}

	@Override
	public void modificarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception {
		cuentaRegistradaLogica.modificar(cuentaRegistrada);
	}

	@Override
	public void borrarCuentaRegistrada(CuentaRegistrada cuentaRegistrada) throws Exception {
		cuentaRegistradaLogica.borrar(cuentaRegistrada);
	}

	@Override
	public CuentaRegistrada consultarCuentaRegistradaPorId(long cureId) {
		return cuentaRegistradaLogica.consultarPorId(cureId);
	}

	@Override
	public List<CuentaRegistrada> consultarCuentaRegistradaTodo() {
		return cuentaRegistradaLogica.consultarTodo();
	}

	@Override
	public void retirar(String cuenId, BigDecimal valorRetiro, String usuUsuario) throws Exception {
		transaccionBancariaLogica.retirar(cuenId, valorRetiro, usuUsuario);
		
	}

	@Override
	public void consignar(String cuenId, BigDecimal valorConsig, String usuUsuario) throws Exception {
		transaccionBancariaLogica.consignar(cuenId, valorConsig, usuUsuario);
		
	}

	@Override
	public void transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valorTrans, String usuUsuario) throws Exception {
		transaccionBancariaLogica.transferencia(cuenIdOrigen, cuenIdDestino, valorTrans, usuUsuario);
		
	}

	@Override
	public void transferenciaAlmacenaBD(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		transaccionBancariaLogica.transferenciaAlmacenaBD(cuenId, valor, usuUsuario);
		
	}

	@Override
	public Long consultarCuenExistente(String cuenId, BigDecimal clieId) {
		return cuentaRegistradaLogica.consultarCuenExistente(cuenId, clieId);
	}

	@Override
	public List<Cuenta> buscarLasCuentasCliente(BigDecimal clieId) {
		return clienteLogica.buscarLasCuentasCliente(clieId);
	}

	@Override
	public List<Cuenta> buscarLasCuentasRegistradas(BigDecimal clieId) {
		return clienteLogica.buscarLasCuentasRegistradas(clieId);
	}

	@Override
	public List<Transaccion> consultarRetiros(String cuenId) {
		return transaccionLogica.consultarRetiros(cuenId);
	}

	@Override
	public List<Transaccion> consultarConsignaciones(String cuenId) {
		return transaccionLogica.consultarConsignaciones(cuenId);
	}

	@Override
	public List<Transaccion> consultarTransferencias(String cuenId) {
		return transaccionLogica.consultarTransferencias(cuenId);
	}

	@Override
	public List<Transaccion> consultarPorUsu(String usuUsuario) {
		return transaccionLogica.consultarPorUsu(usuUsuario);
	}
}
