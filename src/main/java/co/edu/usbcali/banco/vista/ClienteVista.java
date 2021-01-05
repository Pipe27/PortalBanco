package co.edu.usbcali.banco.vista;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
import co.edu.usbcali.banco.modelo.TipoDocumento;

@ManagedBean
@ViewScoped
public class ClienteVista {
	
	private InputText txtIdenticacion;
	private InputText txtNombre;
	private InputText txtDireccion;
	private InputText txtTelefono;
	private InputText txtMail;
	private SelectOneMenu somTipoDocumento;
	private SelectOneMenu somActivo;
	
	private CommandButton btnCrear;
	private CommandButton btnModificar;
	private CommandButton btnBorrar;
	private CommandButton btnLimpiar;
	
	private List<SelectItem> losTipoDocumentoSelectItem;
	
	@ManagedProperty(value="#{delegadoDeNegocio}")
	private IDelegadoDeNegocio delegadoDeNegocio;
	
	private List<Cliente> losClientes;
	
	public List<Cliente> getLosClientes() {
		if(losClientes == null) {
			losClientes = delegadoDeNegocio.consultarClienteTodo();
		}
		return losClientes;
	}

	public void setLosClientes(List<Cliente> losClientes) {
		this.losClientes = losClientes;
	}

	public void txtIdentificacionListener() {
		
		BigDecimal id = null;
		try {
			id = new BigDecimal(txtIdenticacion.getValue().toString());
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "La identificación debe ser únicamente numérica", "Por ejemplo: 1144098101"));
		}
		Cliente cliente = delegadoDeNegocio.consultarClientePorId(id);
		
		if(txtIdenticacion.getValue().toString().length() > 10) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Has ingresado una identificación demasiado larga, son máximo 10 caracteres.", ""));
			txtDireccion.setDisabled(true);
			txtMail.setDisabled(true);
			txtNombre.setDisabled(true);
			txtTelefono.setDisabled(true);
			btnCrear.setDisabled(true);
			btnBorrar.setDisabled(true);
			btnModificar.setDisabled(true);
			
		}else {
			if(cliente == null) {
				txtDireccion.resetValue();
				txtMail.resetValue();
				txtNombre.resetValue();
				txtTelefono.resetValue();
				somActivo.resetValue();
				somTipoDocumento.resetValue();
				
				txtDireccion.setDisabled(false);
				txtMail.setDisabled(false);
				txtNombre.setDisabled(false);
				txtTelefono.setDisabled(false);
				
				btnBorrar.setDisabled(true);
				btnModificar.setDisabled(true);
				btnCrear.setDisabled(false);
			} else {
				txtDireccion.setValue(cliente.getDireccion());
				txtMail.setValue(cliente.getEmail());
				txtNombre.setValue(cliente.getNombre());
				txtTelefono.setValue(cliente.getTelefono());
				somActivo.setValue(cliente.getActivo());
				somTipoDocumento.setValue(cliente.getTipoDocumento().getTdocId());
				txtIdenticacion.setDisabled(true);
				txtDireccion.setDisabled(false);
				txtMail.setDisabled(false);
				txtTelefono.setDisabled(false);
				
				btnBorrar.setDisabled(false);
				btnModificar.setDisabled(false);
				btnCrear.setDisabled(true);
			}
		}
	}
	
	public List<SelectItem> getLosTipoDocumentoSelectItem() {
		if(losTipoDocumentoSelectItem==null) {
			List<TipoDocumento> losTipoDocumento=delegadoDeNegocio.consultarTipoDocumentoTodo();
			losTipoDocumentoSelectItem=new ArrayList<>();
			for (TipoDocumento tipoDocumento: losTipoDocumento) {
				losTipoDocumentoSelectItem.add(new SelectItem(tipoDocumento.getTdocId(), tipoDocumento.getTdocId()+" - "+tipoDocumento.getNombre()));
			}
		}
		return losTipoDocumentoSelectItem;
	}

	public void setLosTipoDocumentoSelectItem(List<SelectItem> losTipoDocumentoSelectItem) {
		this.losTipoDocumentoSelectItem = losTipoDocumentoSelectItem;
	}

	public IDelegadoDeNegocio getDelegadoDeNegocio() {
		return delegadoDeNegocio;
	}

	public void setDelegadoDeNegocio(IDelegadoDeNegocio delegadoDeNegocio) {
		this.delegadoDeNegocio = delegadoDeNegocio;
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
	
	public String crearAction() {
		
		try {
			Cliente cliente = new Cliente();
			cliente.setActivo(somActivo.getValue().toString().charAt(0));
			cliente.setClieId(new BigDecimal(txtIdenticacion.getValue().toString()));
			cliente.setDireccion(txtDireccion.getValue().toString());
			cliente.setEmail(txtMail.getValue().toString());
			cliente.setNombre(txtNombre.getValue().toString());
			cliente.setTelefono(txtTelefono.getValue().toString());
			TipoDocumento tipoDocumento = delegadoDeNegocio.consultarTipoDocumentoPorId(Long.parseLong(somTipoDocumento.getValue().toString()));
			cliente.setTipoDocumento(tipoDocumento);
			
			delegadoDeNegocio.grabarCliente(cliente);
			
			Cuenta cuenta = new Cuenta();
			cuenta.setActiva('N');
			cuenta.setClave(generadorContras());
			cuenta.setCuenId(generarIdCuenta());
			cuenta.setSaldo(new BigDecimal(0));
			cuenta.setCliente(cliente);
			
			delegadoDeNegocio.grabarCuenta(cuenta);
			btnCrear.setDisabled(true);
			losClientes = null;
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se creó con éxito", ""));
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "La cuenta asignada es: "+cuenta.getCuenId()+" . Con clave: "+cuenta.getClave()+". ", "Por ejemplo: 1144098101"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String modificarAction() {
		
		Cliente cliente2= delegadoDeNegocio.consultarClientePorId(new BigDecimal(txtIdenticacion.getValue().toString()));
		try {
			if(cliente2.getNombre().toString().equals(txtNombre.getValue().toString()) && cliente2.getDireccion().toString().equals(txtDireccion.getValue().toString()) && cliente2.getTelefono().toString().equals(txtTelefono.getValue().toString()) && cliente2.getEmail().toString().equals(txtMail.getValue().toString())){
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "No se ha realizado ningún cambio.", ""));
			}else {
				Cliente cliente = new Cliente();
				cliente.setActivo(somActivo.getValue().toString().charAt(0));
				cliente.setClieId(new BigDecimal(txtIdenticacion.getValue().toString()));
				cliente.setDireccion(txtDireccion.getValue().toString());
				cliente.setEmail(txtMail.getValue().toString());
				cliente.setNombre(txtNombre.getValue().toString());
				cliente.setTelefono(txtTelefono.getValue().toString());
				TipoDocumento tipoDocumento = delegadoDeNegocio.consultarTipoDocumentoPorId(Long.parseLong(somTipoDocumento.getValue().toString()));
				cliente.setTipoDocumento(tipoDocumento);
				delegadoDeNegocio.modificarCliente(cliente);
				losClientes = null;
				FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se modificó con éxito", ""));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
		}
		
		return "";
	}
	
	public String borrarAction() {
		
		try {
			Cliente cliente = new Cliente();
			cliente.setActivo(somActivo.getValue().toString().charAt(0));
			cliente.setClieId(new BigDecimal(txtIdenticacion.getValue().toString()));
			cliente.setDireccion(txtDireccion.getValue().toString());
			cliente.setEmail(txtMail.getValue().toString());
			cliente.setNombre(txtNombre.getValue().toString());
			cliente.setTelefono(txtTelefono.getValue().toString());
			TipoDocumento tipoDocumento = delegadoDeNegocio.consultarTipoDocumentoPorId(Long.parseLong(somTipoDocumento.getValue().toString()));
			cliente.setTipoDocumento(tipoDocumento);
			
			delegadoDeNegocio.borrarCliente(cliente);
			losClientes = null;
			limpiarAction();
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, "El cliente se borró con éxito", ""));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fallo al eliminar el cliente.", ""));
			FacesContext.getCurrentInstance().addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, "Recuerda que un cliente que tenga cuentas no puede ser eliminado.", ""));
		}
		
		return "";
	}
	
	public String limpiarAction() {
		txtIdenticacion.resetValue();
		txtDireccion.resetValue();
		txtMail.resetValue();
		txtNombre.resetValue();
		txtTelefono.resetValue();
		somActivo.resetValue();
		somTipoDocumento.resetValue();
		losClientes = null;
		
		txtIdenticacion.setDisabled(false);
		
		btnBorrar.setDisabled(true);
		btnModificar.setDisabled(true);
		btnCrear.setDisabled(true);
		return "";
	}
	
	public InputText getTxtIdenticacion() {
		return txtIdenticacion;
	}
	public void setTxtIdenticacion(InputText txtIdenticacion) {
		this.txtIdenticacion = txtIdenticacion;
	}
	public InputText getTxtNombre() {
		return txtNombre;
	}
	public void setTxtNombre(InputText txtNombre) {
		this.txtNombre = txtNombre;
	}
	public InputText getTxtDireccion() {
		return txtDireccion;
	}
	public void setTxtDireccion(InputText txtDireccion) {
		this.txtDireccion = txtDireccion;
	}
	public InputText getTxtTelefono() {
		return txtTelefono;
	}
	public void setTxtTelefono(InputText txtTelefono) {
		this.txtTelefono = txtTelefono;
	}
	public InputText getTxtMail() {
		return txtMail;
	}
	public void setTxtMail(InputText txtMail) {
		this.txtMail = txtMail;
	}
	public SelectOneMenu getSomTipoDocumento() {
		return somTipoDocumento;
	}
	public void setSomTipoDocumento(SelectOneMenu somTipoDocumento) {
		this.somTipoDocumento = somTipoDocumento;
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

	public SelectOneMenu getSomActivo() {
		return somActivo;
	}

	public void setSomActivo(SelectOneMenu somActivo) {
		this.somActivo = somActivo;
	}
}