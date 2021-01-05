package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;

public interface ITransaccionBancariasLogica {

	public void retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception;
	public void consignar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception;
	public void transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario) throws Exception;
	public void transferenciaAlmacenaBD(String cuenId, BigDecimal valor, String usuUsuario) throws Exception;
	public boolean validarPass(String cuenId, String clave);
}
