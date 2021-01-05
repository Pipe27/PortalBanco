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

import co.edu.usbcali.banco.modelo.TipoDocumento;

@ManagedBean
@ViewScoped
public class TipoDocumentoVista {

	private InputText txtIdTipDoc;
	private InputText txtNombre;
	private SelectOneMenu somActivo;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<TipoDocumento> losTipoDocumento;
	
public void txtIdentificacionListener() {
		
		Long id = null;
		try {
			id = new Long(txtIdTipDoc.getValue().toString());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser únicamente numérica", "Por ejemplo: 1, 2, 3, etc."));
		}
		TipoDocumento tipoDocumento = delegadoDeNegocio.consultarTipoDocumentoPorId(id);
		
		if(tipoDocumento == null) {
			txtNombre.resetValue();
			somActivo.resetValue();
			
			txtNombre.setDisabled(false);
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
		} else {
			txtIdTipDoc.setValue(tipoDocumento.getTdocId());
			txtNombre.setValue(tipoDocumento.getNombre());
			somActivo.setValue(tipoDocumento.getActivo());
			
			txtNombre.setDisabled(false);
			txtIdTipDoc.setDisabled(true);
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
		}
	}

	public String crearAction() {
		
		try {
			TipoDocumento tipoDocumento = new TipoDocumento();
			tipoDocumento.setActivo(somActivo.getValue().toString().charAt(0));
			tipoDocumento.setTdocId(new Long(txtIdTipDoc.getValue().toString()));
			tipoDocumento.setNombre(txtNombre.getValue().toString());
			
			delegadoDeNegocio.grabarTipoDocumento(tipoDocumento);
			losTipoDocumento = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo de documento se creó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String modificarAction() {
		
		TipoDocumento tipoDocumento2 = delegadoDeNegocio.consultarTipoDocumentoPorId(Long.parseLong(txtIdTipDoc.getValue().toString()));
		try {
			if(tipoDocumento2.getNombre().toString().equals(txtNombre.getValue().toString()) && tipoDocumento2.getActivo() == somActivo.getValue().toString().charAt(0)) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha modificado nada.", ""));
			}else {
				TipoDocumento tipoDocumento = new TipoDocumento();
				tipoDocumento.setActivo(somActivo.getValue().toString().charAt(0));
				tipoDocumento.setTdocId(new Long(txtIdTipDoc.getValue().toString()));
				tipoDocumento.setNombre(txtNombre.getValue().toString());
				
				delegadoDeNegocio.modificarTipoDocumento(tipoDocumento);
				losTipoDocumento = null;
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El tipo de documento se modificó con éxito", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String limpiarAction() {
		txtIdTipDoc.resetValue();
		txtNombre.resetValue();
		somActivo.resetValue();
		losTipoDocumento = null;
		
		txtNombre.setDisabled(true);
		txtIdTipDoc.setDisabled(false);
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}

	public InputText getTxtIdTipDoc() {
		return txtIdTipDoc;
	}

	public void setTxtIdTipDoc(InputText txtIdTipDoc) {
		this.txtIdTipDoc = txtIdTipDoc;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
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

	public List<TipoDocumento> getLosTipoDocumento() {
		if(losTipoDocumento == null) {
			losTipoDocumento = delegadoDeNegocio.consultarTipoDocumentoTodo();
		}
		return losTipoDocumento;
	}

	public void setLosTipoDocumento(List<TipoDocumento> losTipoDocumento) {
		this.losTipoDocumento = losTipoDocumento;
	}
}
