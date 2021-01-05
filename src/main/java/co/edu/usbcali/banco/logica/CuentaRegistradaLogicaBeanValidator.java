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


import co.edu.usbcali.banco.dao.ICuentaRegistradaDAO;

import co.edu.usbcali.banco.modelo.CuentaRegistrada;


@Service
@Scope("singleton")
public class CuentaRegistradaLogicaBeanValidator implements ICuentaRegistradaLogica {
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ICuentaRegistradaDAO cuentaRegistradaDAO;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(CuentaRegistrada cuentaRegistrada) throws Exception {
		if(cuentaRegistrada==null) {
			throw new Exception("La cuenta  no puede ser nula");
		}
		
		validarCuentaRegistrada(cuentaRegistrada);
		
		if(cuentaRegistradaDAO.consultarCuenExistente(cuentaRegistrada.getCuenta().getCuenId(), cuentaRegistrada.getCliente().getClieId()) == 1) {
			throw new Exception("La cuenta  ya fue registrada anteriormente.");
		}
		
		cuentaRegistradaDAO.grabar(cuentaRegistrada);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(CuentaRegistrada cuentaRegistrada) throws Exception {
		if(cuentaRegistrada==null) {
			throw new Exception("La cuenta no puede ser nula");
		}
		validarCuentaRegistrada(cuentaRegistrada);
		cuentaRegistradaDAO.modificar(cuentaRegistrada);
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(CuentaRegistrada cuentaRegistrada) throws Exception {
		if(cuentaRegistrada==null) {
			throw new Exception("La cuenta no puede ser nula");
		}
		validarCuentaRegistrada(cuentaRegistrada);
		CuentaRegistrada entity=cuentaRegistradaDAO.consultarPorId(cuentaRegistrada.getCureId());
		cuentaRegistradaDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public CuentaRegistrada consultarPorId(long cureId) {
		return cuentaRegistradaDAO.consultarPorId(cureId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<CuentaRegistrada> consultarTodo() {
		return cuentaRegistradaDAO.consultarTodo();
	}
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public Long consultarCuenExistente(String cuenId, BigDecimal clieId) {
		return cuentaRegistradaDAO.consultarCuenExistente(cuenId, clieId);
	}
	
	public void validarCuentaRegistrada(CuentaRegistrada  cuentaRegistrada) throws Exception {
	    try {
	        Set<ConstraintViolation<CuentaRegistrada>> constraintViolations = validator.validate(cuentaRegistrada);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<CuentaRegistrada> constraintViolation : constraintViolations) {
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
