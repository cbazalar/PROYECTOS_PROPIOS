package biz.belcorp.ssicc.web.form;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;



public class MantenimientoBASParametroPaisSearchForm extends BaseSearchForm{

private static final long serialVersionUID = 1L;
	
	private String codigoPais;
    private String codigoSistema;
    private String codigoParametro;
    private String nombreParametro;
    private String valorParametro;
    private String obsParametro;
    private String estado;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.form.BaseSearchForm#limpiarVariables(javax.servlet.http.HttpServletRequest)
	 */
	public void limpiarVariables() {
		this.codigoSistema = Constants.FORMATEAR_TODOS;
	}
	
	/* (non-Javadoc)
	 * @see org.apache.struts.action.ActionForm#reset(org.apache.struts.action.ActionMapping, javax.servlet.http.HttpServletRequest)
	 */

	
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
	 * @return the codigoSistema
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema the codigoSistema to set
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return the codigoParametro
	 */
	public String getCodigoParametro() {
		return codigoParametro;
	}

	/**
	 * @param codigoParametro the codigoParametro to set
	 */
	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

	/**
	 * @return the nombreParametro
	 */
	public String getNombreParametro() {
		return nombreParametro;
	}

	/**
	 * @param nombreParametro the nombreParametro to set
	 */
	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	/**
	 * @return the valorParametro
	 */
	public String getValorParametro() {
		return valorParametro;
	}

	/**
	 * @param valorParametro the valorParametro to set
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	/**
	 * @return the obsParametro
	 */
	public String getObsParametro() {
		return obsParametro;
	}

	/**
	 * @param obsParametro the obsParametro to set
	 */
	public void setObsParametro(String obsParametro) {
		this.obsParametro = obsParametro;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
}
