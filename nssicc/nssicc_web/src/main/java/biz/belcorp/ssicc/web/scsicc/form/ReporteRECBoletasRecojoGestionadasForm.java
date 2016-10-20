/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteRECBoletasRecojoGestionadasForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 07/11/2014
 */
public class ReporteRECBoletasRecojoGestionadasForm extends BaseReporteForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoPeriodoInicio;
	
	private String codigoPeriodoFin;
	
	private String[] regionList;

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
	 * @return Returns the codigoPeriodoFin.
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}

	/**
	 * @param codigoPeriodoFin The codigoPeriodoFin to set.
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}

	/**
	 * @return Returns the codigoPeriodoInicio.
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * @param codigoPeriodoInicio The codigoPeriodoInicio to set.
	 * @struts.validator type = "required"
	 * @struts.validator type="mask"
	 * @struts.validator-var name="mask" value="${campaign}"
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	public String[] getRegionList() {
		return regionList;
	}

	public void setRegionList(String[] regionList) {
		this.regionList = regionList;
	}
}
