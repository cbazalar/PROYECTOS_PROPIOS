/*
 * Created on 20-dic-2005
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
 * <a href="InterfazBELFacturasCabecera.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public class InterfazBELFacturasCabecera implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7463887570923341535L;
	private String anyoCampanya;
	private String codigoCliente;
	private Long numeroFactura;
	private String tipoPedido;
	private Timestamp fechaFactura;
	private String tipoDocumento;
		
	/**
	 * @return Returns the anyoCampanya.
	 */
	public String getAnyoCampanya() {
		return anyoCampanya;
	}
	/**
	 * @param anyoCampanya The anyoCampanya to set.
	 */
	public void setAnyoCampanya(String anyoCampanya) {
		this.anyoCampanya = anyoCampanya;
	}
	/**
	 * @return Returns the codigoCliente.
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente The codigoCliente to set.
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
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
	 * @return Returns the tipoPedido.
	 */
	public String getTipoPedido() {
		return tipoPedido;
	}
	/**
	 * @param tipoPedido The tipoPedido to set.
	 */
	public void setTipoPedido(String tipoPedido) {
		this.tipoPedido = tipoPedido;
	}
}
