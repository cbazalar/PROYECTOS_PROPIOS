package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReportePRIConfiguracionPuntajeCampanaForm extends BaseReporteForm
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3809424447764368008L;
	private String codigoPais;
	private String periodo;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo
	 *            the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

}
