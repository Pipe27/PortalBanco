package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;

@Repository
@Scope("singleton")
public class ClienteDAO implements IClienteDAO{
	
	@PersistenceContext
	private EntityManager  entityManager;

	@Override
	public void grabar(Cliente cliente) {
		entityManager.persist(cliente);
	}

	@Override
	public void modificar(Cliente cliente) {
		entityManager.merge(cliente);
	}

	@Override
	public void borrar(Cliente cliente) {
		entityManager.remove(cliente);
	}

	@Override
	public Cliente consultarPorId(BigDecimal clieId) {
		return entityManager.find(Cliente.class, clieId);
	}

	@Override
	public List<Cliente> consultarTodo() {
		String jpql="select cli FROM Cliente cli";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Cuenta> buscarLasCuentasCliente(BigDecimal clieId){
		String jpql="select cue from Cuenta cue where cue.cliente.clieId="+clieId+"";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public List<Cuenta> buscarLasCuentasRegistradas(BigDecimal clieId){
		String jpql="SELECT cue.cuenta FROM CuentaRegistrada cue WHERE cue.cliente.clieId="+clieId+"";
		return entityManager.createQuery(jpql).getResultList();
	}
}
