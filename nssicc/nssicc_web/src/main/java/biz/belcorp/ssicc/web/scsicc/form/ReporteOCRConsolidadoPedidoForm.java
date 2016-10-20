package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteOCRConsolidadoPedidoForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 05/09/2014
 */
public class ReporteOCRConsolidadoPedidoForm extends BaseReporteForm{
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoPeriodo;
	
	private String tipoConsulta;
	
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
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo The codigoPeriodo to set.	
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return Returns the tipoConsulta.
	 */
	public String getTipoConsulta() {
		return tipoConsulta;
	}

	/**
	 * @param tipoConsulta The tipoConsulta to set.
	 */
	public void setTipoConsulta(String tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

}
