/*
 * Created on 10/11/2005 11:58:54 AM biz.belcorp.ssicc.model.Cliente
 */
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
 * <a href="Cliente.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 * @struts.form include-all="false" extends="BaseEditForm"
 * @hibernate.class table="PRI_CLIEN"
 */

public class Cliente extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4803992490331539583L;

	private String codigo;

	private String codigoPais;

	private String codigoConsultora;

	private String nombres;

	private String documentoIdentidad;

	private String numeroFichaInscripcion;

	private String campañaRegistro;

	private int puntajeAbonado;

	private String statusImpresion;

	private String status;

	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo
	 *            The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
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
	 * @return Returns the documentoIdentidad.
	 */
	public String getDocumentoIdentidad() {
		return documentoIdentidad;
	}

	/**
	 * @param documentoIdentidad
	 *            The documentoIdentidad to set.
	 */
	public void setDocumentoIdentidad(String documentoIdentidad) {
		this.documentoIdentidad = documentoIdentidad;
	}

	/**
	 * @return Returns the nombres.
	 */
	public String getNombres() {
		return nombres;
	}

	/**
	 * @param nombres
	 *            The nombres to set.
	 */
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	/**
	 * @return Returns the numeroFichaInscripcion.
	 */
	public String getNumeroFichaInscripcion() {
		return numeroFichaInscripcion;
	}

	/**
	 * @param numeroFichaInscripcion
	 *            The numeroFichaInscripcion to set.
	 */
	public void setNumeroFichaInscripcion(String numeroFichaInscripcion) {
		this.numeroFichaInscripcion = numeroFichaInscripcion;
	}

	/**
	 * @return Returns the puntajeAbonado.
	 */
	public int getPuntajeAbonado() {
		return puntajeAbonado;
	}

	/**
	 * @param puntajeAbonado
	 *            The puntajeAbonado to set.
	 */
	public void setPuntajeAbonado(int puntajeAbonado) {
		this.puntajeAbonado = puntajeAbonado;
	}

	/**
	 * @return Returns the campaaRegistro.
	 */
	public String getCampañaRegistro() {
		return campañaRegistro;
	}

	/**
	 * @param campaaRegistro
	 *            The campaaRegistro to set.
	 */
	public void setCampañaRegistro(String campañaRegistro) {
		this.campañaRegistro = campañaRegistro;
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
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Cliente)) {
			return false;
		}
		Cliente rhs = (Cliente) object;
		return new EqualsBuilder()
				.append(this.statusImpresion, rhs.statusImpresion)
				.append(this.puntajeAbonado, rhs.puntajeAbonado)
				.append(this.codigoPais, rhs.codigoPais)
				.append(this.nombres, rhs.nombres)
				.append(this.codigoConsultora, rhs.codigoConsultora)
				.append(this.auditInfo, rhs.auditInfo)
				.append(this.documentoIdentidad, rhs.documentoIdentidad)
				.append(this.status, rhs.status)
				.append(this.campañaRegistro, rhs.campañaRegistro)
				.append(this.codigo, rhs.codigo)
				.append(this.numeroFichaInscripcion, rhs.numeroFichaInscripcion)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(947949767, 1593984901).append(
				this.statusImpresion).append(this.puntajeAbonado).append(
				this.codigoPais).append(this.nombres).append(
				this.codigoConsultora).append(this.auditInfo).append(
				this.documentoIdentidad).append(this.status).append(
				this.campañaRegistro).append(this.codigo).append(
				this.numeroFichaInscripcion).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("documentoIdentidad", this.documentoIdentidad).append(
						"campaaRegistro", this.campañaRegistro).append(
						"codigoPais", this.codigoPais).append("puntajeAbonado",
						this.puntajeAbonado).append("status", this.status)
				.append("numeroFichaInscripcion", this.numeroFichaInscripcion)
				.append("auditInfo", this.auditInfo).append("nombres",
						this.nombres).append("codigo", this.codigo).append(
						"codigoConsultora", this.codigoConsultora).append(
						"statusImpresion", this.statusImpresion).toString();
	}

}
