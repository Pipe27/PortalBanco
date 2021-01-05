package co.edu.usbcali.banco.dto;

public class ResultadoCuentaClienDTO {
	
	private Long contador;
	private String nombreCliente;
	
	public ResultadoCuentaClienDTO() {
		super();
		
	}

	
	public ResultadoCuentaClienDTO(Long contador, String nombreCliente) {
		super();
		this.contador = contador;
		this.nombreCliente = nombreCliente;
	}

	public Long getContador() {
		return contador;
	}

	public void setContador(Long clieId) {
		this.contador = clieId;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	
	
	

}
