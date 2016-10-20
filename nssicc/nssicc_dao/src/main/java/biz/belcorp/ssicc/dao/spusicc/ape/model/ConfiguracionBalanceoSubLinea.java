package biz.belcorp.ssicc.dao.spusicc.ape.model;

import java.io.Serializable;

/**
 * @author Nicols Lpez
 */

public class ConfiguracionBalanceoSubLinea implements Serializable{

	private static final long serialVersionUID = 1L;

	private String oidCentro;
	
	private String desCentroDist;
	
	private String oidSubLinea;
	
	private String oidLinArmado;
	
	private String descLineaArmado;
	
	private String descFunDistLinea;
	
	private String oidFunDistLinea;
	
	private String descSubLinea;
	
	private String varPorcentaje;
	
	private String nroUnidadPreAsigna;
	
	private String maxAnaquelProd;
	
	private String oidFuncDist;
	
	private String descFunDistSubLinea;
	
	private String codFuncDist;
	
	private String codigoFuncion;

	/**
	 * @return codigoFuncion
	 */
	public String getCodigoFuncion() {
		return codigoFuncion;
	}

	/**
	 * @param codigoFuncion
	 */
	public void setCodigoFuncion(String codigoFuncion) {
		this.codigoFuncion = codigoFuncion;
	}

	/**
	 * @return codFuncDist
	 */
	public String getCodFuncDist() {
		return codFuncDist;
	}

	/**
	 * @param codFuncDist
	 */
	public void setCodFuncDist(String codFuncDist) {
		this.codFuncDist = codFuncDist;
	}

	/**
	 * @return oidLinArmado
	 */
	public String getOidLinArmado() {
		return oidLinArmado;
	}

	/**
	 * @param oidLinArmado
	 */
	public void setOidLinArmado(String oidLinArmado) {
		this.oidLinArmado = oidLinArmado;
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
	 * @return getDesCentroDist
	 */
	public String getDesCentroDist() {
		return desCentroDist;
	}
	
	/**
	 * @param desCentroDist
	 */
	public void setDesCentroDist(String desCentroDist) {
		this.desCentroDist = desCentroDist;
	}
	
	/**
	 * @return oidSubLinea
	 */
	public String getOidSubLinea() {
		return oidSubLinea;
	}
	
	/**
	 * @param oidSubLinea
	 */
	public void setOidSubLinea(String oidSubLinea) {
		this.oidSubLinea = oidSubLinea;
	}
	
	/**
	 * @return descLineaArmado
	 */
	public String getDescLineaArmado() {
		return descLineaArmado;
	}
	
	/**
	 * @param descLineaArmado
	 */
	public void setDescLineaArmado(String descLineaArmado) {
		this.descLineaArmado = descLineaArmado;
	}
	
	/**
	 * @return descFunDistLinea
	 */
	public String getDescFunDistLinea() {
		return descFunDistLinea;
	}
	
	/**
	 * @param descFunDistLinea
	 */
	public void setDescFunDistLinea(String descFunDistLinea) {
		this.descFunDistLinea = descFunDistLinea;
	}
	
	/**
	 * @return oidFunDistLinea
	 */
	public String getOidFunDistLinea() {
		return oidFunDistLinea;
	}
	
	/**
	 * @param oidFunDistLinea
	 */
	public void setOidFunDistLinea(String oidFunDistLinea) {
		this.oidFunDistLinea = oidFunDistLinea;
	}
	
	/**
	 * @return descSubLinea
	 */
	public String getDescSubLinea() {
		return descSubLinea;
	}
	
	/**
	 * @param descSubLinea
	 */
	public void setDescSubLinea(String descSubLinea) {
		this.descSubLinea = descSubLinea;
	}
	
	/**
	 * @return varPorcentaje
	 */
	public String getVarPorcentaje() {
		return varPorcentaje;
	}
	
	/**
	 * @param varPorcentaje
	 */
	public void setVarPorcentaje(String varPorcentaje) {
		this.varPorcentaje = varPorcentaje;
	}
	
	/**
	 * @return nroUnidadPreAsigna
	 */
	public String getNroUnidadPreAsigna() {
		return nroUnidadPreAsigna;
	}
	
	/**
	 * @param nroUnidadPreAsigna
	 */
	public void setNroUnidadPreAsigna(String nroUnidadPreAsigna) {
		this.nroUnidadPreAsigna = nroUnidadPreAsigna;
	}
	
	/**
	 * @return maxAnaquelProd
	 */
	public String getMaxAnaquelProd() {
		return maxAnaquelProd;
	}
	
	/**
	 * @param maxAnaquelProd
	 */
	public void setMaxAnaquelProd(String maxAnaquelProd) {
		this.maxAnaquelProd = maxAnaquelProd;
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
	
	/**
	 * @return descFunDistSubLinea
	 */
	public String getDescFunDistSubLinea() {
		return descFunDistSubLinea;
	}
	
	/**
	 * @param descFunDistSubLinea
	 */
	public void setDescFunDistSubLinea(String descFunDistSubLinea) {
		this.descFunDistSubLinea = descFunDistSubLinea;
	}
	
}