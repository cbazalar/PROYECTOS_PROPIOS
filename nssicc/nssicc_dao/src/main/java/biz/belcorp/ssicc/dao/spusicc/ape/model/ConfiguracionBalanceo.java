package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Nicols Lpez
 */

public class ConfiguracionBalanceo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6668112983163692312L;

	private String oidCentro;

	private String codCentro;

	private String oidLineaArmado;
	
	private String codigoLineaArmado;
	
	private String numPorcentaje;
	
	private String numUnidad;
	
	private String oidFuncDist;
	
	private String codigoFuncDist;

	/**
	 * @return codCentro
	 */
	public String getCodCentro() {
		return codCentro;
	}

	/**
	 * @param codCentro
	 */
	public void setCodCentro(String codCentro) {
		this.codCentro = codCentro;
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
	 * @return codigoFuncDist
	 */
	public String getCodigoFuncDist() {
		return codigoFuncDist;
	}

	/**
	 * @param codigoFuncDist
	 */
	public void setCodigoFuncDist(String codigoFuncDist) {
		this.codigoFuncDist = codigoFuncDist;
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
	 * @return numPorcentaje
	 */
	public String getNumPorcentaje() {
		return numPorcentaje;
	}
	
	/**
	 * @param numPorcentaje
	 */
	public void setNumPorcentaje(String numPorcentaje) {
		this.numPorcentaje = numPorcentaje;
	}
	
	/**
	 * @return numUnidad
	 */
	public String getNumUnidad() {
		return numUnidad;
	}
	
	/**
	 * @param numUnidad
	 */
	public void setNumUnidad(String numUnidad) {
		this.numUnidad = numUnidad;
	}
	
	/**
	 * @return oidFuncDist
	 */
	public String getOidFuncDist() {
		return oidFuncDist;
	}
	
	/**
	 * @param oidFuncDist
	 */
	public void setOidFuncDist(String oidFuncDist) {
		this.oidFuncDist = oidFuncDist;
	}
	
	
}