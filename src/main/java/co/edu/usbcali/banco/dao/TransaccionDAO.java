package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.TipoTransaccion;
import co.edu.usbcali.banco.modelo.Transaccion;

@Repository
@Scope("singleton")
public class TransaccionDAO implements ITransaccionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	
	@Override
	public void grabar(Transaccion transaccion) {
		entityManager.persist(transaccion);
		
	}

	@Override
	public void modificar(Transaccion transaccion) {
		entityManager.merge(transaccion);
	}

	@Override
	public void borrar(Transaccion transaccion) {
		entityManager.remove(transaccion);
	}

	@Override
	public Transaccion consultarPorTransId(long tranId) {
		return entityManager.find(Transaccion.class, tranId);
	}
	
	@Override
	public List<Transaccion> consultarTodo() {
		String jpql="SELECT tr FROM Transaccion tr";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Transaccion> consultarPorUsu(String usuUsuario) {
		String jpql="SELECT tr FROM Transaccion tr WHERE usuario.usuUsuario='"+usuUsuario+"'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Transaccion> consultarRetiros(String cuenId){
		String jpql="SELECT tr FROM Transaccion tr WHERE tr.tipoTransaccion.titrId = 1 AND tr.cuenta.cuenId='"+cuenId+"'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Transaccion> consultarConsignaciones(String cuenId){
		String jpql="SELECT tr FROM Transaccion tr WHERE tr.tipoTransaccion.titrId = 2 AND tr.cuenta.cuenId='"+cuenId+"'";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Transaccion> consultarTransferencias(String cuenId){
		String jpql="SELECT tr FROM Transaccion tr WHERE tr.tipoTransaccion.titrId = 3 AND tr.cuenta.cuenId='"+cuenId+"'";
		return entityManager.createQuery(jpql).getResultList();
	}
}
