package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;

public class PedidoDetalleWebServiceParameter implements Serializable {
	
	private static final long serialVersionUID = -6480085629480704141L;
	
	private String cuv;
	private Integer unidadesSol;
	
	/**
	 * @return the cuv
	 */
	public String getCuv() {
		return cuv;
	}
	/**
	 * @param cuv the cuv to set
	 */
	public void setCuv(String cuv) {
		this.cuv = cuv;
	}
	/**
	 * @return the unidadesSol
	 */
	public Integer getUnidadesSol() {
		return unidadesSol;
	}
	/**
	 * @param unidadesSol the unidadesSol to set
	 */
	public void setUnidadesSol(Integer unidadesSol) {
		this.unidadesSol = unidadesSol;
	}
}