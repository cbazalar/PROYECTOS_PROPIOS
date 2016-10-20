package biz.belcorp.ssicc.web.spusicc.men.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

/**
 * The Class BusquedaClientesSearchForm.
 *
 * @autor: Yahir Rivas L.
 * @version: 1.0
 * 23/02/2015
 */
public class BusquedaGenericaSearchForm extends BaseSearchForm {
	
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private String descripcion;
	private String indexPopup;
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	/**
	 * @return the indexPopup
	 */
	public String getIndexPopup() {
		return indexPopup;
	}
	/**
	 * @param indexPopup the indexPopup to set
	 */
	public void setIndexPopup(String indexPopup) {
		this.indexPopup = indexPopup;
	}
	
	

	
}
