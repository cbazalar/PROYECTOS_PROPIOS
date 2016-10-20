package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Jose Luis Rodriguez
 */

public class OrdenAnaquel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -178869128971135633L;
	private String codigoPais;
	private String codigoCentroDistribucion;
	private String codigoMapaCentroDistribucion;
	private String codigoMapaZona;
	private String codigoOrdenAnaqueles;
	private String descripcionOrdenAnaqueles;
	private String codigoLinea;
	private String indicadorDefecto;
	
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
	 * @return the codigoCentroDistribucion
	 */
	public String getCodigoCentroDistribucion() {
		return codigoCentroDistribucion;
	}
	/**
	 * @param codigoCentroDistribucion the codigoCentroDistribucion to set
	 */
	public void setCodigoCentroDistribucion(String codigoCentroDistribucion) {
		this.codigoCentroDistribucion = codigoCentroDistribucion;
	}
	/**
	 * @return the codigoMapaCentroDistribucion
	 */
	public String getCodigoMapaCentroDistribucion() {
		return codigoMapaCentroDistribucion;
	}
	/**
	 * @param codigoMapaCentroDistribucion the codigoMapaCentroDistribucion to set
	 */
	public void setCodigoMapaCentroDistribucion(String codigoMapaCentroDistribucion) {
		this.codigoMapaCentroDistribucion = codigoMapaCentroDistribucion;
	}
	/**
	 * @return the codigoMapaZona
	 */
	public String getCodigoMapaZona() {
		return codigoMapaZona;
	}
	/**
	 * @param codigoMapaZona the codigoMapaZona to set
	 */
	public void setCodigoMapaZona(String codigoMapaZona) {
		this.codigoMapaZona = codigoMapaZona;
	}
	/**
	 * @return the codigoOrdenAnaqueles
	 */
	public String getCodigoOrdenAnaqueles() {
		return codigoOrdenAnaqueles;
	}
	/**
	 * @param codigoOrdenAnaqueles the codigoOrdenAnaqueles to set
	 */
	public void setCodigoOrdenAnaqueles(String codigoOrdenAnaqueles) {
		this.codigoOrdenAnaqueles = codigoOrdenAnaqueles;
	}
	/**
	 * @return the descripcionOrdenAnaqueles
	 */
	public String getDescripcionOrdenAnaqueles() {
		return descripcionOrdenAnaqueles;
	}
	/**
	 * @param descripcionOrdenAnaqueles the descripcionOrdenAnaqueles to set
	 */
	public void setDescripcionOrdenAnaqueles(String descripcionOrdenAnaqueles) {
		this.descripcionOrdenAnaqueles = descripcionOrdenAnaqueles;
	}
	/**
	 * @return the codigoLinea
	 */
	public String getCodigoLinea() {
		return codigoLinea;
	}
	/**
	 * @param codigoLinea the codigoLinea to set
	 */
	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}
	/**
	 * @return the indicadorDefecto
	 */
	public String getIndicadorDefecto() {
		return indicadorDefecto;
	}
	/**
	 * @param indicadorDefecto the indicadorDefecto to set
	 */
	public void setIndicadorDefecto(String indicadorDefecto) {
		this.indicadorDefecto = indicadorDefecto;
	}
	
}