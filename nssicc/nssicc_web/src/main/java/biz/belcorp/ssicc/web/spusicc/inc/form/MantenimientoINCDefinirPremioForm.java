package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCDefinirPremioForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -676111745037990693L;
	private String numeroNivel;
	private String numeroLote;
	private String numeroPremio;
	private String descripcionLote;
	
	private String codigoSAP;
	private String descripcionSAP;
	
	private String indicadorCentroServicio;
	private String oidCentroServGarantia;
	private String numeroMesesGarantia;
	private String observaciones;
	private String indicadorTipoEntrega;
	private String oidCentroServEntrega;
	
	private String[] selectedItems;
	private String indicadorNivelSelectivo;
	
	private String numeroUnidades;
	private String tipoLote;
	
	private String numeroLoteUltimo;
	private String numeroPremioUltimo;
	private String descripcionLoteUltimo;
	
	private String precio;
	
	private boolean modificar;
	private String indicadorPremiosWeb;
	
	public MantenimientoINCDefinirPremioForm(){
		this.codigoSAP=this.descripcionSAP=null;
		this.oidCentroServGarantia=this.numeroMesesGarantia=this.observaciones=this.oidCentroServEntrega=null;
		
		this.indicadorCentroServicio=Constants.NUMERO_CERO;
		this.indicadorTipoEntrega=Constants.INC_TIPO_ENTREGA_BELCORP;
		this.selectedItems=null;
		
		this.numeroLoteUltimo = null;
		this.numeroPremioUltimo = null;
		this.descripcionLoteUltimo = null;
		this.numeroUnidades="1";
		this.modificar = false;
		this.precio = "0.00";
		this.indicadorPremiosWeb=Constants.NUMERO_CERO;
	}

	/**
	 * @return the numeroNivel
	 */
	public String getNumeroNivel() {
		return numeroNivel;
	}

	/**
	 * @param numeroNivel the numeroNivel to set
	 */
	public void setNumeroNivel(String numeroNivel) {
		this.numeroNivel = numeroNivel;
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
	 * @return the numeroPremio
	 */
	public String getNumeroPremio() {
		return numeroPremio;
	}

	/**
	 * @param numeroPremio the numeroPremio to set
	 */
	public void setNumeroPremio(String numeroPremio) {
		this.numeroPremio = numeroPremio;
	}

	/**
	 * @return the descripcionLote
	 */
	public String getDescripcionLote() {
		return descripcionLote;
	}

	/**
	 * @param descripcionLote the descripcionLote to set
	 */
	public void setDescripcionLote(String descripcionLote) {
		this.descripcionLote = descripcionLote;
	}

	/**
	 * @return the codigoSAP
	 */
	public String getCodigoSAP() {
		return codigoSAP;
	}

	/**
	 * @param codigoSAP the codigoSAP to set
	 */
	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	/**
	 * @return the descripcionSAP
	 */
	public String getDescripcionSAP() {
		return descripcionSAP;
	}

	/**
	 * @param descripcionSAP the descripcionSAP to set
	 */
	public void setDescripcionSAP(String descripcionSAP) {
		this.descripcionSAP = descripcionSAP;
	}

	/**
	 * @return the indicadorCentroServicio
	 */
	public String getIndicadorCentroServicio() {
		return indicadorCentroServicio;
	}

	/**
	 * @param indicadorCentroServicio the indicadorCentroServicio to set
	 */
	public void setIndicadorCentroServicio(String indicadorCentroServicio) {
		this.indicadorCentroServicio = indicadorCentroServicio;
	}

	/**
	 * @return the oidCentroServGarantia
	 */
	public String getOidCentroServGarantia() {
		return oidCentroServGarantia;
	}

	/**
	 * @param oidCentroServGarantia the oidCentroServGarantia to set
	 */
	public void setOidCentroServGarantia(String oidCentroServGarantia) {
		this.oidCentroServGarantia = oidCentroServGarantia;
	}

	/**
	 * @return the numeroMesesGarantia
	 */
	public String getNumeroMesesGarantia() {
		return numeroMesesGarantia;
	}

	/**
	 * @param numeroMesesGarantia the numeroMesesGarantia to set
	 */
	public void setNumeroMesesGarantia(String numeroMesesGarantia) {
		this.numeroMesesGarantia = numeroMesesGarantia;
	}

	/**
	 * @return the observaciones
	 */
	public String getObservaciones() {
		return observaciones;
	}

	/**
	 * @param observaciones the observaciones to set
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * @return the indicadorTipoEntrega
	 */
	public String getIndicadorTipoEntrega() {
		return indicadorTipoEntrega;
	}

	/**
	 * @param indicadorTipoEntrega the indicadorTipoEntrega to set
	 */
	public void setIndicadorTipoEntrega(String indicadorTipoEntrega) {
		this.indicadorTipoEntrega = indicadorTipoEntrega;
	}

	/**
	 * @return the oidCentroServEntrega
	 */
	public String getOidCentroServEntrega() {
		return oidCentroServEntrega;
	}

	/**
	 * @param oidCentroServEntrega the oidCentroServEntrega to set
	 */
	public void setOidCentroServEntrega(String oidCentroServEntrega) {
		this.oidCentroServEntrega = oidCentroServEntrega;
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
	 * @return the indicadorNivelSelectivo
	 */
	public String getIndicadorNivelSelectivo() {
		return indicadorNivelSelectivo;
	}

	/**
	 * @param indicadorNivelSelectivo the indicadorNivelSelectivo to set
	 */
	public void setIndicadorNivelSelectivo(String indicadorNivelSelectivo) {
		this.indicadorNivelSelectivo = indicadorNivelSelectivo;
	}

	/**
	 * @return the numeroUnidades
	 */
	public String getNumeroUnidades() {
		return numeroUnidades;
	}

	/**
	 * @param numeroUnidades the numeroUnidades to set
	 */
	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}

	/**
	 * @return the tipoLote
	 */
	public String getTipoLote() {
		return tipoLote;
	}

	/**
	 * @param tipoLote the tipoLote to set
	 */
	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	/**
	 * @return the numeroLoteUltimo
	 */
	public String getNumeroLoteUltimo() {
		return numeroLoteUltimo;
	}

	/**
	 * @param numeroLoteUltimo the numeroLoteUltimo to set
	 */
	public void setNumeroLoteUltimo(String numeroLoteUltimo) {
		this.numeroLoteUltimo = numeroLoteUltimo;
	}

	/**
	 * @return the numeroPremioUltimo
	 */
	public String getNumeroPremioUltimo() {
		return numeroPremioUltimo;
	}

	/**
	 * @param numeroPremioUltimo the numeroPremioUltimo to set
	 */
	public void setNumeroPremioUltimo(String numeroPremioUltimo) {
		this.numeroPremioUltimo = numeroPremioUltimo;
	}

	/**
	 * @return the descripcionLoteUltimo
	 */
	public String getDescripcionLoteUltimo() {
		return descripcionLoteUltimo;
	}

	/**
	 * @param descripcionLoteUltimo the descripcionLoteUltimo to set
	 */
	public void setDescripcionLoteUltimo(String descripcionLoteUltimo) {
		this.descripcionLoteUltimo = descripcionLoteUltimo;
	}

	/**
	 * @return the precio
	 */
	public String getPrecio() {
		return precio;
	}

	/**
	 * @param precio the precio to set
	 */
	public void setPrecio(String precio) {
		this.precio = precio;
	}

	/**
	 * @return the modificar
	 */
	public boolean isModificar() {
		return modificar;
	}

	/**
	 * @param modificar the modificar to set
	 */
	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	public String getIndicadorPremiosWeb() {
		return indicadorPremiosWeb;
	}

	public void setIndicadorPremiosWeb(String indicadorPremiosWeb) {
		this.indicadorPremiosWeb = indicadorPremiosWeb;
	}
	

}
