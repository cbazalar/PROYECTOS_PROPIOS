/*
 * Created on 28-oct-2005
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
 * <a href="InterfazMyEbelMatrizCampanya.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */
public class InterfazMyEbelMatrizCampanya implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8933211056033430331L;
	private String codigoPais;
    private String codigoPeriodo;
    private String codigoVenta;
    private String codigoGrupo;
    private String codigoProducto;
    private String codigoTipoOferta;
    private Float precioProducto;
    private Integer factorRepeticion;
    private Long unidadesMaximas;
    private Integer paginaCatalogo;
    private Integer productoEstadisticable;

    
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
	 * @return Returns the factorRepeticion.
	 */
	public Integer getFactorRepeticion() {
		return factorRepeticion;
	}
	/**
	 * @param factorRepeticion The factorRepeticion to set.
	 */
	public void setFactorRepeticion(Integer factorRepeticion) {
		this.factorRepeticion = factorRepeticion;
	}
	/**
	 * @return Returns the paginaCatalogo.
	 */
	public Integer getPaginaCatalogo() {
		return paginaCatalogo;
	}
	/**
	 * @param paginaCatalogo The paginaCatalogo to set.
	 */
	public void setPaginaCatalogo(Integer paginaCatalogo) {
		this.paginaCatalogo = paginaCatalogo;
	}
	/**
	 * @return Returns the precioProducto.
	 */
	public Float getPrecioProducto() {
		return precioProducto;
	}
	/**
	 * @param precioProducto The precioProducto to set.
	 */
	public void setPrecioProducto(Float precioProducto) {
		this.precioProducto = precioProducto;
	}
	/**
	 * @return Returns the productoEstadisticable.
	 */
	public Integer getProductoEstadisticable() {
		return productoEstadisticable;
	}
	/**
	 * @param productoEstadisticable The productoEstadisticable to set.
	 */
	public void setProductoEstadisticable(Integer productoEstadisticable) {
		this.productoEstadisticable = productoEstadisticable;
	}
	/**
	 * @return Returns the unidadesMaximas.
	 */
	public Long getUnidadesMaximas() {
		return unidadesMaximas;
	}
	/**
	 * @param unidadesMaximas The unidadesMaximas to set.
	 */
	public void setUnidadesMaximas(Long unidadesMaximas) {
		this.unidadesMaximas = unidadesMaximas;
	}
}
