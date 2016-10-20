package biz.belcorp.ssicc.dao.spusicc.comision.model;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Aurelio Oviedo
 *
 */
public class ConsultoraMasivo extends AuditableBaseObject {

	private String fila;
	private String codigoConsultora;
	private String nombreCompleto;
	private String codigoPrograma;
	private String codigoMotivo;
	private String motivoRechazo;
	private String indicador;
	
	public ConsultoraMasivo() {
		// TODO Auto-generated constructor stub
	}
	
	public ConsultoraMasivo(String fila, String codigoConsultora, String nombreCompleto,
			String programa, String codigoMotivo, String motivoRechazo, String indicador) {
		super();
		this.fila = fila;
		this.codigoConsultora = codigoConsultora;
		if(indicador.equals(Constants.NUMERO_UNO)){
			this.nombreCompleto = nombreCompleto;
			this.codigoPrograma = programa;
		}else{
			this.codigoMotivo = codigoMotivo;
			this.motivoRechazo = motivoRechazo;
		}
		this.indicador = indicador;
	}

	/**
	 * @return the fila
	 */
	public String getFila() {
		return fila;
	}

	/**
	 * @param fila the fila to set
	 */
	public void setFila(String fila) {
		this.fila = fila;
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
	 * @return the nombreCompleto
	 */
	public String getNombreCompleto() {
		return nombreCompleto;
	}

	/**
	 * @param nombreCompleto the nombreCompleto to set
	 */
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
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
	 * @return the codigoMotivo
	 */
	public String getCodigoMotivo() {
		return codigoMotivo;
	}

	/**
	 * @param codigoMotivo the codigoMotivo to set
	 */
	public void setCodigoMotivo(String codigoMotivo) {
		this.codigoMotivo = codigoMotivo;
	}

	/**
	 * @return the motivoRechazo
	 */
	public String getMotivoRechazo() {
		return motivoRechazo;
	}

	/**
	 * @param motivoRechazo the motivoRechazo to set
	 */
	public void setMotivoRechazo(String motivoRechazo) {
		this.motivoRechazo = motivoRechazo;
	}

	/**
	 * @return the indicador
	 */
	public String getIndicador() {
		return indicador;
	}

	/**
	 * @param indicador the indicador to set
	 */
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((codigoConsultora == null) ? 0 : codigoConsultora.hashCode());
		result = prime * result
				+ ((codigoMotivo == null) ? 0 : codigoMotivo.hashCode());
		result = prime * result + ((fila == null) ? 0 : fila.hashCode());
		result = prime * result
				+ ((indicador == null) ? 0 : indicador.hashCode());
		result = prime * result
				+ ((motivoRechazo == null) ? 0 : motivoRechazo.hashCode());
		result = prime * result
				+ ((nombreCompleto == null) ? 0 : nombreCompleto.hashCode());
		result = prime * result
				+ ((codigoPrograma == null) ? 0 : codigoPrograma.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConsultoraMasivo other = (ConsultoraMasivo) obj;
		if (codigoConsultora == null) {
			if (other.codigoConsultora != null)
				return false;
		} else if (!codigoConsultora.equals(other.codigoConsultora))
			return false;
		if (codigoMotivo == null) {
			if (other.codigoMotivo != null)
				return false;
		} else if (!codigoMotivo.equals(other.codigoMotivo))
			return false;
		if (fila == null) {
			if (other.fila != null)
				return false;
		} else if (!fila.equals(other.fila))
			return false;
		if (indicador == null) {
			if (other.indicador != null)
				return false;
		} else if (!indicador.equals(other.indicador))
			return false;
		if (motivoRechazo == null) {
			if (other.motivoRechazo != null)
				return false;
		} else if (!motivoRechazo.equals(other.motivoRechazo))
			return false;
		if (nombreCompleto == null) {
			if (other.nombreCompleto != null)
				return false;
		} else if (!nombreCompleto.equals(other.nombreCompleto))
			return false;
		if (codigoPrograma == null) {
			if (other.codigoPrograma != null)
				return false;
		} else if (!codigoPrograma.equals(other.codigoPrograma))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ConsultoraMasivo [fila=" + fila + ", codigoConsultora="
				+ codigoConsultora + ", nombreCompleto=" + nombreCompleto
				+ ", programa=" + codigoPrograma + ", codigoMotivo=" + codigoMotivo
				+ ", motivoRechazo=" + motivoRechazo + ", indicador="
				+ indicador + "]";
	}
}