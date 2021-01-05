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
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class TransferenciaVista {

	private String txtCuentaOrigen;
	private InputNumber txtValor;
	private String txtNuevoSaldo;
	
	private SelectOneMenu cuenRegis;
	
	private CommandButton btnTransferir;
	private CommandButton btnLimpiar;
	
	private List<SelectItem> cuentasRegistradas;
	
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public String transferirAction() {
		
		try {
			
			Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
			txtCuentaOrigen=cuenta.getCuenId();
			BigDecimal transferencia = new BigDecimal(txtValor.getValue().toString());
			getTxtNuevoSaldo();
			delegadoDeNegocio.transferencia(cuenta.getCuenId(), cuenRegis.getValue().toString(), transferencia, "Banco-Web");
			delegadoDeNegocio.transferenciaAlmacenaBD(txtCuentaOrigen, transferencia, "Banco-Web");
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La transferencia se efectuó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String limpiarAction() {
		
		txtValor.resetValue();
		
		return "";
	}
	
	public String getTxtNuevoSaldo() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		txtNuevoSaldo=String.valueOf(cuenta.getSaldo());
		return txtNuevoSaldo;
	}

	public void setTxtNuevoSaldo(String txtNuevoSaldo) {
		this.txtNuevoSaldo = txtNuevoSaldo;
	}

	public String getTxtCuentaOrigen() {
		Cuenta cuenta=(Cuenta)FacesUtils.getfromSession("usuario");
		txtCuentaOrigen=cuenta.getCuenId();
		return txtCuentaOrigen;
	}

	public void setTxtCuentaOrigen(String txtCuentaOrigen) {
		this.txtCuentaOrigen = txtCuentaOrigen;
	}

	public InputNumber getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(InputNumber txtValor) {
		this.txtValor = txtValor;
	}

	public SelectOneMenu getCuenRegis() {
		return cuenRegis;
	}

	public void setCuenRegis(SelectOneMenu cuenRegis) {
		this.cuenRegis = cuenRegis;
	}

	public CommandButton getBtnTransferir() {
		return btnTransferir;
	}

	public void setBtnTransferir(CommandButton btnTransferir) {
		this.btnTransferir = btnTransferir;
	}

	public CommandButton getBtnLimpiar() {
		return btnLimpiar;
	}

	public void setBtnLimpiar(CommandButton btnLimpiar) {
		this.btnLimpiar = btnLimpiar;
	}

	public List<SelectItem> getCuentasRegistradas() {
		
		Cuenta cuenta = (Cuenta)FacesUtils.getfromSession("usuario");
		Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
		cuentasRegistradas = new ArrayList<SelectItem>();
		
		List<Cuenta> cuentasRegis = delegadoDeNegocio.buscarLasCuentasRegistradas(cliente.getClieId());
		for (Cuenta cuenta1 : cuentasRegis) {
			cuentasRegistradas.add(new SelectItem(cuenta1.getCuenId(), cuenta1.getCuenId()));
		}
		
		return cuentasRegistradas;
	}

	public void setCuentasRegistradas(List<SelectItem> cuentasRegistradas) {
		this.cuentasRegistradas = cuentasRegistradas;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
}
