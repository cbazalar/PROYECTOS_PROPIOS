/*
 * Created on 28-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.web.form;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="EstructuraArchivoForm.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class EstructuraArchivoForm extends BaseEditForm {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2204847771282546766L;
	
	private String codigoPais;
	private String codigoSistema;
	private String codigoInterfaz;
	private String codigo;
	private String posicionCampo;
	private String descripcionCampo;
	private String codigoTipoDato;
	private String longitudCampo;
	private String cantidadDecimales;
	private String observacionCampo;
	private String estado;
	private String identificadorCampo;
	
	protected String[] selectedItems = {};
	protected String selectedItem = null;
	
	
	/**
	 * @return Returns the cantidadDecimales.
	 */
	public String getCantidadDecimales() {
		return cantidadDecimales;
	}
	/**
	 * @param cantidadDecimales The cantidadDecimales to set.
	 */
	public void setCantidadDecimales(String cantidadDecimales) {
		this.cantidadDecimales = cantidadDecimales;
	}
	/**
	 * @return Returns the codigo.
	 */
	public String getCodigo() {
		return codigo;
	}
	/**
	 * @param codigo The codigo to set.
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}
	/**
	 * @param codigoInterfaz The codigoInterfaz to set.
	 * 
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 * 
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}
	/**
	 * @param codigoSistema The codigoSistema to set.
	 * 
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	/**
	 * @return Returns the codigoTipoDato.
	 */
	public String getCodigoTipoDato() {
		return codigoTipoDato;
	}
	/**
	 * @param codigoTipoDato The codigoTipoDato to set.
	 * 
	 */
	public void setCodigoTipoDato(String codigoTipoDato) {
		this.codigoTipoDato = codigoTipoDato;
	}
	/**
	 * @return Returns the descripcionCampo.
	 */
	public String getDescripcionCampo() {
		return descripcionCampo;
	}
	/**
	 * @param descripcionCampo The descripcionCampo to set.
	 * 
	 */
	public void setDescripcionCampo(String descripcionCampo) {
		this.descripcionCampo = descripcionCampo;
	}
	/**
	 * @return Returns the estado.
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * @param estado The estado to set.
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	/**
	 * @return Returns the longitudCampo.
	 */
	public String getLongitudCampo() {
		return longitudCampo;
	}
	/**
	 * @param longitudCampo The longitudCampo to set.
	 * 
	 */
	public void setLongitudCampo(String longitudCampo) {
		this.longitudCampo = longitudCampo;
	}
	/**
	 * @return Returns the observacionCampo.
	 */
	public String getObservacionCampo() {
		return observacionCampo;
	}
	/**
	 * @param observacionCampo The observacionCampo to set.
	 */
	public void setObservacionCampo(String observacionCampo) {
		this.observacionCampo = observacionCampo;
	}
	/**
	 * @return Returns the posicionCampo.
	 */
	public String getPosicionCampo() {
		return posicionCampo;
	}
	/**
	 * @param posicionCampo The posicionCampo to set.
	 */
	public void setPosicionCampo(String posicionCampo) {
		this.posicionCampo = posicionCampo;
	}
	/**
	 * @return Returns the selectedItem.
	 */
	public String getSelectedItem() {
		return selectedItem;
	}
	/**
	 * @param selectedItem The selectedItem to set.
	 */
	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}
	/**
	 * @return Returns the selectedItems.
	 */
	public String[] getSelectedItems() {
		return selectedItems;
	}
	/**
	 * @param selectedItems The selectedItems to set.
	 */
	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}
	/**
	 * @return Returns the identificadorCampo.
	 */
	public String getIdentificadorCampo() {
		return identificadorCampo;
	}
	/**
	 * @param identificadorCampo The identificadorCampo to set.
	 * 	  
	 */
	public void setIdentificadorCampo(String identificadorCampo) {
		this.identificadorCampo = identificadorCampo;
	}
	
	
}
