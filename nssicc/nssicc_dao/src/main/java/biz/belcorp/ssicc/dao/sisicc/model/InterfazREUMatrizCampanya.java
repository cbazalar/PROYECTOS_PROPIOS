/*
 * Created on 21-dic-2005
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
 * <a href="InterfazREUMatrizCampanya.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * @deprecated
 */

public class InterfazREUMatrizCampanya implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3721418205423519275L;
	private String codigoPais;
	private String codigoPeriodo;
	private String codigoVenta;
	private String codigoGrupo;
	private String codigoProducto;
	private String codigoTipoOferta;
	private Double precioProducto;
	private Double precioContable;
	private Integer numeroPaginaCatalogo;
	private Integer factorRepeticionProducto;
	private Integer numeroUnidadesMaximas;
	private String indicadorProductoEstadisticable;
	private String grupoDescuento;
	private String indicadorComisionable;
	private String indicadorComisionAdicional;
	
	private String codigoNegocio;
	private String codigoUnidadNegocio;
	private String codigoMarcaProducto;
	private String comisionable;
	
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
	 * @return Returns the codigoGrupo.
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	/**
	 * @param codigoGrupo The codigoGrupo to set.
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
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
	 * @return Returns the factorRepeticionProducto.
	 */
	public Integer getFactorRepeticionProducto() {
		return factorRepeticionProducto;
	}
	/**
	 * @param factorRepeticionProducto The factorRepeticionProducto to set.
	 */
	public void setFactorRepeticionProducto(Integer factorRepeticionProducto) {
		this.factorRepeticionProducto = factorRepeticionProducto;
	}
	/**
	 * @return Returns the grupoDescuento.
	 */
	public String getGrupoDescuento() {
		return grupoDescuento;
	}
	/**
	 * @param grupoDescuento The grupoDescuento to set.
	 */
	public void setGrupoDescuento(String grupoDescuento) {
		this.grupoDescuento = grupoDescuento;
	}
	/**
	 * @return Returns the indicadorComisionable.
	 */
	public String getIndicadorComisionable() {
		return indicadorComisionable;
	}
	/**
	 * @param indicadorComisionable The indicadorComisionable to set.
	 */
	public void setIndicadorComisionable(String indicadorComisionable) {
		this.indicadorComisionable = indicadorComisionable;
	}
	/**
	 * @return Returns the indicadorComisionAdicional.
	 */
	public String getIndicadorComisionAdicional() {
		return indicadorComisionAdicional;
	}
	/**
	 * @param indicadorComisionAdicional The indicadorComisionAdicional to set.
	 */
	public void setIndicadorComisionAdicional(String indicadorComisionAdicional) {
		this.indicadorComisionAdicional = indicadorComisionAdicional;
	}
	/**
	 * @return Returns the indicadorProductoEstadisticable.
	 */
	public String getIndicadorProductoEstadisticable() {
		return indicadorProductoEstadisticable;
	}
	/**
	 * @param indicadorProductoEstadisticable The indicadorProductoEstadisticable to set.
	 */
	public void setIndicadorProductoEstadisticable(
			String indicadorProductoEstadisticable) {
		this.indicadorProductoEstadisticable = indicadorProductoEstadisticable;
	}
	/**
	 * @return Returns the numeroPaginaCatalogo.
	 */
	public Integer getNumeroPaginaCatalogo() {
		return numeroPaginaCatalogo;
	}
	/**
	 * @param numeroPaginaCatalogo The numeroPaginaCatalogo to set.
	 */
	public void setNumeroPaginaCatalogo(Integer numeroPaginaCatalogo) {
		this.numeroPaginaCatalogo = numeroPaginaCatalogo;
	}
	/**
	 * @return Returns the numeroUnidadesMaximas.
	 */
	public Integer getNumeroUnidadesMaximas() {
		return numeroUnidadesMaximas;
	}
	/**
	 * @param numeroUnidadesMaximas The numeroUnidadesMaximas to set.
	 */
	public void setNumeroUnidadesMaximas(Integer numeroUnidadesMaximas) {
		this.numeroUnidadesMaximas = numeroUnidadesMaximas;
	}
	/**
	 * @return Returns the precioContable.
	 */
	public Double getPrecioContable() {
		return precioContable;
	}
	/**
	 * @param precioContable The precioContable to set.
	 */
	public void setPrecioContable(Double precioContable) {
		this.precioContable = precioContable;
	}
	/**
	 * @return Returns the precioProducto.
	 */
	public Double getPrecioProducto() {
		return precioProducto;
	}
	/**
	 * @param precioProducto The precioProducto to set.
	 */
	public void setPrecioProducto(Double precioProducto) {
		this.precioProducto = precioProducto;
	}
	
	
	/**
	 * @return Returns the codigoMarcaProducto.
	 */
	public String getCodigoMarcaProducto() {
		return codigoMarcaProducto;
	}
	/**
	 * @param codigoMarcaProducto The codigoMarcaProducto to set.
	 */
	public void setCodigoMarcaProducto(String codigoMarcaProducto) {
		this.codigoMarcaProducto = codigoMarcaProducto;
	}
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
	 * @return Returns the comisionable.
	 */
	public String getComisionable() {
		return comisionable;
	}
	/**
	 * @param comisionable The comisionable to set.
	 */
	public void setComisionable(String comisionable) {
		this.comisionable = comisionable;
	}
}
