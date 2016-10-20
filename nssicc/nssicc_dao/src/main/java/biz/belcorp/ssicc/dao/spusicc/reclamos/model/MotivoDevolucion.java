package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class MotivoDevolucion extends AuditableBaseObject implements Serializable {

	  private String codigoPais ;
	  private String oidMotiDevo ; 
	  private String codMotiDevo ;
	  private String paisOidPais ;
	  private String indRegMod   ;
	  private String tipMotDev   ;
	  private String descripcionMotivo;
	  private String idioma ;
	  private String indicador ;
	  private String indicadorCalidad;
	  private String indicadorTipoDevolucion;
	  private String codigoOperacionAnulacion;
	  private String valorRecuperaDCRS;
	  private String valorRecuperaPremios;
	  

	/**
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador the indicador to set
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/**
	 * @return the idioma
	 */
	public String getIdioma() {
		return idioma;
	}

	/**
	 * @param idioma the idioma to set
	 */
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	/**
	 * @return the descripcionMotivo
	 */
	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}

	/**
	 * @param descripcionMotivo the descripcionMotivo to set
	 */
	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
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
	 * @return the oidMotiDevo
	 */
	public String getOidMotiDevo() {
		return oidMotiDevo;
	}

	/**
	 * @param oidMotiDevo the oidMotiDevo to set
	 */
	public void setOidMotiDevo(String oidMotiDevo) {
		this.oidMotiDevo = oidMotiDevo;
	}

	/**
	 * @return the codMotiDevo
	 */
	public String getCodMotiDevo() {
		return codMotiDevo;
	}

	/**
	 * @param codMotiDevo the codMotiDevo to set
	 */
	public void setCodMotiDevo(String codMotiDevo) {
		this.codMotiDevo = codMotiDevo;
	}

	/**
	 * @return the paisOidPais
	 */
	public String getPaisOidPais() {
		return paisOidPais;
	}

	/**
	 * @param paisOidPais the paisOidPais to set
	 */
	public void setPaisOidPais(String paisOidPais) {
		this.paisOidPais = paisOidPais;
	}

	/**
	 * @return the indRegMod
	 */
	public String getIndRegMod() {
		return indRegMod;
	}

	/**
	 * @param indRegMod the indRegMod to set
	 */
	public void setIndRegMod(String indRegMod) {
		this.indRegMod = indRegMod;
	}

	/**
	 * @return the tipMotDev
	 */
	public String getTipMotDev() {
		return tipMotDev;
	}

	/**
	 * @param tipMotDev the tipMotDev to set
	 */
	public void setTipMotDev(String tipMotDev) {
		this.tipMotDev = tipMotDev;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	/**
	 * @return the indicadorCalidad
	 */
	public String getIndicadorCalidad() {
		return indicadorCalidad;
	}

	/**
	 * @param indicadorCalidad the indicadorCalidad to set
	 */
	public void setIndicadorCalidad(String indicadorCalidad) {
		this.indicadorCalidad = indicadorCalidad;
	}

	/**
	 * @return the indicadorTipoDevolucion
	 */
	public String getIndicadorTipoDevolucion() {
		return indicadorTipoDevolucion;
	}

	/**
	 * @param indicadorTipoDevolucion the indicadorTipoDevolucion to set
	 */
	public void setIndicadorTipoDevolucion(String indicadorTipoDevolucion) {
		this.indicadorTipoDevolucion = indicadorTipoDevolucion;
	}

	/**
	 * @return the codigoOperacionAnulacion
	 */
	public String getCodigoOperacionAnulacion() {
		return codigoOperacionAnulacion;
	}

	/**
	 * @param codigoOperacionAnulacion the codigoOperacionAnulacion to set
	 */
	public void setCodigoOperacionAnulacion(String codigoOperacionAnulacion) {
		this.codigoOperacionAnulacion = codigoOperacionAnulacion;
	}

	/**
	 * @return the valorRecuperaDCRS
	 */
	public String getValorRecuperaDCRS() {
		return valorRecuperaDCRS;
	}

	/**
	 * @param valorRecuperaDCRS the valorRecuperaDCRS to set
	 */
	public void setValorRecuperaDCRS(String valorRecuperaDCRS) {
		this.valorRecuperaDCRS = valorRecuperaDCRS;
	}

	/**
	 * @return the valorRecuperaPremios
	 */
	public String getValorRecuperaPremios() {
		return valorRecuperaPremios;
	}

	/**
	 * @param valorRecuperaPremios the valorRecuperaPremios to set
	 */
	public void setValorRecuperaPremios(String valorRecuperaPremios) {
		this.valorRecuperaPremios = valorRecuperaPremios;
	}
	
	
}
