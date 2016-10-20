/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 * 
 * @struts.form name="reporteFDVFuerzaDeVentaForm" extends="baseReporteForm"
 */
public class ReporteFDVFuerzaDeVentaForm extends BaseReporteForm{

	private String codigoProceso;
	private String codigoPais;
	private String anyoProceso;
	private String periodoAnyoProceso;
	private String codigoRegion;
	private String codigoZona;
	private String nombreProceso;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the anyoProceso
	 */
	public String getAnyoProceso() {
		return anyoProceso;
	}

	public void setAnyoProceso(String anyoProceso) {
		this.anyoProceso = anyoProceso;
	}
	/**
	 * @return the periodoAnyoProceso
	 */
	public String getPeriodoAnyoProceso() {
		return periodoAnyoProceso;
	}

	public void setPeriodoAnyoProceso(String periodoAnyoProceso) {
		this.periodoAnyoProceso = periodoAnyoProceso;
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
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}
	/**
	 * @param codigoProceso the codigoProceso to set
	 * 
	 * @struts.validator type="required"
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	/**
	 * @return the nombreProceso
	 */
	public String getNombreProceso() {
		return nombreProceso;
	}
	/**
	 * @param nombreProceso the nombreProceso to set
	 */
	public void setNombreProceso(String nombreProceso) {
		this.nombreProceso = nombreProceso;
	}
}
