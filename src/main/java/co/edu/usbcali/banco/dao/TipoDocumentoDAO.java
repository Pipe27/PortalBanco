package co.edu.usbcali.banco.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.TipoDocumento;

@Repository
@Scope("singleton")
public class TipoDocumentoDAO implements ITipoDocumentoDAO{

	@PersistenceContext
	private EntityManager  entityManager;
	
	@Override
	public void grabar(TipoDocumento tipoDocumento) {
		entityManager.persist(tipoDocumento);
		
	}

	@Override
	public void modificar(TipoDocumento tipoDocumento) {
		entityManager.merge(tipoDocumento);
		
	}

	@Override
	public void borrar(TipoDocumento tipoDocumento) {
		entityManager.remove(tipoDocumento);
		
	}

	@Override
	public TipoDocumento consultarPorId(long tdocId) {
		return entityManager.find(TipoDocumento.class, tdocId);
	}

	@Override
	public List<TipoDocumento> consultarTodo() {
		String jpql="select td FROM TipoDocumento td";
		return entityManager.createQuery(jpql).getResultList();
		
	}

}
