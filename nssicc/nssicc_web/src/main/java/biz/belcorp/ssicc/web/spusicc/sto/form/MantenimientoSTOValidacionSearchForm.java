package biz.belcorp.ssicc.web.spusicc.sto.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoSTOValidacionSearchForm extends BaseSearchForm {


	/**
	 * JPPS
	 */
	private static final long serialVersionUID = -3366085171724767995L;
	private String codigoPais;
	private String tipoDocumento;
	private String codigoValidacion;
	private String descripcionValidacion;
	
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
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento the tipoDocumento to set
	 * @struts.validator type = "required"
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return the codigoValidacion
	 */
	public String getCodigoValidacion() {
		return codigoValidacion;
	}
	/**
	 * @param codigoValidacion the codigoValidacion to set
	 */
	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}
	/**
	 * @return the descripcionValidacion
	 */
	public String getDescripcionValidacion() {
		return descripcionValidacion;
	}
	/**
	 * @param descripcionValidacion the descripcionValidacion to set
	 */
	public void setDescripcionValidacion(String descripcionValidacion) {
		this.descripcionValidacion = descripcionValidacion;
	}
	
}
