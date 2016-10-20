package biz.belcorp.ssicc.web.spusicc.pre.form;

import java.io.Serializable;

import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoPREMatrizAlternativosForm extends BaseEditForm implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String codigoPeriodo;
	private String codigoVentaPrincipal ;
	private String codigoSAPPrincipal ;
	private String descripcionPrincipal;
	private String codigoVentaAlternativo;
	private String codigoSAPAlternativo;
	private String descripcionAlternativo;
	private String indicadorMensaje ;
	private Boolean indicadorMensajeBool;
	private String numeroOrden ;	
	private String oidMatrizPrincipal;
	private String oidMatrizAlternativo;
		
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}

	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 * 
	 * @struts.validator type = "required"
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
	 * 
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
	 * @return the codigoVentaAlternativo
	 */
	public String getCodigoVentaAlternativo() {
		return codigoVentaAlternativo;
	}

	/**
	 * @param codigoVentaAlternativo the codigoVentaAlternativo to set
	 * 
	 * @struts.validator type = "required"
	 * 
	 */
	public void setCodigoVentaAlternativo(String codigoVentaAlternativo) {
		this.codigoVentaAlternativo = codigoVentaAlternativo;
	}

	/**
	 * @return the codigoSAPAlternativo
	 */
	public String getCodigoSAPAlternativo() {
		return codigoSAPAlternativo;
	}

	/**
	 * @param codigoSAPAlternativo the codigoSAPAlternativo to set
	 */
	public void setCodigoSAPAlternativo(String codigoSAPAlternativo) {
		this.codigoSAPAlternativo = codigoSAPAlternativo;
	}

	/**
	 * @return the descripcionAlternativo
	 */
	public String getDescripcionAlternativo() {
		return descripcionAlternativo;
	}

	/**
	 * @param descripcionAlternativo the descripcionAlternativo to set
	 */
	public void setDescripcionAlternativo(String descripcionAlternativo) {
		this.descripcionAlternativo = descripcionAlternativo;
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
	 * @return the numeroOrden
	 */
	public String getNumeroOrden() {
		return numeroOrden;
	}

	/**
	 * @param numeroOrden the numeroOrden to set
	 * 
	 * @struts.validator type = "required"
	 * 
	 */
	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	/**
	 * @return the oidMatrizPrincipal
	 */
	public String getOidMatrizPrincipal() {
		return oidMatrizPrincipal;
	}

	/**
	 * @param oidMatrizPrincipal the oidMatrizPrincipal to set
	 */
	public void setOidMatrizPrincipal(String oidMatrizPrincipal) {
		this.oidMatrizPrincipal = oidMatrizPrincipal;
	}

	/**
	 * @return the oidMatrizAlternativo
	 */
	public String getOidMatrizAlternativo() {
		return oidMatrizAlternativo;
	}

	/**
	 * @param oidMatrizAlternativo the oidMatrizAlternativo to set
	 */
	public void setOidMatrizAlternativo(String oidMatrizAlternativo) {
		this.oidMatrizAlternativo = oidMatrizAlternativo;
	}

	public Boolean getIndicadorMensajeBool() {
		return indicadorMensajeBool;
	}

	public void setIndicadorMensajeBool(Boolean indicadorMensajeBool) {
		this.indicadorMensajeBool = indicadorMensajeBool;
	}
}