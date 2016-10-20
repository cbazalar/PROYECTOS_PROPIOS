package biz.belcorp.ssicc.web.spisicc.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoIMPParametroProcesoImpresionSearchForm.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 29/01/2015
 */
public class MantenimientoIMPParametroProcesoImpresionSearchForm extends BaseSearchForm {

	private static final long serialVersionUID = 1L;
	private String codigoPais;
	private String codigoProceso;
    private String codigoParametro;
    private String nombreParametro;
    private String valorParametro;
    private String obsParametro;
    private String estado;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type = "required"  
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoProceso.
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}
	
	/**
	 * @param codigoProceso The codigoProceso to set.
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
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
