package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.CuentaRegistrada;

@Repository
@Scope("singleton")
public class CuentaRegistradaDAO implements ICuentaRegistradaDAO {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void grabar(CuentaRegistrada cuentaRegistrada) {
		entityManager.persist(cuentaRegistrada);
		
		
	}

	@Override
	public void modificar(CuentaRegistrada cuentaRegistrada) {
		entityManager.merge(cuentaRegistrada);
		
	}

	@Override
	public void borrar(CuentaRegistrada cuentaRegistrada) {
		entityManager.remove(cuentaRegistrada);
		
	}

	@Override
	public CuentaRegistrada consultarPorId(long cureId) {
		return entityManager.find(CuentaRegistrada.class, cureId);
	}
	
	@Override
	public Long consultarCuenExistente(String cuenId, BigDecimal clieId) {
		String jpql = "SELECT COUNT(cuenRe) FROM CuentaRegistrada cuenRe WHERE cuenRe.cuenta.cuenId='"+cuenId+"' AND cuenRe.cliente.clieId="+clieId;
		return (Long) entityManager.createQuery(jpql).getSingleResult();
	}

	@Override
	public List<CuentaRegistrada> consultarTodo() {
		String jpql="SELECT cuenRe FROM CuentaRegistrada cuenRe";
		return entityManager.createQuery(jpql).getResultList();
	}

}