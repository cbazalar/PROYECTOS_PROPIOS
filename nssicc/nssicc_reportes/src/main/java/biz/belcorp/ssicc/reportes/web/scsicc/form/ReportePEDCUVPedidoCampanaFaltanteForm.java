package biz.belcorp.ssicc.reportes.web.scsicc.form;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReportePEDCUVPedidoCampanaFaltanteForm extends BaseReporteForm {
	
	/**
	 *  JPPS
	 */
	private static final long serialVersionUID = 3302766625939859146L;

	private String codigoPais;
	private String indicadorCampanaVenta;
	private String codigoPeriodoCampana;
	private String codigoVenta;
	private String codigoPeriodoVenta;
		
//	private String region;
	

	public String getCodigoPais() {
		return codigoPais;
	}

	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the indicadorCampanaVenta
	 */
	public String getIndicadorCampanaVenta() {
		return indicadorCampanaVenta;
	}

	/**
	 * @param indicadorCampanaVenta the indicadorCampanaVenta to set
	 */
	public void setIndicadorCampanaVenta(String indicadorCampanaVenta) {
		this.indicadorCampanaVenta = indicadorCampanaVenta;
	}

	/**
	 * @return the codigoPeriodoCampana
	 */
	public String getCodigoPeriodoCampana() {
		return codigoPeriodoCampana;
	}

	/**
	 * @param codigoPeriodoCampana the codigoPeriodoCampana to set
	 */
	public void setCodigoPeriodoCampana(String codigoPeriodoCampana) {
		this.codigoPeriodoCampana = codigoPeriodoCampana;
	}

	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}

	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	/**
	 * @return the codigoPeriodoVenta
	 */
	public String getCodigoPeriodoVenta() {
		return codigoPeriodoVenta;
	}

	/**
	 * @param codigoPeriodoVenta the codigoPeriodoVenta to set
	 */
	public void setCodigoPeriodoVenta(String codigoPeriodoVenta) {
		this.codigoPeriodoVenta = codigoPeriodoVenta;
	}
	
	
}
