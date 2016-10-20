package biz.belcorp.ssicc.service.zon.ws.beans;

import java.io.Serializable;

public class ResponsableZONWebServiceResultado implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoResultado;
	private String mensajeResultado;
	private String codigoCampaniaAsign;

	/**
	 * @return
	 */
	public String getCodigoResultado() {
		return codigoResultado;
	}

	/**
	 * @param codigoResultado
	 */
	public void setCodigoResultado(String codigoResultado) {
		this.codigoResultado = codigoResultado;
	}

	/**
	 * @return
	 */
	public String getMensajeResultado() {
		return mensajeResultado;
	}

	/**
	 * @param mensajeResultado
	 */
	public void setMensajeResultado(String mensajeResultado) {
		this.mensajeResultado = mensajeResultado;
	}

	/**
	 * @return
	 */
	public String getCodigoCampaniaAsign() {
		return codigoCampaniaAsign;
	}

	/**
	 * @param codigoCampaniaAsign
	 */
	public void setCodigoCampaniaAsign(String codigoCampaniaAsign) {
		this.codigoCampaniaAsign = codigoCampaniaAsign;
	}

}
