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



import co.edu.usbcali.banco.dao.IUsuarioDAO;
import co.edu.usbcali.banco.modelo.Usuario;

@Service
@Scope("singleton")
public class UsuarioLogicaIBeanValidator implements IUsuarioLogica {

	@Autowired
	private Validator validator;
	
	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void grabar(Usuario usuario) throws Exception {

		if(usuario==null) {
			throw new Exception("El usuario no puede ser Nulo");
		}
		
		validarUsuarios(usuario);
		
		if(consultarPorUsuUsuario(usuario.getUsuUsuario())!=null) {
			throw new Exception("El usuario ya existe");
		}
		
		if(usuarioDAO.identificacionUsuario(usuario.getIdentificacion()) == 1) {
			throw new Exception("Ya existe un usuario con la identificación ingresada.");
		}
		
		usuarioDAO.grabar(usuario);

	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void modificar(Usuario usuario) throws Exception {
		if(usuario==null) {
			throw new Exception("El usuario no puede ser Nulo");
		}
		
		validarUsuarios(usuario);
		
		usuarioDAO.modificar(usuario);
				
	}

	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void borrar(Usuario usuario) throws Exception {
		if(usuario==null) {
			throw new Exception("El usuario no puede ser nulo");
		}
		validarUsuarios(usuario);
		
		Usuario entity = usuarioDAO.consultarPorUsuUsuario(usuario.getUsuUsuario());
		usuarioDAO.borrar(entity);
		
	}

	@Override
	@Transactional(readOnly=true)
	public Usuario consultarPorUsuUsuario(String usuUsuario){
		return usuarioDAO.consultarPorUsuUsuario(usuUsuario);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long identificacionUsuario(BigDecimal identificacion) {
		return usuarioDAO.identificacionUsuario(identificacion);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Usuario> consultarTodo() {
		return usuarioDAO.consultarTodo();
	}

	public void validarUsuarios(Usuario usuario) throws Exception {
	    try {
	        Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

	        if (constraintViolations.size() > 0) {
	            StringBuilder strMessage = new StringBuilder();

	            for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
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
