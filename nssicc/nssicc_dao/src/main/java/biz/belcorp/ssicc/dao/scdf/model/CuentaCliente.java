/*
 * Created on 10/02/2005 02:07:43 PM biz.belcorp.ssicc.model.CuentaCliente
 */
package biz.belcorp.ssicc.dao.scdf.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class descripcion here.
 * <p>
 * <a href="CuentaCliente.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 * @struts.form include-all="false" extends="BaseEditForm"
 */

public class CuentaCliente extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2489596091189809349L;

	private String codigoPais;
	
	private String codigoCliente;
	
	private int puntajeUtilizado;
	
	private int puntajeObtenido;	//Puntaje Acumulado
	
	private int puntajeComprometido;
	
	private int puntajeAbonadoCuenta;
	
	private int saldoPuntaje;
	
    /**
	 * @return Returns the puntajeComprometido.
	 */
	public int getPuntajeComprometido() {
		return puntajeComprometido;
	}

	/**
	 * @param puntajeComprometido The puntajeComprometido to set.
	 */
	public void setPuntajeComprometido(int puntajeCompartido) {
		this.puntajeComprometido = puntajeCompartido;
	}

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
	 * @return Returns the puntajeObtenido.
	 */
	public int getPuntajeObtenido() {
		return puntajeObtenido;
	}

	/**
	 * @param puntajeObtenido The puntajeObtenido to set.
	 */
	public void setPuntajeObtenido(int puntajeObtenido) {
		this.puntajeObtenido = puntajeObtenido;
	}

	/**
	 * @return Returns the puntajeUtilizado.
	 */
	public int getPuntajeUtilizado() {
		return puntajeUtilizado;
	}

	/**
	 * @param puntajeUtilizado The puntajeUtilizado to set.
	 */
	public void setPuntajeUtilizado(int puntajeUtilizado) {
		this.puntajeUtilizado = puntajeUtilizado;
	}

	/**
	 * @return Returns the puntajeAbonadoCuenta.
	 */
	public int getPuntajeAbonadoCuenta() {
		return puntajeAbonadoCuenta;
	}

	/**
	 * @param puntajeAbonadoCuenta The puntajeAbonadoCuenta to set.
	 */
	public void setPuntajeAbonadoCuenta(int puntajeAbonado) {
		this.puntajeAbonadoCuenta = puntajeAbonado;
	}

	/**
	 * @return Returns the saldoPuntaje.
	 */
	public int getSaldoPuntaje() {
		return saldoPuntaje;
	}

	/**
	 * @param saldoPuntaje The saldoPuntaje to set.
	 */
	public void setSaldoPuntaje(int saldoPuntaje) {
		this.saldoPuntaje = saldoPuntaje;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CuentaCliente)) {
			return false;
		}
		CuentaCliente rhs = (CuentaCliente) object;
		return new EqualsBuilder().append(this.puntajeUtilizado,
				rhs.puntajeUtilizado).append(this.codigoCliente,
				rhs.codigoCliente).append(this.puntajeObtenido,
				rhs.puntajeObtenido).append(this.puntajeAbonadoCuenta,
				rhs.puntajeAbonadoCuenta).append(this.codigoPais, rhs.codigoPais)
				.append(this.saldoPuntaje, rhs.saldoPuntaje).append(
						this.puntajeComprometido, rhs.puntajeComprometido).append(
						this.auditInfo, rhs.auditInfo).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1993755083, -699748345).append(
				this.puntajeUtilizado).append(this.codigoCliente).append(
				this.puntajeObtenido).append(this.puntajeAbonadoCuenta).append(
				this.codigoPais).append(this.saldoPuntaje).append(
				this.puntajeComprometido).append(this.auditInfo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("puntajeUtilizado", this.puntajeUtilizado).append(
						"codigoPais", this.codigoPais).append("puntajeAbonadoCuenta",
						this.puntajeAbonadoCuenta).append("codigoCliente",
						this.codigoCliente).append("puntajeComprometido",
						this.puntajeComprometido).append("saldoPuntaje",
						this.saldoPuntaje).append("auditInfo", this.auditInfo)
				.append("puntajeObtenido", this.puntajeObtenido).toString();
	}
}