package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

public class ParametroProceso implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3109576469100515912L;
	private String codigoProceso;
	private String codigoParametro;
	private String valorCadenaParametro;
	private String valorNumericoParametro;
	
	/**
	 * @return Returns the codigoParametro.
	 */
	public String getCodigoParametro() {
		return codigoParametro;
	}
	/**
	 * @param codigoParametro The codigoParametro to set.
	 */
	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}
	/**
	 * @return Returns the codigoProceso.
	 */
	public String getCodigoProceso() {
		return codigoProceso;
	}
	/**
	 * @param codigoProceso The codigoProceso to set.
	 */
	public void setCodigoProceso(String codigoProceso) {
		this.codigoProceso = codigoProceso;
	}
	/**
	 * @return Returns the valorCadenaParametro.
	 */
	public String getValorCadenaParametro() {
		return valorCadenaParametro;
	}
	/**
	 * @param valorCadenaParametro The valorCadenaParametro to set.
	 */
	public void setValorCadenaParametro(String valorCadenaParametro) {
		this.valorCadenaParametro = valorCadenaParametro;
	}
	/**
	 * @return Returns the valorNumericoParametro.
	 */
	public String getValorNumericoParametro() {
		return valorNumericoParametro;
	}
	/**
	 * @param valorNumericoParametro The valorNumericoParametro to set.
	 */
	public void setValorNumericoParametro(String valorNumericoParametro) {
		this.valorNumericoParametro = valorNumericoParametro;
	}

}
