/*
 * Created on 07/05/2007 11:18:28 AM
 * biz.belcorp.ssicc.sisicc.web.ws.impl.InterfazResultado
 */
package biz.belcorp.ssicc.dao.spusicc.mae.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * <p>
 * <a href="SegmentoGrupoLove.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 * 
 * 
 */
public class SegmentoGrupoLove extends AuditableBaseObject implements Serializable {

 
	private static final long serialVersionUID = 1L;
	private Long secuenciaSegmento;
	private String codigoSegmento;    
    private String descripcionSegmento;
    private String codigoGrupo;
    private String descripcionGrupo;
    
	/**
	 * @return the secuenciaSegmento
	 */
	public Long getSecuenciaSegmento() {
		return secuenciaSegmento;
	}
	/**
	 * @param secuenciaSegmento the secuenciaSegmento to set
	 */
	public void setSecuenciaSegmento(Long secuenciaSegmento) {
		this.secuenciaSegmento = secuenciaSegmento;
	}
	/**
	 * @return the codigoSegmento
	 */
	public String getCodigoSegmento() {
		return codigoSegmento;
	}
	/**
	 * @param codigoSegmento the codigoSegmento to set
	 */
	public void setCodigoSegmento(String codigoSegmento) {
		this.codigoSegmento = codigoSegmento;
	}
	/**
	 * @return the descripcionSegmento
	 */
	public String getDescripcionSegmento() {
		return descripcionSegmento;
	}
	/**
	 * @param descripcionSegmento the descripcionSegmento to set
	 */
	public void setDescripcionSegmento(String descripcionSegmento) {
		this.descripcionSegmento = descripcionSegmento;
	}
	/**
	 * @return the codigoGrupo
	 */
	public String getCodigoGrupo() {
		return codigoGrupo;
	}
	/**
	 * @param codigoGrupo the codigoGrupo to set
	 */
	public void setCodigoGrupo(String codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}
	/**
	 * @return the descripcionGrupo
	 */
	public String getDescripcionGrupo() {
		return descripcionGrupo;
	}
	/**
	 * @param descripcionGrupo the descripcionGrupo to set
	 */
	public void setDescripcionGrupo(String descripcionGrupo) {
		this.descripcionGrupo = descripcionGrupo;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		return false;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#hashCode()
	 */
	@Override
	public int hashCode() {
		return 0;
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.model.BaseObject#toString()
	 */
	@Override
	public String toString() {
		return null;
	}
	
}
