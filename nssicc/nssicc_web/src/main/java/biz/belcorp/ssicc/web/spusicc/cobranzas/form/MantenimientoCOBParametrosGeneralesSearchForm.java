package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoCOBParametrosGeneralesSearchForm extends BaseSearchForm implements Serializable{
	
	private static final long serialVersionUID = 5610310587653367804L;
	
	private String codigoParametro;
	private String descripcionParametro;
	
	
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
