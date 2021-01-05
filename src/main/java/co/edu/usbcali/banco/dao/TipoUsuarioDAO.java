package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.TipoDocumento;
import co.edu.usbcali.banco.modelo.TipoUsuario;

@Repository
@Scope("singleton")
public class TipoUsuarioDAO implements ITipoUsuarioDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void grabar(TipoUsuario tipoUsuario) {
		entityManager.persist(tipoUsuario);
		
	}

	@Override
	public void modificar(TipoUsuario tipoUsuario) {
		entityManager.merge(tipoUsuario);
		
	}

	@Override
	public void borrar(TipoUsuario tipoUsuario) {
		entityManager.remove(tipoUsuario);
		
	}
	
	@Override
	public TipoUsuario consultarPorId(long tiusId) {
		return entityManager.find(TipoUsuario.class, tiusId);
		
	}

	@Override
	public List<TipoUsuario> consultarTodo() {
		String jpql="SELECT tUs FROM TipoUsuario tUs";
		return entityManager.createQuery(jpql).getResultList();
	}

}
