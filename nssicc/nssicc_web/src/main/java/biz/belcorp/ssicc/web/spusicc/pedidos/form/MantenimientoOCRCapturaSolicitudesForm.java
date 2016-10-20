package biz.belcorp.ssicc.web.spusicc.pedidos.form;

import java.io.Serializable;
import java.util.Date;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoOCRCapturaSolicitudesForm extends BaseEditForm implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5332030765511180598L;
	private String codigoPais;
	private String codigoPeriodo;
	private String fechaFacturacion;
	private Date fechaFacturacionD;
	private String numeroLote;
	private String[] listaRegiones;
	private String[] listaZonas;
	private String[] listaFechasFacturacion;
	private String[] listaSolicitudes;
	private String[] chkSolicitudes={};
	private String totalSolicitudes;
	private String codigoPaisSearch;
	private String codigoPeriodoSearch;
	private String codigoZonaSearch;
	private String fechaFacturacionSearch;
	protected String[] selectedItems = {};
	protected String selectedItem = null;
	private String id;
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
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the fechaFacturacion
	 */
	public String getFechaFacturacion() {
		return fechaFacturacion;
	}
	/**
	 * @param fechaFacturacion the fechaFacturacion to set
	 */
	public void setFechaFacturacion(String fechaFacturacion) {
		this.fechaFacturacion = fechaFacturacion;
	}
	/**
	 * @return the numeroLote
	 */
	public String getNumeroLote() {
		return numeroLote;
	}
	/**
	 * @param numeroLote the numeroLote to set
	 */
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	/**
	 * @return the listaRegiones
	 */
	public String[] getListaRegiones() {
		return listaRegiones;
	}
	/**
	 * @param listaRegiones the listaRegiones to set
	 */
	public void setListaRegiones(String[] listaRegiones) {
		this.listaRegiones = listaRegiones;
	}
	/**
	 * @return the listaZonas
	 */
	public String[] getListaZonas() {
		return listaZonas;
	}
	/**
	 * @param listaZonas the listaZonas to set
	 */
	public void setListaZonas(String[] listaZonas) {
		this.listaZonas = listaZonas;
	}
	/**
	 * @return the listaFechasFacturacion
	 */
	public String[] getListaFechasFacturacion() {
		return listaFechasFacturacion;
	}
	/**
	 * @param listaFechasFacturacion the listaFechasFacturacion to set
	 */
	public void setListaFechasFacturacion(String[] listaFechasFacturacion) {
		this.listaFechasFacturacion = listaFechasFacturacion;
	}
	/**
	 * @return the listaSolicitudes
	 */
	public String[] getListaSolicitudes() {
		return listaSolicitudes;
	}
	/**
	 * @param listaSolicitudes the listaSolicitudes to set
	 */
	public void setListaSolicitudes(String[] listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}
	/**
	 * @return the chkSolicitudes
	 */
	public String[] getChkSolicitudes() {
		return chkSolicitudes;
	}
	/**
	 * @param chkSolicitudes the chkSolicitudes to set
	 */
	public void setChkSolicitudes(String[] chkSolicitudes) {
		this.chkSolicitudes = chkSolicitudes;
	}
	/**
	 * @return the totalSolicitudes
	 */
	public String getTotalSolicitudes() {
		return totalSolicitudes;
	}
	/**
	 * @param totalSolicitudes the totalSolicitudes to set
	 */
	public void setTotalSolicitudes(String totalSolicitudes) {
		this.totalSolicitudes = totalSolicitudes;
	}
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
	 * @return the fechaFacturacionD
	 */
	public Date getFechaFacturacionD() {
		return fechaFacturacionD;
	}
	/**
	 * @param fechaFacturacionD the fechaFacturacionD to set
	 */
	public void setFechaFacturacionD(Date fechaFacturacionD) {
		this.fechaFacturacionD = fechaFacturacionD;
	}
	
	

}
