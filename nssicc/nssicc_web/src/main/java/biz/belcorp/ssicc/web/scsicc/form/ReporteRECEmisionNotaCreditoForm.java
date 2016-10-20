package biz.belcorp.ssicc.web.scsicc.form;


import java.util.Date;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
/**
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
public class ReporteRECEmisionNotaCreditoForm extends BaseReporteForm {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String fechaInicio;

	private String fechaFinal;
	
	private String[] regionList;

	private String[] zonaList;

	private String descripcionRegionList;

	private String descripcionZonaList;
	
	private String oidTipoSolicitudPais;
	
	private String codigoPeriodo;
	
	private Date fechaInicioD;

	private Date fechaFinalD;
	
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
	/**
	 * 
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the regionList.
	 */
	public String[] getRegionList() {
		return regionList;
	}

	/**
	 * @param regionList
	 *            The regionList to set.
	 *         
	 */
	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}

	/**
	 * @return Returns the zonaList.
	 */
	public String[] getZonaList() {
		return zonaList;
	}

	/**
	 * @param zonaList
	 *            The zonaList to set.
	 *   
	 */
	public void setZonaList(String[] zonaList) {
		this.zonaList = zonaList;
	}

	/**
	 * @return Returns the descripcionRegionList.
	 */
	public String getDescripcionRegionList() {
		return descripcionRegionList;
	}
	/**
	 * @param descripcionRegionList The descripcionRegionList to set.
	 */
	public void setDescripcionRegionList(String descripcionRegionList) {
		String temp = StringUtils.replace(descripcionRegionList, "&&","\n" );
		this.descripcionRegionList = temp;
	}
	/**
	 * @return Returns the descripcionZonaList.
	 */
	public String getDescripcionZonaList() {
		return descripcionZonaList;
	}
	/**
	 * @param descripcionZonaList The descripcionZonaList to set.
	 */
	public void setDescripcionZonaList(String descripcionZonaList) {
		String temp = StringUtils.replace(descripcionZonaList, "&&","\n" );
		this.descripcionZonaList = temp;
	}
	/**
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicial the fechaInicial to set
	 * 
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	/**
	 * @return the fechaFinal
	 */
	public String getFechaFinal() {
		return fechaFinal;
	}
	/**
	 * @param fechaFinal the fechaFinal to set
	 * 
	 */
	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}
	/**
	 * @return the oidTipoSolicitudPais
	 */
	public String getOidTipoSolicitudPais() {
		return oidTipoSolicitudPais;
	}
	/**
	 * @param oidTipoSolicitudPais the oidTipoSolicitudPais to set
	 */
	public void setOidTipoSolicitudPais(String oidTipoSolicitudPais) {
		this.oidTipoSolicitudPais = oidTipoSolicitudPais;
	}
	/**
	 * @return the fechaInicioD
	 */
	public Date getFechaInicioD() {
		return fechaInicioD;
	}
	/**
	 * @param fechaInicioD the fechaInicioD to set
	 */
	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}
	/**
	 * @return the fechaFinalD
	 */
	public Date getFechaFinalD() {
		return fechaFinalD;
	}
	/**
	 * @param fechaFinalD the fechaFinalD to set
	 */
	public void setFechaFinalD(Date fechaFinalD) {
		this.fechaFinalD = fechaFinalD;
	}
}
