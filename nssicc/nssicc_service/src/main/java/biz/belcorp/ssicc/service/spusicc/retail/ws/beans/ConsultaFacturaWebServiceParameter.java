/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * @author Richar Cruzado
 * @date   29/12/2015
 */
public class ConsultaFacturaWebServiceParameter implements Serializable {

	private static final long serialVersionUID = -6893994059990157265L;
 
	private String numeroFactura;
	private String numeroPedido;
	//Código de campaña
	private String codigoPeriodo;
	
	/**
	 * @return the numeroFactura
	 */
	public String getNumeroFactura() {
		return numeroFactura;
	}
	/**
	 * @param numeroFactura the numeroFactura to set
	 */
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	/**
	 * @return the numeroPedido
	 */
	public String getNumeroPedido() {
		return numeroPedido;
	}
	/**
	 * @param numeroPedido the numeroPedido to set
	 */
	public void setNumeroPedido(String numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	/**
	 * @return the codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}	    
	/**
	 * @param codigoPeriodo the codigoPeriodo to set
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}

}
