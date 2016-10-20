/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

/**
 * @author jvelasquez
 *
 */
public class DetalleCuvExistencia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oidDetalleOferta;
	private String codigoSap;
	private String descripcion;
	private String codigoVenta;
	private String indicadorValido;
	
	public String getOidDetalleOferta() {
		return oidDetalleOferta;
	}
	public void setOidDetalleOferta(String oidDetalleOferta) {
		this.oidDetalleOferta = oidDetalleOferta;
	}
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public String getIndicadorValido() {
		return indicadorValido;
	}
	public void setIndicadorValido(String indicadorValido) {
		this.indicadorValido = indicadorValido;
	}
	
}
