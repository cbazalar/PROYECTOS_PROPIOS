package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="Tarjeta.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */

public class Tarjeta extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6445005486385557217L;

	private String numeroTarjeta;

	private String codigoPais;

	private String codigoCliente;

	private String codigoConsultora;

	private int puntajeAbonadoTarjeta;

	private int puntajeTotal; // Puntaje en Tarjeta de Puntos

	private String campañaRegistro;

	private String statusImpresion;

	private String status;

	/**
	 * @return Returns the campañaRegion.
	 */
	public String getCampañaRegistro() {
		return campañaRegistro;
	}

	/**
	 * @param campañaRegion
	 *            The campañaRegion to set.
	 */
	public void setCampañaRegistro(String campañaRegistro) {
		this.campañaRegistro = campañaRegistro;
	}

	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return Returns the statusImpresion.
	 */
	public String getStatusImpresion() {
		return statusImpresion;
	}

	/**
	 * @param statusImpresion
	 *            The statusImpresion to set.
	 */
	public void setStatusImpresion(String statusImpresion) {
		this.statusImpresion = statusImpresion;
	}

	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 *            The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return Returns the codigoConsultora.
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora
	 *            The codigoConsultora to set.
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
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
	 * @return Returns the numeroTarjeta.
	 */
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	/**
	 * @param numeroTarjeta
	 *            The numeroTarjeta to set.
	 */
	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	/**
	 * @return Returns the puntajeAbonadoTarjeta.
	 */
	public int getPuntajeAbonadoTarjeta() {
		return puntajeAbonadoTarjeta;
	}

	/**
	 * @param puntajeAbonadoTarjeta
	 *            The puntajeAbonadoTarjeta to set.
	 */
	public void setPuntajeAbonadoTarjeta(int puntajeAbonado) {
		this.puntajeAbonadoTarjeta = puntajeAbonado;
	}

	/**
	 * @return Returns the puntajeTotal.
	 */
	public int getPuntajeTotal() {
		return puntajeTotal;
	}

	/**
	 * @param puntajeTotal
	 *            The puntajeTotal to set.
	 */
	public void setPuntajeTotal(int puntajeTotal) {
		this.puntajeTotal = puntajeTotal;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Tarjeta)) {
			return false;
		}
		Tarjeta rhs = (Tarjeta) object;
		return new EqualsBuilder()
				.append(this.codigoCliente, rhs.codigoCliente).append(
						this.statusImpresion, rhs.statusImpresion).append(
						this.puntajeAbonadoTarjeta, rhs.puntajeAbonadoTarjeta).append(
						this.codigoPais, rhs.codigoPais).append(
						this.codigoConsultora, rhs.codigoConsultora).append(
						this.puntajeTotal, rhs.puntajeTotal).append(
						this.auditInfo, rhs.auditInfo).append(this.status,
						rhs.status).append(this.campañaRegistro,
						rhs.campañaRegistro).append(this.numeroTarjeta,
						rhs.numeroTarjeta).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(2101996509, 156532001).append(
				this.codigoCliente).append(this.statusImpresion).append(
				this.puntajeAbonadoTarjeta).append(this.codigoPais).append(
				this.codigoConsultora).append(this.puntajeTotal).append(
				this.auditInfo).append(this.status)
				.append(this.campañaRegistro).append(this.numeroTarjeta)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("numeroTarjeta", this.numeroTarjeta).append(
						"codigoPais", this.codigoPais).append(
						"campañaRegistro", this.campañaRegistro).append(
						"puntajeAbonadoTarjeta", this.puntajeAbonadoTarjeta).append(
						"codigoCliente", this.codigoCliente).append("status",
						this.status).append("auditInfo", this.auditInfo)
				.append("puntajeTotal", this.puntajeTotal).append(
						"codigoConsultora", this.codigoConsultora).append(
						"statusImpresion", this.statusImpresion).toString();
	}
}