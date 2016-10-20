/*
 * Created on 08/11/2005 02:09:25 PM
 *
 * biz.belcorp.ssicc.model.NivelRol
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MovimientoCliente.java.html"> <ci>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:jrosas@belcorp.biz">Jhenifer Rosas L. </a>
 */

public class MovimientoCliente extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7771913788113893050L;

	private String codigoPais;

	private String codigoConsultora;

	private String numeroDocumento;

	private String codigoCliente;

	private String nombreCliente;

	private int puntosAcumulados;

	private int puntosUtilizados;

	private int saldoDisponible;

	private int puntosComprometidos;

	private String periodoIngreso;

	private String numeroTelefono;

    private int puntosInscripcion;

	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente The codigoCliente to set.
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
	 * @param codigoConsultora The codigoConsultora to set.
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
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the nombreCliente.
	 */
	public String getNombreCliente() {
		return nombreCliente;
	}

	/**
	 * @param nombreCliente The nombreCliente to set.
	 */
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	/**
	 * @return Returns the numeroDocumento.
	 */
	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	/**
	 * @param numeroDocumento The numeroDocumento to set.
	 */
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	/**
	 * @return Returns the numeroTelefono.
	 */
	public String getNumeroTelefono() {
		return numeroTelefono;
	}

	/**
	 * @param numeroTelefono The numeroTelefono to set.
	 */
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}

	/**
	 * @return Returns the periodoIngreso.
	 */
	public String getPeriodoIngreso() {
		return periodoIngreso;
	}

	/**
	 * @param periodoIngreso The periodoIngreso to set.
	 */
	public void setPeriodoIngreso(String periodoIngreso) {
		this.periodoIngreso = periodoIngreso;
	}

	/**
	 * @return Returns the puntosAcumulados.
	 */
	public int getPuntosAcumulados() {
		return puntosAcumulados;
	}

	/**
	 * @param puntosAcumulados The puntosAcumulados to set.
	 */
	public void setPuntosAcumulados(int puntosAcumulados) {
		this.puntosAcumulados = puntosAcumulados;
	}

	/**
	 * @return Returns the puntosComprometidos.
	 */
	public int getPuntosComprometidos() {
		return puntosComprometidos;
	}

	/**
	 * @param puntosComprometidos The puntosComprometidos to set.
	 */
	public void setPuntosComprometidos(int puntosComprometidos) {
		this.puntosComprometidos = puntosComprometidos;
	}

	/**
	 * @return Returns the puntosUtilizados.
	 */
	public int getPuntosUtilizados() {
		return puntosUtilizados;
	}

	/**
	 * @param puntosUtilizados The puntosUtilizados to set.
	 */
	public void setPuntosUtilizados(int puntosUtilizados) {
		this.puntosUtilizados = puntosUtilizados;
	}

	/**
	 * @return Returns the saldoDisponible.
	 */
	public int getSaldoDisponible() {
		return saldoDisponible;
	}

	/**
	 * @param saldoDisponible The saldoDisponible to set.
	 */
	public void setSaldoDisponible(int saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	/**
     * @return the puntosInscripcion
     */
    public int getPuntosInscripcion() {
        return puntosInscripcion;
    }

    /**
     * @param puntosInscripcion the puntosInscripcion to set
     */
    public void setPuntosInscripcion(int puntosInscripcion) {
        this.puntosInscripcion = puntosInscripcion;
    }

    /**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof MovimientoCliente)) {
			return false;
		}
		MovimientoCliente rhs = (MovimientoCliente) object;
		return new EqualsBuilder().append(
				this.numeroTelefono, rhs.numeroTelefono).append(
				this.codigoCliente, rhs.codigoCliente).append(
				this.numeroDocumento, rhs.numeroDocumento).append(
				this.puntosComprometidos, rhs.puntosComprometidos).append(
				this.saldoDisponible, rhs.saldoDisponible).append(
				this.nombreCliente, rhs.nombreCliente).append(
				this.codigoConsultora, rhs.codigoConsultora).append(
				this.puntosUtilizados, rhs.puntosUtilizados).append(
				this.puntosAcumulados, rhs.puntosAcumulados).append(
				this.codigoPais, rhs.codigoPais).append(this.periodoIngreso,
				rhs.periodoIngreso).append(this.auditInfo, rhs.auditInfo)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1693347577, -1413507507).append(this.numeroTelefono).append(
				this.codigoCliente).append(this.numeroDocumento).append(
				this.puntosComprometidos).append(this.saldoDisponible).append(
				this.nombreCliente).append(this.codigoConsultora).append(
				this.puntosUtilizados).append(this.puntosAcumulados).append(
				this.codigoPais).append(this.periodoIngreso).append(
				this.auditInfo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("puntosComprometidos",
				this.puntosComprometidos).append("auditInfo", this.auditInfo)
				.append("periodoIngreso", this.periodoIngreso).append(
						"nombreCliente", this.nombreCliente).append(
						"codigoCliente", this.codigoCliente).append(
						"codigoPais", this.codigoPais).append("numeroTelefono",
						this.numeroTelefono).append("puntosUtilizados",
						this.puntosUtilizados).append("codigoConsultora",
						this.codigoConsultora).append("numeroDocumento",
						this.numeroDocumento).append("saldoDisponible",
						this.saldoDisponible).append("puntosAcumulados",
						this.puntosAcumulados).toString();
	}




}