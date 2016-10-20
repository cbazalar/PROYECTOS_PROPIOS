/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.dao.scsicc.bean;

import java.io.Serializable;

/**
 * TODO Include class description here.
 * <p>
 * <a href="DetallePedidoFacturadoBean.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class DetallePedidoFacturadoBean implements Serializable {

	
	private static final long serialVersionUID = 5300536502354806682L;
	private String codigoVenta;
	private String descripcionProducto;
	private String formaVenta;
	private String unidadesDemandaReal;
	private String unidadesAtendidas;
	private String precioCatalogoUnitario;
	private String precioCatalogoTotal;
	private String porcentajeDescuento;
	private String importeDescuentoTotal;
	private String precioFacturacion;
	private String observacion;
	/**
	 * @return the codigoVenta
	 */
	public String getCodigoVenta() {
		return codigoVenta;
	}
	/**
	 * @param codigoVenta the codigoVenta to set
	 */
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
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
	 * @return the formaVenta
	 */
	public String getFormaVenta() {
		return formaVenta;
	}
	/**
	 * @param formaVenta the formaVenta to set
	 */
	public void setFormaVenta(String formaVenta) {
		this.formaVenta = formaVenta;
	}
	/**
	 * @return the unidadesDemandaReal
	 */
	public String getUnidadesDemandaReal() {
		return unidadesDemandaReal;
	}
	/**
	 * @param unidadesDemandaReal the unidadesDemandaReal to set
	 */
	public void setUnidadesDemandaReal(String unidadesDemandaReal) {
		this.unidadesDemandaReal = unidadesDemandaReal;
	}
	/**
	 * @return the unidadesAtendidas
	 */
	public String getUnidadesAtendidas() {
		return unidadesAtendidas;
	}
	/**
	 * @param unidadesAtendidas the unidadesAtendidas to set
	 */
	public void setUnidadesAtendidas(String unidadesAtendidas) {
		this.unidadesAtendidas = unidadesAtendidas;
	}
	/**
	 * @return the precioCatalogoUnitario
	 */
	public String getPrecioCatalogoUnitario() {
		return precioCatalogoUnitario;
	}
	/**
	 * @param precioCatalogoUnitario the precioCatalogoUnitario to set
	 */
	public void setPrecioCatalogoUnitario(String precioCatalogoUnitario) {
		this.precioCatalogoUnitario = precioCatalogoUnitario;
	}
	/**
	 * @return the precioCatalogoTotal
	 */
	public String getPrecioCatalogoTotal() {
		return precioCatalogoTotal;
	}
	/**
	 * @param precioCatalogoTotal the precioCatalogoTotal to set
	 */
	public void setPrecioCatalogoTotal(String precioCatalogoTotal) {
		this.precioCatalogoTotal = precioCatalogoTotal;
	}
	/**
	 * @return the porcentajeDescuento
	 */
	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}
	/**
	 * @param porcentajeDescuento the porcentajeDescuento to set
	 */
	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}
	/**
	 * @return the importeDescuentoTotal
	 */
	public String getImporteDescuentoTotal() {
		return importeDescuentoTotal;
	}
	/**
	 * @param importeDescuentoTotal the importeDescuentoTotal to set
	 */
	public void setImporteDescuentoTotal(String importeDescuentoTotal) {
		this.importeDescuentoTotal = importeDescuentoTotal;
	}
	/**
	 * @return the precioFacturacion
	 */
	public String getPrecioFacturacion() {
		return precioFacturacion;
	}
	/**
	 * @param precioFacturacion the precioFacturacion to set
	 */
	public void setPrecioFacturacion(String precioFacturacion) {
		this.precioFacturacion = precioFacturacion;
	}
	/**
	 * @return the observacion
	 */
	public String getObservacion() {
		return observacion;
	}
	/**
	 * @param observacion the observacion to set
	 */
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	
}
