package co.edu.usbcali.banco.dto;

import java.math.BigDecimal;

public class ClienteDTO {
	
	private BigDecimal clieId;
	private String nombre;
	private String direccion;
	private String telefono;
	private String email;
	private char activo;
	
	private long idTipoDocumento;
	private String nombreTipoDocumento;
	
	public BigDecimal getClieId() {
		return clieId;
	}
	public void setClieId(BigDecimal clieId) {
		this.clieId = clieId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getActivo() {
		return activo;
	}
	public void setActivo(char activo) {
		this.activo = activo;
	}
	public long getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getNombreTipoDocumento() {
		return nombreTipoDocumento;
	}
	public void setNombreTipoDocumento(String nombreTipoDocumento) {
		this.nombreTipoDocumento = nombreTipoDocumento;
	}
	
}
