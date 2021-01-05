package co.edu.usbcali.banco.logica;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.usbcali.banco.dao.ICuentaDAO;

import co.edu.usbcali.banco.modelo.Cuenta;

@Service
@Scope("singleton")
public class CuentaLogicaBeanValidator implements ICuentaLogica {
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ICuentaDAO cuentaDAO;


	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(Cuenta cuenta) throws Exception {
		
		if(cuenta==null) {
			throw new Exception("La cuenta  no puede ser nula");
		}
		
		validarCuenta(cuenta);
		
		if(consultarPorId(cuenta.getCuenId())!=null) {
			throw new Exception("La cuenta ya existe");
		}
		cuentaDAO.grabar(cuenta);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(Cuenta cuenta) throws Exception {
		if(cuenta==null) {
			throw new Exception("La cuenta no puede ser nula");
		}
		
		validarCuenta(cuenta);
		cuentaDAO.modificar(cuenta);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(Cuenta cuenta) throws Exception {
		if(cuenta==null) {
			throw new Exception("La cuenta no puede ser nula");
		}
		validarCuenta(cuenta);
		
		Cuenta entity=cuentaDAO.consultarPorId(cuenta.getCuenId());
		
		cuentaDAO.borrar(entity);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Cuenta consultarPorId(String cuenId) {
		return cuentaDAO.consultarPorId(cuenId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cuenta> consultarTodo() {
		return cuentaDAO.consultarTodo();
	}
	
	public void validarCuenta(Cuenta  cuenta) throws Exception {
	    try {
	        Set<ConstraintViolation<Cuenta>> constraintViolations = validator.validate(cuenta);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Cuenta> constraintViolation : constraintViolations) {
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
