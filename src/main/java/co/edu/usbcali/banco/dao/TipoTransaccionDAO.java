package co.edu.usbcali.banco.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.TipoTransaccion;


@Repository
@Scope("singleton")
public class TipoTransaccionDAO implements ITipoTransaccionDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void grabar(TipoTransaccion tipoTransaccion) {
		entityManager.persist(tipoTransaccion);
		
	}

	@Override
	public void modificar(TipoTransaccion tipoTransaccion) {
		entityManager.merge(tipoTransaccion);
		
	}

	@Override
	public void borrar(TipoTransaccion tipoTransaccion) {
		entityManager.remove(tipoTransaccion);
		
	}
	
	@Override
	public TipoTransaccion consultarPorTipoTraId(long titrId) {
		return entityManager.find(TipoTransaccion.class, titrId);
	}

	@Override
	public List<TipoTransaccion> consultarTodo() {
		String jpql="SELECT tTr FROM TipoTransaccion tTr";
		return entityManager.createQuery(jpql).getResultList();
	}



}
