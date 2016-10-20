package biz.belcorp.ssicc.web.spusicc.pre.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPREMatrizRecuperacionesForm extends BaseEditForm implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoPeriodo;
	private String codigoVentaPrincipal ;
	private String codigoSAPPrincipal ;
	private String descripcionPrincipal;
	private String codigoPeriodoFaltante ;
	private String codigoVentaRecuperar;
	private String codigoSAPRecuperar;
	private String descripcionRecuperar;
	private String indicadorMensaje ;
	private String indicadorMensajeBool ;
	private String oidTipoCliente ;
	private String oidSubTipoCliente ;
	private String oidTipoClasificacion ;
	private String oidClasificacion ;
	private String codigoRegion ;
	private String codigoZona;
	private String oidMatrizRecuperacion;
	private String oidMatrizReemplazo;
	private String numeroUnidadRecuperacion;
	/*
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		this.codigoPeriodo = "";
		this.codigoVentaPrincipal  = "";
		this.codigoSAPPrincipal  = "";
		this.descripcionPrincipal = "";
		this.codigoPeriodoFaltante  = "";
		this.codigoVentaRecuperar = "";
		this.codigoSAPRecuperar = "";
		this.descripcionRecuperar = "";
		this.indicadorMensaje  = "";
		this.oidTipoCliente  = "";
		this.oidSubTipoCliente  = "";
		this.oidTipoClasificacion  = "";
		this.oidClasificacion  = "";
		this.codigoRegion  = "";
		this.codigoZona = "";
		this.oidMatrizRecuperacion = "";
		this.oidMatrizReemplazo = "";
	}
	*/
	
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * @struts.validator type = "required"
	 * 
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return the codigoVentaPrincipal
	 */
	public String getCodigoVentaPrincipal() {
		return codigoVentaPrincipal;
	}
	/**
	 * @param codigoVentaPrincipal the codigoVentaPrincipal to set
	 * @struts.validator type = "required"
	 * 
	 */
	public void setCodigoVentaPrincipal(String codigoVentaPrincipal) {
		this.codigoVentaPrincipal = codigoVentaPrincipal;
	}
	/**
	 * @return the codigoSAPPrincipal
	 */
	public String getCodigoSAPPrincipal() {
		return codigoSAPPrincipal;
	}
	/**
	 * @param codigoSAPPrincipal the codigoSAPPrincipal to set
	 */
	public void setCodigoSAPPrincipal(String codigoSAPPrincipal) {
		this.codigoSAPPrincipal = codigoSAPPrincipal;
	}
	/**
	 * @return the descripcionPrincipal
	 */
	public String getDescripcionPrincipal() {
		return descripcionPrincipal;
	}
	/**
	 * @param descripcionPrincipal the descripcionPrincipal to set
	 */
	public void setDescripcionPrincipal(String descripcionPrincipal) {
		this.descripcionPrincipal = descripcionPrincipal;
	}
	/**
	 * @return the codigoPeriodoFaltante
	 */
	public String getCodigoPeriodoFaltante() {
		return codigoPeriodoFaltante;
	}
	/**
	 * @param codigoPeriodoFaltante the codigoPeriodoFaltante to set
	 * @struts.validator type = "required"
	 * 
	 */
	public void setCodigoPeriodoFaltante(String codigoPeriodoFaltante) {
		this.codigoPeriodoFaltante = codigoPeriodoFaltante;
	}
	/**
	 * @return the codigoVentaRecuperar
	 */
	public String getCodigoVentaRecuperar() {
		return codigoVentaRecuperar;
	}
	/**
	 * @param codigoVentaRecuperar the codigoVentaRecuperar to set
	 * @struts.validator type = "required"
	 * 
	 */
	public void setCodigoVentaRecuperar(String codigoVentaRecuperar) {
		this.codigoVentaRecuperar = codigoVentaRecuperar;
	}
	/**
	 * @return the codigoSAPRecuperar
	 */
	public String getCodigoSAPRecuperar() {
		return codigoSAPRecuperar;
	}
	/**
	 * @param codigoSAPRecuperar the codigoSAPRecuperar to set
	 */
	public void setCodigoSAPRecuperar(String codigoSAPRecuperar) {
		this.codigoSAPRecuperar = codigoSAPRecuperar;
	}
	/**
	 * @return the descripcionRecuperar
	 */
	public String getDescripcionRecuperar() {
		return descripcionRecuperar;
	}
	/**
	 * @param descripcionRecuperar the descripcionRecuperar to set
	 */
	public void setDescripcionRecuperar(String descripcionRecuperar) {
		this.descripcionRecuperar = descripcionRecuperar;
	}
	/**
	 * @return the indicadorMensaje
	 */
	public String getIndicadorMensaje() {
		return indicadorMensaje;
	}
	/**
	 * @param indicadorMensaje the indicadorMensaje to set
	 */
	public void setIndicadorMensaje(String indicadorMensaje) {
		this.indicadorMensaje = indicadorMensaje;
	}
	/**
	 * @return the oidMatrizRecuperacion
	 */
	public String getOidMatrizRecuperacion() {
		return oidMatrizRecuperacion;
	}
	/**
	 * @param oidMatrizRecuperacion the oidMatrizRecuperacion to set
	 */
	public void setOidMatrizRecuperacion(String oidMatrizRecuperacion) {
		this.oidMatrizRecuperacion = oidMatrizRecuperacion;
	}
	/**
	 * @return the oidMatrizReemplazo
	 */
	public String getOidMatrizReemplazo() {
		return oidMatrizReemplazo;
	}
	/**
	 * @param oidMatrizReemplazo the oidMatrizReemplazo to set
	 */
	public void setOidMatrizReemplazo(String oidMatrizReemplazo) {
		this.oidMatrizReemplazo = oidMatrizReemplazo;
	}
	/**
	 * @return the oidTipoCliente
	 */
	public String getOidTipoCliente() {
		return oidTipoCliente;
	}
	/**
	 * @param oidTipoCliente the oidTipoCliente to set
	 */
	public void setOidTipoCliente(String oidTipoCliente) {
		this.oidTipoCliente = oidTipoCliente;
	}
	/**
	 * @return the oidSubTipoCliente
	 */
	public String getOidSubTipoCliente() {
		return oidSubTipoCliente;
	}
	/**
	 * @param oidSubTipoCliente the oidSubTipoCliente to set
	 */
	public void setOidSubTipoCliente(String oidSubTipoCliente) {
		this.oidSubTipoCliente = oidSubTipoCliente;
	}
	/**
	 * @return the oidTipoClasificacion
	 */
	public String getOidTipoClasificacion() {
		return oidTipoClasificacion;
	}
	/**
	 * @param oidTipoClasificacion the oidTipoClasificacion to set
	 */
	public void setOidTipoClasificacion(String oidTipoClasificacion) {
		this.oidTipoClasificacion = oidTipoClasificacion;
	}
	/**
	 * @return the oidClasificacion
	 */
	public String getOidClasificacion() {
		return oidClasificacion;
	}
	/**
	 * @param oidClasificacion the oidClasificacion to set
	 */
	public void setOidClasificacion(String oidClasificacion) {
		this.oidClasificacion = oidClasificacion;
	}
	/**
	 * @return the codigoRegion
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion the codigoRegion to set
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	public String getIndicadorMensajeBool() {
		return indicadorMensajeBool;
	}
	public void setIndicadorMensajeBool(String indicadorMensajeBool) {
		this.indicadorMensajeBool = indicadorMensajeBool;
	}
	/**
	 * @return the numeroUnidadRecuperacion
	 */
	public String getNumeroUnidadRecuperacion() {
		return numeroUnidadRecuperacion;
	}
	/**
	 * @param numeroUnidadRecuperacion the numeroUnidadRecuperacion to set
	 */
	public void setNumeroUnidadRecuperacion(String numeroUnidadRecuperacion) {
		this.numeroUnidadRecuperacion = numeroUnidadRecuperacion;
	}
}