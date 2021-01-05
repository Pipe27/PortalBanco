package co.edu.usbcali.banco.vista;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class PrincipalUsuarioVista {
	private String loginUsuario;
	private String tipoUsuario;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public String getLoginUsuario() {
		Usuario usuario=(Usuario)FacesUtils.getfromSession("usuario");
		loginUsuario="Hola, "+usuario.getNombre()+"!";
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

	public String getTipoUsuario() {
		Usuario usuario=(Usuario)FacesUtils.getfromSession("usuario");
		TipoUsuario tipoUsu = delegadoDeNegocio.consultarTipoUsuarioPorId(usuario.getTipoUsuario().getTiusId());
		tipoUsuario = tipoUsu.getNombre();
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
}
