/*
 * Created on 20-dic-2005
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
 * <a href="InterfazBELFacturasDetalle.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazBELFacturasDetalle implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1404664402388750514L;
	private Long numeroFactura;
	private String codigoVenta;
	private String codigoProducto;
	private Long unidadesAtendidas;
	private Long unidadesFaltantes;
	private Double precioCatalogo;
	private Double precioFactura;
	private Double precioContable;
	
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
	 * @return Returns the numeroFactura.
	 */
	public Long getNumeroFactura() {
		return numeroFactura;
	}
	/**
	 * @param numeroFactura The numeroFactura to set.
	 */
	public void setNumeroFactura(Long numeroFactura) {
		this.numeroFactura = numeroFactura;
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
	 * @return Returns the precioFactura.
	 */
	public Double getPrecioFactura() {
		return precioFactura;
	}
	/**
	 * @param precioFactura The precioFactura to set.
	 */
	public void setPrecioFactura(Double precioFactura) {
		this.precioFactura = precioFactura;
	}
	/**
	 * @return Returns the unidadesAtendidas.
	 */
	public Long getUnidadesAtendidas() {
		return unidadesAtendidas;
	}
	/**
	 * @param unidadesAtendidas The unidadesAtendidas to set.
	 */
	public void setUnidadesAtendidas(Long unidadesAtendidas) {
		this.unidadesAtendidas = unidadesAtendidas;
	}
	/**
	 * @return Returns the unidadesFaltantes.
	 */
	public Long getUnidadesFaltantes() {
		return unidadesFaltantes;
	}
	/**
	 * @param unidadesFaltantes The unidadesFaltantes to set.
	 */
	public void setUnidadesFaltantes(Long unidadesFaltantes) {
		this.unidadesFaltantes = unidadesFaltantes;
	}
}
