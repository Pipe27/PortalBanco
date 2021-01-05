package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class ConsignarVista {

	private InputText txtCuenta;
	private InputText txtNombre;
	private InputNumber txtValor;
	private InputNumber txtNuevoSaldo;
	
	private CommandButton btnConsignar;
	private CommandButton btnLimpiar;
	
	private List<Transaccion> losMovimientos;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public void txtCuentaListener() {
		String id = null;
		try {
			id = new String(txtCuenta.getValue().toString());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La cuenta debe existir", "Y ser del tipo: 1747-5102-2907-6287"));
		}
		
		Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(id);
		
		if (cuenta == null) {
			txtNombre.resetValue();
			txtValor.resetValue();
			txtNuevoSaldo.resetValue();
			
			btnConsignar.setDisabled(false);
			btnLimpiar.setDisabled(false);
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "La cuenta no existe.", ""));
		} else {
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
			txtNombre.setValue(cliente.getNombre());
			txtNombre.setDisabled(true);
			txtCuenta.setDisabled(true);
			txtNuevoSaldo.setValue(cuenta.getSaldo());
			txtNuevoSaldo.setDisabled(true);
			
			btnConsignar.setDisabled(false);
			btnLimpiar.setDisabled(false);
		}
		
	}
	
	public String consignarAction() {
		try {

			Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(txtCuenta.getValue().toString());
			BigDecimal consignacion = new BigDecimal(txtValor.getValue().toString());
			Usuario usuario=(Usuario)FacesUtils.getfromSession("usuario");
				
			delegadoDeNegocio.consignar(cuenta.getCuenId(), consignacion, usuario.getUsuUsuario());
			losMovimientos = null;
			txtValor.resetValue();
			txtCuentaListener();
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La consignación se efectuó con éxito", ""));
			
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String limpiarAction() {
		
		txtCuenta.resetValue();
		txtNombre.resetValue();
		txtValor.resetValue();
		txtNuevoSaldo.resetValue();
		losMovimientos = null;
		
		txtNombre.setDisabled(false);
		txtCuenta.setDisabled(false);
		txtNuevoSaldo.setDisabled(false);
		
		btnConsignar.setDisabled(false);
		btnLimpiar.setDisabled(false);
		
		return "";
	}

	public List<Transaccion> getLosMovimientos() {
		Usuario usuario=(Usuario)FacesUtils.getfromSession("usuario");
		if(losMovimientos == null) {
			losMovimientos = delegadoDeNegocio.consultarPorUsu(usuario.getUsuUsuario());
		}
		return losMovimientos;
	}


	public void setLosMovimientos(List<Transaccion> losMovimientos) {
		this.losMovimientos = losMovimientos;
	}

	public InputNumber getTxtNuevoSaldo() {
		return txtNuevoSaldo;
	}

	public void setTxtNuevoSaldo(InputNumber txtNuevoSaldo) {
		this.txtNuevoSaldo = txtNuevoSaldo;
	}

	public InputText getTxtCuenta() {
		return txtCuenta;
	}

	public void setTxtCuenta(InputText txtCuenta) {
		this.txtCuenta = txtCuenta;
	}

	public InputText getTxtNombre() {
		return txtNombre;
	}

	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}

	public InputNumber getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(InputNumber txtValor) {
		this.txtValor = txtValor;
	}

	public CommandButton getBtnConsignar() {
		return btnConsignar;
	}

	public void setBtnConsignar(CommandButton btnConsignar) {
		this.btnConsignar = btnConsignar;
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
