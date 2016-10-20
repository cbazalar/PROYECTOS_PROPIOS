package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

public class InterfazOCRPaqueteConsolidadoForm extends
		BaseInterfazForm implements Serializable {
	
	private String indValidacionSTO;
	private static final long serialVersionUID = 1L;
	private String codigoPeriodo;	
	private Boolean indValidacionSTOBool;

	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

	/**
	 * @return the indValidacionSTO
	 */
	public String getIndValidacionSTO() {
		return indValidacionSTO;
	}

	/**
	 * @param indValidacionSTO the indValidacionSTO to set
	 */
	public void setIndValidacionSTO(String indValidacionSTO) {
		this.indValidacionSTO = indValidacionSTO;
	}

	/**
	 * @return the indValidacionSTOBool
	 */
	public Boolean getIndValidacionSTOBool() {
		return indValidacionSTOBool;
	}

	/**
	 * @param indValidacionSTOBool the indValidacionSTOBool to set
	 */
	public void setIndValidacionSTOBool(Boolean indValidacionSTOBool) {
		this.indValidacionSTOBool = indValidacionSTOBool;
	}
}