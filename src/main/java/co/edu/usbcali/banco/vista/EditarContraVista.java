package co.edu.usbcali.banco.vista;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;

import co.edu.usbcali.banco.modelo.Usuario;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class EditarContraVista {
	
	private String txtUsuUsuario;
	private String txtClave;
	private String txtNombreUsuario;

	private CommandButton btnModificar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public String modificarAction() {
		
		Usuario usuario2=(Usuario)FacesUtils.getfromSession("usuario");
		
		try {
			if(txtClave.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"Los campos se encuentra vacíos, por favor ingrese una contraseña que coincidan.",""));
			}else {
				Usuario usuario = new Usuario();
				usuario.setActivo(usuario2.getActivo());
				usuario.setClave(txtClave);
				usuario.setIdentificacion(usuario2.getIdentificacion());
				usuario.setNombre(usuario2.getNombre());
				usuario.setTipoUsuario(usuario2.getTipoUsuario());
				usuario.setUsuUsuario(new String(txtUsuUsuario));
				
				delegadoDeNegocio.modificarUsuario(usuario);
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La contraseña se ha modificado.",""));
				
			}
		} catch (Exception e) {
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		return "";
	}

	public String getTxtUsuUsuario() {
		Usuario usuario=(Usuario)FacesUtils.getfromSession("usuario");
		txtUsuUsuario=usuario.getUsuUsuario();
		return txtUsuUsuario;
	}

	public void setTxtUsuUsuario(String txtUsuUsuario) {
		this.txtUsuUsuario = txtUsuUsuario;
	}

	public String getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(String txtClave) {
		this.txtClave = txtClave;
	}

	public String getTxtNombreUsuario() {
		Usuario usuario=(Usuario)FacesUtils.getfromSession("usuario");
		txtNombreUsuario=usuario.getNombre();
		return txtNombreUsuario;
	}

	public void setTxtNombreUsuario(String txtNombreUsuario) {
		this.txtNombreUsuario = txtNombreUsuario;
	}

	public CommandButton getBtnModificar() {
		return btnModificar;
	}

	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
}
