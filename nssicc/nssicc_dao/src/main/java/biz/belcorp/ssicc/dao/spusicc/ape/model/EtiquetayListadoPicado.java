package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author David Ramos
 */

public class EtiquetayListadoPicado implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9095003173813019947L;
	private String oidPais;
	private String oidSoli;
	private String numCaja;
	private String usuario;
	private String oidConsolidado;
	private String codListaPicado;	
	
	
	/**
	 * @return usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	
	/**
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return oidPais
	 */
	public String getOidPais() {
		return oidPais;
	}
	/**
	 * @param oidPais
	 */
	public void setOidPais(String oidPais) {
		this.oidPais = oidPais;
	}
	/**
	 * @return oidSoli
	 */
	public String getOidSoli() {
		return oidSoli;
	}
	/**
	 * @param oidSoli
	 */
	public void setOidSoli(String oidSoli) {
		this.oidSoli = oidSoli;
	}
	/**
	 * @return numCaja
	 */
	public String getNumCaja() {
		return numCaja;
	}
	/**
	 * @param numCaja
	 */
	public void setNumCaja(String numCaja) {
		this.numCaja = numCaja;
	}
	/**
	 * @return oidConsolidado
	 */
	public String getOidConsolidado() {
		return oidConsolidado;
	}
	/**
	 * @param oidConsolidado
	 */
	public void setOidConsolidado(String oidConsolidado) {
		this.oidConsolidado = oidConsolidado;
	}
	/**
	 * @return codListaPicado
	 */
	public String getCodListaPicado() {
		return codListaPicado;
	}
	/**
	 * @param codListaPicado
	 */
	public void setCodListaPicado(String codListaPicado) {
		this.codListaPicado = codListaPicado;
	}



	
	
	
}