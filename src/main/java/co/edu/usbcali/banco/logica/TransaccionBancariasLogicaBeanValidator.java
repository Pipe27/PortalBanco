package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

@Service
@Scope("singleton")
public class TransaccionBancariasLogicaBeanValidator implements ITransaccionBancariasLogica{
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITransaccionLogica transaccionLogica;
	
	@Autowired
	private ITipoTransaccionLogica tipoTransaccionLogica;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void retirar(String cuenId, BigDecimal valorRetiro, String usuUsuario) throws Exception {
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		Usuario usuario = usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		BigDecimal saldoCuenta = cuenta.getSaldo();
		
		if(cuenta == null) {
			throw new Exception("La cuenta a retirar no existe");
		}
		if (cuenta.getActiva() == 'N') {
			throw new Exception("Esta cuenta no se encuentra activa");
		}
		if (usuario.getActivo() == 'N') {
			throw new Exception("Este usuario no se encuentra activo");
		}
		if (Double.parseDouble(valorRetiro.toString())== 0) {
			throw new Exception("El valor no puede ser cero");
		}
		if (Double.parseDouble(valorRetiro.toString())<0) {
			throw new Exception("El valor no puede ser negativo");
		}
		if (Double.parseDouble(valorRetiro.toString())>=Double.parseDouble(cuenta.getSaldo().toString())) {
			throw new Exception("No posee fondos suficientes para realizar el retiro");
		}else {
			Date fechaMovimiento = new Date();
			BigDecimal saldoFinal = saldoCuenta.subtract(valorRetiro);
			cuenta.setSaldo(saldoFinal);
			cuentaLogica.modificar(cuenta);
			
			Transaccion transaccion = new Transaccion();
			transaccion.setCuenta(cuenta);
			TipoTransaccion tipoTransaccion = tipoTransaccionLogica.consultarPorTipoTraId(1);
			transaccion.setTipoTransaccion(tipoTransaccion);
			transaccion.setUsuario(usuario);
			transaccion.setValor(valorRetiro);
			transaccion.setFecha(fechaMovimiento);
			transaccionLogica.grabar(transaccion);
			
			cuenta.setSaldo(saldoFinal);
			cuentaLogica.modificar(cuenta);
		}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void consignar(String cuenId, BigDecimal valorConsig, String usuUsuario) throws Exception {
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		Usuario usuario = usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		BigDecimal saldoCuenta = cuenta.getSaldo();
		
		if(cuenta == null) {
			throw new Exception("La cuenta origen no puede ser nula");
		}
		if (Double.parseDouble(valorConsig.toString())== 0) {
			throw new Exception("El valor no puede ser cero");
		}
		if (Double.parseDouble(valorConsig.toString())<0) {
			throw new Exception("El valor no puede ser negativo");
		}
		if (usuario.getActivo() == 'N') {
			throw new Exception("Este usuario no se encuentra activo");
		} else {
			Date fechaMovimiento = new Date();
			BigDecimal saldoFinal = saldoCuenta.add(valorConsig);
			cuenta.setSaldo(saldoFinal);
			cuentaLogica.modificar(cuenta);
			
			Transaccion transaccion = new Transaccion();
			transaccion.setCuenta(cuenta);
			TipoTransaccion tipoTransaccion = tipoTransaccionLogica.consultarPorTipoTraId(2);
			transaccion.setTipoTransaccion(tipoTransaccion);
			transaccion.setUsuario(usuario);
			transaccion.setValor(valorConsig);
			transaccion.setFecha(fechaMovimiento);
			transaccionLogica.grabar(transaccion);
			
			cuenta.setSaldo(saldoFinal);
			String salditoFinal = String.valueOf(saldoFinal);
			if(Double.parseDouble(salditoFinal) < 200000 && cuenta.getActiva() == 'N') {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "La consignación ha sido exitosa, pero la cuenta sigue innactiva.", ""));
			}
			if(Double.parseDouble(salditoFinal) >= 200000 && cuenta.getActiva() == 'N') {
				cuenta.setActiva('S');
				cuentaLogica.modificar(cuenta);
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La cuenta ha sido Activada", ""));
			}
			cuentaLogica.modificar(cuenta);
		}
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valorTrans, String usuUsuario) throws Exception {
		Cuenta cuentaOrigen = cuentaLogica.consultarPorId(cuenIdOrigen);
		Cuenta cuenDestino = cuentaLogica.consultarPorId(cuenIdDestino);
		Usuario usuarioDestino = usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		BigDecimal costoTrans = new BigDecimal(900);
		BigDecimal saldoCuentaOrigen = cuentaOrigen.getSaldo();
		
		if(cuenIdOrigen == null) {
			throw new Exception("La cuenta origen no puede ser nula");
		}
		if (cuenDestino == null) {
			throw new Exception("La cuenta destino no puede ser nula");
		}
		if(cuentaOrigen.getCuenId() == cuenDestino.getCuenId()) {
			throw new Exception("La cuenta origen y la destino no puede ser las mismas");
		}
		if (Double.parseDouble(valorTrans.toString())<0) {
			throw new Exception("El valor no puede ser negativo");
		}
		if (Double.parseDouble(valorTrans.toString())+ Double.parseDouble(costoTrans.toString()) > Double.parseDouble(saldoCuentaOrigen.toString())) {
			throw new Exception("No posee fondos suficientes para esta transacción");
		}
		if (usuarioDestino.getActivo() == 'N') {
			throw new Exception("El usuario destino no se encuentra activo");
		}
		if (cuentaOrigen.getActiva() == 'N') {
			throw new Exception("La cuenta origen no se encuentra activa");
		}
		if (cuenDestino.getActiva() == 'N') {
			throw new Exception("La cuenta destino no se encuentra activa");
		} else {
			
			retirar(cuenIdOrigen, valorTrans, usuUsuario);
			retirar(cuenIdOrigen, costoTrans, usuUsuario);
			consignar(cuenIdDestino, valorTrans, usuUsuario);
			consignar("9999-9999-9999-9999", costoTrans, usuUsuario);
			transferenciaAlmacenaBD(cuenIdOrigen, valorTrans, usuUsuario);
			transferenciaAlmacenaBD(cuenIdDestino, valorTrans, usuUsuario);
		}
	}
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void transferenciaAlmacenaBD(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		Usuario usuario= usuarioLogica.consultarPorUsuUsuario(usuUsuario);
		
		if(cuenta==null) {
			throw new Exception("La cuenta no puede ser nula");
		}
		if(usuario.getActivo()=='N') {
			throw new Exception("El usuario no se encuentra activo");
		}
		if(Double.parseDouble(valor.toString())<0) {
			throw new Exception("El valor no puede ser negativo");
		}
		if(cuenta.getActiva()=='N') {
			throw new Exception("La cuenta no se encuentra activa");		
		}
		
		else {
			Date fechaTrans = new Date();
			Transaccion transaccion= new Transaccion();
			transaccion.setCuenta(cuenta);
			TipoTransaccion tipoTransaccion = tipoTransaccionLogica.consultarPorTipoTraId(3);
			transaccion.setTipoTransaccion(tipoTransaccion);
			transaccion.setUsuario(usuario);
			transaccion.setValor(valor);
			transaccion.setFecha(fechaTrans);
			transaccionLogica.grabar(transaccion);	
		}
	}

	@Override
	@Transactional(readOnly=true)
	public boolean validarPass(String cuenId, String clave){
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		String password = cuenta.getClave();
		
		if(!clave.equals(password)) {
			return false;
		} else {
		return true;
		}
	}
}