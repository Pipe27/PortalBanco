package co.edu.usbcali.banco.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Usuario;

@Repository
@Scope("singleton")
public class UsuarioDAO implements IUsuarioDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void grabar(Usuario usuario) {
		entityManager.persist(usuario);
		
	}

	@Override
	public void modificar(Usuario usuario) {
		entityManager.merge(usuario);
		
	}

	@Override
	public void borrar(Usuario usuario) {
		entityManager.remove(usuario);
	}

	@Override
	public Usuario consultarPorUsuUsuario(String usuUsuario) {
		return entityManager.find(Usuario.class, usuUsuario);
	}
	
	@Override
	public List<Usuario> consultarTodo() {
		String jpql="SELECT usu FROM Usuario usu";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	@Override
	public Long identificacionUsuario(BigDecimal identificacion) {
		String jpql="SELECT COUNT(user) FROM Usuario user WHERE user.identificacion="+identificacion;
		return (Long)entityManager.createQuery(jpql).getSingleResult();
	}
}
