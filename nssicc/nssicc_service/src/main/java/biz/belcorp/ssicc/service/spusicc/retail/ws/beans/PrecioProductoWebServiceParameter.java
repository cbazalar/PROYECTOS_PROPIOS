/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.retail.ws.beans;

import java.io.Serializable;

/**
 * @author Richar Cruzado
 * @date   28/12/2015
 */
public class PrecioProductoWebServiceParameter implements Serializable {

	private static final long serialVersionUID = 6893994059990157265L;

	private String codigoPeriodo;
	private String codigoSap;
	
	
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
	/**
	 * @return the codigoSap
	 */
	public String getCodigoSap() {
		return codigoSap;
	}
	/**
	 * @param codigoSap the codigoSap to set
	 */
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
		

}
