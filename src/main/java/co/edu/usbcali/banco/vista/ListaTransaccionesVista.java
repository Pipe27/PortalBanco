package co.edu.usbcali.banco.vista;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.selectoneradio.SelectOneRadio;

import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.Transaccion;
import co.edu.usbcali.banco.util.FacesUtils;

@ManagedBean
@ViewScoped
public class ListaTransaccionesVista {
	private SelectOneRadio tipoTransaccion;
	
	private CommandButton btnBuscar;
	
	private List<SelectItem> losTipoTrans;
	private List<Transaccion> lasTransacciones;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	public String buscarAction() {
		
		try {
			if(tipoTransaccion.getValue().equals("RETIRO")) {
				
				Cuenta cuenta = (Cuenta)FacesUtils.getfromSession("usuario");
				lasTransacciones= delegadoDeNegocio.consultarRetiros(cuenta.getCuenId());
				
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Se muestra: El listado de retiros.",""));
				if(lasTransacciones.isEmpty()) {
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"La cuenta "+cuenta.getCuenId()+" no ha tenido retiros.",""));
				}
			}
			if(tipoTransaccion.getValue().equals("CONSIGNACION")) {
				
				Cuenta cuenta = (Cuenta)FacesUtils.getfromSession("usuario");
				lasTransacciones= delegadoDeNegocio.consultarConsignaciones(cuenta.getCuenId());
				
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Se muestra: El listado de consignaciones.",""));
				if(lasTransacciones.isEmpty()) {
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"La cuenta "+cuenta.getCuenId()+" no ha tenido consignaciones.",""));
				}
			}
			
			if(tipoTransaccion.getValue().equals("TRANSFERENCIA")) {
				
				Cuenta cuenta = (Cuenta)FacesUtils.getfromSession("usuario");
				lasTransacciones= delegadoDeNegocio.consultarTransferencias(cuenta.getCuenId());
				
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"Se muestra: El listado de transferencias.",""));
				if(lasTransacciones.isEmpty()) {
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"La cuenta "+cuenta.getCuenId()+" no ha tenido transferencias.",""));
				}
			}
		} catch (Exception e) {
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		return null;
	}
	
	public SelectOneRadio getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(SelectOneRadio tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	
	public List<SelectItem> getLosTipoTrans() {
		if(losTipoTrans==null) {
			List<TipoTransaccion> losTipoTransaccion = delegadoDeNegocio.consultarTipoTransaccionTodo();
			losTipoTrans=new ArrayList<>();
			for (TipoTransaccion tipoTransaccion : losTipoTransaccion) {
				losTipoTrans.add(new SelectItem(tipoTransaccion.getNombre(), tipoTransaccion.getNombre()));
			}
		}
		return losTipoTrans;
	}

	public void setLosTipoTrans(List<SelectItem> losTipoTrans) {
		this.losTipoTrans = losTipoTrans;
	}

	public CommandButton getBtnBuscar() {
		return btnBuscar;
	}

	public void setBtnBuscar(CommandButton btnBuscar) {
		this.btnBuscar = btnBuscar;
	}

	public List<Transaccion> getLasTransacciones() {
		return lasTransacciones;
	}

	public void setLasTransacciones(List<Transaccion> lasTransacciones) {
		this.lasTransacciones = lasTransacciones;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
}
