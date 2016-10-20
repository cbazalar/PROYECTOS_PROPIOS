package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCDefinirPremioDescuentoForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	
	private String montoDescuento;

	public String getNumeroNivel() {
		return numeroNivel;
	}

	public void setNumeroNivel(String numeroNivel) {
		this.numeroNivel = numeroNivel;
	}

	public String getNumeroLote() {
		return numeroLote;
	}

	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}

	public String getNumeroPremio() {
		return numeroPremio;
	}

	public void setNumeroPremio(String numeroPremio) {
		this.numeroPremio = numeroPremio;
	}

	public String getDescripcionLote() {
		return descripcionLote;
	}

	public void setDescripcionLote(String descripcionLote) {
		this.descripcionLote = descripcionLote;
	}

	public String getCodigoSAP() {
		return codigoSAP;
	}

	public void setCodigoSAP(String codigoSAP) {
		this.codigoSAP = codigoSAP;
	}

	public String getDescripcionSAP() {
		return descripcionSAP;
	}

	public void setDescripcionSAP(String descripcionSAP) {
		this.descripcionSAP = descripcionSAP;
	}

	public String getIndicadorCentroServicio() {
		return indicadorCentroServicio;
	}

	public void setIndicadorCentroServicio(String indicadorCentroServicio) {
		this.indicadorCentroServicio = indicadorCentroServicio;
	}

	public String getOidCentroServGarantia() {
		return oidCentroServGarantia;
	}

	public void setOidCentroServGarantia(String oidCentroServGarantia) {
		this.oidCentroServGarantia = oidCentroServGarantia;
	}

	public String getNumeroMesesGarantia() {
		return numeroMesesGarantia;
	}

	public void setNumeroMesesGarantia(String numeroMesesGarantia) {
		this.numeroMesesGarantia = numeroMesesGarantia;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String getIndicadorTipoEntrega() {
		return indicadorTipoEntrega;
	}

	public void setIndicadorTipoEntrega(String indicadorTipoEntrega) {
		this.indicadorTipoEntrega = indicadorTipoEntrega;
	}

	public String getOidCentroServEntrega() {
		return oidCentroServEntrega;
	}

	public void setOidCentroServEntrega(String oidCentroServEntrega) {
		this.oidCentroServEntrega = oidCentroServEntrega;
	}

	public String[] getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(String[] selectedItems) {
		this.selectedItems = selectedItems;
	}

	public String getIndicadorNivelSelectivo() {
		return indicadorNivelSelectivo;
	}

	public void setIndicadorNivelSelectivo(String indicadorNivelSelectivo) {
		this.indicadorNivelSelectivo = indicadorNivelSelectivo;
	}

	public String getNumeroUnidades() {
		return numeroUnidades;
	}

	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}

	public String getTipoLote() {
		return tipoLote;
	}

	public void setTipoLote(String tipoLote) {
		this.tipoLote = tipoLote;
	}

	public String getNumeroLoteUltimo() {
		return numeroLoteUltimo;
	}

	public void setNumeroLoteUltimo(String numeroLoteUltimo) {
		this.numeroLoteUltimo = numeroLoteUltimo;
	}

	public String getNumeroPremioUltimo() {
		return numeroPremioUltimo;
	}

	public void setNumeroPremioUltimo(String numeroPremioUltimo) {
		this.numeroPremioUltimo = numeroPremioUltimo;
	}

	public String getDescripcionLoteUltimo() {
		return descripcionLoteUltimo;
	}

	public void setDescripcionLoteUltimo(String descripcionLoteUltimo) {
		this.descripcionLoteUltimo = descripcionLoteUltimo;
	}

	public String getPrecio() {
		return precio;
	}

	public void setPrecio(String precio) {
		this.precio = precio;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	public String getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(String montoDescuento) {
		this.montoDescuento = montoDescuento;
	}
	
		

}
