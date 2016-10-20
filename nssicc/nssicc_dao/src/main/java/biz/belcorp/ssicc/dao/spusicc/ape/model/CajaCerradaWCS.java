package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;


/**
 * @author Jose Luis Rodriguez
 */

public class CajaCerradaWCS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8858104683455075017L;
	private String pais;
	private String marca;
	private String centroDistribucion;
	private String linea;
	private String lote;
	private String factura;
	private String numeroCaja;
	private String numeroUnicoCaja;
	private String indicadorArmado;
	
	/**
	 * @return the pais
	 */
	public String getPais() {
		return pais;
	}
	/**
	 * @param pais the pais to set
	 */
	public void setPais(String pais) {
		this.pais = pais;
	}
	/**
	 * @return the centroDistribucion
	 */
	public String getCentroDistribucion() {
		return centroDistribucion;
	}
	/**
	 * @param centroDistribucion the centroDistribucion to set
	 */
	public void setCentroDistribucion(String centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
	}
	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}
	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	/**
	 * @return the linea
	 */
	public String getLinea() {
		return linea;
	}
	/**
	 * @param linea the linea to set
	 */
	public void setLinea(String linea) {
		this.linea = linea;
	}
	/**
	 * @return the lote
	 */
	public String getLote() {
		return lote;
	}
	/**
	 * @param lote the lote to set
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}
	/**
	 * @return the factura
	 */
	public String getFactura() {
		return factura;
	}
	/**
	 * @param factura the factura to set
	 */
	public void setFactura(String factura) {
		this.factura = factura;
	}
	/**
	 * @return the numeroCaja
	 */
	public String getNumeroCaja() {
		return numeroCaja;
	}
	/**
	 * @param numeroCaja the numeroCaja to set
	 */
	public void setNumeroCaja(String numeroCaja) {
		this.numeroCaja = numeroCaja;
	}
	/**
	 * @return the numerounicoCaja
	 */
	public String getNumerounicoCaja() {
		return numeroUnicoCaja;
	}
	/**
	 * @param numerounicoCaja the numerounicoCaja to set
	 */
	public void setNumerounicoCaja(String numerounicoCaja) {
		this.numeroUnicoCaja = numerounicoCaja;
	}
	/**
	 * @return the indicadorArmado
	 */
	public String getIndicadorArmado() {
		return indicadorArmado;
	}
	/**
	 * @param indicadorArmado the indicadorArmado to set
	 */
	public void setIndicadorArmado(String indicadorArmado) {
		this.indicadorArmado = indicadorArmado;
	}
	
}