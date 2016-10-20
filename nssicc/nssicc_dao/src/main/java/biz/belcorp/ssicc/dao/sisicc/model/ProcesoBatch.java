/*
 * Created on 19-oct-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 *  
 * <p>
 * <a href="Interfaz.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar</a>
 * 
 */
public class ProcesoBatch extends AuditableBaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7712236855560840782L;

	private String codigoPais;

	private String codigoSistema;

	private String codigoProcesoBatch;
	
	private String descripcionProcesoBatch;
	
	private String codigoInterfaz;

	private String estadoProceso;

	public ProcesoBatch() {
		codigoPais = "";
		codigoSistema = "";
		codigoProcesoBatch = "";
		descripcionProcesoBatch = "";
		codigoInterfaz = "";
		estadoProceso  = "";
	}

	
	/**
	 * @return Returns the codigoPais.
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais
	 *            The codigoPais to set.
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return Returns the codigoSistema.
	 */
	public String getCodigoSistema() {
		return codigoSistema;
	}

	/**
	 * @param codigoSistema
	 *            The codigoSistema to set.
	 */
	public void setCodigoSistema(String codigoSistema) {
		this.codigoSistema = codigoSistema;
	}
	
	/**
	 * @return Returns the codigoInterfaz.
	 */
	public String getCodigoInterfaz() {
		return codigoInterfaz;
	}


	/**
	 * @param codigoInterfaz The codigoInterfaz to set.
	 */
	public void setCodigoInterfaz(String codigoInterfaz) {
		this.codigoInterfaz = codigoInterfaz;
	}


	/**
	 * @return Returns the descripcionProcesoBatch.
	 */
	public String getDescripcionProcesoBatch() {
		return descripcionProcesoBatch;
	}


	/**
	 * @param descripcionProcesoBatch The descripcionProcesoBatch to set.
	 */
	public void setDescripcionProcesoBatch(String descripcionProcesoBatch) {
		this.descripcionProcesoBatch = descripcionProcesoBatch;
	}


	/**
	 * @return Returns the estadoProceso.
	 */
	public String getEstadoProceso() {
		return estadoProceso;
	}


	/**
	 * @param estadoProceso The estadoProceso to set.
	 */
	public void setEstadoProceso(String estadoProceso) {
		this.estadoProceso = estadoProceso;
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


	/**
	 * @return Returns the codigoProcesoBatch.
	 */
	public String getCodigoProcesoBatch() {
		return codigoProcesoBatch;
	}


	/**
	 * @param codigoProcesoBatch The codigoProcesoBatch to set.
	 */
	public void setCodigoProcesoBatch(String codigoProcesoBatch) {
		this.codigoProcesoBatch = codigoProcesoBatch;
	}
	
	

}
