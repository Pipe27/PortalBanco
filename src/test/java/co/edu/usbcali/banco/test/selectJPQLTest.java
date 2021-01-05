package co.edu.usbcali.banco.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import co.edu.usbcali.banco.dto.ResultadoCuentaDTO;
import co.edu.usbcali.banco.dto.ResultadoCuentaClienDTO;
import co.edu.usbcali.banco.modelo.Cliente;
import co.edu.usbcali.banco.modelo.Cuenta;
import co.edu.usbcali.banco.modelo.Transaccion;

class selectJPQLTest {

private final static Logger log=LoggerFactory.getLogger(UsuarioJPATest.class);
	
	static EntityManagerFactory entityManagerFactory;
	static EntityManager entityManager;
	
	
	@Test
	@DisplayName("Consultar todos los clientes")
	void selectAll() {
		
		String jpql="select cli from Cliente cli";
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		
		for(Cliente cliente : losClientes) {
			log.info("Id: "+ cliente.getClieId());
			log.info("Nombre: "+ cliente.getNombre());
			log.info("email: "+ cliente.getEmail());
		}
		/*
		losClientes.forEach(cliente->{
			log.info("Id: "+ cliente.getClieId());
			log.info("Nombre: "+ cliente.getNombre());
			log.info("email: "+ cliente.getEmail());
		});
		*/
		
	}
	
	@Test
	@DisplayName("Consultar clientes con where")
	void selectWhere() {
		
		String jpql="select cli from Cliente cli WHERE cli.tipoDocumento.tdocId=2";
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		

		losClientes.forEach(cliente->{
			log.info("Id: "+ cliente.getClieId());
			log.info("Nombre: "+ cliente.getNombre());
			log.info("email: "+ cliente.getEmail());
			log.info("Nombre Tipo Documento:"+cliente.getTipoDocumento().getNombre());
		});
		
		
	}
	
	@Test
	@DisplayName("Consultar clientes con LIKE")
	void selectLike() {
		
		String jpql="select cli from Cliente cli WHERE cli.nombre LIKE 'J%%o'";
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		

		losClientes.forEach(cliente->{
			log.info("Id: "+ cliente.getClieId());
			log.info("Nombre: "+ cliente.getNombre());
			log.info("email: "+ cliente.getEmail());
			log.info("Nombre Tipo Documento:"+cliente.getTipoDocumento().getNombre());
		});
		
		
	}
	
	@Test
	@DisplayName("Consultar Aritmetica")
	void selectAritmetico() {
		
		String jpql="SELECT COUNT(cli) FROM Cliente cli";

		Long numeroDeClientes=(Long)entityManager.createQuery(jpql).getSingleResult();
		log.info(numeroDeClientes.toString());
		
		jpql="SELECT COUNT(cue), MIN(cue.saldo), MAX(cue.saldo), AVG(cue.saldo) FROM Cuenta cue";
		Object []resultado=(Object[])entityManager.createQuery(jpql).getSingleResult();
		log.info("Count:"+resultado[0]);
		log.info("Min:"+resultado[1]);
		log.info("Max:"+resultado[2]);
		log.info("Avg:"+resultado[3]);
		
		jpql="SELECT new co.edu.usbcali.banco.dto.ResultadoCuentaDTO( COUNT(cue), MIN(cue.saldo), MAX(cue.saldo), AVG(cue.saldo)) FROM Cuenta cue";
		ResultadoCuentaDTO resultadoCuentaDTO=(ResultadoCuentaDTO)entityManager.createQuery(jpql).getSingleResult();
		log.info("Count:"+resultadoCuentaDTO.getCount());
		log.info("Min:"+resultadoCuentaDTO.getMin());
		log.info("Max:"+resultadoCuentaDTO.getMax());
		log.info("Avg:"+resultadoCuentaDTO.getAvg());
	}
	/*Tarea*/
	
	/*1 Punto*/
	@Test
	@DisplayName("Consultar Nombre cliente y cuenta con más dinero ")
	void selectCuenta() {
		
		String jpql="select cue from Cuenta cue where cue.saldo=(select MAX(cue.saldo) from Cuenta cue)";
		
		Cuenta cuentica =(Cuenta) entityManager.createQuery(jpql).getSingleResult();
		
		
		log.info("Cuenta: "+cuentica.getCuenId());
		log.info("Nmobre Cliente: "+cuentica.getCliente().getNombre());
		
			
	}
	/*2 Punto*/
	@Test
	@DisplayName("Suma de saldos disponibles")
	void selectSumCuenta() {
		
		String jpql="select SUM(cue.saldo) from Cuenta cue";
		BigDecimal resultado = (BigDecimal)entityManager.createQuery(jpql).getSingleResult();
		
		log.info("Suma de saldos: "+resultado);		
	}
	
	/*3 Punto*/
	@Test
	@DisplayName("Consultar saldo total de un cliente")
	void selectSaldoPorCliente() {
		
		String jpql="select Sum(saldo) from Cuenta cue where cue.cliente.clieId=100 ";
		
		BigDecimal resultado = (BigDecimal)entityManager.createQuery(jpql).getSingleResult();
		
		log.info("Sumatoria de dinero de cuentas:"+resultado);	
		
	}
	
	/*4 Punto*/
	@Test
	@DisplayName("Promedio de saldos disponibles")
	void selectPromedioCuenta() {
		
		String jpql="select AVG(cue.saldo) from Cuenta cue";
		Double resultado = (Double)entityManager.createQuery(jpql).getSingleResult();
		
		log.info("Promedio de saldos: "+resultado);		
	}
	
	/*5 Punto*/
	@Test
	@DisplayName("Consulta retiros por intervalos")
	void selectBetween() {
		
		String jpql="select tr from Transaccion tr WHERE tr.tipoTransaccion=1 AND tr.valor between 100000 and 15000000 ";
		List<Transaccion> retiros = entityManager.createQuery(jpql).getResultList();
		
		retiros.forEach(retiro->{
			log.info("id: "+retiro.getTranId());
			log.info("Valor: "+retiro.getValor());
			log.info("Usuario: "+retiro.getUsuario().getNombre());
		});
			
	}
	
	/*6 Punto*/
	
	@Test
	@DisplayName("Consultar clientes con Nombres con a")
	void selectLikeA() {
		
		String jpql="select cli from Cliente cli WHERE cli.nombre LIKE '%a%'";
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		

		losClientes.forEach(cliente->{
			log.info("Id: "+ cliente.getClieId());
			log.info("Nombre: "+ cliente.getNombre());
			log.info("email: "+ cliente.getEmail());
			log.info("Nombre Tipo Documento:"+cliente.getTipoDocumento().getNombre());
		});
		
		
	}
	
	/*7 Punto */
	@Test
	@DisplayName("Consultar clientes con mas de una cuenta")
	void selectClienteConCuentas() {
		
		String jpql="select cue.cliente from Cuenta cue where (select distinct count(cuen.cliente) from Cuenta cuen where cue.cliente=cuen.cliente group by cuen.cliente)>1";
		List<Cliente> losClientes = entityManager.createQuery(jpql).getResultList();
		
		losClientes.forEach(cliente->{
			log.info("Nombre: "+ cliente.getNombre());
		});
	}

		
	/*8 Punto*/
	@Test
	@DisplayName("Consulta de cosignaciones por numero de cuenta")
	void selectConsignacionCuenta() {
		
		String jpql="select tr from Transaccion tr WHERE tr.tipoTransaccion=2 AND tr.cuenta.cuenId='8682-7001-7740-8714'";
		List<Transaccion> retiros = entityManager.createQuery(jpql).getResultList();
		
		retiros.forEach(retiro->{
			log.info("id: "+retiro.getTranId());
			log.info("Valor: "+retiro.getValor());
			log.info("Usuario: "+retiro.getUsuario().getNombre());
		});
	}
	
	/*9 Punto*/
	@Test
	@DisplayName("Consulta retiros mayores a 100000")
	void selectRetirosMayores() {
		
		String jpql="select tr from Transaccion tr WHERE tr.tipoTransaccion=1 AND tr.valor>100000";
		List<Transaccion> retiros = entityManager.createQuery(jpql).getResultList();
		
		retiros.forEach(retiro->{
			log.info("id: "+retiro.getTranId());
			log.info("Valor: "+retiro.getValor());
			log.info("Usuario: "+retiro.getUsuario().getNombre());
			log.info("Cuenta: "+retiro.getCuenta().getCuenId());
		});
			
	}
	
	/*10 Punto*/
	
	@Test
	@DisplayName("Consulta de numero de las cuentas y el nombre el propietario por identificacion del cliente ")
	void selectCuentasIdCliente() {
		
		
		String jpql="SELECT distinct NEW co.edu.usbcali.banco.dto.ResultadoCuentaClienDTO ( count(cu.cliente.clieId) ,cu.cliente.nombre) from Cuenta cu where cu.cliente.clieId=100 group by cu.cliente.nombre ,cu.cliente.clieId";
		ResultadoCuentaClienDTO resultado = (ResultadoCuentaClienDTO)entityManager.createQuery(jpql).getSingleResult();
		log.info("Numero de Cuentas:"+resultado.getContador());
		log.info("Nombre Cliente:"+resultado.getNombreCliente());
		
		/*
		
		 String jpql="select cue from Cuenta cue where cue.cliente.clieId=100 ";
		 List<Cuenta> Cuentas = entityManager.createQuery(jpql).getResultList();
		Cuentas.forEach(cuenta->{
			log.info("Cuenta id: "+cuenta.getCuenId());
			log.info("Nombre: "+cuenta.getCliente().getNombre());
			
		});
		*/
			
	}
	
	
	
	@BeforeAll
	public static void inicializarTodo() {
		entityManagerFactory=Persistence.createEntityManagerFactory("banco-persitencia");
		entityManager=entityManagerFactory.createEntityManager();
	}	
	@AfterAll
	public static void cerrarTodo() {
		entityManager.close();
		entityManagerFactory.close();
	}
	

}
