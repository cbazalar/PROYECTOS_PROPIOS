package biz.belcorp.ssicc.dao.edu.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrvela
 *
 */
public class CalificacionAptaDemandaCampanha extends AuditableBaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3179371881500373563L;
	private String codigoPais;
	private String codigoEmpresa;
	private String nombreEmpresa;
	private String codigoCurso;
	private String nombreCurso;
	private String campanhaCalificacion;
	private String campanhaIngreso;
	
	/**
	 * @return Returns the campanhaIngreso.
	 */
	public String getCampanhaIngreso() {
		return campanhaIngreso;
	}
	/**
	 * @param campanhaIngreso The campanhaIngreso to set.
	 */
	public void setCampanhaIngreso(String campanhaIngreso) {
		this.campanhaIngreso = campanhaIngreso;
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
	 * @return Returns the campanhaCalificacion.
	 */
	public String getCampanhaCalificacion() {
		return campanhaCalificacion;
	}
	/**
	 * @param campanhaCalificacion The campanhaCalificacion to set.
	 */
	public void setCampanhaCalificacion(String campanhaCalificacion) {
		this.campanhaCalificacion = campanhaCalificacion;
	}

}
