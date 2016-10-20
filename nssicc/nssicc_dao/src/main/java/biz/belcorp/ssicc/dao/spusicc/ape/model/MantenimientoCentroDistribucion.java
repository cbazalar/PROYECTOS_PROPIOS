package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

public class MantenimientoCentroDistribucion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4335109093832842514L;
	private String codigoPais;
	private String codigoCD;
	private String codigoMapaCD;
	private String estadoAnaquel;
	private String descripcionCD;
	private String descripcionMapaCD;
	
	/**
	 * @return estadoAnaquel
	 */
	public String getEstadoAnaquel() {
		return estadoAnaquel;
	}

	/**
	 * @param estadoAnaquel
	 */
	public void setEstadoAnaquel(String estadoAnaquel) {
		this.estadoAnaquel = estadoAnaquel;
	}

	/**
	 * @return codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	
	/**
	 * @param codigoPais
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	
	/**
	 * @return codigoCD
	 */
	public String getCodigoCD() {
		return codigoCD;
	}
	
	/**
	 * @param codigoCD
	 */
	public void setCodigoCD(String codigoCD) {
		this.codigoCD = codigoCD;
	}
	
	/**
	 * @return descripcionCD
	 */
	public String getDescripcionCD() {
		return descripcionCD;
	}
	
	/**
	 * @param descripcionCD
	 */
	public void setDescripcionCD(String descripcionCD) {
		this.descripcionCD = descripcionCD;
	}
	
	/**
	 * @return codigoMapaCD
	 */
	public String getCodigoMapaCD() {
		return codigoMapaCD;
	}
	
	/**
	 * @param codigoMapaCD
	 */
	public void setCodigoMapaCD(String codigoMapaCD) {
		this.codigoMapaCD = codigoMapaCD;
	}
	
	/**
	 * @return descripcionMapaCD
	 */
	public String getDescripcionMapaCD() {
		return descripcionMapaCD;
	}
	
	/**
	 * @param descripcionMapaCD
	 */
	public void setDescripcionMapaCD(String descripcionMapaCD) {
		this.descripcionMapaCD = descripcionMapaCD;
	}
	
}
