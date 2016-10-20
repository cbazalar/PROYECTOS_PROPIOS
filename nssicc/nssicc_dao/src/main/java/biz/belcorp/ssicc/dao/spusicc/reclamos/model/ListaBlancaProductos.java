package biz.belcorp.ssicc.dao.spusicc.reclamos.model;

import java.io.Serializable;

public class ListaBlancaProductos implements Serializable{
	
	private String oidListaBlancaProductos;
	private String codigoOperacion;
    private String descripcionOperacion;
    private String codigoVenta;
    private String codigoCliente;
    private String codigoRegion;
    private String descripcionRegion;
    private String codigoZona;
    private String descripcionZona;
    private String codigoPeriodoInicio;
    private String codigoPeriodoFinal;
    private String codigoTipoOperacion;
    private String indicadorListaBlanca;
    private String codigoMotivoReal;
    private String descripcionMotivo;
    
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
	/**
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	public String getCodigoZona() {
		return codigoZona;
	}
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	public String getDescripcionZona() {
		return descripcionZona;
	}
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
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
	public String getIndicadorListaBlanca() {
		return indicadorListaBlanca;
	}
	public void setIndicadorListaBlanca(String indicadorListaBlanca) {
		this.indicadorListaBlanca = indicadorListaBlanca;
	}
	public String getDescripcionMotivo() {
		return descripcionMotivo;
	}
	public void setDescripcionMotivo(String descripcionMotivo) {
		this.descripcionMotivo = descripcionMotivo;
	}
	public String getCodigoMotivoReal() {
		return codigoMotivoReal;
	}
	public void setCodigoMotivoReal(String codigoMotivoReal) {
		this.codigoMotivoReal = codigoMotivoReal;
	}
	public String getOidListaBlancaProductos() {
		return oidListaBlancaProductos;
	}
	public void setOidListaBlancaProductos(String oidListaBlancaProductos) {
		this.oidListaBlancaProductos = oidListaBlancaProductos;
	}
    
	
}
