package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class RetiroDTO {
	
	private String cuenId;
	private String clave;
	private BigDecimal valor;
	
	public String getCuenId() {
		return cuenId;
	}
	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
}
