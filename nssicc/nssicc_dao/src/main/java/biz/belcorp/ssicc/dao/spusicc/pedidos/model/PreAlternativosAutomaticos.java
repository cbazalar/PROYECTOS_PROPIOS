/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.model;

import java.io.Serializable;

/**
 * @author peextcroman
 *
 */
public class PreAlternativosAutomaticos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1493176803541489650L;
	private String codigoPeriodo;
	private String codigoSAPPrincipal;
	private String codigoSAPAlternativo;
	
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	public String getCodigoSAPPrincipal() {
		return codigoSAPPrincipal;
	}
	public void setCodigoSAPPrincipal(String codigoSAPPrincipal) {
		this.codigoSAPPrincipal = codigoSAPPrincipal;
	}
	public String getCodigoSAPAlternativo() {
		return codigoSAPAlternativo;
	}
	public void setCodigoSAPAlternativo(String codigoSAPAlternativo) {
		this.codigoSAPAlternativo = codigoSAPAlternativo;
	}
	
	
	
}
