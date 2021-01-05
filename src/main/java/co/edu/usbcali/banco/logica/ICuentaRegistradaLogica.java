package co.edu.usbcali.banco.logica;


import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.CuentaRegistrada;

public interface ICuentaRegistradaLogica {
	public void grabar(CuentaRegistrada cuentaRegistrada) throws Exception;
	public void modificar(CuentaRegistrada cuentaRegistrada)throws Exception;
	public void borrar(CuentaRegistrada cuentaRegistrada)throws Exception;
	public CuentaRegistrada consultarPorId(long cureId);
	public Long consultarCuenExistente(String cuenId, BigDecimal clieId);
	public List<CuentaRegistrada> consultarTodo();

}
