package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class EditarDatosClienteVista {
	private BigDecimal txtIdenticacion;
	private String txtNombre;
	private String txtDireccion;
	private String txtTelefono;
	private String txtMail;
	private String somTipoDocumento;
	
	
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}

	public String modificarAction() {
		
		try {
			Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
			Cliente clie= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
			if(clie.getDireccion().toString().equals(txtDireccion.toString()) && clie.getTelefono().toString().equals(txtTelefono.toString()) && clie.getEmail().toString().equals(txtMail.toString())) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "No has modificado ningún dato.", ""));
			}else {
				Cliente cliente = new Cliente();
				cliente.setClieId(txtIdenticacion);
				cliente.setDireccion(txtDireccion);
				cliente.setEmail(txtMail);
				cliente.setNombre(txtNombre);
				cliente.setTelefono(txtTelefono);
				//Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
				//Cliente clie= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
				TipoDocumento tipoDocumento = delegadoDeNegocio.consultarTipoDocumentoPorId(clie.getTipoDocumento().getTdocId());
				cliente.setTipoDocumento(tipoDocumento);
				cliente.setActivo(clie.getActivo());
				
				delegadoDeNegocio.modificarCliente(cliente);
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "Tus datos se modificaron con éxito", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String limpiarAction() {
		txtDireccion=null;
		txtMail=null;
		txtTelefono=null;

		return "";
	}
	
	public BigDecimal getTxtIdenticacion() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		txtIdenticacion=cliente.getClieId();
		return txtIdenticacion;
	}
	public void setTxtIdenticacion(BigDecimal txtIdenticacion) {
		this.txtIdenticacion = txtIdenticacion;
	}
	public String getTxtNombre() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		txtNombre=cliente.getNombre();
		return txtNombre;
	}
	public void setTxtNombre(String txtNombre) {
		this.txtNombre = txtNombre;
	}
	public String getTxtDireccion() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		txtDireccion=cliente.getDireccion();
		return txtDireccion;
	}
	public void setTxtDireccion(String txtDireccion) {
		this.txtDireccion = txtDireccion;
	}
	public String getTxtTelefono() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		txtTelefono=cliente.getTelefono();
		return txtTelefono;
	}
	public void setTxtTelefono(String txtTelefono) {
		this.txtTelefono = txtTelefono;
	}
	public String getTxtMail() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		txtMail=cliente.getEmail();
		return txtMail;
	}
	public void setTxtMail(String txtMail) {
		this.txtMail = txtMail;
	}
	
	public String getSomTipoDocumento() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		TipoDocumento tipoDoc = delegadoDeNegocio.consultarTipoDocumentoPorId(cliente.getTipoDocumento().getTdocId());
		somTipoDocumento=tipoDoc.getNombre();
		return somTipoDocumento;
	}

	public void setSomTipoDocumento(String somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
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
}
