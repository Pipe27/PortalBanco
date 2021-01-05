package co.edu.usbcali.banco.vista;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.TipoUsuario;

@ManagedBean
@ViewScoped
public class TipoUsuarioVista {

	private InputText txtTiusId;
	private InputText txtNombre;
	private SelectOneMenu somActivo;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<TipoUsuario> losTipoUsuario;
	
	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}

	public void txtIdentificacionListener() {
		
		Long id = null;
		try {
			id = new Long(txtTiusId.getValue().toString());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser únicamente numérica", "Por ejemplo: 1, 2, 3, etc."));
		}
		TipoUsuario tipoUsuario = delegadoDeNegocio.consultarTipoUsuarioPorId(id);
		
		if(tipoUsuario == null) {
			txtNombre.resetValue();
			somActivo.resetValue();
			
			txtNombre.setDisabled(false);
			btnBorrar.setDisabled(true);
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
		} else {
			txtTiusId.setValue(tipoUsuario.getTiusId());
			txtNombre.setValue(tipoUsuario.getNombre());
			somActivo.setValue(tipoUsuario.getActivo());
			
			txtNombre.setDisabled(false);
			txtTiusId.setDisabled(true);
			btnBorrar.setDisabled(false);
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
		}
	}
	
	public String crearAction() {
		
		try {
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setActivo(somActivo.getValue().toString().charAt(0));
			tipoUsuario.setTiusId(new Long(txtTiusId.getValue().toString()));
			tipoUsuario.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.grabarTipoUsuario(tipoUsuario);
			losTipoUsuario = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo de usuario se creó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String modificarAction() {
		
		TipoUsuario tipoUsuario2 = delegadoDeNegocio.consultarTipoUsuarioPorId(Long.parseLong(txtTiusId.getValue().toString()));
		try {
			if(tipoUsuario2.getNombre().toString().equals(txtNombre.getValue().toString()) && tipoUsuario2.getActivo() == somActivo.getValue().toString().charAt(0)) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha modificado nada.", ""));
			}else {
				TipoUsuario tipoUsuario = new TipoUsuario();
				tipoUsuario.setActivo(somActivo.getValue().toString().charAt(0));
				tipoUsuario.setTiusId(new Long(txtTiusId.getValue().toString()));
				tipoUsuario.setNombre(txtNombre.getValue().toString());
				
				delegadoDeNegocio.modificarTipoUsuario(tipoUsuario);
				losTipoUsuario = null;
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo de usuario se modificó con éxito", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String borrarAction() {
		
		try {
			TipoUsuario tipoUsuario = new TipoUsuario();
			tipoUsuario.setActivo(somActivo.getValue().toString().charAt(0));
			tipoUsuario.setTiusId(new Long(txtTiusId.getValue().toString()));
			tipoUsuario.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.borrarTipoUsuario(tipoUsuario);
			losTipoUsuario = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo de usuario se borró con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}

	public String limpiarAction() {
		txtTiusId.resetValue();
		txtNombre.resetValue();
		somActivo.resetValue();
		losTipoUsuario = null;
		
		txtNombre.setDisabled(true);
		txtTiusId.setDisabled(false);
		btnBorrar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}

	public InputText getTxtTiusId() {
		return txtTiusId;
	}

	public void setTxtTiusId(InputText txtTiusId) {
		this.txtTiusId = txtTiusId;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
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

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public List<TipoUsuario> getLosTipoUsuario() {
		if(losTipoUsuario == null) {
			losTipoUsuario = delegadoDeNegocio.consultarTipoUsuarioTodo();
		}
		return losTipoUsuario;
	}

	public void setLosTipoUsuario(List<TipoUsuario> losTipoUsuario) {
		this.losTipoUsuario = losTipoUsuario;
	}
}
