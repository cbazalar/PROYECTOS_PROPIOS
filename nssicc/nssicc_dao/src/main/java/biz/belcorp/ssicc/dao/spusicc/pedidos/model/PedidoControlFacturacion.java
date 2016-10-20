package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class PedidoControlFacturacion extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3471951656456189540L;

	private String id;
	
	private String codigoPais;

	private String codigoPeriodo;

	private String fechaProceso;

	private BigDecimal montoMinimoFact;

	private BigDecimal montoMinimoAcept;
	
	private BigDecimal montoMinimoDeuda;	

	private BigDecimal montoMaximo;

	private int unidadesMaximo;

	private String estadoCampanha;
	
	private String codigoMarca;	
	
	private String descripcionMarca;
	
	private String codigoCanal;	
	
	private String descripcionCanal;	
	
	private String numeroLote;	
	
	private String campaniaActiva;
	
	private String usuario;	

	/**
	 * @return the campaniaActiva
	 */
	public String getCampaniaActiva() {
		return campaniaActiva;
	}

	/**
	 * @param campaniaActiva the campaniaActiva to set
	 */
	public void setCampaniaActiva(String campaniaActiva) {
		this.campaniaActiva = campaniaActiva;
	}

	public String getCodigoCanal() {
		return codigoCanal;
	}

	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	public String getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getDescripcionCanal() {
		return descripcionCanal;
	}

	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}

	public String getDescripcionMarca() {
		return descripcionMarca;
	}

	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	public String getEstadoCampanha() {
		return estadoCampanha;
	}

	public void setEstadoCampanha(String estadoCampanha) {
		this.estadoCampanha = estadoCampanha;
	}

	public String getFechaProceso() {
		return fechaProceso;
	}

	public void setFechaProceso(String fechaProceso) {
		this.fechaProceso = fechaProceso;
	}

	public int getUnidadesMaximo() {
		return unidadesMaximo;
	}

	public void setUnidadesMaximo(int unidadesMaximo) {
		this.unidadesMaximo = unidadesMaximo;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PedidoControlFacturacion)) {
			return false;
		}
		PedidoControlFacturacion rhs = (PedidoControlFacturacion) object;
		return new EqualsBuilder().append(
				this.codigoPais, rhs.codigoPais).append(this.unidadesMaximo,
				rhs.unidadesMaximo).append(this.montoMaximo, rhs.montoMaximo)
				.append(this.montoMinimoAcept, rhs.montoMinimoAcept).append(
						this.auditInfo, rhs.auditInfo).append(
						this.montoMinimoFact, rhs.montoMinimoFact).append(
						this.fechaProceso, rhs.fechaProceso).append(
						this.estadoCampanha, rhs.estadoCampanha).append(
						this.codigoPeriodo, rhs.codigoPeriodo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1244145513, -856686905).append(this.codigoPais).append(
				this.unidadesMaximo).append(this.montoMaximo).append(
				this.montoMinimoAcept).append(this.auditInfo).append(
				this.montoMinimoFact).append(this.fechaProceso).append(
				this.estadoCampanha).append(this.codigoPeriodo).toHashCode();
	}

	public BigDecimal getMontoMaximo() {
		return montoMaximo;
	}

	public void setMontoMaximo(BigDecimal montoMaximo) {
		this.montoMaximo = montoMaximo;
	}

	public BigDecimal getMontoMinimoAcept() {
		return montoMinimoAcept;
	}

	public void setMontoMinimoAcept(BigDecimal montoMinimoAcept) {
		this.montoMinimoAcept = montoMinimoAcept;
	}

	public BigDecimal getMontoMinimoFact() {
		return montoMinimoFact;
	}

	public void setMontoMinimoFact(BigDecimal montoMinimoFact) {
		this.montoMinimoFact = montoMinimoFact;
	}

	/**
	 * @see java.lang.Object#toString()
	 */


	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PedidoControlFacturacion [id=" + id + ", codigoPais="
				+ codigoPais + ", codigoPeriodo=" + codigoPeriodo
				+ ", fechaProceso=" + fechaProceso + ", montoMinimoFact="
				+ montoMinimoFact + ", montoMinimoAcept=" + montoMinimoAcept
				+ ", montoMinimoDeuda=" + montoMinimoDeuda + ", montoMaximo="
				+ montoMaximo + ", unidadesMaximo=" + unidadesMaximo
				+ ", estadoCampanha=" + estadoCampanha + ", codigoMarca="
				+ codigoMarca + ", descripcionMarca=" + descripcionMarca
				+ ", codigoCanal=" + codigoCanal + ", descripcionCanal="
				+ descripcionCanal + ", numeroLote=" + numeroLote
				+ ", campaniaActiva=" + campaniaActiva + ", usuario=" + usuario
				+ "]";
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getMontoMinimoDeuda() {
		return montoMinimoDeuda;
	}

	public void setMontoMinimoDeuda(BigDecimal montoMinimoDeuda) {
		this.montoMinimoDeuda = montoMinimoDeuda;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
}
