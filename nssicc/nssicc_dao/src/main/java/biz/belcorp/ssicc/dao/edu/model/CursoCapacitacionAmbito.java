package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

public class CursoCapacitacionAmbito extends AuditableBaseObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -70354094472877230L;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private int    correlativo;
	private String codigoAmbito;
	private String descripcionAmbito;
	private String codigoRegion;
	private String descripcionRegion;
	private String codigoZona;
	private String descripcionZona;
	private String codigoClasificacion;
	private String descripcionClasificacion;
	private String estadoRegistro;
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
	 * @return Returns the nombreCurso.
	 */
	public String getNombreCurso() {
		return nombreCurso;
	}
	/**
	 * @param nombreCurso The nombreCurso to set.
	 */
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
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
	 * @return Returns the codigoClasificacion.
	 */
	public String getCodigoClasificacion() {
		return codigoClasificacion;
	}
	/**
	 * @param codigoClasificacion The codigoClasificacion to set.
	 */
	public void setCodigoClasificacion(String codigoClasificacion) {
		this.codigoClasificacion = codigoClasificacion;
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
	 * @return Returns the descripcionAmbito.
	 */
	public String getDescripcionAmbito() {
		return descripcionAmbito;
	}
	/**
	 * @param descripcionAmbito The descripcionAmbito to set.
	 */
	public void setDescripcionAmbito(String descripcionAmbito) {
		this.descripcionAmbito = descripcionAmbito;
	}
	/**
	 * @return Returns the descripcionClasificacion.
	 */
	public String getDescripcionClasificacion() {
		return descripcionClasificacion;
	}
	/**
	 * @param descripcionClasificacion The descripcionClasificacion to set.
	 */
	public void setDescripcionClasificacion(String descripcionClasificacion) {
		this.descripcionClasificacion = descripcionClasificacion;
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
	 * @return Returns the correlativo.
	 */
	public int getCorrelativo() {
		return correlativo;
	}
	/**
	 * @param correlativo The correlativo to set.
	 */
	public void setCorrelativo(int correlativo) {
		this.correlativo = correlativo;
	}
	
}
