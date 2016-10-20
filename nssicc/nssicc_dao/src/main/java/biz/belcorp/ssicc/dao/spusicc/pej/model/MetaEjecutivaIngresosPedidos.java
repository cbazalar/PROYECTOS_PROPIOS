package biz.belcorp.ssicc.dao.spusicc.pej.model;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class MetaEjecutivaIngresosPedidos extends AuditableBaseObject implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private int fila;
	
	private String descripcionError;
		
	private String codigoPais;
	
	private String periodoInicial;
	
	private String periodoFinal;
	
	private String anioInicio;
	
	private String codigoEtapa;
	
	private String codigoCliente;
	
	private String codigoZona;
	
	private String codigoSeccion;
	
	private Integer metaPedidos;
	
	private Integer metaIngresosCampaña;
	
	private Integer metaIngresosBiCampaña;
	
	private String usuarioDigitacion;
	
	private Date fechaDigitacion;
	
	/**
	 * @return
	 */
	public String getAnioInicio() {
		return anioInicio;
	}

	/**
	 * @param anioInicio
	 */
	public void setAnioInicio(String anioInicio) {
		this.anioInicio = anioInicio;
	}

	/**
	 * @return
	 */
	public String getPeriodoInicial() {
		return periodoInicial;
	}

	/**
	 * @param periodoInicial
	 */
	public void setPeriodoInicial(String periodoInicial) {
		this.periodoInicial = periodoInicial;
	}

	/**
	 * @return
	 */
	public String getPeriodoFinal() {
		return periodoFinal;
	}

	/**
	 * @param periodoFinal
	 */
	public void setPeriodoFinal(String periodoFinal) {
		this.periodoFinal = periodoFinal;
	}

	/**
	 * @return
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}

	/**
	 * @param codigoSeccion
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}

	/**
	 * @return
	 */
	public Integer getMetaPedidos() {
		return metaPedidos;
	}

	/**
	 * @param metaPedidos
	 */
	public void setMetaPedidos(Integer metaPedidos) {
		this.metaPedidos = metaPedidos;
	}

	/**
	 * @return
	 */
	public Integer getMetaIngresosCampaña() {
		return metaIngresosCampaña;
	}

	/**
	 * @param metaIngresosCampaa
	 */
	public void setMetaIngresosCampaña(Integer metaIngresosCampaña) {
		this.metaIngresosCampaña = metaIngresosCampaña;
	}

	/**
	 * @return
	 */
	public Integer getMetaIngresosBiCampaña() {
		return metaIngresosBiCampaña;
	}

	/**
	 * @param metaIngresosBiCampaa
	 */
	public void setMetaIngresosBiCampaña(Integer metaIngresosBiCampaña) {
		this.metaIngresosBiCampaña = metaIngresosBiCampaña;
	}

	/**
	 * @return
	 */
	public int getFila() {
		return fila;
	}

	/**
	 * @param fila
	 */
	public void setFila(int fila) {
		this.fila = fila;
	}

	/**
	 * @return
	 */
	public String getDescripcionError() {
		return descripcionError;
	}

	/**
	 * @param descripcionError
	 */
	public void setDescripcionError(String descripcionError) {
		this.descripcionError = descripcionError;
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
	public String getCodigoEtapa() {
		return codigoEtapa;
	}

	/**
	 * @param codigoEtapa
	 */
	public void setCodigoEtapa(String codigoEtapa) {
		this.codigoEtapa = codigoEtapa;
	}

	/**
	 * @return
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}

	/**
	 * @param codigoCliente
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	/**
	 * @return
	 */
	public String getUsuarioDigitacion() {
		return usuarioDigitacion;
	}

	/**
	 * @param usuarioDigitacion
	 */
	public void setUsuarioDigitacion(String usuarioDigitacion) {
		this.usuarioDigitacion = usuarioDigitacion;
	}

	/**
	 * @return
	 */
	public Date getFechaDigitacion() {
		return fechaDigitacion;
	}

	/**
	 * @param fechaDigitacion
	 */
	public void setFechaDigitacion(Date fechaDigitacion) {
		this.fechaDigitacion = fechaDigitacion;
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