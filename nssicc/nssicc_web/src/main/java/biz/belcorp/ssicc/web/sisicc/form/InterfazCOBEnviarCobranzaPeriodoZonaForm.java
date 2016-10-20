package biz.belcorp.ssicc.web.sisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseInterfazForm;

/**
 * 
 * @author <a href="mailto:">Jose Luis Rodriguez</a>
 * 
 * @struts.form name = "interfazCOBEnviarCobranzaPeriodoZonaForm" extends ="BaseInterfazForm"
 *              
 */
public class InterfazCOBEnviarCobranzaPeriodoZonaForm extends BaseInterfazForm implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoSociedad;
	private String anho;
	private String mes;
	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais The codigoPais to set.
	 * @struts.validator type = "required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoSociedad
	 */
	public String getCodigoSociedad() {
		return codigoSociedad;
	}

	/**
	 * @param codigoSociedad the codigoSociedad to set
	 * @struts.validator type="required"
	 */
	public void setCodigoSociedad(String codigoSociedad) {
		this.codigoSociedad = codigoSociedad;
	}
	
	/**
	 * @return the anho
	 */
	public String getAnho() {
		return anho;
	}

	/**
	 * @param anho the anho to set
	 */
	public void setAnho(String anho) {
		this.anho = anho;
	}

	/**
	 * @return the mes
	 */
	public String getMes() {
		return mes;
	}

	/**
	 * @param mes the mes to set
	 */
	public void setMes(String mes) {
		this.mes = mes;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.web.framework.form.BaseInterfazForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */
}