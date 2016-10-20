package biz.belcorp.ssicc.dao.spusicc.cobranzas.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author pejflorencio
 *
 */
public class ReferenciaDeudoraCobranza extends AuditableBaseObject implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String tipoReferencia;
	private String nombreReferencia;
	private String direccionReferencia;
	private String telefonoFijoReferencia;
	private String telefonoMovilReferencia;   	   
	private String telefonoTrabajoReferencia;
	
	
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

	public void setTelefonoTrabajoReferencia(String telefonoTrabajoReferencia) {
		this.telefonoTrabajoReferencia = telefonoTrabajoReferencia;
	}

	public String getTelefonoTrabajoReferencia() {
		return telefonoTrabajoReferencia;
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
