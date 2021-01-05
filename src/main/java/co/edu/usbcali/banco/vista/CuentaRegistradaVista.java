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

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.CuentaRegistrada;
import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class CuentaRegistradaVista {
	
	private InputText txtIdCliente;
	private InputText txtNombre;
	private InputText txtDocumento;
	private SelectOneMenu somTipoDocumento;
	private SelectOneMenu cuenRegis;
	
	private CommandButton btnRegistrar;
	private CommandButton btnLimpiar;
	
	private List<SelectItem> cuentasRegistradas;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	
	public List<SelectItem> getCuentasRegistradas() {
		return cuentasRegistradas;
	}

	public void setCuentasRegistradas(List<SelectItem> cuentasRegistradas) {
		this.cuentasRegistradas = cuentasRegistradas;
	}
	
	public void txtIdClienteListener() {
		Cliente cliente = delegadoDeNegocio.consultarClientePorId(new BigDecimal(txtIdCliente.getValue().toString()));
		TipoDocumento tipoDocumento = delegadoDeNegocio.consultarTipoDocumentoPorId(cliente.getTipoDocumento().getTdocId());
		if(!cliente.equals(null)) {
			txtNombre.setValue(cliente.getNombre());
			txtDocumento.setValue(tipoDocumento.getNombre());
			cuentasRegistradas = new ArrayList<SelectItem>();
			List<Cuenta> cuentas = delegadoDeNegocio.buscarLasCuentasCliente(cliente.getClieId());
			for (Cuenta cuenta : cuentas) {
				cuentasRegistradas.add(new SelectItem(cuenta.getCuenId(), cuenta.getCuenId()));
			}
		}
	}
	
	public String registrarAction() {
		
		try {
			Cuenta cuenta = (Cuenta)FacesUtils.getfromSession("usuario");
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
					
			Cuenta cuen = delegadoDeNegocio.consultarCuentaPorId(cuenRegis.getValue().toString());
			CuentaRegistrada cuentaRegistrada = new CuentaRegistrada();
			cuentaRegistrada.setCliente(cliente);
			cuentaRegistrada.setCuenta(cuen);
				
			delegadoDeNegocio.grabarCuentaRegistrada(cuentaRegistrada);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La cuenta se registró con éxito", ""));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String limpiarAction() {
		
		txtIdCliente.resetValue();
		txtNombre.resetValue();
		txtDocumento.resetValue();
		cuentasRegistradas = null;
		return "";
	}

	public InputText getTxtIdCliente() {
		return txtIdCliente;
	}

	public void setTxtIdCliente(InputText txtIdCliente) {
		this.txtIdCliente = txtIdCliente;
	}

	public SelectOneMenu getCuenRegis() {
		return cuenRegis;
	}

	public void setCuenRegis(SelectOneMenu cuenRegis) {
		this.cuenRegis = cuenRegis;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputText getTxtDocumento() {
		return txtDocumento;
	}

	public void setTxtDocumento(InputText txtDocumento) {
		this.txtDocumento = txtDocumento;
	}

	public SelectOneMenu getSomTipoDocumento() {
		return somTipoDocumento;
	}

	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
	}

	public CommandButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public void setBtnRegistrar(CommandButton btnRegistrar) {
		this.btnRegistrar = btnRegistrar;
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
}
