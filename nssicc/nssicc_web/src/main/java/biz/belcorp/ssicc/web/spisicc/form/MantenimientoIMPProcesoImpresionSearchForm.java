package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoIMPProcesoImpresionSearchForm.
 * @autor: Danny Santa Cruz Rojas
 */
public class MantenimientoIMPProcesoImpresionSearchForm extends BaseSearchForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoProceso;
	private String descripcionProceso;
	
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
	 * @return the codigoProceso
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}
	
	/**
	 * @param codigoProceso the codigoProceso to set
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	
	/**
	 * @return the descripcionProceso
	 */
	public String getDescripcionProceso() {
		return descripcionProceso;
	}
	/**
	 * @param descripcionProceso the descripcionProceso to set
	 */
	public void setDescripcionProceso(String descripcionProceso) {
		this.descripcionProceso = descripcionProceso;
	}
}
