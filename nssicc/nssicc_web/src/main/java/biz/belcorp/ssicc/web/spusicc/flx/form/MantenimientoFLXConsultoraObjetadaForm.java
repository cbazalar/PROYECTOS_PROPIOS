package biz.belcorp.ssicc.web.spusicc.flx.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoFLXConsultoraObjetadaForm extends BaseEditForm implements Serializable {

	private static final long serialVersionUID = -4793287040658135119L;
	private String codigoPais;
	private String codigoCampanyaFacturacion;
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
	 * @return the codigoCampanyaFacturacion
	 */
	public String getCodigoCampanyaFacturacion() {
		return codigoCampanyaFacturacion;
	}
	/**
	 * @param codigoCampanyaFacturacion the codigoCampanyaFacturacion to set
	 * 
	 */
	public void setCodigoCampanyaFacturacion(String codigoCampanyaFacturacion) {
		this.codigoCampanyaFacturacion = codigoCampanyaFacturacion;
	}
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 * 
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
}