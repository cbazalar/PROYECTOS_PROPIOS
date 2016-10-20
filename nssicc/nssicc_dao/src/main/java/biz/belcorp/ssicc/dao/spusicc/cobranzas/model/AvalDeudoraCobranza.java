package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author pejflorencio
 *
 */
public class AvalDeudoraCobranza extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String tipoReferencia;
	private String nombreReferencia;
	private String tipoDocumentoReferencia;
	private String numeroDocumentoReferencia;
	private String direccionReferencia;
	private String telefonoFijoReferencia;
	private String telefonoMovilReferencia;   	   
	
	
	/**
	 * @return the tipoReferencia
	 */
	public String getTipoReferencia() {
		return tipoReferencia;
	}

	/**
	 * @param tipoReferencia the tipoReferencia to set
	 */
	public void setTipoReferencia(String tipoReferencia) {
		this.tipoReferencia = tipoReferencia;
	}

	/**
	 * @return the nombreReferencia
	 */
	public String getNombreReferencia() {
		return nombreReferencia;
	}

	/**
	 * @param nombreReferencia the nombreReferencia to set
	 */
	public void setNombreReferencia(String nombreReferencia) {
		this.nombreReferencia = nombreReferencia;
	}

	/**
	 * @return the tipoDocumentoReferencia
	 */
	public String getTipoDocumentoReferencia() {
		return tipoDocumentoReferencia;
	}

	/**
	 * @param tipoDocumentoReferencia the tipoDocumentoReferencia to set
	 */
	public void setTipoDocumentoReferencia(String tipoDocumentoReferencia) {
		this.tipoDocumentoReferencia = tipoDocumentoReferencia;
	}

	/**
	 * @return the numeroDocumentoReferencia
	 */
	public String getNumeroDocumentoReferencia() {
		return numeroDocumentoReferencia;
	}

	/**
	 * @param numeroDocumentoReferencia the numeroDocumentoReferencia to set
	 */
	public void setNumeroDocumentoReferencia(String numeroDocumentoReferencia) {
		this.numeroDocumentoReferencia = numeroDocumentoReferencia;
	}

	/**
	 * @return the direccionReferencia
	 */
	public String getDireccionReferencia() {
		return direccionReferencia;
	}

	/**
	 * @param direccionReferencia the direccionReferencia to set
	 */
	public void setDireccionReferencia(String direccionReferencia) {
		this.direccionReferencia = direccionReferencia;
	}



	/**
	 * @return the telefonoFijoReferencia
	 */
	public String getTelefonoFijoReferencia() {
		return telefonoFijoReferencia;
	}

	/**
	 * @param telefonoFijoReferencia the telefonoFijoReferencia to set
	 */
	public void setTelefonoFijoReferencia(String telefonoFijoReferencia) {
		this.telefonoFijoReferencia = telefonoFijoReferencia;
	}



	/**
	 * @return the telefonoMovilReferencia
	 */
	public String getTelefonoMovilReferencia() {
		return telefonoMovilReferencia;
	}

	/**
	 * @param telefonoMovilReferencia the telefonoMovilReferencia to set
	 */
	public void setTelefonoMovilReferencia(String telefonoMovilReferencia) {
		this.telefonoMovilReferencia = telefonoMovilReferencia;
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

	    			
}
