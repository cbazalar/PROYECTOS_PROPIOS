package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOBConsultorasConDeudaPorDiaForm extends BaseReporteForm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String numeroDias;

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#setCodigoPais(java.lang.String)
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the numeroDias
	 */
	public String getNumeroDias() {
		return numeroDias;
	}

	/**
	 * @param numeroDias
	 */
	public void setNumeroDias(String numeroDias) {
		this.numeroDias = numeroDias; 
	}
}