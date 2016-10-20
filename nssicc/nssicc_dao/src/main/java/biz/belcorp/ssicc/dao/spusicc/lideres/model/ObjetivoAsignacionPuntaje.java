package biz.belcorp.ssicc.dao.spusicc.lideres.model;

import java.io.Serializable;

/**
 * 
 * <p>
 * <a href="ObjetivoAsignacionPuntaje.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:etovar@csigcomt.com">Eder Tovar
 *         </a>
 */

public class ObjetivoAsignacionPuntaje implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	private String codigoTipoAsignacionPuntaje;
	private String descTipoAsignacionPuntaje;
	private String codigoPeriodoIni;
	private String codigoPeriodoFin;
	private double valorObjetivo;
	
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
	 * @return the codigoTipoAsignacionPuntaje
	 */
	public String getCodigoTipoAsignacionPuntaje() {
		return codigoTipoAsignacionPuntaje;
	}
	/**
	 * @param codigoTipoAsignacionPuntaje the codigoTipoAsignacionPuntaje to set
	 */
	public void setCodigoTipoAsignacionPuntaje(String codigoTipoAsignacionPuntaje) {
		this.codigoTipoAsignacionPuntaje = codigoTipoAsignacionPuntaje;
	}
	/**
	 * @return the codigoPeriodoIni
	 */
	public String getCodigoPeriodoIni() {
		return codigoPeriodoIni;
	}
	/**
	 * @param codigoPeriodoIni the codigoPeriodoIni to set
	 */
	public void setCodigoPeriodoIni(String codigoPeriodoIni) {
		this.codigoPeriodoIni = codigoPeriodoIni;
	}
	/**
	 * @return the codigoPeriodoFin
	 */
	public String getCodigoPeriodoFin() {
		return codigoPeriodoFin;
	}
	/**
	 * @param codigoPeriodoFin the codigoPeriodoFin to set
	 */
	public void setCodigoPeriodoFin(String codigoPeriodoFin) {
		this.codigoPeriodoFin = codigoPeriodoFin;
	}
	/**
	 * @return the valorObjetivo
	 */
	public double getValorObjetivo() {
		return valorObjetivo;
	}
	/**
	 * @param valorObjetivo the valorObjetivo to set
	 */
	public void setValorObjetivo(double valorObjetivo) {
		this.valorObjetivo = valorObjetivo;
	}
	/**
	 * @return the descTipoAsignacionPuntaje
	 */
	public String getDescTipoAsignacionPuntaje() {
		return descTipoAsignacionPuntaje;
	}
	/**
	 * @param descTipoAsignacionPuntaje the descTipoAsignacionPuntaje to set
	 */
	public void setDescTipoAsignacionPuntaje(String descTipoAsignacionPuntaje) {
		this.descTipoAsignacionPuntaje = descTipoAsignacionPuntaje;
	}

}
