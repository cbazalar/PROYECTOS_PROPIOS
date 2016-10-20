package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextdoliva
 *
 */
public class Concurso extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long oidConcurso;	
	private String numeroConcurso;
	private String nombreConcurso;
	private String periodoDesde;
	private String periodoHasta;
	
	private Integer numeroNivel;
	private Integer numeroNivelSelectivo;
	
	private Integer totalGeografico;
	private String codigoZona;
	
	private String tipoPremio;
	
	public Concurso() {
		
	}
	
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	public Long getOidConcurso() {
		return oidConcurso;
	}

	public void setOidConcurso(Long oidConcurso) {
		this.oidConcurso = oidConcurso;
	}

	public String getNumeroConcurso() {
		return numeroConcurso;
	}

	public void setNumeroConcurso(String numeroConcurso) {
		this.numeroConcurso = numeroConcurso;
	}

	public String getNombreConcurso() {
		return nombreConcurso;
	}

	public void setNombreConcurso(String nombreConcurso) {
		this.nombreConcurso = nombreConcurso;
	}

	public String getPeriodoDesde() {
		return periodoDesde;
	}

	public void setPeriodoDesde(String periodoDesde) {
		this.periodoDesde = periodoDesde;
	}

	public String getPeriodoHasta() {
		return periodoHasta;
	}

	public void setPeriodoHasta(String periodoHasta) {
		this.periodoHasta = periodoHasta;
	}

	/**
	 * @return the numeroNivel
	 */
	public Integer getNumeroNivel() {
		return numeroNivel;
	}

	/**
	 * @param numeroNivel the numeroNivel to set
	 */
	public void setNumeroNivel(Integer numeroNivel) {
		this.numeroNivel = numeroNivel;
	}

	/**
	 * @return the totalGeografico
	 */
	public Integer getTotalGeografico() {
		return totalGeografico;
	}

	/**
	 * @param totalGeografico the totalGeografico to set
	 */
	public void setTotalGeografico(Integer totalGeografico) {
		this.totalGeografico = totalGeografico;
	}

	/**
	 * @return the codigoZona
	 */
	public String getCodigoZona() {
		return codigoZona;
	}

	/**
	 * @param codigoZona the codigoZona to set
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}

	/**
	 * @return the numeroNivelSelectivo
	 */
	public Integer getNumeroNivelSelectivo() {
		return numeroNivelSelectivo;
	}

	/**
	 * @param numeroNivelSelectivo the numeroNivelSelectivo to set
	 */
	public void setNumeroNivelSelectivo(Integer numeroNivelSelectivo) {
		this.numeroNivelSelectivo = numeroNivelSelectivo;
	}

	/**
	 * @return the tipoPremio
	 */
	public String getTipoPremio() {
		return tipoPremio;
	}

	/**
	 * @param tipoPremio the tipoPremio to set
	 */
	public void setTipoPremio(String tipoPremio) {
		this.tipoPremio = tipoPremio;
	}

	
}
