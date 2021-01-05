package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PruebaBasica {

	private final static Logger log=LoggerFactory.getLogger(PruebaBasica.class);
	
	@BeforeEach
	public  void antesDeCadaPrueba() {
		log.info("Se ejecuto antesDeCadaPrueba");
	}
	
	@AfterEach
	public  void despuesDeCadaPrueba() {
		log.info("Se ejecuto despuesDeCadaPrueba");
	}
	
	
	@Test
	@DisplayName("Consultar")
	public void pruebita() {
		log.info("Se ejecuto pruebita");		
	}
	
	
	@Test
	@DisplayName("Crear")
	public void pruebitaDos() {
		log.info("Se ejecuto pruebitaDos");		
	}
	
	
	@BeforeAll
	public static void antesDeTodo() {
		log.info("Se ejecuto AntesDeTodo");		
	}
	
	@AfterAll
	public static void despuesDeTodo() {
		log.info("Se ejecuto despuesDeTodo");		
	}

}
