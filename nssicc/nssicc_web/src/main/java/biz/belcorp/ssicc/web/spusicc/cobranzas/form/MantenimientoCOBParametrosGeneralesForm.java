package biz.belcorp.ssicc.web.spusicc.cobranzas.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoCOBParametrosGeneralesForm extends BaseEditForm
	implements Serializable{
	
	private static final long serialVersionUID = -917494750755359345L;
	
	private String codigoParametro;
	private String descripcionParametro;
	private String valorParametro;
	private String observacionParametro;
	
	
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
	 * @return the observacionParametro
	 */
	public String getObservacionParametro() {
		return observacionParametro;
	}
	
	/**
	 * @param observacionParametro the observacionParametro to set
	 */
	public void setObservacionParametro(String observacionParametro) {
		this.observacionParametro = observacionParametro;
	}
	
	

}
