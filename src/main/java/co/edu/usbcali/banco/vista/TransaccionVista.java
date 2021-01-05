package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.modelo.Usuario;

									/*Este no es*/
@ManagedBean
@ViewScoped
public class TransaccionVista {
	
	private InputText txtIdTrans;
	private InputText txtCuenta;
	private InputText txtUsuario;
	private InputText txtValor;
	private Date txtFecha;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	private SelectOneMenu somTipoTransaccion;

	private List<SelectItem> losTipoTransaccionSelectItem;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Transaccion> lasTransacciones;
	
	public List<Transaccion> getLasTransacciones() {
		if(lasTransacciones == null) {
			lasTransacciones = delegadoDeNegocio.consultarTransaccionTodo();
		}
		return lasTransacciones;
	}

	public void setLasTransacciones(List<Transaccion> lasTransacciones) {
		this.lasTransacciones = lasTransacciones;
	}

public void txtIdentificacionListener() {
		
		Long id = null;
		try {
			id = new Long(txtIdTrans.getValue().toString());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser únicamente numérica", "Por ejemplo: 1144098101"));
		}
		Transaccion transaccion = delegadoDeNegocio.consultarTransaccionPorId(id);
		
		if(transaccion == null) {
			txtCuenta.resetValue();
			txtUsuario.resetValue();
			txtValor.resetValue();
			txtFecha.setTime(0);
			somTipoTransaccion.resetValue();
			
			btnBorrar.setDisabled(true);
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
		} else {
			txtCuenta.setValue(transaccion.getCuenta());
			txtUsuario.setValue(transaccion.getUsuario());
			txtValor.setValue(transaccion.getValor());
			txtFecha.setDate(Integer.parseInt(transaccion.getFecha().toString()));
			somTipoTransaccion.setValue(transaccion.getTipoTransaccion().getTitrId());
			
			btnBorrar.setDisabled(false);
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);
		}
		
	}
	
	public List<SelectItem> getLosTipoTransaccionSelectItem() {
		if(losTipoTransaccionSelectItem==null) {
			List<TipoTransaccion> losTipoTransaccion=delegadoDeNegocio.consultarTipoTransaccionTodo();
			losTipoTransaccionSelectItem=new ArrayList<>();
			for (TipoTransaccion tipoTransaccion : losTipoTransaccion) {
				losTipoTransaccionSelectItem.add(new SelectItem(tipoTransaccion.getTitrId(), tipoTransaccion.getTitrId()+" - "+tipoTransaccion.getNombre()));
			}
		}
		return losTipoTransaccionSelectItem;
	}
	
	public void setLosTipoTransaccionSelectItem(List<SelectItem> losTipoTransaccionSelectItem) {
		this.losTipoTransaccionSelectItem = losTipoTransaccionSelectItem;
	}

	public InputText getTxtIdTrans() {
		return txtIdTrans;
	}

	public void setTxtIdTrans(InputText txtIdTrans) {
		this.txtIdTrans = txtIdTrans;
	}

	public InputText getTxtCuenta() {
		return txtCuenta;
	}

	public void setTxtCuenta(InputText txtCuenta) {
		this.txtCuenta = txtCuenta;
	}
	
	public Date getTxtFecha() {
		return txtFecha;
	}

	public void setTxtFecha(Date txtFecha) {
		this.txtFecha = txtFecha;
	}

	public InputText getTxtUsuario() {
		return txtUsuario;
	}

	public void setTxtUsuario(InputText txtUsuario) {
		this.txtUsuario = txtUsuario;
	}

	public InputText getTxtValor() {
		return txtValor;
	}

	public void setTxtValor(InputText txtValor) {
		this.txtValor = txtValor;
	}

	public SelectOneMenu getSomTipoTransaccion() {
		return somTipoTransaccion;
	}

	public void setSomTipoTransaccion(SelectOneMenu somTipoTransaccion) {
		this.somTipoTransaccion = somTipoTransaccion;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	public String crearAction() {
		try {
			Transaccion transaccion = new Transaccion();
			transaccion.setTranId(new Long(txtIdTrans.getValue().toString()));
			Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(txtCuenta.getValue().toString());
			transaccion.setCuenta(cuenta);
			TipoTransaccion tipoTransaccion = delegadoDeNegocio.consultarTipoTransaccionPorId(Long.parseLong(somTipoTransaccion.getValue().toString()));
			transaccion.setTipoTransaccion(tipoTransaccion);
			Usuario usuario = delegadoDeNegocio.consultarUsuarioPorId(txtUsuario.getValue().toString());
			transaccion.setUsuario(usuario);
			transaccion.setValor(new BigDecimal(txtValor.getValue().toString()));
			transaccion.setFecha(new Date(txtFecha.getDate()));
			
			delegadoDeNegocio.grabarTransaccion(transaccion);
			lasTransacciones = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La transacción se efectuó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String modificarAction() {
		try {
			Transaccion transaccion = new Transaccion();
			transaccion.setTranId(new Long(txtIdTrans.getValue().toString()));
			Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(txtCuenta.getValue().toString());
			transaccion.setCuenta(cuenta);
			TipoTransaccion tipoTransaccion = delegadoDeNegocio.consultarTipoTransaccionPorId(Long.parseLong(somTipoTransaccion.getValue().toString()));
			transaccion.setTipoTransaccion(tipoTransaccion);
			Usuario usuario = delegadoDeNegocio.consultarUsuarioPorId(txtUsuario.getValue().toString());
			transaccion.setUsuario(usuario);
			transaccion.setValor(new BigDecimal(txtValor.getValue().toString()));
			transaccion.setFecha(new Date(txtFecha.getDate()));
			
			delegadoDeNegocio.modificarTransaccion(transaccion);
			lasTransacciones = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La transacción se modificó con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String borrarAction() {
		try {
			Transaccion transaccion = new Transaccion();
			transaccion.setTranId(new Long(txtIdTrans.getValue().toString()));
			Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(txtCuenta.getValue().toString());
			transaccion.setCuenta(cuenta);
			TipoTransaccion tipoTransaccion = delegadoDeNegocio.consultarTipoTransaccionPorId(Long.parseLong(somTipoTransaccion.getValue().toString()));
			transaccion.setTipoTransaccion(tipoTransaccion);
			Usuario usuario = delegadoDeNegocio.consultarUsuarioPorId(txtUsuario.getValue().toString());
			transaccion.setUsuario(usuario);
			transaccion.setValor(new BigDecimal(txtValor.getValue().toString()));
			transaccion.setFecha(new Date(txtFecha.getDate()));
			
			delegadoDeNegocio.borrarTransaccion(transaccion);
			lasTransacciones = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La transacción se deshizo con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String limpiarAction() {
		txtIdTrans.resetValue();
		txtCuenta.resetValue();
		somTipoTransaccion.resetValue();
		txtUsuario.resetValue();
		txtValor.resetValue();
		txtFecha.setTime(0);
		lasTransacciones = null;
		
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
