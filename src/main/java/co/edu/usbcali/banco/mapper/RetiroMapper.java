package co.edu.usbcali.banco.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import co.edu.usbcali.banco.dto.RetiroDTO;
import co.edu.usbcali.banco.logica.ICuentaLogica;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Transaccion;

@Component
@Scope("singleton")
public class RetiroMapper implements IRetiroMapper {
	
	@Autowired
	private ICuentaLogica cuentaLogica;
	
	@Override
	public RetiroDTO transaccionToRetiroDTO(Transaccion transaccion) {
		RetiroDTO retiroDTO = new RetiroDTO();
		Cuenta cuenta = cuentaLogica.consultarPorId(transaccion.getCuenta().getCuenId());
		
		retiroDTO.setCuenId(cuenta.getCuenId());
		retiroDTO.setClave(cuenta.getClave());
		retiroDTO.setValor(transaccion.getValor());
		return retiroDTO;
	}

	@Override
	public Transaccion retiroDTOToTransaccion(RetiroDTO retiroDTO) {
		Transaccion transaccion = new Transaccion();
		Cuenta cuenta = cuentaLogica.consultarPorId(retiroDTO.getCuenId());
		
		transaccion.setCuenta(cuenta);
		transaccion.setValor(retiroDTO.getValor());
		return transaccion;
	}

}
