package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

public class CodigoVentaOpera implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6763197115086382329L;
	private String codigoOperacion;
    private String descripcionOperacion;
    private String codigoVenta;
    private String codigoTipoOferta;
    private String descripcionTipoOferta;
    private String codigoCatalogo;
    private String descripcionCatalogo;
    private String codigoPeriodoInicio;
    private String codigoPeriodoFinal;
    private String codigoTipoOperacion;
    private String indicadorListaBlanca;
    
	public String getCodigoOperacion() {
		return codigoOperacion;
	}
	public void setCodigoOperacion(String codigoOperacion) {
		this.codigoOperacion = codigoOperacion;
	}
	public String getDescripcionOperacion() {
		return descripcionOperacion;
	}
	public void setDescripcionOperacion(String descripcionOperacion) {
		this.descripcionOperacion = descripcionOperacion;
	}
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public String getCodigoTipoOferta() {
		return codigoTipoOferta;
	}
	public void setCodigoTipoOferta(String codigoTipoOferta) {
		this.codigoTipoOferta = codigoTipoOferta;
	}
	public String getDescripcionTipoOferta() {
		return descripcionTipoOferta;
	}
	public void setDescripcionTipoOferta(String descripcionTipoOferta) {
		this.descripcionTipoOferta = descripcionTipoOferta;
	}
	public String getCodigoCatalogo() {
		return codigoCatalogo;
	}
	public void setCodigoCatalogo(String codigoCatalogo) {
		this.codigoCatalogo = codigoCatalogo;
	}
	public String getDescripcionCatalogo() {
		return descripcionCatalogo;
	}
	public void setDescripcionCatalogo(String descripcionCatalogo) {
		this.descripcionCatalogo = descripcionCatalogo;
	}
	public String getCodigoPeriodoInicio() {
		return codigoPeriodoInicio;
	}
	public void setCodigoPeriodoInicio(String codigoPeriodoInicio) {
		this.codigoPeriodoInicio = codigoPeriodoInicio;
	}
	public String getCodigoPeriodoFinal() {
		return codigoPeriodoFinal;
	}
	public void setCodigoPeriodoFinal(String codigoPeriodoFinal) {
		this.codigoPeriodoFinal = codigoPeriodoFinal;
	}
	public String getCodigoTipoOperacion() {
		return codigoTipoOperacion;
	}
	public void setCodigoTipoOperacion(String codigoTipoOperacion) {
		this.codigoTipoOperacion = codigoTipoOperacion;
	}
	/**
	 * @return the indicadorListaBlanca
	 */
	public String getIndicadorListaBlanca() {
		return indicadorListaBlanca;
	}
	/**
	 * @param indicadorListaBlanca the indicadorListaBlanca to set
	 */
	public void setIndicadorListaBlanca(String indicadorListaBlanca) {
		this.indicadorListaBlanca = indicadorListaBlanca;
	}
	
    
}
