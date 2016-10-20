package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * The Class ReporteSACIndicadoresForm.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 22/05/2014
 */
public class ReporteSACIndicadoresForm extends BaseReporteForm	implements Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String codigoPeriodo;
	
	private String[] indicadores;

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
	 * @return Returns the indicadores.
	 */
	public String[] getIndicadores() {
		return indicadores;
	}

	/**
	 * @param indicadores The indicadores to set.
	 */
	public void setIndicadores(String[] indicadores) {
		this.indicadores = indicadores;
	}	
	
	
}
