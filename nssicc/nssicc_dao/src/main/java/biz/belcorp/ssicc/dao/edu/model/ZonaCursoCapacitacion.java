package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.BaseObject;

public class ZonaCursoCapacitacion extends BaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3680185136024050420L;
	private String codigoPais;
	private String codigoRegion;
	private String codigoZona;
	private String codigoZonaAnterior;
	private String descripcionZona;
	private String estadoRegistro;
	private String codigoEmpresa;
	private String codigoCurso;	
	private String codigoAmbito;
	private String descripcionRegion;
	private String descripcionEmpresa;
	private String correoGerenteZona;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}
	/**
	 * @param codigoPais The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}
	/**
	 * @return Returns the codigoRegion.
	 */
	public String getCodigoRegion() {
		return codigoRegion;
	}
	/**
	 * @param codigoRegion The codigoRegion to set.
	 */
	public void setCodigoRegion(String codigoRegion) {
		this.codigoRegion = codigoRegion;
	}
	/**
	 * @return Returns the estadoRegistro.
	 */
	public String getEstadoRegistro() {
		return estadoRegistro;
	}
	/**
	 * @param estadoRegistro The estadoRegistro to set.
	 */
	public void setEstadoRegistro(String estadoRegistro) {
		this.estadoRegistro = estadoRegistro;
	}
	/**
	 * @return Returns the codigoZona.
	 */
	public String getCodigoZona() {
		return codigoZona;
	}
	/**
	 * @param codigoZona The codigoZona to set.
	 */
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	/**
	 * @return Returns the codigoZonaAnterior.
	 */
	public String getCodigoZonaAnterior() {
		return codigoZonaAnterior;
	}
	/**
	 * @param codigoZonaAnterior The codigoZonaAnterior to set.
	 */
	public void setCodigoZonaAnterior(String codigoZonaAnterior) {
		this.codigoZonaAnterior = codigoZonaAnterior;
	}
	/**
	 * @return Returns the descripcionZona.
	 */
	public String getDescripcionZona() {
		return descripcionZona;
	}
	/**
	 * @param descripcionZona The descripcionZona to set.
	 */
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	/**
	 * @return Returns the codigoCurso.
	 */
	public String getCodigoCurso() {
		return codigoCurso;
	}
	/**
	 * @param codigoCurso The codigoCurso to set.
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	/**
	 * @return Returns the codigoEmpresa.
	 */
	public String getCodigoEmpresa() {
		return codigoEmpresa;
	}
	/**
	 * @param codigoEmpresa The codigoEmpresa to set.
	 */
	public void setCodigoEmpresa(String codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}
	/**
	 * @return Returns the codigoAmbito.
	 */
	public String getCodigoAmbito() {
		return codigoAmbito;
	}
	/**
	 * @param codigoAmbito The codigoAmbito to set.
	 */
	public void setCodigoAmbito(String codigoAmbito) {
		this.codigoAmbito = codigoAmbito;
	}
	public String getDescripcionEmpresa() {
		return descripcionEmpresa;
	}
	public void setDescripcionEmpresa(String descripcionEmpresa) {
		this.descripcionEmpresa = descripcionEmpresa;
	}
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
	}
	/**
	 * @return Returns the correoGerenteZona.
	 */
	public String getCorreoGerenteZona() {
		return correoGerenteZona;
	}
	/**
	 * @param correoGerenteZona The correoGerenteZona to set.
	 */
	public void setCorreoGerenteZona(String correoGerenteZona) {
		this.correoGerenteZona = correoGerenteZona;
	}
	
	
	
}
