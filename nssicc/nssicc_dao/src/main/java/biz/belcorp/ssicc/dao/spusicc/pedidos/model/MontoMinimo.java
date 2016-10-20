package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Jose Luis Rodriguez
 */

public class MontoMinimo extends AuditableBaseObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String oidTipoSolicitud;
	private String oidTipoCliente;
	private String oidSubTipoCliente;
	private String oidTipoClasificacion;	
	private String oidClasificacion;
	private String codigoRegion;
	private String codigoZona;
	private String nivel1;
	private String nivel2;
	private String nivel3;
	private String recargo;
	private String nominal;
	private String oidRegion;
	private String oidZona;

	/**
	 * @return the oidRegion
	 */
	public String getOidRegion() {
		return oidRegion;
	}

	/**
	 * @param oidRegion the oidRegion to set
	 */
	public void setOidRegion(String oidRegion) {
		this.oidRegion = oidRegion;
	}

	/**
	 * @return the oidZona
	 */
	public String getOidZona() {
		return oidZona;
	}

	/**
	 * @param oidZona the oidZona to set
	 */
	public void setOidZona(String oidZona) {
		this.oidZona = oidZona;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the oidTipoSolicitud
	 */
	public String getOidTipoSolicitud() {
		return oidTipoSolicitud;
	}

	/**
	 * @param oidTipoSolicitud the oidTipoSolicitud to set
	 */
	public void setOidTipoSolicitud(String oidTipoSolicitud) {
		this.oidTipoSolicitud = oidTipoSolicitud;
	}

	/**
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}

	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}

	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}

	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}

	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}

	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}

	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}

	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the nivel1
	 */
	public String getNivel1() {
		return nivel1;
	}

	/**
	 * @param nivel1 the nivel1 to set
	 */
	public void setNivel1(String nivel1) {
		this.nivel1 = nivel1;
	}

	/**
	 * @return the nivel2
	 */
	public String getNivel2() {
		return nivel2;
	}

	/**
	 * @param nivel2 the nivel2 to set
	 */
	public void setNivel2(String nivel2) {
		this.nivel2 = nivel2;
	}

	/**
	 * @return the nivel3
	 */
	public String getNivel3() {
		return nivel3;
	}

	/**
	 * @param nivel3 the nivel3 to set
	 */
	public void setNivel3(String nivel3) {
		this.nivel3 = nivel3;
	}

	/**
	 * @return the recargo
	 */
	public String getRecargo() {
		return recargo;
	}

	/**
	 * @param recargo the recargo to set
	 */
	public void setRecargo(String recargo) {
		this.recargo = recargo;
	}

	/**
	 * @return the nominal
	 */
	public String getNominal() {
		return nominal;
	}

	/**
	 * @param nominal the nominal to set
	 */
	public void setNominal(String nominal) {
		this.nominal = nominal;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}