package co.edu.usbcali.banco.vista;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class EditarDatosCuentaVista {
	
	private String txtIdentificacion;
	private String txtClave;
	private String txtNombreCliente;

	private CommandButton btnModificar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Cuenta> lasCuentas;
	
	public List<Cuenta> getLasCuentas() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		lasCuentas=delegadoDeNegocio.buscarLasCuentasCliente(cliente.getClieId());
		return lasCuentas;
	}
	
	public void setLasCuentas(List<Cuenta> lasCuentas) {
		this.lasCuentas = lasCuentas;
	}
	
	public String getTxtNombreCliente() {
		Cuenta cuenta2=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta2.getCliente().getClieId());
		txtNombreCliente=cliente.getNombre();
		return txtNombreCliente;
	}
	public void setTxtNombreCliente(String txtNombreCliente) {
		this.txtNombreCliente = txtNombreCliente;
	}
	
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	public String modificarAction() {
		
		Cuenta cuenta2=(Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente= delegadoDeNegocio.consultarClientePorId(cuenta2.getCliente().getClieId());
		try {
			if(txtClave.isEmpty()) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"Los campos se encuentra vacíos, por favor ingrese dos contraseñas que coincidan.",""));
			}else {
			Cuenta cuenta = new Cuenta();
			cuenta.setActiva(cuenta2.getActiva());
			cuenta.setClave(txtClave);
			cuenta.setCuenId(new String(txtIdentificacion));
			cuenta.setSaldo(cuenta2.getSaldo());
			cuenta.setCliente(cliente);
			
			delegadoDeNegocio.modificarCuenta(cuenta);
			lasCuentas = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La contraseña se ha modificado.",""));
			}
			
		} catch (Exception e) {
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		
		return "";
	}
	
	public String getTxtIdentificacion() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		txtIdentificacion=cuenta.getCuenId();
		return txtIdentificacion;
	}
	public void setTxtIdentificacion(String txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}
	public String getTxtClave() {
		return txtClave;
	}
	public void setTxtClave(String txtClave) {
		this.txtClave = txtClave;
	}
	public CommandButton getBtnModificar() {
		return btnModificar;
	}
	public void setBtnModificar(CommandButton btnModificar) {
		this.btnModificar = btnModificar;
	}
}
