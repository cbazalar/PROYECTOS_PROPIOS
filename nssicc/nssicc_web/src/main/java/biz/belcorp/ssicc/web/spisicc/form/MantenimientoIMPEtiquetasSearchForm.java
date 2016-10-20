package biz.belcorp.ssicc.web.spisicc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;
import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class MantenimientoIMPProcesoImpresionSearchForm.
 * @autor: Danny Santa Cruz Rojas
 */
public class MantenimientoIMPEtiquetasSearchForm extends BaseSearchForm implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String valorEtiqueta;
	
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
	 * @return the valorEtiqueta
	 */
	public String getValorEtiqueta() {
		return valorEtiqueta;
	}
	/**
	 * @param valorEtiqueta the valorEtiqueta to set
	 */
	public void setValorEtiqueta(String valorEtiqueta) {
		this.valorEtiqueta = valorEtiqueta;
	}
}
