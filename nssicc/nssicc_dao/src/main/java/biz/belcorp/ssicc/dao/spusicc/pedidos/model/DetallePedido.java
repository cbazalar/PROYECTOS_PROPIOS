/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

/**
 * @author peextcroman
 *
 */
public class DetallePedido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -921296451123960276L;
	private String codigoCliente;
	private String numeroLote;
	private String codigoVenta;
	private String numeroUnidades;
	private String indRecepDD;
	private String indRecepWEB;
	private String indRecepOCR;
	
	
	public String getCodigoCliente() {
		return codigoCliente;
	}
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	public String getNumeroLote() {
		return numeroLote;
	}
	public void setNumeroLote(String numeroLote) {
		this.numeroLote = numeroLote;
	}
	public String getCodigoVenta() {
		return codigoVenta;
	}
	public void setCodigoVenta(String codigoVenta) {
		this.codigoVenta = codigoVenta;
	}
	public String getNumeroUnidades() {
		return numeroUnidades;
	}
	public void setNumeroUnidades(String numeroUnidades) {
		this.numeroUnidades = numeroUnidades;
	}
	public String getIndRecepDD() {
		return indRecepDD;
	}
	public void setIndRecepDD(String indRecepDD) {
		this.indRecepDD = indRecepDD;
	}
	public String getIndRecepWEB() {
		return indRecepWEB;
	}
	public void setIndRecepWEB(String indRecepWEB) {
		this.indRecepWEB = indRecepWEB;
	}
	public String getIndRecepOCR() {
		return indRecepOCR;
	}
	public void setIndRecepOCR(String indRecepOCR) {
		this.indRecepOCR = indRecepOCR;
	}
	
}
