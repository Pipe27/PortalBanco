package co.edu.usbcali.banco.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usbcali.banco.dto.RetiroDTO;
import co.edu.usbcali.banco.logica.ICuentaLogica;
import co.edu.usbcali.banco.logica.ITransaccionBancariasLogica;
import co.edu.usbcali.banco.mapper.IRetiroMapper;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Transaccion;

@RestController
@RequestMapping("/retiro")
@CrossOrigin(origins="*")
public class RetiroRest {
	
	@Autowired
	private ITransaccionBancariasLogica transaccionBancariasLogica;
	
	@Autowired
	private IRetiroMapper retiroMapper;
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@PostMapping("/retiro")
	public String retirar(@RequestBody RetiroDTO retiroDTO)throws Exception{
		
		if(transaccionBancariasLogica.validarPass(retiroDTO.getCuenId(), retiroDTO.getClave())) {
			Transaccion transaccion = retiroMapper.retiroDTOToTransaccion(retiroDTO);
			String cuenta = transaccion.getCuenta().getCuenId();
			BigDecimal valor = transaccion.getValor();
			
			transaccionBancariasLogica.retirar(cuenta, valor, "Cajero-Automatico");
			Cuenta cuenta2 = cuentaLogica.consultarPorId(cuenta);
			String saldoFinal = String.valueOf(cuenta2.getSaldo());
			return saldoFinal;
			
		} else {
			throw new Exception("La clave ingresada no coincide con la contraseña de la cuenta digitada.");
		}
		
			
	}
}
