package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Nicols Lpez
 */

public class ControlEnvioWCS implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4519594523269630533L;
	private String codigoPais;
	private String oidOlaDia;
	private String fecha;
	private String oidCentro;
	private String codigoCentro;
	private String descripcionCentro;
	private String oidLineaArmado; 
	private String codigoLineaArmado;
	private String descripcionLineaArmado;
	private String numeroOla;
	
	/**
	 * @return numeroOla
	 */
	public String getNumeroOla() {
		return numeroOla;
	}

	/**
	 * @param numeroOla
	 */
	public void setNumeroOla(String numeroOla) {
		this.numeroOla = numeroOla;
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
	 * @return oidOlaDia
	 */
	public String getOidOlaDia() {
		return oidOlaDia;
	}
	
	/**
	 * @param oidOlaDia
	 */
	public void setOidOlaDia(String oidOlaDia) {
		this.oidOlaDia = oidOlaDia;
	}
	
	/**
	 * @return fecha
	 */
	public String getFecha() {
		return fecha;
	}
	
	/**
	 * @param fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	/**
	 * @return oidCentro
	 */
	public String getOidCentro() {
		return oidCentro;
	}
	
	/**
	 * @param oidCentro
	 */
	public void setOidCentro(String oidCentro) {
		this.oidCentro = oidCentro;
	}
	
	/**
	 * @return codigoCentro
	 */
	public String getCodigoCentro() {
		return codigoCentro;
	}
	
	/**
	 * @param codigoCentro
	 */
	public void setCodigoCentro(String codigoCentro) {
		this.codigoCentro = codigoCentro;
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
	 * @return oidLineaArmado
	 */
	public String getOidLineaArmado() {
		return oidLineaArmado;
	}
	
	/**
	 * @param oidLineaArmado
	 */
	public void setOidLineaArmado(String oidLineaArmado) {
		this.oidLineaArmado = oidLineaArmado;
	}
	
	/**
	 * @return codigoLineaArmado
	 */
	public String getCodigoLineaArmado() {
		return codigoLineaArmado;
	}
	
	/**
	 * @param codigoLineaArmado
	 */
	public void setCodigoLineaArmado(String codigoLineaArmado) {
		this.codigoLineaArmado = codigoLineaArmado;
	}
	
	/**
	 * @return descripcionLineaArmado
	 */
	public String getDescripcionLineaArmado() {
		return descripcionLineaArmado;
	}
	
	/**
	 * @param descripcionLineaArmado
	 */
	public void setDescripcionLineaArmado(String descripcionLineaArmado) {
		this.descripcionLineaArmado = descripcionLineaArmado;
	}
	
}