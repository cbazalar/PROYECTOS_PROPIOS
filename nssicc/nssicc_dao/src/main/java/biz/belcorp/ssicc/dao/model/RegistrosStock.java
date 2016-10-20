package biz.belcorp.ssicc.dao.model;

import java.io.Serializable;

/* @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 * 
 */
public class RegistrosStock  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5589562471185906895L;
	protected String codigoSap;
	protected String unidadesAtendidas;
	protected String candidadStock;
	public String getCodigoSap() {
		return codigoSap;
	}
	public void setCodigoSap(String codigoSap) {
		this.codigoSap = codigoSap;
	}
	public String getUnidadesAtendidas() {
		return unidadesAtendidas;
	}
	public void setUnidadesAtendidas(String unidadesAtendidas) {
		this.unidadesAtendidas = unidadesAtendidas;
	}
	public String getCandidadStock() {
		return candidadStock;
	}
	public void setCandidadStock(String candidadStock) {
		this.candidadStock = candidadStock;
	}
	
	
	
}
