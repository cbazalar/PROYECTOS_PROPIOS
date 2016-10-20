package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class RegionCursoCapacitacion extends AuditableBaseObject implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1527668244751768353L;
	private String codigoPais;
	private String codigoRegion;
	private String codigoAmbito;
	private String codigoRegionAnterior;
	private String descripcionRegion;
	private String estadoRegistro;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;	
	private String codigoInstructora;
	private String correoInstructora;
	private String descripcionInstructora;
	private String codigoZona;
	private String descripcionZona;
	//gerente regional
	private String nombreGerenteRegional;
	private String emailGerenteRegional;
	private String codigoGerenteRegional;	
	
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
	 * @return Returns the codigoRegionAnterior.
	 */
	public String getCodigoRegionAnterior() {
		return codigoRegionAnterior;
	}
	/**
	 * @param codigoRegionAnterior The codigoRegionAnterior to set.
	 */
	public void setCodigoRegionAnterior(String codigoRegionAnterior) {
		this.codigoRegionAnterior = codigoRegionAnterior;
	}
	/**
	 * @return Returns the descripcionRegion.
	 */
	public String getDescripcionRegion() {
		return descripcionRegion;
	}
	/**
	 * @param descripcionRegion The descripcionRegion to set.
	 */
	public void setDescripcionRegion(String descripcionRegion) {
		this.descripcionRegion = descripcionRegion;
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
	/**
	 * @return Returns the codigoInstructora.
	 */
	public String getCodigoInstructora() {
		return codigoInstructora;
	}
	/**
	 * @param codigoInstructora The codigoInstructora to set.
	 */
	public void setCodigoInstructora(String codigoInstructora) {
		this.codigoInstructora = codigoInstructora;
	}
	/**
	 * @return Returns the descripcionInstructora.
	 */
	public String getDescripcionInstructora() {
		return descripcionInstructora;
	}
	/**
	 * @param descripcionInstructora The descripcionInstructora to set.
	 */
	public void setDescripcionInstructora(String descripcionInstructora) {
		this.descripcionInstructora = descripcionInstructora;
	}
	/**
	 * @return Returns the nombreEmpresa.
	 */
	public String getNombreEmpresa() {
		return nombreEmpresa;
	}
	/**
	 * @param nombreEmpresa The nombreEmpresa to set.
	 */
	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
	}
	public String getCodigoZona() {
		return codigoZona;
	}
	public void setCodigoZona(String codigoZona) {
		this.codigoZona = codigoZona;
	}
	public String getDescripcionZona() {
		return descripcionZona;
	}
	public void setDescripcionZona(String descripcionZona) {
		this.descripcionZona = descripcionZona;
	}
	/**
	 * @return Returns the correoInstructora.
	 */
	public String getCorreoInstructora() {
		return correoInstructora;
	}
	/**
	 * @param correoInstructora The correoInstructora to set.
	 */
	public void setCorreoInstructora(String correoInstructora) {
		this.correoInstructora = correoInstructora;
	}
	/**
	 * @return Returns the nombreGerenteRegional.
	 */
	public String getNombreGerenteRegional() {
		return nombreGerenteRegional;
	}
	/**
	 * @param nombreGerenteRegional The nombreGerenteRegional to set.
	 */
	public void setNombreGerenteRegional(String nombreGerenteRegional) {
		this.nombreGerenteRegional = nombreGerenteRegional;
	}
	/**
	 * @return Returns the codigoGerenteRegional.
	 */
	public String getCodigoGerenteRegional() {
		return codigoGerenteRegional;
	}
	/**
	 * @param codigoGerenteRegional The codigoGerenteRegional to set.
	 */
	public void setCodigoGerenteRegional(String codigoGerenteRegional) {
		this.codigoGerenteRegional = codigoGerenteRegional;
	}
	/**
	 * @return Returns the emailGerenteRegional.
	 */
	public String getEmailGerenteRegional() {
		return emailGerenteRegional;
	}
	/**
	 * @param emailGerenteRegional The emailGerenteRegional to set.
	 */
	public void setEmailGerenteRegional(String emailGerenteRegional) {
		this.emailGerenteRegional = emailGerenteRegional;
	}
	
	

}
