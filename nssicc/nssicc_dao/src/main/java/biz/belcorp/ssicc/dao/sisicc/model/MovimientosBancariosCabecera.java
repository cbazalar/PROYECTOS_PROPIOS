package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class MovimientosBancariosCabecera extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3767228838412260571L;

	private String codigoPais;
	
	private Date fechaProceso;
	
	private String codigoSociedad;
	
	private String codigoBancoSicc;
		
	private String statusLote;
	
	private String estado;
	
	private String numeroLoteInterno;
	
	private String numeroLoteExterno;
	
	private String codigoTipoOrigenDatos;
		
	/**
	 * @return Returns the codigoBancoSicc.
	 */
	public String getCodigoBancoSicc() {
		return codigoBancoSicc;
	}
	/**
	 * @param codigoBancoSicc
	 *            The codigoBancoSicc to set.
	 */
	public void setCodigoBancoSicc(String codigoBancoSicc) {
		this.codigoBancoSicc = codigoBancoSicc;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoSociedad.
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	/**
	 * @param codigoSociedad
	 *            The codigoSociedad to set.
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado
	 *            The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Returns the fechaProceso.
	 */
	public Date getFechaProceso() {
		return fechaProceso;
	}
	/**
	 * @param fechaProceso
	 *            The fechaProceso to set.
	 */
	public void setFechaProceso(Date fechaProceso) {
		this.fechaProceso = fechaProceso;
	}
	/**
	 * @return Returns the statusLote.
	 */
	public String getStatusLote() {
		return statusLote;
	}
	/**
	 * @param statusLote
	 *            The statusLote to set.
	 */
	public void setStatusLote(String statusLote) {
		this.statusLote = statusLote;
	}
		
	/**
	 * @return Returns the numeroLoteExterno.
	 */
	public String getNumeroLoteExterno() {
		return numeroLoteExterno;
	}
	/**
	 * @param numeroLoteExterno The numeroLoteExterno to set.
	 */
	public void setNumeroLoteExterno(String numeroLoteExterno) {
		this.numeroLoteExterno = numeroLoteExterno;
	}
	/**
	 * @return Returns the numeroLoteInterno.
	 */
	public String getNumeroLoteInterno() {
		return numeroLoteInterno;
	}
	/**
	 * @param numeroLoteInterno The numeroLoteInterno to set.
	 */
	public void setNumeroLoteInterno(String numeroLoteInterno) {
		this.numeroLoteInterno = numeroLoteInterno;
	}
	
	
	/**
	 * @return Returns the codigoTipoOrigenDatos.
	 */
	public String getCodigoTipoOrigenDatos() {
		return codigoTipoOrigenDatos;
	}
	/**
	 * @param codigoTipoOrigenDatos The codigoTipoOrigenDatos to set.
	 */
	public void setCodigoTipoOrigenDatos(String codigoTipoOrigenDatos) {
		this.codigoTipoOrigenDatos = codigoTipoOrigenDatos;
	}
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1600772383, -1519281453).append(
				this.codigoTipoOrigenDatos).append(this.codigoPais).append(
				this.codigoSociedad).append(this.codigoBancoSicc).append(
				this.numeroLoteInterno).append(this.auditInfo).append(this.estado)
				.append(this.fechaProceso).append(this.statusLote).toHashCode();
	}
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoPais", this.codigoPais)
				.append("estado",this.estado)
				.append("codigoSociedad",this.codigoSociedad)
				.append("codigoBancoSicc",this.codigoBancoSicc)
				.append("numeroLoteInterno",this.numeroLoteInterno)
				.append("codigoTipoOrigenDatos", this.codigoTipoOrigenDatos)
				.append("auditInfo", this.auditInfo)
				.append("fechaProceso",this.fechaProceso)
				.append("statusLote", this.statusLote).toString();
	}
	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof MovimientosBancariosCabecera)) {
			return false;
		}
		MovimientosBancariosCabecera rhs = (MovimientosBancariosCabecera) object;
		return new EqualsBuilder().append(this.codigoTipoOrigenDatos, rhs.codigoTipoOrigenDatos)
				.append(this.codigoPais, rhs.codigoPais).append(
						this.codigoSociedad, rhs.codigoSociedad).append(
						this.codigoBancoSicc, rhs.codigoBancoSicc).append(
						this.numeroLoteInterno, rhs.numeroLoteInterno).append(this.auditInfo,
						rhs.auditInfo).append(this.estado, rhs.estado).append(
						this.fechaProceso, rhs.fechaProceso).append(
						this.statusLote, rhs.statusLote).isEquals();
	}
	
}
