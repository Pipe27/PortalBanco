package co.edu.usbcali.banco.logica;

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

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContext.xml")
@Rollback(false)
class TestConsignarLogica {

	private final static Logger log = LoggerFactory.getLogger(TestConsignarLogica.class);
	
	@Autowired
	private ITransaccionBancariasLogica transaccionBancariasLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	static String user = "odanbi7d";
	static String userInac = "ssouthcoate4";
	static Date fechaConsig = new Date();
	
	@Test
	@DisplayName("Consignación Exitosa")
	public void ConsignacionOk() throws Exception{
		String cuenId = "1747-5102-2907-6287";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		BigDecimal valorRetiro = new BigDecimal(500000);
		
		log.info("SALDO ANTES: " + cuenta.getSaldo());
		transaccionBancariasLogica.consignar(cuenId, valorRetiro, user);
		
		log.info("SALDO DESPUÉS: " + cuenta.getSaldo());
	}
	
	@Test
	@DisplayName("Fallo Consignación Negativo")
	public void consignacionNegativa() throws Exception{
		String cuenIdN = "6544-8611-6302-4110";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenIdN);
		BigDecimal valorRetiroN = new BigDecimal(-200000);
		
		log.info("SALDO ANTES NEGATIVO: " + cuenta.getSaldo());
		transaccionBancariasLogica.consignar(cuenIdN, valorRetiroN, user);
		
		log.info("SALDO DESPUÉS NEGATIVO: " + cuenta.getSaldo());
	}

	@Test
	@DisplayName("Fallo Consginación Cuenta No Activa")
	public void consignacionCuentaInac() throws Exception{
		String cuenIdN = "2639-9433-6813-5967";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenIdN);
		BigDecimal valorRetiro = new BigDecimal(500000.000);
		
		log.info("SALDO ANTES MAYOR: " + cuenta.getSaldo());
		transaccionBancariasLogica.consignar(cuenIdN, valorRetiro, user);
		
		log.info("SALDO DESPUÉS MAYOR: " + cuenta.getSaldo());
	}
	
	@Test
	@DisplayName("Fallo Consginación Usuario No Activo")
	public void consignacionUsuarioInac() throws Exception{
		String cuenIdN = "6473-3225-8322-1257";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenIdN);
		BigDecimal valorRetiro = new BigDecimal(500000.000);
		
		log.info("SALDO ANTES MAYOR: " + cuenta.getSaldo());
		transaccionBancariasLogica.consignar(cuenIdN, valorRetiro, userInac);
		
		log.info("SALDO DESPUÉS MAYOR: " + cuenta.getSaldo());
	}
}
