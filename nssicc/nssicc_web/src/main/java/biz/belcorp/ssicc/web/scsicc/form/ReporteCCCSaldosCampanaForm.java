package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
public class ReporteCCCSaldosCampanaForm extends BaseReporteForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String codigoPais;
	
	private String codigoSociedad;

	private String codigoPeriodoInicial;

	private String codigoPeriodoFinal;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm#getCodigoPais()
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
	 * @return
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}
	
	/**
	 * @param codigoSociedad
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}
	
	/**
	 * @param codigoPeriodoFinal
	 */
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}

	/**
	 * @return
	 */
	public String getCodigoPeriodoInicial() {
		return codigoPeriodoInicial;
	}

	/**
	 * @param codigoPeriodoInicial
	 */
	public void setCodigoPeriodoInicial(String codigoPeriodoInicial) {
		this.codigoPeriodoInicial = codigoPeriodoInicial;
	}
}