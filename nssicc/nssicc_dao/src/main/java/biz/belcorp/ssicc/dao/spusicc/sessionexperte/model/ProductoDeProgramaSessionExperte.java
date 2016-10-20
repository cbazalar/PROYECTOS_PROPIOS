/*
 * Created on 25-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.spusicc.sessionexperte.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ProductoDeProgramaSessionExperte.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:ivega@belcorp.biz">Isabel Vega Palomino</a>
 * 
 */

public class ProductoDeProgramaSessionExperte extends AuditableBaseObject 
	implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoPrograma;
	private String codigoProducto;
	private int codigoAnho;
	private String codigoCiclo;
	private String descripcionProducto;
	private String limiteFrecuenciaProducto;
	private String limiteUnidadesProducto;
	private String indicadorUnidad;
	private String tipoCiclo;
	private String ciclo;
	private String estadoRegistro;

	/*
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("codigoPais", codigoPais)
				.append("codigoPrograma", codigoPrograma)
				.append("codigoProducto", codigoProducto)
				.append("codigoAnho",codigoAnho)
				.append("codigoCiclo",codigoCiclo)
				.append("descripcionProducto", descripcionProducto)
				.append("limiteFrecuenciaProducto",limiteFrecuenciaProducto)
				.append("limiteUnidadesProducto", limiteUnidadesProducto)
				.append("indicadorUnidad",indicadorUnidad)
				.append("tipoCiclo",tipoCiclo)
				.append("ciclo",ciclo)
				.append("estadoRegistro",	estadoRegistro)
				.toString();
	}

	/*
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return false;
	}

	/*
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return 0;
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
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return Returns the codigoPrograma.
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma The codigoPrograma to set.
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return Returns the descripcionProducto.
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto The descripcionProducto to set.
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}

	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}

	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}

	/**
	 * @return Returns the limiteUnidadesProducto.
	 */
	public String getLimiteUnidadesProducto() {
		return limiteUnidadesProducto;
	}

	/**
	 * @param limiteUnidadesProducto The limiteUnidadesProducto to set.
	 */
	public void setLimiteUnidadesProducto(String limiteUnidadesProducto) {
		this.limiteUnidadesProducto = limiteUnidadesProducto;
	}

	/**
	 * @return Returns the ciclo.
	 */
	public String getCiclo() {
		return ciclo;
	}

	/**
	 * @param ciclo The ciclo to set.
	 */
	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	/**
	 * @return Returns the limiteFrecuenciaProducto.
	 */
	public String getLimiteFrecuenciaProducto() {
		return limiteFrecuenciaProducto;
	}

	/**
	 * @param limiteFrecuenciaProducto The limiteFrecuenciaProducto to set.
	 */
	public void setLimiteFrecuenciaProducto(String limiteFrecuenciaProducto) {
		this.limiteFrecuenciaProducto = limiteFrecuenciaProducto;
	}

	/**
	 * @return Returns the tipoCiclo.
	 */
	public String getTipoCiclo() {
		return tipoCiclo;
	}

	/**
	 * @param tipoCiclo The tipoCiclo to set.
	 */
	public void setTipoCiclo(String tipoCiclo) {
		this.tipoCiclo = tipoCiclo;
	}

	/**
	 * @return Returns the codigoAnho.
	 */
	public int getCodigoAnho() {
		return codigoAnho;
	}

	/**
	 * @param codigoAnho The codigoAnho to set.
	 */
	public void setCodigoAnho(int codigoAnho) {
		this.codigoAnho = codigoAnho;
	}

	/**
	 * @return Returns the codigoCiclo.
	 */
	public String getCodigoCiclo() {
		return codigoCiclo;
	}

	/**
	 * @param codigoCiclo The codigoCiclo to set.
	 */
	
	
	
	public void setCodigoCiclo(String codigoCiclo) {
		this.codigoCiclo = codigoCiclo;
	}

	/**
	 * @return Returns the indicadorUnidad.
	 */
	public String getIndicadorUnidad() {
		return indicadorUnidad;
	}

	/**
	 * @param indicadorUnidad The indicadorUnidad to set.
	 */
	public void setIndicadorUnidad(String indicadorUnidad) {
		this.indicadorUnidad = indicadorUnidad;
	}
}