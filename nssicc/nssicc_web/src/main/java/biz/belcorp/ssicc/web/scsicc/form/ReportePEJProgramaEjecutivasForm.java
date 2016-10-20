package biz.belcorp.ssicc.web.scsicc.form;

import java.util.Date;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;






public class ReportePEJProgramaEjecutivasForm extends BaseReporteForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String tipoReporte;
	private String codigoRegion;
	private String codigoZona;
	private String codigoSeccion;
	private String campanyaProceso;
	private String fechaFacturacion;
	private Date fechaFacturacionD;
	private String formatoCampanyaProceso;
	
	/**
	 * @return the campanyaProceso
	 */
	
	
	public String getCampanyaProceso() {
		return campanyaProceso;
	}
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @return the codigoSeccion
	 */
	public String getCodigoSeccion() {
		return codigoSeccion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}
	/**
	 * @param campanyaProceso the campanyaProceso to set
	 * @struts.validator type="required"
	 */
	public void setCampanyaProceso(String campanyaProceso) {
		this.campanyaProceso = campanyaProceso;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @param codigoSeccion the codigoSeccion to set
	 */
	public void setCodigoSeccion(String codigoSeccion) {
		this.codigoSeccion = codigoSeccion;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 * @struts.validator type="required"
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @param tipoReporte the tipoReporte to set
	 * @struts.validator type="required"
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	/**
	 * @return the formatoCampanyaProceso
	 */
	public String getFormatoCampanyaProceso() {
		return formatoCampanyaProceso;
	}
	/**
	 * @param formatoCampanyaProceso the formatoCampanyaProceso to set
	 */
	public void setFormatoCampanyaProceso(String formatoCampanyaProceso) {
		this.formatoCampanyaProceso = formatoCampanyaProceso;
	}
}