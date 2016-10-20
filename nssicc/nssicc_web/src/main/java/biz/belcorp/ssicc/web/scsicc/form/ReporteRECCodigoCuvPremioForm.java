package biz.belcorp.ssicc.web.scsicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
public class ReporteRECCodigoCuvPremioForm extends BaseReporteForm implements
		Serializable {

	private static final long serialVersionUID = 1L;

	private String codigoPais;

	private String concurso;

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
	 * @return the concurso
	 */
	public String getConcurso() {
		return concurso;
	}

	/**
	 * @param concurso
	 *            the concurso to set
	 */
	public void setConcurso(String concurso) {
		this.concurso = concurso;
	}
}