/*
 * Created on 29-nov-2005
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
 * <a href="InterfazRetailComplementoFacturasVentaDirecta.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazRetailComplementoFacturasVentaDirecta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1645280440330038642L;
	private String codigoPais;
	private Long numeroFactura;
	private String codigoVenta;
	private String codigoProducto;
	private String cuentaConsultora;
	private Double valorVentaCatalogoUnitario;
	private Double valorVentaCatalogoUnitarioDescuento;
	private Double valorVentaCatalogoDescuento;
	private Double totalImpuesto; 			
	private Double flete;
	private String tipoDocumento;
	
	
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
	 * @return Returns the cuentaConsultora.
	 */
	public String getCuentaConsultora() {
		return cuentaConsultora;
	}
	/**
	 * @param cuentaConsultora The cuentaConsultora to set.
	 */
	public void setCuentaConsultora(String cuentaConsultora) {
		this.cuentaConsultora = cuentaConsultora;
	}
	/**
	 * @return Returns the flete.
	 */
	public Double getFlete() {
		return flete;
	}
	/**
	 * @param flete The flete to set.
	 */
	public void setFlete(Double flete) {
		this.flete = flete;
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
	 * @return Returns the tipoDocumento.
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	/**
	 * @param tipoDocumento The tipoDocumento to set.
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	/**
	 * @return Returns the totalImpuesto.
	 */
	public Double getTotalImpuesto() {
		return totalImpuesto;
	}
	/**
	 * @param totalImpuesto The totalImpuesto to set.
	 */
	public void setTotalImpuesto(Double totalImpuesto) {
		this.totalImpuesto = totalImpuesto;
	}
	/**
	 * @return Returns the valorVentaCatalogoDescuento.
	 */
	public Double getValorVentaCatalogoDescuento() {
		return valorVentaCatalogoDescuento;
	}
	/**
	 * @param valorVentaCatalogoDescuento The valorVentaCatalogoDescuento to set.
	 */
	public void setValorVentaCatalogoDescuento(
			Double valorVentaCatalogoDescuento) {
		this.valorVentaCatalogoDescuento = valorVentaCatalogoDescuento;
	}
	/**
	 * @return Returns the valorVentaCatalogoUnitario.
	 */
	public Double getValorVentaCatalogoUnitario() {
		return valorVentaCatalogoUnitario;
	}
	/**
	 * @param valorVentaCatalogoUnitario The valorVentaCatalogoUnitario to set.
	 */
	public void setValorVentaCatalogoUnitario(Double valorVentaCatalogoUnitario) {
		this.valorVentaCatalogoUnitario = valorVentaCatalogoUnitario;
	}
	/**
	 * @return Returns the valorVentaCatalogoUnitarioDescuento.
	 */
	public Double getValorVentaCatalogoUnitarioDescuento() {
		return valorVentaCatalogoUnitarioDescuento;
	}
	/**
	 * @param valorVentaCatalogoUnitarioDescuento The valorVentaCatalogoUnitarioDescuento to set.
	 */
	public void setValorVentaCatalogoUnitarioDescuento(
			Double valorVentaCatalogoUnitarioDescuento) {
		this.valorVentaCatalogoUnitarioDescuento = valorVentaCatalogoUnitarioDescuento;
	}
}
