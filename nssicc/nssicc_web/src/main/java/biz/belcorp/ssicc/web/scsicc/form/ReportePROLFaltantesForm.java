package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * 
 * @author RRG
 * 
 */
public class ReportePROLFaltantesForm extends BaseReporteForm implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238118172814740691L;

	private String codigoPais;

	private String campanhia;

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
	 * @return the campanhia
	 */
	public String getCampanhia() {
		return campanhia;
	}

	/**
	 * @param campanhia
	 *            the campanhia to set
	 */
	public void setCampanhia(String campanhia) {
		this.campanhia = campanhia;
	}

}
