package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse implements Serializable {

	private static final long serialVersionUID = 6222431448834869834L;
	
	private String cuvOriginal;
	private String cuvAlternativo;
	private String descAlternativo;
	private String catalogo;
	
	public ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse() {
		this.cuvOriginal = "";
		this.cuvAlternativo = "";
		this.descAlternativo = "";
		this.catalogo = "";
	}

	public ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse(String cuvOriginal, String cuvAlternativo, String descAlternativo, String catalogo) {
		this.cuvOriginal = cuvOriginal;
		this.cuvAlternativo = cuvAlternativo;
		this.descAlternativo = descAlternativo;
		this.catalogo = catalogo;
	}

	/**
	 * @return the cuvOriginal
	 */
	public String getCuvOriginal() {
		return cuvOriginal;
	}

	/**
	 * @param cuvOriginal the cuvOriginal to set
	 */
	public void setCuvOriginal(String cuvOriginal) {
		this.cuvOriginal = cuvOriginal;
	}

	/**
	 * @return the cuvAlternativo
	 */
	public String getCuvAlternativo() {
		return cuvAlternativo;
	}

	/**
	 * @param cuvAlternativo the cuvAlternativo to set
	 */
	public void setCuvAlternativo(String cuvAlternativo) {
		this.cuvAlternativo = cuvAlternativo;
	}

	/**
	 * @return the descAlternativo
	 */
	public String getDescAlternativo() {
		return descAlternativo;
	}

	/**
	 * @param descAlternativo the descAlternativo to set
	 */
	public void setDescAlternativo(String descAlternativo) {
		this.descAlternativo = descAlternativo;
	}

	/**
	 * @return the catalogo
	 */
	public String getCatalogo() {
		return catalogo;
	}

	/**
	 * @param catalogo the catalogo to set
	 */
	public void setCatalogo(String catalogo) {
		this.catalogo = catalogo;
	}
}