package co.edu.usbcali.banco.logica;

import java.math.BigDecimal;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dao.IClienteDAO;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;

@Service
@Scope("singleton")
public class ClienteLogicaBeanValidator implements IClienteLogica{
	
	@Autowired
	private IClienteDAO clienteDAO;
	
	@Autowired
	private Validator validator;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(Cliente cliente) throws Exception {
		if(cliente==null) {
			throw new Exception("El cliente no puede ser Nulo");
		}
		
		validarClientes(cliente);
		
		if(consultarPorId(cliente.getClieId())!=null) {
			throw new Exception("El cliente ya existe");
		}
		
		clienteDAO.grabar(cliente);	
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(Cliente cliente) throws Exception {
		if(cliente==null) {
			throw new Exception("El cliente no puede ser Nulo");
		}
		
		validarClientes(cliente);
		
		clienteDAO.modificar(cliente);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(Cliente cliente) throws Exception {
		if(cliente==null) {
			throw new Exception("El cliente no puede ser Nulo");
		}
		
		validarClientes(cliente);
		
		Cliente entity=clienteDAO.consultarPorId(cliente.getClieId());
		
		clienteDAO.borrar(entity);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cliente consultarPorId(BigDecimal clieId) {
		return clienteDAO.consultarPorId(clieId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cliente> consultarTodo() {
		return clienteDAO.consultarTodo();
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Cuenta> buscarLasCuentasCliente(BigDecimal clieId){
		return clienteDAO.buscarLasCuentasCliente(clieId);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<Cuenta> buscarLasCuentasRegistradas(BigDecimal clieId){
		return clienteDAO.buscarLasCuentasRegistradas(clieId);
	}
	
	public void validarClientes(Cliente cliente) throws Exception {
	    try {
	        Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Cliente> constraintViolation : constraintViolations) {
	                strMessage.append(constraintViolation.getPropertyPath().toString());
	                strMessage.append(" - ");
	                strMessage.append(constraintViolation.getMessage());
	                strMessage.append(". \n");
	            }

	            throw new Exception(strMessage.toString());
	        }
	    } catch (Exception e) {
	        throw e;
	    }
	}
}
