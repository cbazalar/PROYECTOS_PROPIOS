package biz.belcorp.ssicc.web.spusicc.flx.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoFLXConsultoraObjetadaSearchForm extends BaseSearchForm implements Serializable {
			 
	private static final long serialVersionUID = -1526323896999176226L;
	private String codigoPais;
	private String campanyaFacturacion;
	private String codigoCliente;
	
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the campanyaFacturacion
	 */
	public String getCampanyaFacturacion() {
		return campanyaFacturacion;
	}
	/**
	 * @param campanyaFacturacion the campanyaFacturacion to set
	 */
	public void setCampanyaFacturacion(String campanyaFacturacion) {
		this.campanyaFacturacion = campanyaFacturacion;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
}