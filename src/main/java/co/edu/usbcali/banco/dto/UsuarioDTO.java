package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class UsuarioDTO {
	
	private String usuUsuario;
	private String clave;
	private BigDecimal identificacion;
	private String nombre;
	private char activo;
	
	private long idTipoUsuario;
	private String nombreTipoUsuario;
	
	public String getUsuUsuario() {
		return usuUsuario;
	}
	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public BigDecimal getIdentificacion() {
		return identificacion;
	}
	public void setIdentificacion(BigDecimal identificacion) {
		this.identificacion = identificacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public char getActivo() {
		return activo;
	}
	public void setActivo(char activo) {
		this.activo = activo;
	}
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	public String getNombreTipoUsuario() {
		return nombreTipoUsuario;
	}
	public void setNombreTipoUsuario(String nombreTipoUsuario) {
		this.nombreTipoUsuario = nombreTipoUsuario;
	}
}
