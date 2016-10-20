package biz.belcorp.ssicc.dao.spusicc.lideres.model;

import java.io.Serializable;

public class FactorPuntaje implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4124638854259936413L;
	private String codConcurso;
	private String tipAsignacionPuntaje;
	private String codPeriodo;
	private String valorFactorMultiplicador;
	private String codPais;
	private int flag;
	
		/**
	 * @return Returns the codPeriodo.
	 */
	public String getCodPeriodo() {
		return codPeriodo;
	}
	/**
	 * @param codPeriodo The codPeriodo to set.
	 */
	public void setCodPeriodo(String codPeriodo) {
		this.codPeriodo = codPeriodo;
	}
	/**
	 * @return Returns the tipAsignacionPuntaje.
	 */
	public String getTipAsignacionPuntaje() {
		return tipAsignacionPuntaje;
	}
	/**
	 * @param tipAsignacionPuntaje The tipAsignacionPuntaje to set.
	 */
	public void setTipAsignacionPuntaje(String tipAsignacionPuntaje) {
		this.tipAsignacionPuntaje = tipAsignacionPuntaje;
	}
	/**
	 * @return Returns the valorFactorMultiplicador.
	 */
	public String getValorFactorMultiplicador() {
		return valorFactorMultiplicador;
	}
	/**
	 * @param valorFactorMultiplicador The valorFactorMultiplicador to set.
	 */
	public void setValorFactorMultiplicador(String valorFactorMultiplicador) {
		this.valorFactorMultiplicador = valorFactorMultiplicador;
	}
	/**
	 * @return Returns the codConcurso.
	 */
	public String getCodConcurso() {
		return codConcurso;
	}
	/**
	 * @param codConcurso The codConcurso to set.
	 */
	public void setCodConcurso(String codConcurso) {
		this.codConcurso = codConcurso;
	}
	/**
	 * @return Returns the codPais.
	 */
	public String getCodPais() {
		return codPais;
	}
	/**
	 * @param codPais The codPais to set.
	 */
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	/**
	 * @return Returns the flag.
	 */
	public int getFlag() {
		return flag;
	}
	/**
	 * @param flag The flag to set.
	 */
	public void setFlag(int flag) {
		this.flag = flag;
	}

}
