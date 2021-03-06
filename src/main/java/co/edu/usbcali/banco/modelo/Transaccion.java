package co.edu.usbcali.banco.modelo;
// Generated 30/01/2018 10:10:31 AM by Hibernate Tools 5.2.3.Final

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * Transaccion generated by hbm2java
 */
@Entity
@Table(name = "transaccion", schema = "public")
public class Transaccion implements java.io.Serializable {

	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long tranId;
	
	@NotNull(message="La cuenta no puede estar nula")
	private Cuenta cuenta;
	
	@NotNull(message="El tipo de transaccion  no puede estar nulo")
	private TipoTransaccion tipoTransaccion;
	
	@NotNull(message="El usuario no puede estar nulo")
	private Usuario usuario;
	
	@NotNull(message="El valor no puede ser nulo")
	private BigDecimal valor;
	
	@NotNull(message="La fecha no puede ser nula")
	private Date fecha;

	public Transaccion() {
	}

	public Transaccion(long tranId, BigDecimal valor, Date fecha) {
		this.tranId = tranId;
		this.valor = valor;
		this.fecha = fecha;
	}

	public Transaccion(long tranId, Cuenta cuenta, TipoTransaccion tipoTransaccion, Usuario usuario, BigDecimal valor,
			Date fecha) {
		this.tranId = tranId;
		this.cuenta = cuenta;
		this.tipoTransaccion = tipoTransaccion;
		this.usuario = usuario;
		this.valor = valor;
		this.fecha = fecha;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "tran_id", unique = true, nullable = false)
	public long getTranId() {
		return this.tranId;
	}

	public void setTranId(long tranId) {
		this.tranId = tranId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cuen_id")
	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "titr_id")
	public TipoTransaccion getTipoTransaccion() {
		return this.tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usu_usuario")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Column(name = "valor", nullable = false, precision = 30, scale = 6)
	public BigDecimal getValor() {
		return this.valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha", nullable = false, length = 29)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
