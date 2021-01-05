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
class TestRetirarLogica {

	private final static Logger log = LoggerFactory.getLogger(TestRetirarLogica.class);
	
	@Autowired
	private ITransaccionBancariasLogica transaccionBancariasLogica;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	static String user = "odanbi7d";
	static String userInac = "ssouthcoate4";
	static Date fechaRetiro = new Date();
	
	@Test
	@DisplayName("Retiro Exitoso")
	public void retiroOk() throws Exception{
		String cuenId = "0069-9507-9459-5342";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenId);
		BigDecimal valorRetiro = new BigDecimal(700000);
		
		log.info("SALDO ANTES: " + cuenta.getSaldo());
		transaccionBancariasLogica.retirar(cuenId, valorRetiro, user);

		log.info("SALDO DESPU�S: " + cuenta.getSaldo());
	}
	
	@Test
	@DisplayName("Fallo Retiro Negativo")
	public void retiroNegativo() throws Exception{
		String cuenIdN = "6544-8611-6302-4110";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenIdN);
		BigDecimal valorRetiroN = new BigDecimal(-200000);
		
		log.info("SALDO ANTES NEGATIVO: " + cuenta.getSaldo());
		transaccionBancariasLogica.retirar(cuenIdN, valorRetiroN, user);
		
		log.info("SALDO DESPU�S NEGATIVO: " + cuenta.getSaldo());
	}

	@Test
	@DisplayName("Fallo Retiro Superior")
	public void retiroMayor() throws Exception{
		String cuenIdN = "2702-8403-9856-5066";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenIdN);
		BigDecimal valorRetiroM = new BigDecimal(7389418.000);
		
		log.info("SALDO ANTES MAYOR: " + cuenta.getSaldo());
		transaccionBancariasLogica.retirar(cuenIdN, valorRetiroM, user);
		
		log.info("SALDO DESPU�S MAYOR: " + cuenta.getSaldo());
	}
	
	@Test
	@DisplayName("Fallo Retiro Cuenta No Activa")
	public void retiroCuentaInac() throws Exception{
		String cuenIdN = "2639-9433-6813-5967";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenIdN);
		BigDecimal valorRetiro = new BigDecimal(500000.000);
		
		log.info("SALDO ANTES MAYOR: " + cuenta.getSaldo());
		transaccionBancariasLogica.retirar(cuenIdN, valorRetiro, user);
		
		log.info("SALDO DESPU�S MAYOR: " + cuenta.getSaldo());
	}
	
	@Test
	@DisplayName("Fallo Retiro Usuario No Activo")
	public void retiroUsuarioInac() throws Exception{
		String cuenIdN = "6473-3225-8322-1257";
		Cuenta cuenta = cuentaLogica.consultarPorId(cuenIdN);
		BigDecimal valorRetiro = new BigDecimal(500000.000);
		
		log.info("SALDO ANTES MAYOR: " + cuenta.getSaldo());
		transaccionBancariasLogica.retirar(cuenIdN, valorRetiro, userInac);
		

		log.info("SALDO DESPU�S MAYOR: " + cuenta.getSaldo());
	}
}
