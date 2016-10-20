package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


/** 
 * 
 * @author <a href="nlopez@csigcomt.com">Nicolás López</a>
 * 
 * @struts.form name="reporteSTOCambioDocumIdentidadSADForm" extends="baseReporteForm"
 */

public class ReporteSTOCambioDocumIdentidadSADForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;
	private String fechaInicio;
	private String fechaFin;


	private Date fechaInicioD;
	private Date fechaFinD;
	private String codigoPais;
	private String[] codigoRegion;
	private String[] codigoZona;
	private String codigoPeriodo;
	
	
	public Date getFechaInicioD() {
		return fechaInicioD;
	}

	public void setFechaInicioD(Date fechaInicioD) {
		this.fechaInicioD = fechaInicioD;
	}

	public Date getFechaFinD() {
		return fechaFinD;
	}

	public void setFechaFinD(Date fechaFinD) {
		this.fechaFinD = fechaFinD;
	}
	/**
	 * @return codigoRegion
	 */
	public String[] getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @param codigoRegion
	 */
	public void setCodigoRegion(String[] codigoRegion) {
		this.codigoRegion = codigoRegion;
	}

	/**
	 * @return codigoZona
	 */
	public String[] getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona
	 */
	public void setCodigoZona(String[] codigoZona) {
		this.codigoZona = codigoZona;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.validator.ValidatorForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
	public void reset() {
		this.fechaInicioD = new Date(System.currentTimeMillis());
		this.fechaFinD = new Date(System.currentTimeMillis());
	}

	/**
	 * @return codigoPais
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
	 * @return the fechaInicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	/**
	 * @struts.validator type="required"
	 * @param fechaInicio
	 *            The fechaInicio to set.
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	/**
	 * @return the fechaFin
	 */
	public String getFechaFin() {
		return fechaFin;
	}

	/**
	 * @struts.validator type="required"
	 * @param fechaFin
	 *            The fechaFin to set.
	 */
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
}
