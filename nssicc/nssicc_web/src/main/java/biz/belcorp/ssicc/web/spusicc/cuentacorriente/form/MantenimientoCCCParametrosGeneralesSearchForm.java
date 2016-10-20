package biz.belcorp.ssicc.web.spusicc.cuentacorriente.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCCCParametrosGeneralesSearchForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = -6690814960314749391L;
	
	private String codigoPais;
	private String codigoParametro;
	private String descripcionParametro;
	
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
	 * @return the descripcionParametro
	 */
	public String getDescripcionParametro() {
		return descripcionParametro;
	}
	/**
	 * @param descripcionParametro the descripcionParametro to set
	 */
	public void setDescripcionParametro(String descripcionParametro) {
		this.descripcionParametro = descripcionParametro;
	}
}