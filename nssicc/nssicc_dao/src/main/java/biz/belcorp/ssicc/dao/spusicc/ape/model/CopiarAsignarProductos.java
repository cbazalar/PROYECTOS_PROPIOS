package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Nicols Lpez
 */

public class CopiarAsignarProductos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3230126087792271203L;
	private String codigoPais;
	private String codigoCentroDistribucion;
	private String descripcionCentro;
	private String codigoMarca;
	private String descripcionMarca;
	private String codigoCanal;
	private String descripcionCanal;
	private String codigoPeriodo;

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
	 * @return codigoCentro
	 */ 
	public String getCodigoCentroDistribucion() {
		return codigoCentroDistribucion;
	}
	
	/**
	 * @param codigoCentro
	 */
	public void setCodigoCentroDistribucion(String codigoCentroDistribucion) {
		this.codigoCentroDistribucion = codigoCentroDistribucion;
	}
	
	/**
	 * @return descripcionCentro
	 */ 
	public String getDescripcionCentro() {
		return descripcionCentro;
	}
	
	/**
	 * @param descripcionCentro
	 */
	public void setDescripcionCentro(String descripcionCentro) {
		this.descripcionCentro = descripcionCentro;
	}
	
	/**
	 * @return codigoMarca
	 */
	public String getCodigoMarca() {
		return codigoMarca;
	}
	
	/**
	 * @param codigoMarca
	 */
	public void setCodigoMarca(String codigoMarca) {
		this.codigoMarca = codigoMarca;
	}
	
	/**
	 * @return descripcionMarca
	 */
	public String getDescripcionMarca() {
		return descripcionMarca;
	}
	
	/**
	 * @param descripcionMarca
	 */
	public void setDescripcionMarca(String descripcionMarca) {
		this.descripcionMarca = descripcionMarca;
	}
	
	/**
	 * @return codigoCanal
	 */
	public String getCodigoCanal() {
		return codigoCanal;
	}
	
	/**
	 * @param codigoCanal
	 */
	public void setCodigoCanal(String codigoCanal) {
		this.codigoCanal = codigoCanal;
	}
	
	/**
	 * @return descripcionCanal
	 */
	public String getDescripcionCanal() {
		return descripcionCanal;
	}
	
	/**
	 * @param descripcionCanal
	 */
	public void setDescripcionCanal(String descripcionCanal) {
		this.descripcionCanal = descripcionCanal;
	}
	
	/**
	 * @return codigoPeriodo
	 */
	public String getCodigoPeriodo() {
		return codigoPeriodo;
	}
	
	/**
	 * @param codigoPeriodo
	 */
	public void setCodigoPeriodo(String codigoPeriodo) {
		this.codigoPeriodo = codigoPeriodo;
	}
	
}