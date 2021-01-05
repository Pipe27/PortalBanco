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

import co.edu.usbcali.banco.dao.ITipoTransaccionDAO;
import co.edu.usbcali.banco.modelo.TipoTransaccion;


@Service
@Scope("singleton")
public class TipoTransaccionLogicaBeanValidator implements ITipoTransaccionLogica {
	
	@Autowired
	private Validator validator;
	
	@Autowired
	private ITipoTransaccionDAO tipoTransaccionDAO;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(TipoTransaccion tipoTransaccion) throws Exception {
		if(tipoTransaccion==null) {
			throw new Exception("El tipo Transaccion no puede ser nulo");
		}
		
		validarTipoTransaccion(tipoTransaccion);
		
		if(tipoTransaccionDAO.consultarPorTipoTraId(tipoTransaccion.getTitrId())!=null) {
			throw new Exception("El tipo de transaccion ya existe");
		}
		tipoTransaccionDAO.grabar(tipoTransaccion);
		
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(TipoTransaccion tipoTransaccion) throws Exception {
		if(tipoTransaccion==null) {
			throw new Exception("El tipo de transaccion no puede ser nulo");
		}
		
		validarTipoTransaccion(tipoTransaccion);
		tipoTransaccionDAO.modificar(tipoTransaccion);
		
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(TipoTransaccion tipoTransaccion) throws Exception {
		if(tipoTransaccion==null) {
			throw new Exception("El tipo transaccion no puede ser nulo");
		}
		validarTipoTransaccion(tipoTransaccion);
		
		TipoTransaccion entity=tipoTransaccionDAO.consultarPorTipoTraId(tipoTransaccion.getTitrId());
		
		tipoTransaccionDAO.borrar(entity);
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public TipoTransaccion consultarPorTipoTraId(long titrId) {
		return tipoTransaccionDAO.consultarPorTipoTraId(titrId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoTransaccion> consultarTodo() {
		return tipoTransaccionDAO.consultarTodo();
	}


	
	public void validarTipoTransaccion(TipoTransaccion  tipoTransaccion) throws Exception {
	    try {
	        Set<ConstraintViolation<TipoTransaccion>> constraintViolations = validator.validate(tipoTransaccion);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<TipoTransaccion> constraintViolation : constraintViolations) {
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
