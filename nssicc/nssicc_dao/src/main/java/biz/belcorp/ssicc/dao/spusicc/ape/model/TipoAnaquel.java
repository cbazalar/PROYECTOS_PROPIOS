package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

public class TipoAnaquel implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2175205250942787403L;
	private String codigoPais;
	private String oidTipoAnaquel;
	private String codigoTipoAnaquel;
	private String descripcionTipoAnaquel;
	private String tipo;
	private String codigoTipoChanel;
	private String indDefault;
	
	/**
	 * @return the oidTipoAnaquel
	 */
	public String getOidTipoAnaquel() {
		return oidTipoAnaquel;
	}
	/**
	 * @param oidTipoAnaquel the oidTipoAnaquel to set
	 */
	public void setOidTipoAnaquel(String oidTipoAnaquel) {
		this.oidTipoAnaquel = oidTipoAnaquel;
	}
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return the codigoTipoAnaquel
	 */
	public String getCodigoTipoAnaquel() {
		return codigoTipoAnaquel;
	}
	/**
	 * @param codigoTipoAnaquel the codigoTipoAnaquel to set
	 */
	public void setCodigoTipoAnaquel(String codigoTipoAnaquel) {
		this.codigoTipoAnaquel = codigoTipoAnaquel;
	}
	/**
	 * @return the descripcionTipoAnaquel
	 */
	public String getDescripcionTipoAnaquel() {
		return descripcionTipoAnaquel;
	}
	/**
	 * @param descripcionTipoAnaquel the descripcionTipoAnaquel to set
	 */
	public void setDescripcionTipoAnaquel(String descripcionTipoAnaquel) {
		this.descripcionTipoAnaquel = descripcionTipoAnaquel;
	}
	
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the codigoTipoChanel
	 */
	public String getCodigoTipoChanel() {
		return codigoTipoChanel;
	}
	/**
	 * @param codigoTipoChanel the codigoTipoChanel to set
	 */
	public void setCodigoTipoChanel(String codigoTipoChanel) {
		this.codigoTipoChanel = codigoTipoChanel;
	}
	/**
	 * @return the indDefault
	 */
	public String getIndDefault() {
		return indDefault;
	}
	/**
	 * @param indDefault the indDefault to set
	 */
	public void setIndDefault(String indDefault) {
		this.indDefault = indDefault;
	}
	
}
