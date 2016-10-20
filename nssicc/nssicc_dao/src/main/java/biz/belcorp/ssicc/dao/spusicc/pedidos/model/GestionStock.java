/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

/**
 * @author jvelasquez
 *
 */
public class GestionStock implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oidGestionStock;
	private String codigoVentaPrincipal;
	private String codigoProducto;
	private String descripcionProducto;
	private String codigoZona;
	private String codigoRegion;
	private String tipoCliente;
	private String subTipoCliente;
	private String tipoClasificacion;
	private String clasificacion;
	private String limiteVenta;
	private String valorPorcentual;
	private String valorUnidades;
	private String indicadorActivo;
	private String fechaActivacion;
	private String indicadorDetalleOferta;
	
	public String getOidGestionStock() {
		return oidGestionStock;
	}
	public void setOidGestionStock(String oidGestionStock) {
		this.oidGestionStock = oidGestionStock;
	}
	public String getCodigoVentaPrincipal() {
		return codigoVentaPrincipal;
	}
	public void setCodigoVentaPrincipal(String codigoVentaPrincipal) {
		this.codigoVentaPrincipal = codigoVentaPrincipal;
	}
	public String getCodigoProducto() {
		return codigoProducto;
	}
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
	public String getDescripcionProducto() {
		return descripcionProducto;
	}
	public void setDescripcionProducto(String descripcionProducto) {
		this.descripcionProducto = descripcionProducto;
	}
	public String getCodigoZona() {
		return codigoZona;
	}
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	public String getCodigoRegion() {
		return codigoRegion;
	}
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	public String getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	public String getSubTipoCliente() {
		return subTipoCliente;
	}
	public void setSubTipoCliente(String subTipoCliente) {
		this.subTipoCliente = subTipoCliente;
	}
	public String getTipoClasificacion() {
		return tipoClasificacion;
	}
	public void setTipoClasificacion(String tipoClasificacion) {
		this.tipoClasificacion = tipoClasificacion;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getLimiteVenta() {
		return limiteVenta;
	}
	public void setLimiteVenta(String limiteVenta) {
		this.limiteVenta = limiteVenta;
	}
	public String getValorPorcentual() {
		return valorPorcentual;
	}
	public void setValorPorcentual(String valorPorcentual) {
		this.valorPorcentual = valorPorcentual;
	}
	public String getValorUnidades() {
		return valorUnidades;
	}
	public void setValorUnidades(String valorUnidades) {
		this.valorUnidades = valorUnidades;
	}
	public String getIndicadorActivo() {
		return indicadorActivo;
	}
	public void setIndicadorActivo(String indicadorActivo) {
		this.indicadorActivo = indicadorActivo;
	}
	public String getFechaActivacion() {
		return fechaActivacion;
	}
	public void setFechaActivacion(String fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}
	public String getIndicadorDetalleOferta() {
		return indicadorDetalleOferta;
	}
	public void setIndicadorDetalleOferta(String indicadorDetalleOferta) {
		this.indicadorDetalleOferta = indicadorDetalleOferta;
	}

}
