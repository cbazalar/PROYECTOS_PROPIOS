package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCOBSeccionesNoCriticasSearchForm extends BaseSearchForm implements Serializable{

	private static final long serialVersionUID = 8401188707558018322L;
	
	private String codigoPais;
	private String codEtapaDeuda;
	private String codigoRegion;
	private String codigoZona;
	
	
	/**
	 * @return the codEtapaDeuda
	 */
	public String getCodEtapaDeuda() {
		return codEtapaDeuda;
	}
	/**
	 * @param codEtapaDeuda the codEtapaDeuda to set
	 */
	public void setCodEtapaDeuda(String codEtapaDeuda) {
		this.codEtapaDeuda = codEtapaDeuda;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	
}
