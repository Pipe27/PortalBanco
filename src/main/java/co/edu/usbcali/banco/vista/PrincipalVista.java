package co.edu.usbcali.banco.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class PrincipalVista {

	private String loginUsuario;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public String getLoginUsuario() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		loginUsuario="Hola, "+cliente.getNombre()+"!";
		return loginUsuario;
	}

	public void setLoginUsuario(String loginUsuario) {
		this.loginUsuario = loginUsuario;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
}
