package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class ProcesoPEDServicioGenericoWebServiceResponse implements Serializable {


	private static final long serialVersionUID = -7277453747492712804L;
	private String codigoRetorno;
	private String valorRetorno;
	private String valorRetorno2;
	private String valorRetorno3;
	private String valorRetorno4;
		
	/**
	 * 
	 */
	public ProcesoPEDServicioGenericoWebServiceResponse() {
		this.codigoRetorno = "";
		this.valorRetorno = "";
	}

	
	public ProcesoPEDServicioGenericoWebServiceResponse(String codigoRetorno, String valorRetorno) {
		this.codigoRetorno = codigoRetorno;
		this.valorRetorno = valorRetorno;
	}


	/**
	 * @return the codigoRetorno
	 */
	public String getCodigoRetorno() {
		return codigoRetorno;
	}


	/**
	 * @param codigoRetorno the codigoRetorno to set
	 */
	public void setCodigoRetorno(String codigoRetorno) {
		this.codigoRetorno = codigoRetorno;
	}


	/**
	 * @return the valorRetorno
	 */
	public String getValorRetorno() {
		return valorRetorno;
	}


	/**
	 * @param valorRetorno the valorRetorno to set
	 */
	public void setValorRetorno(String valorRetorno) {
		this.valorRetorno = valorRetorno;
	}


	/**
	 * @return the valorRetorno2
	 */
	public String getValorRetorno2() {
		return valorRetorno2;
	}


	/**
	 * @param valorRetorno2 the valorRetorno2 to set
	 */
	public void setValorRetorno2(String valorRetorno2) {
		this.valorRetorno2 = valorRetorno2;
	}


	/**
	 * @return the valorRetorno3
	 */
	public String getValorRetorno3() {
		return valorRetorno3;
	}


	/**
	 * @param valorRetorno3 the valorRetorno3 to set
	 */
	public void setValorRetorno3(String valorRetorno3) {
		this.valorRetorno3 = valorRetorno3;
	}


	/**
	 * @return the valorRetorno4
	 */
	public String getValorRetorno4() {
		return valorRetorno4;
	}


	/**
	 * @param valorRetorno4 the valorRetorno4 to set
	 */
	public void setValorRetorno4(String valorRetorno4) {
		this.valorRetorno4 = valorRetorno4;
	}	
}
