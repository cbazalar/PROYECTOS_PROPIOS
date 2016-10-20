package biz.belcorp.ssicc.dao.bas.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ParametroPais.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:yrivas@sigcomt.com">Yahir Rivas Luna</a>
 * 
 *                      
 */
public class BASParametroPais extends AuditableBaseObject implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
    private String codigoSistema;
    private String codigoParametro;
    private String nombreParametro;
    private String valorParametro;
    private String obsParametro;
    private String estado;
    /*Usuario eliminado*/
    private String deletedBy;
    /*Fecha eliminado*/
    private Timestamp deleted;
    


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
	 * @return the codigoSistema
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema the codigoSistema to set
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}

	/**
	 * @return the codigoParametro
	 */
	public String getCodigoParametro() {
		return codigoParametro;
	}

	/**
	 * @param codigoParametro the codigoParametro to set
	 */
	public void setCodigoParametro(String codigoParametro) {
		this.codigoParametro = codigoParametro;
	}

	/**
	 * @return the nombreParametro
	 */
	public String getNombreParametro() {
		return nombreParametro;
	}

	/**
	 * @param nombreParametro the nombreParametro to set
	 */
	public void setNombreParametro(String nombreParametro) {
		this.nombreParametro = nombreParametro;
	}

	/**
	 * @return the valorParametro
	 */
	public String getValorParametro() {
		return valorParametro;
	}

	/**
	 * @param valorParametro the valorParametro to set
	 */
	public void setValorParametro(String valorParametro) {
		this.valorParametro = valorParametro;
	}

	
	
	
	/**
	 * @return the obsParametro
	 */
	public String getObsParametro() {
		return obsParametro;
	}

	/**
	 * @param obsParametro the obsParametro to set
	 */
	public void setObsParametro(String obsParametro) {
		this.obsParametro = obsParametro;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
	

	/**
	 * @return the deletedBy
	 */
	public String getDeletedBy() {
		return deletedBy;
	}

	/**
	 * @param deletedBy the deletedBy to set
	 */
	public void setDeletedBy(String deletedBy) {
		this.deletedBy = deletedBy;
	}

	/**
	 * @return the deleted
	 */
	public Timestamp getDeleted() {
		return deleted;
	}

	/**
	 * @param deleted the deleted to set
	 */
	public void setDeleted(Timestamp deleted) {
		this.deleted = deleted;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	
}