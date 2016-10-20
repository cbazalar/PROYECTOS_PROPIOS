package biz.belcorp.ssicc.reportes.web.edu.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;


public class ReporteEDUResumenProgramadasPlanillaForm extends BaseReporteForm  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoEmpresa;

	private String nombreEmpresa;

	private String codigoRegion;
	
	private String campanhaProceso;
	
	private String codigoZona;

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
	 * @return the codigoEmpresa
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}

	/**
	 * @param codigoEmpresa the codigoEmpresa to set
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	/**
	 * @return the nombreEmpresa
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	/**
	 * @param nombreEmpresa the nombreEmpresa to set
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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
	 * @return the campanhaProceso
	 */
	public String getCampanhaProceso() {
		return campanhaProceso;
	}

	/**
	 * @param campanhaProceso the campanhaProceso to set
	 */
	public void setCampanhaProceso(String campanhaProceso) {
		this.campanhaProceso = campanhaProceso;
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
	

}
