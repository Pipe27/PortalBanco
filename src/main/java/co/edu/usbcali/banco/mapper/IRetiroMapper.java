package co.edu.usbcali.banco.mapper;

import co.edu.usbcali.banco.dto.RetiroDTO;
import co.edu.usbcali.banco.modelo.Transaccion;

public interface IRetiroMapper {
	public RetiroDTO transaccionToRetiroDTO(Transaccion transaccion);
	public Transaccion retiroDTOToTransaccion(RetiroDTO retiroDTO);
	
}
