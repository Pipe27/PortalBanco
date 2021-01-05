package co.edu.usbcali.banco.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.Cuenta;

@Repository
@Scope("singleton")
public class CuentaDAO implements ICuentaDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void grabar(Cuenta cuenta) {
		entityManager.persist(cuenta);
		
	}

	@Override
	public void modificar(Cuenta cuenta) {
		entityManager.merge(cuenta);
	}

	@Override
	public void borrar(Cuenta cuenta) {
		entityManager.remove(cuenta);
		
	}
	
	@Override
	public Cuenta consultarPorId(String cuenId) {
		return entityManager.find(Cuenta.class, cuenId);
	}


	@Override
	public List<Cuenta> consultarTodo() {
		String jpql="SELECT cue FROM Cuenta cue";
		return entityManager.createQuery(jpql).getResultList();
	}

}
