/**
 * 
 */
package biz.belcorp.ssicc.dao.spncd.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author peextrramirez - Rosalvina Ramirez Guardia
 *
 */
public class SuscripcionNivelConsultora extends AuditableBaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String codigoPais;
	
	private String codigoPrograma;
	
	private String codigoConsultora;
	
	private String codigoNivel;
	
	private String campanhaInicio;
	
	private String campanhaFin;

	private String codigoUsuario;
	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the codigoPrograma
	 */
	public String getCodigoPrograma() {
		return codigoPrograma;
	}

	/**
	 * @param codigoPrograma the codigoPrograma to set
	 */
	public void setCodigoPrograma(String codigoPrograma) {
		this.codigoPrograma = codigoPrograma;
	}

	/**
	 * @return the codigoConsultora
	 */
	public String getCodigoConsultora() {
		return codigoConsultora;
	}

	/**
	 * @param codigoConsultora the codigoConsultora to set
	 */
	public void setCodigoConsultora(String codigoConsultora) {
		this.codigoConsultora = codigoConsultora;
	}

	/**
	 * @return the codigoNivel
	 */
	public String getCodigoNivel() {
		return codigoNivel;
	}

	/**
	 * @param codigoNivel the codigoNivel to set
	 */
	public void setCodigoNivel(String codigoNivel) {
		this.codigoNivel = codigoNivel;
	}

	/**
	 * @return the campanhaInicio
	 */
	public String getCampanhaInicio() {
		return campanhaInicio;
	}

	/**
	 * @param campanhaInicio the campanhaInicio to set
	 */
	public void setCampanhaInicio(String campanhaInicio) {
		this.campanhaInicio = campanhaInicio;
	}

	/**
	 * @return the campanhaFin
	 */
	public String getCampanhaFin() {
		return campanhaFin;
	}

	/**
	 * @param campanhaFin the campanhaFin to set
	 */
	public void setCampanhaFin(String campanhaFin) {
		this.campanhaFin = campanhaFin;
	}

	/**
	 * @return the codigoUsuario
	 */
	public String getCodigoUsuario() {
		return codigoUsuario;
	}

	/**
	 * @param codigoUsuario the codigoUsuario to set
	 */
	public void setCodigoUsuario(String codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
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
