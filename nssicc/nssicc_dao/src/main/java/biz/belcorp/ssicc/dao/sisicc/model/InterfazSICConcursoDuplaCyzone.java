/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Carla Marius
 * 
 */
public class InterfazSICConcursoDuplaCyzone extends
		AuditableBaseObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7476168237542153875L;
	private String idConcursoDuplaCyzone;
	private String codigoConcurso;
	private String indicadorDupla;
	private String nombreConcurso;
	private String idConcurso;


	/**
	 * @return Returns the codigoConcurso.
	 */
	public String getCodigoConcurso() {
		return codigoConcurso;
	}

	/**
	 * @param codigoConcurso The codigoConcurso to set.
	 */
	public void setCodigoConcurso(String codigoConcurso) {
		this.codigoConcurso = codigoConcurso;
	}

	/**
	 * @return Returns the indicadorDupla.
	 */
	public String getIndicadorDupla() {
		return indicadorDupla;
	}

	/**
	 * @param indicadorDupla The indicadorDupla to set.
	 */
	public void setIndicadorDupla(String indicadorDupla) {
		this.indicadorDupla = indicadorDupla;
	}

	/**
	 * @return Returns the nombreConcurso.
	 */
	public String getNombreConcurso() {
		return nombreConcurso;
	}

	/**
	 * @param nombreConcurso The nombreConcurso to set.
	 */
	public void setNombreConcurso(String nombreConcurso) {
		this.nombreConcurso = nombreConcurso;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @return Returns the idConcursoDuplaCyzone.
	 */
	public String getIdConcursoDuplaCyzone() {
		return idConcursoDuplaCyzone;
	}

	/**
	 * @param idConcursoDuplaCyzone The idConcursoDuplaCyzone to set.
	 */
	public void setIdConcursoDuplaCyzone(String idConcursoDuplaCyzone) {
		this.idConcursoDuplaCyzone = idConcursoDuplaCyzone;
	}

	/**
	 * @return Returns the idConcurso.
	 */
	public String getIdConcurso() {
		return idConcurso;
	}

	/**
	 * @param idConcurso The idConcurso to set.
	 */
	public void setIdConcurso(String idConcurso) {
		this.idConcurso = idConcurso;
	}

}
