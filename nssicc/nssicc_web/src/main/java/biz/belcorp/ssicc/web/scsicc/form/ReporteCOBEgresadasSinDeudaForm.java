package biz.belcorp.ssicc.web.scsicc.form;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

public class ReporteCOBEgresadasSinDeudaForm extends BaseReporteForm {

	private static final long serialVersionUID = 1L;

	private String codigoPais;
	private String importeDeuda;
	
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
	 * @return the importeDeuda
	 */
	public String getImporteDeuda() {
		return importeDeuda;
	}

	/**
	 * @param importeDeuda
	 */
	public void setImporteDeuda(String importeDeuda) {
		this.importeDeuda = importeDeuda;
	}
}