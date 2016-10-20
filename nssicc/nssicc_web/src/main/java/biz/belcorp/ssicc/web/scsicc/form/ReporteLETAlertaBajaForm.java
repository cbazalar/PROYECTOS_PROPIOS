package biz.belcorp.ssicc.web.scsicc.form;


import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author <a href="mailto:yrivas@sigcomt.com">Yahir Rivas Luna</a>
 * 
 * @struts.form name="reporteLETAlertaBajaForm" extends="baseReporteForm"
 */
public class ReporteLETAlertaBajaForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoPeriodoFinal;
	private String codigoPeriodoInicio;
	private String codigoPrograma;
	private String codigoRegion;


	public void reset() {
		
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
	 * @param codigoPais  The codigoPais to set.
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoPeriodoFinal.
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}

	/**
	 * 
	 *  @struts.validator type="required"
	 *  @param codigoPeriodoFinal The codigoPeriodoFinal to set.
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	
	/**
	 * @return Returns the codigoPeriodoInicio.
	 */
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}

	/**
	 * 
	 *  @struts.validator type="required"
	 *  @param codigoPeriodoInicio The codigoPeriodoInicio to set.
	 */
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}

	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}

	/**
	 * @struts.validator type="required"
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	
	
	
	
	
}
