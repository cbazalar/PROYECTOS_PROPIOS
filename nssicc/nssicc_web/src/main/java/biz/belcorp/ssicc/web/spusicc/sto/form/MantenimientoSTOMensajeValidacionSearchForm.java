package biz.belcorp.ssicc.web.spusicc.sto.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoSTOMensajeValidacionSearchForm extends BaseSearchForm implements Serializable{

	private static final long serialVersionUID = 4692570628785833513L;
	
	
	private String codigoPais;
	private String tipoDocumento;
	private String codigoValidacion;
	
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

}
