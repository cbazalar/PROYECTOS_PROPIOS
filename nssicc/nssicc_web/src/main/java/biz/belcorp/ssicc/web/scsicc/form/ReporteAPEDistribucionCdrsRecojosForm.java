package biz.belcorp.ssicc.web.scsicc.form;


import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteAPEDistribucionCdrsRecojosForm extends BaseReporteForm{

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String periodoGeneracion;
	
	private String periodoDespacho;
	
	private Date fechaFacturacionInicio;
	
	private Date fechaFacturacionFin;
	
	private String[] codigoRegion;
	
	private String[] codigoZona;
	
	private String numeroRecojo;


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
	 * @return the fechaFacturacionInicio
	 */
	public Date getFechaFacturacionInicio() {
		return fechaFacturacionInicio;
	}

	/**
	 * @param fechaFacturacionInicio the fechaFacturacionInicio to set
	 */
	public void setFechaFacturacionInicio(Date fechaFacturacionInicio) {
		this.fechaFacturacionInicio = fechaFacturacionInicio;
	}

	/**
	 * @return the fechaFacturacionFin
	 */
	public Date getFechaFacturacionFin() {
		return fechaFacturacionFin;
	}

	/**
	 * @param fechaFacturacionFin the fechaFacturacionFin to set
	 */
	public void setFechaFacturacionFin(Date fechaFacturacionFin) {
		this.fechaFacturacionFin = fechaFacturacionFin;
	}

	/**
	 * @return the codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return the codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the numeroRecojo
	 */
	public String getNumeroRecojo() {
		return numeroRecojo;
	}

	/**
	 * @param numeroRecojo the numeroRecojo to set
	 */
	public void setNumeroRecojo(String numeroRecojo) {
		this.numeroRecojo = numeroRecojo;
	}

	public String getPeriodoDespacho() {
		return periodoDespacho;
	}

	public void setPeriodoDespacho(String periodoDespacho) {		
		this.periodoDespacho = periodoDespacho;
	}

	public String getPeriodoGeneracion() {
		return periodoGeneracion;
	}

	public void setPeriodoGeneracion(String periodoGeneracion) {
		this.periodoGeneracion = periodoGeneracion;
	}


	
}
