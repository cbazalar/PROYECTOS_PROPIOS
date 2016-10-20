package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoIMPEtiquetasEstatusSearchForm.
 * @autor: Danny Santa Cruz Rojas
 */
public class MantenimientoIMPEtiquetasEstatusSearchForm extends BaseSearchForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String oidEstatus;
	private String oidEtiqueta;
	
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
	 * @return the oidEstatus
	 */
	public String getOidEstatus() {
		return oidEstatus;
	}
	/**
	 * @param oidEstatus the oidEstatus to set
	 */
	public void setOidEstatus(String oidEstatus) {
		this.oidEstatus = oidEstatus;
	}
	
	/**
	 * @return the oidEtiqueta
	 */
	public String getOidEtiqueta() {
		return oidEtiqueta;
	}
	/**
	 * @param oidEtiqueta the oidEtiqueta to set
	 */
	public void setOidEtiqueta(String oidEtiqueta) {
		this.oidEtiqueta = oidEtiqueta;
	}
}
