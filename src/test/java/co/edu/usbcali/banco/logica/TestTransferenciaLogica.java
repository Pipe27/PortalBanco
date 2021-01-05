/*package co.edu.usbcali.banco.logica;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestTransferenciaLogica {

	private final static Logger log = LoggerFactory.getLogger(TestRetirarLogica.class);
	
	@Autowired
	private ITransaccionBancariasLogica transaccionBancariasLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Autowired
	private IUsuarioLogica usuarioLogica;
	
	@Autowired
	private ITipoTransaccionLogica tipoTransaccionLogica;
	
	@Autowired
	private ITransaccionLogica transaccionLogica;

	static Date fechaRetiro = new Date();
	
	@Test
	@DisplayName("Transferencia Exitosa")
	public void transferenciaOk() throws Exception{
		String cuentaOrigenActiva = "0918-4472-2848-7283";
		String cuentaDestinoActiva = "3386-4862-5982-2134";
		String usuarioEnSesion = "pdoig70";
		Cuenta cuentaOriAct = cuentaLogica.consultarPorId(cuentaOrigenActiva);
		Cuenta cuentaDestAct = cuentaLogica.consultarPorId(cuentaDestinoActiva);
		Usuario usuEnSesion = usuarioLogica.consultarPorUsuUsuario(usuarioEnSesion);
		
		BigDecimal valorTransfe = new BigDecimal(500000);
		
		BigDecimal saldoCuentaOrigen = cuentaOriAct.getSaldo();
		BigDecimal saldoCuentaDestino = cuentaDestAct.getSaldo();
		
		log.info("SALDO ORIGEN ANTES: " + cuentaOriAct.getSaldo());
		log.info("SALDO DESTINO ANTES: " + cuentaOriAct.getSaldo());
		transaccionBancariasLogica.transferencia(cuentaOrigenActiva, cuentaDestinoActiva, valorTransfe, usuarioEnSesion);
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuentaOriAct.getCuenId());
		transaccion.setValor(valorTransfe);
		transaccion.setUsuario(usuEnSesion);
		transaccion.setTipoTransaccion(tipoTransaccionLogica.consultarPorTipoTraId(1L));
		transaccion.setFecha(fechaRetiro);
		transaccionLogica.grabar(transaccion);
		
		BigDecimal saldoFinal = new BigDecimal(Double.parseDouble(saldoCuenta.toString()) - Double.parseDouble(valorRetiro.toString()));
		cuenta.setSaldo(saldoFinal);
		cuentaLogica.modificar(cuenta);
		log.info("SALDO DESPUÉS: " + cuenta.getSaldo());
	}
	
	
}*/
