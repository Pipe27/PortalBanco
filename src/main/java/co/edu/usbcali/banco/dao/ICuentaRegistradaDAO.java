package co.edu.usbcali.banco.dao;


import java.math.BigDecimal;
import java.util.List;

import co.edu.usbcali.banco.modelo.CuentaRegistrada;

public interface ICuentaRegistradaDAO {
	public void grabar(CuentaRegistrada cuentaRegistrada);
	public void modificar(CuentaRegistrada CuentaRegistrada);
	public void borrar(CuentaRegistrada CuentaRegistrada);
	public CuentaRegistrada consultarPorId(long cureId);
	public Long consultarCuenExistente(String cuenId, BigDecimal clieId);
	public List<CuentaRegistrada> consultarTodo();

}
