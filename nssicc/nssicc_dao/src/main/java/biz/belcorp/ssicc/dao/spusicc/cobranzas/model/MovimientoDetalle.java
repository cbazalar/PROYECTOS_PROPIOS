package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextdoliva
 *
 */
public class MovimientoDetalle extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigoVenta;
	private String codigoVentaPosicion;
	private String producto;                   
	private String precioCatalogo;              
	private String precioFactura;    
	private String unidadesDemandadas;
	private String unidadesAtendidas;           
	private String unidadesFaltantes;           
	private String porcentajeDescuento;        
	private String observacion;
	private String importe;      
	private String montoMN;
	
	public MovimientoDetalle() {
	
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCodigoVenta() {
		return codigoVenta;
	}

	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}

	public String getProducto() {
		return producto;
	}

	public void setProducto(String producto) {
		this.producto = producto;
	}

	public String getPrecioCatalogo() {
		return precioCatalogo;
	}

	public void setPrecioCatalogo(String precioCatalogo) {
		this.precioCatalogo = precioCatalogo;
	}
    
	public String getPrecioFactura() {
		return precioFactura;
	}

	public void setPrecioFactura(String precioFactura) {
		this.precioFactura = precioFactura;
	}
	
	public String getUnidadesAtendidas() {
		return unidadesAtendidas;
	}

	public void setUnidadesAtendidas(String unidadesAtendidas) {
		this.unidadesAtendidas = unidadesAtendidas;
	}

	
    public String getUnidadesFaltantes() {
			return unidadesFaltantes;
		}

	public void setUnidadesFaltantes(String unidadesFaltantes) {
			this.unidadesFaltantes = unidadesFaltantes;
		}
	
	public String getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(String porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public String getImporte() {
		return importe;
	}

	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * @return the codigoVentaPosicion
	 */
	public String getCodigoVentaPosicion() {
		return codigoVentaPosicion;
	}

	/**
	 * @param codigoVentaPosicion the codigoVentaPosicion to set
	 */
	public void setCodigoVentaPosicion(String codigoVentaPosicion) {
		this.codigoVentaPosicion = codigoVentaPosicion;
	}

	/**
	 * @return the unidadesDemandadas
	 */
	public String getUnidadesDemandadas() {
		return unidadesDemandadas;
	}

	/**
	 * @param unidadesDemandadas the unidadesDemandadas to set
	 */
	public void setUnidadesDemandadas(String unidadesDemandadas) {
		this.unidadesDemandadas = unidadesDemandadas;
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

	/**
	 * @return the montoMN
	 */
	public String getMontoMN() {
		return montoMN;
	}

	/**
	 * @param montoMN the montoMN to set
	 */
	public void setMontoMN(String montoMN) {
		this.montoMN = montoMN;
	}
	
		
}
