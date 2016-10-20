package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class PedidoAlternativosWebServiceParameter implements Serializable {
	
	private static final long serialVersionUID = -6480085629480704141L;
	
	private String cuvOriginal;
	private String cuvAlternativo;
	private Integer unidadesSolOrig;
	private Integer unidadesSolAlte;
	
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
	 * @return the unidadesSolOrig
	 */
	public Integer getUnidadesSolOrig() {
		return unidadesSolOrig;
	}
	/**
	 * @param unidadesSolOrig the unidadesSolOrig to set
	 */
	public void setUnidadesSolOrig(Integer unidadesSolOrig) {
		this.unidadesSolOrig = unidadesSolOrig;
	}
	/**
	 * @return the unidadesSolAlte
	 */
	public Integer getUnidadesSolAlte() {
		return unidadesSolAlte;
	}
	/**
	 * @param unidadesSolAlte the unidadesSolAlte to set
	 */
	public void setUnidadesSolAlte(Integer unidadesSolAlte) {
		this.unidadesSolAlte = unidadesSolAlte;
	}
}