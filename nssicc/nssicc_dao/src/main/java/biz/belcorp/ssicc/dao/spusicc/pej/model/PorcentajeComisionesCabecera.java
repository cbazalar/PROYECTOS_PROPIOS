package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class PorcentajeComisionesCabecera extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPorcentaje;
	private String codigoMarca;
	private String codigoCanal;
	private String campaniaDesde;
	private String campaniaHasta;
	private String usuario;
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return
	 */
	public String getCodigoPorcentaje() {
		return codigoPorcentaje;
	}

	/**
	 * @param codigoPorcentaje
	 */
	public void setCodigoPorcentaje(String codigoPorcentaje) {
		this.codigoPorcentaje = codigoPorcentaje;
	}

	/**
	 * @return
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}

	/**
	 * @param codigoMarca
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	/**
	 * @return
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}

	/**
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}

	/**
	 * @return
	 */
	public String getCampaniaDesde() {
		return campaniaDesde;
	}

	/**
	 * @param campaniaDesde
	 */
	public void setCampaniaDesde(String campaniaDesde) {
		this.campaniaDesde = campaniaDesde;
	}

	/**
	 * @return
	 */
	public String getCampaniaHasta() {
		return campaniaHasta;
	}

	/**
	 * @param campaniaHasta
	 */
	public void setCampaniaHasta(String campaniaHasta) {
		this.campaniaHasta = campaniaHasta;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return false;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}