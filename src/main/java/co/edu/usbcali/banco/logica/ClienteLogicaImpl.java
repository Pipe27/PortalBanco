/*package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dao.IClienteDAO;
import co.edu.usbcali.banco.dao.ITipoDocumentoDAO;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.TipoDocumento;


public class ClienteLogicaImpl implements IClienteLogica {

	private final static Logger log=LoggerFactory.getLogger(ClienteLogicaImpl.class);
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(Cliente cliente) throws Exception {
		log.info("Inicio el grabado del cliente");
		
		if(cliente==null) {
			throw new Exception("El cliente no puede ser nulo");
		}
		if(cliente.getActivo()==' ') {
			throw new Exception("El campo activo es obligatorio");
		}
		if(cliente.getClieId()==null || cliente.getClieId().intValue()==0) {
			throw new Exception("La identificacion es obligatoria");
		}
		if(cliente.getDireccion()==null || cliente.getDireccion().trim().equals("")==true) {
			throw new Exception("La direccion es obligatoria");
		}
		if(cliente.getEmail()==null || cliente.getEmail().trim().equals("")==true) {
			throw new Exception("El email es obligatorio");
		}
		if(cliente.getNombre()==null || cliente.getNombre().trim().equals("")==true) {
			throw new Exception("El nombre es obligatorio");
		}
		if(cliente.getTelefono()==null || cliente.getTelefono().trim().equals("")==true) {
			throw new Exception("El telefono es obligatorio");
		}
		if(cliente.getTipoDocumento()==null || cliente.getTipoDocumento().getTdocId()==0L) {
			throw new Exception("El tipo de documento es obligatorio");
		}
		TipoDocumento tipoDocumento=tipoDocumentoDAO.ConsultarPorId(cliente.getTipoDocumento().getTdocId());
		if(tipoDocumento==null) {
			throw new Exception("El tipo documento con Id: "+cliente.getTipoDocumento().getTdocId()+" no existe");
		}
		
		clienteDAO.grabar(cliente);	
		log.info("Termino el grabado del cliente");
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(Cliente cliente) throws Exception {
		if(cliente==null) {
			throw new Exception("El cliente no puede ser nulo");
		}
		if(cliente.getActivo()==' ') {
			throw new Exception("El campo activo es obligatorio");
		}
		if(cliente.getClieId()==null || cliente.getClieId().intValue()==0) {
			throw new Exception("La identificacion es obligatoria");
		}
		if(cliente.getDireccion()==null || cliente.getDireccion().trim().equals("")==true) {
			throw new Exception("La direccion es obligatoria");
		}
		if(cliente.getEmail()==null || cliente.getEmail().trim().equals("")==true) {
			throw new Exception("El email es obligatorio");
		}
		if(cliente.getNombre()==null || cliente.getNombre().trim().equals("")==true) {
			throw new Exception("El nombre es obligatorio");
		}
		if(cliente.getTelefono()==null || cliente.getTelefono().trim().equals("")==true) {
			throw new Exception("El telefono es obligatorio");
		}
		if(cliente.getTipoDocumento()==null || cliente.getTipoDocumento().getTdocId()==0L) {
			throw new Exception("El tipo de documento es obligatorio");
		}
		TipoDocumento tipoDocumento=tipoDocumentoDAO.ConsultarPorId(cliente.getTipoDocumento().getTdocId());
		if(tipoDocumento==null) {
			throw new Exception("El tipo documento con Id: "+cliente.getTipoDocumento().getTdocId()+" no existe");
		}
		
		clienteDAO.modificar(cliente);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(Cliente cliente) throws Exception {
		if(cliente==null) {
			throw new Exception("El cliente no puede ser nulo");
		}
		Cliente entity=clienteDAO.ConsultarPorId(cliente.getClieId());
		clienteDAO.borrar(entity);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente consultarPorId(BigDecimal clieId) {
		return clienteDAO.ConsultarPorId(clieId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> consultarTodos() {
		return clienteDAO.consultarTodos();
	}

}
*/