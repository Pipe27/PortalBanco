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

import co.edu.usbcali.banco.dao.ITipoUsuarioDAO;
import co.edu.usbcali.banco.modelo.TipoUsuario;


@Service
@Scope("singleton")
public class TipoUsuarioLogicaBeanValidator implements ITipoUsuarioLogica{
		
	@Autowired
	private ITipoUsuarioDAO tipoUsuarioDAO;
	
	@Autowired
	private Validator validator;

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(TipoUsuario tipoUsuario) throws Exception {
		if(tipoUsuario==null) {
			throw new Exception("El tipoUsuario no puede ser Nulo");
		}
		
		validarTipoUsuario(tipoUsuario);
		
		if(consultarPorId(tipoUsuario.getTiusId())!=null) {
			throw new Exception("El tipoUsuario ya existe");
		}
		
		tipoUsuarioDAO.grabar(tipoUsuario);	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(TipoUsuario tipoUsuario) throws Exception {
		if(tipoUsuario==null) {
			throw new Exception("El tipoUsuario no puede ser Nulo");
		}
		
		validarTipoUsuario(tipoUsuario);
		
		tipoUsuarioDAO.modificar(tipoUsuario);
	
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(TipoUsuario tipoUsuario) throws Exception {
		if(tipoUsuario==null) {
			throw new Exception("El tipoUsuario no puede ser Nulo");
		}
		
		validarTipoUsuario(tipoUsuario);
		
		TipoUsuario entity=tipoUsuarioDAO.consultarPorId(tipoUsuario.getTiusId());
		
		tipoUsuarioDAO.borrar(entity);
	}

	@Override
	@Transactional(readOnly=true)
	public TipoUsuario consultarPorId(long tiusId) {
		return tipoUsuarioDAO.consultarPorId(tiusId);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TipoUsuario> consultarTodo() {
		return tipoUsuarioDAO.consultarTodo();
	}

	public void validarTipoUsuario(TipoUsuario tipoUsuario) throws Exception {
	    try {
	        Set<ConstraintViolation<TipoUsuario>> constraintViolations = validator.validate(tipoUsuario);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<TipoUsuario> constraintViolation : constraintViolations) {
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
