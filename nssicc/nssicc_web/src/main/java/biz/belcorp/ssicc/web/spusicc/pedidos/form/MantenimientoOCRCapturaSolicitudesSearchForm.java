package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseSearchForm;

public class MantenimientoOCRCapturaSolicitudesSearchForm extends BaseSearchForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4358962086998618986L;

	private String codigoPaisSearch;
	private String codigoPeriodoSearch;
	private String codigoZonaSearch;
	private String fechaFacturacionSearch;
	private Date fechaFacturacionSearchD;
	protected String[] selectedItems = {};
	protected String selectedItem = null;
	private String id;
	/**
	 * @return the codigoPaisSearch
	 */
	public String getCodigoPaisSearch() {
		return codigoPaisSearch;
	}
	/**
	 * @param codigoPaisSearch the codigoPaisSearch to set
	 */
	public void setCodigoPaisSearch(String codigoPaisSearch) {
		this.codigoPaisSearch = codigoPaisSearch;
	}
	/**
	 * @return the codigoPeriodoSearch
	 */
	public String getCodigoPeriodoSearch() {
		return codigoPeriodoSearch;
	}
	/**
	 * @param codigoPeriodoSearch the codigoPeriodoSearch to set
	 */
	public void setCodigoPeriodoSearch(String codigoPeriodoSearch) {
		this.codigoPeriodoSearch = codigoPeriodoSearch;
	}
	/**
	 * @return the codigoZonaSearch
	 */
	public String getCodigoZonaSearch() {
		return codigoZonaSearch;
	}
	/**
	 * @param codigoZonaSearch the codigoZonaSearch to set
	 */
	public void setCodigoZonaSearch(String codigoZonaSearch) {
		this.codigoZonaSearch = codigoZonaSearch;
	}
	/**
	 * @return the fechaFacturacionSearch
	 */
	public String getFechaFacturacionSearch() {
		return fechaFacturacionSearch;
	}
	/**
	 * @param fechaFacturacionSearch the fechaFacturacionSearch to set
	 */
	public void setFechaFacturacionSearch(String fechaFacturacionSearch) {
		this.fechaFacturacionSearch = fechaFacturacionSearch;
	}
	/**
	 * @return the selectedItems
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}
	/**
	 * @param selectedItems the selectedItems to set
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	/**
	 * @return the selectedItem
	 */
	public String getSelectedItem() {
		return selectedItem;
	}
	/**
	 * @param selectedItem the selectedItem to set
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the fechaFacturacionSearchD
	 */
	public Date getFechaFacturacionSearchD() {
		return fechaFacturacionSearchD;
	}
	/**
	 * @param fechaFacturacionSearchD the fechaFacturacionSearchD to set
	 */
	public void setFechaFacturacionSearchD(Date fechaFacturacionSearchD) {
		this.fechaFacturacionSearchD = fechaFacturacionSearchD;
	}
	
	
	
	

}
