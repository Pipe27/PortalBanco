package co.edu.usbcali.banco.vista;


import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputnumber.InputNumber;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectonemenu.SelectOneMenu;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;

@ManagedBean
@ViewScoped
public class CuentaVista {
	
	private InputText txtIdentificacion;
	private InputText txtCliente;
	private InputNumber txtSaldo;
	private InputText txtClave;
	private InputText txtNombreCliente;
	private SelectOneMenu somActiva;

	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnLimpiar;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Cuenta> lasCuentas;
	
	public List<Cuenta> getLasCuentas() {
		if(lasCuentas==null) {
			lasCuentas=delegadoDeNegocio.consultarCuentaTodo();
		}
		
		return lasCuentas;
	}
	public void setLasCuentas(List<Cuenta> lasCuentas) {
		this.lasCuentas = lasCuentas;
	}
	
	public InputText getTxtNombreCliente() {
		return txtNombreCliente;
	}
	public void setTxtNombreCliente(InputText txtNombreCliente) {
		this.txtNombreCliente = txtNombreCliente;
	}
	public static String generadorContras(){
        String psw = "";
        String[] symbols = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int tamanoContra = 10; 
        Random aleatoreo = new Random();
        for (int i = 0; i < tamanoContra; i++) {
                psw += symbols[aleatoreo.nextInt(62)];
        }
        return psw;
    }
	
	public String generarIdCuenta() {
		String nuevaCuenta="";
		for (int j = 0; j <=3; j++) {
				for (int i = 0; i <=3; i++) {
				int resultado;
				Random r = new Random();
				resultado =r.nextInt((9-0)+1)+0;
				nuevaCuenta+=resultado;
			}
			if(j!=3) {
				nuevaCuenta+="-";
			}
		}
		if(validarExistenciaCuenta(nuevaCuenta)==true) {
			return generarIdCuenta();
		}else {
			return nuevaCuenta;
		}
			
	}
	public boolean validarExistenciaCuenta(String idCuenta) {
		Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(idCuenta);
			if(cuenta==null) {
				return false;
			}	
		return true;
	}
	
	public void txtIdListener() {
		BigDecimal idCli=null;
		try {
			idCli=new BigDecimal(txtCliente.getValue().toString());
		} catch (Exception e) {
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificacion debe ser numerica", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		Cliente cliente = delegadoDeNegocio.consultarClientePorId(idCli);
		
		if (cliente == null){
			txtCliente.resetValue();
			txtSaldo.resetValue();
			txtClave.resetValue();
			txtNombreCliente.resetValue();
			somActiva.resetValue();
		
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
		} else {
			txtNombreCliente.setValue(cliente.getNombre());
			txtClave.setDisabled(true);
			txtSaldo.setDisabled(true);
			txtIdentificacion.setDisabled(true);
		}
		
	}
	
	public void txtIdentificacionListener() {
		
		String id=null;
		try {
			id=new String(txtIdentificacion.getValue().toString());
		} catch (Exception e) {
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificacion debe ser numerica", "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		
		Cuenta cuenta = delegadoDeNegocio.consultarCuentaPorId(id);
		
		if(cuenta==null) {
			
			txtCliente.resetValue();
			txtSaldo.resetValue();
			txtClave.resetValue();
			txtNombreCliente.resetValue();
			somActiva.resetValue();
		
			btnModificar.setDisabled(true);
			btnCrear.setDisabled(false);
		}else {
			Cliente cliente = delegadoDeNegocio.consultarClientePorId(cuenta.getCliente().getClieId());
			txtNombreCliente.setValue(cliente.getNombre());
			txtCliente.setValue(cuenta.getCliente().getClieId());
			txtSaldo.setValue(cuenta.getSaldo());
			txtClave.setValue(cuenta.getClave());
			somActiva.setValue(cuenta.getActiva());
			txtIdentificacion.setDisabled(true);
			txtCliente.setDisabled(true);
			txtSaldo.setDisabled(true);
			txtClave.setDisabled(false);
			txtNombreCliente.setDisabled(true);
			
			btnModificar.setDisabled(false);
			btnCrear.setDisabled(true);	
			
		}
	}
	
	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}
	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
	}
	
	public String crearAction() {
		
		try {
			if(txtCliente.getValue().toString().equals("")) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"Ingrese una identificación válida para cliente o busque una cuenta existente.",""));
			}else {			
				if(somActiva.getValue().toString().charAt(0) == 'S') {
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se ha podido crear la cuenta.",""));
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"Recuerde que al crear una nueva cuenta, debe estar inactiva.",""));
				}else {
					Cuenta cuenta = new Cuenta();
					cuenta.setActiva(somActiva.getValue().toString().charAt(0));
					cuenta.setClave(generadorContras());
					cuenta.setCuenId(generarIdCuenta());
					cuenta.setSaldo(new BigDecimal(0));
					Cliente cliente=delegadoDeNegocio.consultarClientePorId(new BigDecimal(txtCliente.getValue().toString()));
					cuenta.setCliente(cliente);
					
					delegadoDeNegocio.grabarCuenta(cuenta);
					lasCuentas = null;
					FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La cuenta se creo con exito",""));
					txtClave.setValue(cuenta.getClave());
					txtIdentificacion.setValue(cuenta.getCuenId());
					txtIdentificacion.setDisabled(true);
					txtCliente.setDisabled(true);
					txtSaldo.setDisabled(true);
					txtNombreCliente.setDisabled(true);
				}
			}
		} catch (Exception e) {
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		return "";
	}
	
	public String modificarAction() {		
		try {
			Cuenta cuenta2 = delegadoDeNegocio.consultarCuentaPorId(txtIdentificacion.getValue().toString());
			if(cuenta2.getClave().equals(txtClave.getValue().toString()) && somActiva.getValue().toString().charAt(0) == cuenta2.getActiva()) {
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN,"No se ha modificado ningún dato.",""));
				return "";
			}
			Cuenta cuenta = new Cuenta();
			cuenta.setActiva(somActiva.getValue().toString().charAt(0));
			cuenta.setClave(txtClave.getValue().toString());
			cuenta.setCuenId(new String(txtIdentificacion.getValue().toString()));
			cuenta.setSaldo(new BigDecimal(txtSaldo.getValue().toString()));
			Cliente cliente=delegadoDeNegocio.consultarClientePorId(new BigDecimal(txtCliente.getValue().toString()));
			cuenta.setCliente(cliente);
			
			delegadoDeNegocio.modificarCuenta(cuenta);
			lasCuentas = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO,"La cuenta se modifico con exito.",""));
			
			
		} catch (Exception e) {
			
			FacesMessage mensaje = new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), "");
			FacesContext.getCurrentInstance().addMessage("", mensaje);
		}
		
		return "";
	}
	
	public String limpiarAction() {
		
		txtIdentificacion.resetValue();
		txtCliente.resetValue();
		txtSaldo.resetValue();
		txtNombreCliente.resetValue();
		txtClave.resetValue();
		somActiva.resetValue();
		lasCuentas=null;
	
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(false);
		txtClave.setDisabled(false);
		txtIdentificacion.setDisabled(false);
		txtCliente.setDisabled(false);
		txtSaldo.setDisabled(false);
		txtClave.setDisabled(true);
		txtNombreCliente.setDisabled(false);
		
		return "";
	}
	
	public InputText getTxtIdentificacion() {
		return txtIdentificacion;
	}
	public void setTxtIdentificacion(InputText txtIdentificacion) {
		this.txtIdentificacion = txtIdentificacion;
	}
	public InputText getTxtCliente() {
		return txtCliente;
	}
	public void setTxtCliente(InputText txtCliente) {
		this.txtCliente = txtCliente;
	}
	public InputNumber getTxtSaldo() {
		return txtSaldo;
	}
	public void setTxtSaldo(InputNumber txtSaldo) {
		this.txtSaldo = txtSaldo;
	}
	
	public InputText getTxtClave() {
		return txtClave;
	}
	public void setTxtClave(InputText txtClave) {
		this.txtClave = txtClave;
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
	public SelectOneMenu getSomActiva() {
		return somActiva;
	}
	public void setSomActiva(SelectOneMenu somActiva) {
		this.somActiva = somActiva;
	}
}
