package biz.belcorp.ssicc.web.spusicc.inc.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoINCReemplazosForm extends BaseEditForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7714948670747236234L;
	private String codigoPais;
	private String codigoProducto;
	private String descripcionProducto;
	private String numeroUnidades;
	private String oidCriterio;

	private String numeroPremio;
	private String codigoSAP;
	private String codigoPremio;
	private String descripcionPremio;
	private String oidArticuloLote;
	private String oid;
	
	//Seccion Ambito Geografico
	private String [] regiones;
	private String [] zonas;
	private String indActAmbitos;
	private String[] selectedItemsAmbito;
	
	private String numeroOrden;
	private String indicadorActivo;
	
	private String codigoProductoInicio;
	private String descripcionProductoInicio;
	private String posicionReemplazo;
	
	//Reemplazo Compuesto
	private String precio;
	private String tipoAgrupacion;
	private String oidCompuesto;
	
	private boolean habilitarIndicadorActivo;

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
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the descripcionProducto
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}

	/**
	 * @param descripcionProducto the descripcionProducto to set
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
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
	 * @return the oidCriterio
	 */
	public String getOidCriterio() {
		return oidCriterio;
	}

	/**
	 * @param oidCriterio the oidCriterio to set
	 */
	public void setOidCriterio(String oidCriterio) {
		this.oidCriterio = oidCriterio;
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
	 * @return the codigoPremio
	 */
	public String getCodigoPremio() {
		return codigoPremio;
	}

	/**
	 * @param codigoPremio the codigoPremio to set
	 */
	public void setCodigoPremio(String codigoPremio) {
		this.codigoPremio = codigoPremio;
	}

	/**
	 * @return the descripcionPremio
	 */
	public String getDescripcionPremio() {
		return descripcionPremio;
	}

	/**
	 * @param descripcionPremio the descripcionPremio to set
	 */
	public void setDescripcionPremio(String descripcionPremio) {
		this.descripcionPremio = descripcionPremio;
	}

	/**
	 * @return the oidArticuloLote
	 */
	public String getOidArticuloLote() {
		return oidArticuloLote;
	}

	/**
	 * @param oidArticuloLote the oidArticuloLote to set
	 */
	public void setOidArticuloLote(String oidArticuloLote) {
		this.oidArticuloLote = oidArticuloLote;
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the regiones
	 */
	public String[] getRegiones() {
		return regiones;
	}

	/**
	 * @param regiones the regiones to set
	 */
	public void setRegiones(String[] regiones) {
		this.regiones = regiones;
	}

	/**
	 * @return the zonas
	 */
	public String[] getZonas() {
		return zonas;
	}

	/**
	 * @param zonas the zonas to set
	 */
	public void setZonas(String[] zonas) {
		this.zonas = zonas;
	}

	/**
	 * @return the indActAmbitos
	 */
	public String getIndActAmbitos() {
		return indActAmbitos;
	}

	/**
	 * @param indActAmbitos the indActAmbitos to set
	 */
	public void setIndActAmbitos(String indActAmbitos) {
		this.indActAmbitos = indActAmbitos;
	}

	/**
	 * @return the selectedItemsAmbito
	 */
	public String[] getSelectedItemsAmbito() {
		return selectedItemsAmbito;
	}

	/**
	 * @param selectedItemsAmbito the selectedItemsAmbito to set
	 */
	public void setSelectedItemsAmbito(String[] selectedItemsAmbito) {
		this.selectedItemsAmbito = selectedItemsAmbito;
	}

	/**
	 * @return the numeroOrden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * @param numeroOrden the numeroOrden to set
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * @return the indicadorActivo
	 */
	public String getIndicadorActivo() {
		return indicadorActivo;
	}

	/**
	 * @param indicadorActivo the indicadorActivo to set
	 */
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}

	/**
	 * @return the codigoProductoInicio
	 */
	public String getCodigoProductoInicio() {
		return codigoProductoInicio;
	}

	/**
	 * @param codigoProductoInicio the codigoProductoInicio to set
	 */
	public void setCodigoProductoInicio(String codigoProductoInicio) {
		this.codigoProductoInicio = codigoProductoInicio;
	}

	/**
	 * @return the descripcionProductoInicio
	 */
	public String getDescripcionProductoInicio() {
		return descripcionProductoInicio;
	}

	/**
	 * @param descripcionProductoInicio the descripcionProductoInicio to set
	 */
	public void setDescripcionProductoInicio(String descripcionProductoInicio) {
		this.descripcionProductoInicio = descripcionProductoInicio;
	}

	/**
	 * @return the posicionReemplazo
	 */
	public String getPosicionReemplazo() {
		return posicionReemplazo;
	}

	/**
	 * @param posicionReemplazo the posicionReemplazo to set
	 */
	public void setPosicionReemplazo(String posicionReemplazo) {
		this.posicionReemplazo = posicionReemplazo;
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
	 * @return the tipoAgrupacion
	 */
	public String getTipoAgrupacion() {
		return tipoAgrupacion;
	}

	/**
	 * @param tipoAgrupacion the tipoAgrupacion to set
	 */
	public void setTipoAgrupacion(String tipoAgrupacion) {
		this.tipoAgrupacion = tipoAgrupacion;
	}

	/**
	 * @return the oidCompuesto
	 */
	public String getOidCompuesto() {
		return oidCompuesto;
	}

	/**
	 * @param oidCompuesto the oidCompuesto to set
	 */
	public void setOidCompuesto(String oidCompuesto) {
		this.oidCompuesto = oidCompuesto;
	}

	/**
	 * @return the habilitarIndicadorActivo
	 */
	public boolean isHabilitarIndicadorActivo() {
		return habilitarIndicadorActivo;
	}

	/**
	 * @param habilitarIndicadorActivo the habilitarIndicadorActivo to set
	 */
	public void setHabilitarIndicadorActivo(boolean habilitarIndicadorActivo) {
		this.habilitarIndicadorActivo = habilitarIndicadorActivo;
	}
	
	

}
