/**
 * Created on 24-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazRetailFacturasVentaDirecta.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazRetailFacturasVentaDirecta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5819264561162263603L;
	private String codigoPais;
	private String numeroFactura;
	private String codigoVenta;
	private String codigoProducto;
	private String cuentaConsultora;
	private Timestamp fechaFactura;
	private String codigoMarca;
	private Integer unidadesAtendidas;
	private Double valorTotal;
	private String origenFactura;
	private String ubicacionGeografica;
	private String campanyaFactura;
		
	
	/**
	 * @return Returns the campanyaFactura.
	 */
	public String getCampanyaFactura() {
		return campanyaFactura;
	}
	/**
	 * @param campanyaFactura The campanyaFactura to set.
	 */
	public void setCampanyaFactura(String campanyaFactura) {
		this.campanyaFactura = campanyaFactura;
	}
	/**
	 * @return Returns the codigoMarca.
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	/**
	 * @param codigoMarca The codigoMarca to set.
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
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
	 * @return Returns the fechaFactura.
	 */
	public Timestamp getFechaFactura() {
		return fechaFactura;
	}
	/**
	 * @param fechaFactura The fechaFactura to set.
	 */
	public void setFechaFactura(Timestamp fechaFactura) {
		this.fechaFactura = fechaFactura;
	}
	/**
	 * @return Returns the numeroFactura.
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}
	/**
	 * @param numeroFactura The numeroFactura to set.
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	/**
	 * @return Returns the origenFactura.
	 */
	public String getOrigenFactura() {
		return origenFactura;
	}
	/**
	 * @param origenFactura The origenFactura to set.
	 */
	public void setOrigenFactura(String origenFactura) {
		this.origenFactura = origenFactura;
	}
	/**
	 * @return Returns the ubicacionGeografica.
	 */
	public String getUbicacionGeografica() {
		return ubicacionGeografica;
	}
	/**
	 * @param ubicacionGeografica The ubicacionGeografica to set.
	 */
	public void setUbicacionGeografica(String ubicacionGeografica) {
		this.ubicacionGeografica = ubicacionGeografica;
	}
	/**
	 * @return Returns the unidadesAtendidas.
	 */
	public Integer getUnidadesAtendidas() {
		return unidadesAtendidas;
	}
	/**
	 * @param unidadesAtendidas The unidadesAtendidas to set.
	 */
	public void setUnidadesAtendidas(Integer unidadesAtendidas) {
		this.unidadesAtendidas = unidadesAtendidas;
	}
	/**
	 * @return Returns the valorTotal.
	 */
	public Double getValorTotal() {
		return valorTotal;
	}
	/**
	 * @param valorTotal The valorTotal to set.
	 */
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
}
