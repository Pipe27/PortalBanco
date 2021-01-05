package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.TipoUsuario;
import co.edu.usbcali.banco.modelo.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioVista {
	
	private InputText txtUsuUsuario;
	private InputText txtIdenticacion;
	private InputText txtNombre;
	private InputText txtClave;
	private SelectOneMenu somTipoUsuario;
	private SelectOneMenu somActivo;
	
	private List<Usuario> losUsuarios;
	private List<SelectItem> losTipoUsuarioSelectItem;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;

	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public List<Usuario> getLosUsuarios() {
		if(losUsuarios == null) {
			losUsuarios = delegadoDeNegocio.consultarUsuarioTodo();
		}
		return losUsuarios;
	}

	public void setLosUsuarios(List<Usuario> losUsuarios) {
		this.losUsuarios = losUsuarios;
	}
	
	public void txtIdentificacionListener() {
		
		String id = null;
		try {
			id = new String(txtUsuUsuario.getValue().toString());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "El Usuario debe ser un Usuario existente", ""));
		}
		Usuario usuario = delegadoDeNegocio.consultarUsuarioPorId(id);
		
		if(usuario == null) {
			txtNombre.resetValue();
			txtIdenticacion.resetValue();
			txtClave.resetValue();
			somActivo.resetValue();
			somTipoUsuario.resetValue();
			
			btnBorrar.setDisabled(true);
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
		} else {
			txtNombre.setValue(usuario.getNombre());
			txtIdenticacion.setValue(usuario.getIdentificacion());
			txtClave.setValue(usuario.getClave());
			somActivo.setValue(usuario.getActivo());
			somTipoUsuario.setValue(usuario.getTipoUsuario().getTiusId());
			
			txtUsuUsuario.setDisabled(true);
			txtIdenticacion.setDisabled(true);
			btnBorrar.setDisabled(false);
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
		}		
	}

	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

	public SelectOneMenu getSomTipoUsuario() {
		return somTipoUsuario;
	}

	public void setSomTipoUsuario(SelectOneMenu somTipoUsuario) {
		this.somTipoUsuario = somTipoUsuario;
	}
	
	public InputText getTxtIdenticacion() {
		return txtIdenticacion;
	}

	public void setTxtIdenticacion(InputText txtIdenticacion) {
		this.txtIdenticacion = txtIdenticacion;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtUsuUsuario() {
		return txtUsuUsuario;
	}

	public void setTxtUsuUsuario(InputText txtUsuUsuario) {
		this.txtUsuUsuario = txtUsuUsuario;
	}

	public InputText getTxtClave() {
		return txtClave;
	}

	public void setTxtClave(InputText txtClave) {
		this.txtClave = txtClave;
	}

	public List<SelectItem> getLosTipoUsuarioSelectItem() {
		if(losTipoUsuarioSelectItem==null) {
			List<TipoUsuario> losTipoUsuario=delegadoDeNegocio.consultarTipoUsuarioTodo();
			losTipoUsuarioSelectItem=new ArrayList<>();
			for (TipoUsuario tipoUsuario: losTipoUsuario) {
				losTipoUsuarioSelectItem.add(new SelectItem(tipoUsuario.getTiusId(), tipoUsuario.getTiusId()+" - "+tipoUsuario.getNombre()));
			}
		}
		return losTipoUsuarioSelectItem;
	}

	public void setLosTipoUsuarioSelectItem(List<SelectItem> losTipoUsuarioSelectItem) {
		this.losTipoUsuarioSelectItem =losTipoUsuarioSelectItem;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public String crearAction() {
		try {
			if(txtUsuUsuario.getValue().toString().equals("")) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Digite su usuario.", ""));
				return "";
			}
			if(txtIdenticacion.getValue().toString().equals("")) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Digite su identificación.", ""));
				return "";
			}
			if(txtNombre.getValue().toString().equals("")) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Digite su nombre.", ""));
				return "";
			}
			if(txtClave.getValue().toString().equals("")) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Ingrese una clave.", ""));
				return "";
			}
			Usuario usuario = new Usuario();
			usuario.setActivo(somActivo.getValue().toString().charAt(0));
			usuario.setUsuUsuario(new String(txtUsuUsuario.getValue().toString()));
			usuario.setClave(txtClave.getValue().toString());
			usuario.setIdentificacion(new BigDecimal(txtIdenticacion.getValue().toString()));
			usuario.setNombre(txtNombre.getValue().toString());
			TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(Long.parseLong(somTipoUsuario.getValue().toString()));
			usuario.setTipoUsuario(tipoUsuario);
			
			delegadoDeNegocio.grabarUsuario(usuario);
			
			txtClave.setDisabled(true);
			txtIdenticacion.setDisabled(true);
			txtNombre.setDisabled(true);
			txtUsuUsuario.setDisabled(true);
			
			losUsuarios = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se creó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String modificarAction() {
		Usuario usuario2 = delegadoDeNegocio.consultarUsuarioPorId(txtUsuUsuario.getValue().toString());
		try {
			if(usuario2.getActivo() == somActivo.getValue().toString().charAt(0) && usuario2.getClave().toString().equals(txtClave.getValue().toString()) && usuario2.getNombre().toString().equals(txtNombre.getValue().toString())) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha modificado ningún dato.", ""));
			}else {
				if(txtNombre.getValue().toString().equals("")) {
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor ingrese su nombre.", ""));
				}
				if(txtClave.getValue().toString().equals("")) {
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor ingrese una clave.", ""));
				}else {
					Usuario usuario = new Usuario();
					usuario.setActivo(somActivo.getValue().toString().charAt(0));
					usuario.setUsuUsuario(new String(txtUsuUsuario.getValue().toString()));
					usuario.setClave(txtClave.getValue().toString());
					usuario.setIdentificacion(new BigDecimal(txtIdenticacion.getValue().toString()));
					usuario.setNombre(txtNombre.getValue().toString());
					TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(Long.parseLong(somTipoUsuario.getValue().toString()));
					usuario.setTipoUsuario(tipoUsuario);
					
					delegadoDeNegocio.modificarUsuario(usuario);
					losUsuarios = null;
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se modificó con éxito", ""));
				}
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String borrarAction() {
		try {
			Usuario usuario = new Usuario();
			usuario.setActivo(somActivo.getValue().toString().charAt(0));
			usuario.setUsuUsuario(new String(txtUsuUsuario.getValue().toString()));
			usuario.setClave(txtClave.getValue().toString());
			usuario.setIdentificacion(new BigDecimal(txtIdenticacion.getValue().toString()));
			usuario.setNombre(txtNombre.getValue().toString());
			TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(Long.parseLong(somTipoUsuario.getValue().toString()));
			usuario.setTipoUsuario(tipoUsuario);
			
			delegadoDeNegocio.borrarUsuario(usuario);
			losUsuarios = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El usuario se borró con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String limpiarAction() {
		txtUsuUsuario.resetValue();
		txtIdenticacion.resetValue();
		txtNombre.resetValue();
		txtClave.resetValue();
		somTipoUsuario.resetValue();
		somActivo.resetValue();
		
		txtClave.setDisabled(false);
		txtIdenticacion.setDisabled(false);
		txtNombre.setDisabled(false);
		txtUsuUsuario.setDisabled(false);
		
		btnBorrar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}
	
	public CommandButton getBtnCrear() {
		return btnCrear;
	}
	public void setBtnCrear(CommandButton btnCrear) {
		this.btnCrear = btnCrear;
	}
	
	public CommandButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
	public CommandButton getBtnBorrar() {
		return btnBorrar;
	}
	public void setBtnBorrar(CommandButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}
	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}
	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}
}
