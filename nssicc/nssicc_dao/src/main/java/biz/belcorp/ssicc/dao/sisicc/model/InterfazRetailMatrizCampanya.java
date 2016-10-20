/*
 * Created on 08-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazRetailMatrizCampanya.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazRetailMatrizCampanya implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6481848713119441937L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoVenta;
	private String codigoProducto;
	private String descripcionProducto;
	private String codigoTipoOferta;
	private Double precioCatalogo;
	private String flagComisionable;
	private Integer tasaImpuestos;
	private String marcaProducto;
	private String codigoNegocio;
	private String codigoUnidadNegocio;
	private String valorDescuentoEspecifico;

	
	/**
	 * @return Returns the codigoNegocio.
	 */
	public String getCodigoNegocio() {
		return codigoNegocio;
	}
	/**
	 * @param codigoNegocio The codigoNegocio to set.
	 */
	public void setCodigoNegocio(String codigoNegocio) {
		this.codigoNegocio = codigoNegocio;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoPeriodo.
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	/**
	 * @param codigoPeriodo The codigoPeriodo to set.
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	/**
	 * @return Returns the codigoProducto.
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}
	/**
	 * @param codigoProducto The codigoProducto to set.
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	/**
	 * @return Returns the codigoTipoOferta.
	 */
	public String getCodigoTipoOferta() {
		return codigoTipoOferta;
	}
	/**
	 * @param codigoTipoOferta The codigoTipoOferta to set.
	 */
	public void setCodigoTipoOferta(String codigoTipoOferta) {
		this.codigoTipoOferta = codigoTipoOferta;
	}
	/**
	 * @return Returns the codigoUnidadNegocio.
	 */
	public String getCodigoUnidadNegocio() {
		return codigoUnidadNegocio;
	}
	/**
	 * @param codigoUnidadNegocio The codigoUnidadNegocio to set.
	 */
	public void setCodigoUnidadNegocio(String codigoUnidadNegocio) {
		this.codigoUnidadNegocio = codigoUnidadNegocio;
	}
	/**
	 * @return Returns the codigoVenta.
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta The codigoVenta to set.
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	/**
	 * @return Returns the descripcionProducto.
	 */
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	/**
	 * @param descripcionProducto The descripcionProducto to set.
	 */
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	/**
	 * @return Returns the flagComisionable.
	 */
	public String getFlagComisionable() {
		return flagComisionable;
	}
	/**
	 * @param flagComisionable The flagComisionable to set.
	 */
	public void setFlagComisionable(String flagComisionable) {
		this.flagComisionable = flagComisionable;
	}
	/**
	 * @return Returns the marcaProducto.
	 */
	public String getMarcaProducto() {
		return marcaProducto;
	}
	/**
	 * @param marcaProducto The marcaProducto to set.
	 */
	public void setMarcaProducto(String marcaProducto) {
		this.marcaProducto = marcaProducto;
	}
	/**
	 * @return Returns the precioCatalogo.
	 */
	public Double getPrecioCatalogo() {
		return precioCatalogo;
	}
	/**
	 * @param precioCatalogo The precioCatalogo to set.
	 */
	public void setPrecioCatalogo(Double precioCatalogo) {
		this.precioCatalogo = precioCatalogo;
	}
	/**
	 * @return Returns the tasaImpuestos.
	 */
	public Integer getTasaImpuestos() {
		return tasaImpuestos;
	}
	/**
	 * @param tasaImpuestos The tasaImpuestos to set.
	 */
	public void setTasaImpuestos(Integer tasaImpuestos) {
		this.tasaImpuestos = tasaImpuestos;
	}
	/**
	 * @return Returns the valorDescuentoEspecifico.
	 */
	public String getValorDescuentoEspecifico() {
		return valorDescuentoEspecifico;
	}
	/**
	 * @param valorDescuentoEspecifico The valorDescuentoEspecifico to set.
	 */
	public void setValorDescuentoEspecifico(String valorDescuentoEspecifico) {
		this.valorDescuentoEspecifico = valorDescuentoEspecifico;
	}
}
