package biz.belcorp.ssicc.dao.spusicc.mae.model;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;


public class DireccionTelefonoMasivo  extends AuditableBaseObject {
	
	private String fila;
	private String codigoConsultora;	
	private String motivoRechazo;   
	private String direccionDomicilio ;
	private String direccionEntrega ;
	private String direccionVacaciones ;
	private String referenciaDomicilio ;
	private String referenciaEntrega ;
	private String referenciaVacaciones ;
	private String telefonoCasa ;
	private String telefonoCelular ;
	private String telefonoTrabajo ;
	private String email ;
	private String indicador;
	
	public DireccionTelefonoMasivo() {
		// TODO Auto-generated constructor stub
	}
	
	public DireccionTelefonoMasivo(String fila, String codigoConsultora, String motivoRechazo,
			String direccionDomicilio, String direccionEntrega, String direccionVacaciones,
			 String referenciaDomicilio ,String referenciaEntrega , String referenciaVacaciones ,
			 String telefonoCasa , String telefonoCelular ,	 String telefonoTrabajo , String email ,			
			String indicador) {
		super();
		this.fila = fila;
		this.codigoConsultora = codigoConsultora;
		if(indicador.equals(Constants.NUMERO_UNO)){
			this.direccionDomicilio = direccionDomicilio;
			this.direccionEntrega = direccionEntrega;
			this.direccionVacaciones = direccionVacaciones;
			this.referenciaDomicilio = referenciaDomicilio;
			this.referenciaEntrega = referenciaEntrega;
			this.referenciaVacaciones = direccionEntrega;
		
		}else{
			this.telefonoCasa = telefonoCasa;
			this.telefonoCelular = telefonoCelular;
			this.telefonoTrabajo = telefonoTrabajo;
			this.email = email;
		}
		this.indicador = indicador;
		this.motivoRechazo = motivoRechazo;
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
				+ ((motivoRechazo == null) ? 0 : motivoRechazo.hashCode());
		result = prime * result 
				+ ((fila == null) ? 0 : fila.hashCode());
		result = prime * result
				+ ((direccionDomicilio == null) ? 0 : direccionDomicilio.hashCode());
		result = prime * result
				+ ((direccionEntrega == null) ? 0 : direccionEntrega.hashCode());
		result = prime * result
				+ ((direccionVacaciones == null) ? 0 : direccionVacaciones.hashCode());
		result = prime * result
				+ ((referenciaDomicilio == null) ? 0 : referenciaDomicilio.hashCode());
		result = prime * result 
				+ ((referenciaEntrega == null) ? 0 : referenciaEntrega.hashCode());
		result = prime * result
				+ ((referenciaVacaciones == null) ? 0 : referenciaVacaciones.hashCode());
		result = prime * result
				+ ((telefonoCasa == null) ? 0 : telefonoCasa.hashCode());
		result = prime * result
				+ ((telefonoCelular == null) ? 0 : telefonoCelular.hashCode());
		result = prime * result
				+ ((telefonoTrabajo == null) ? 0 : telefonoTrabajo.hashCode());
		result = prime * result
				+ ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((indicador == null) ? 0 : indicador.hashCode());
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
		DireccionTelefonoMasivo other = (DireccionTelefonoMasivo) obj;
		if (codigoConsultora == null) {
			if (other.codigoConsultora != null)
				return false;
		} else if (!codigoConsultora.equals(other.codigoConsultora))
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
			
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ConsultoraMasivo [fila=" + fila + ", codigoConsultora="
				+ codigoConsultora + ", motivoRechazo=" + motivoRechazo
				+ ", direccionDomicilio=" + direccionDomicilio + ", direccionEntrega=" + direccionEntrega
				+ ", direccionVacaciones=" + direccionVacaciones 
				+ ", referenciaDomicilio=" + referenciaDomicilio + ", referenciaEntrega=" + referenciaEntrega
				+ ", referenciaVacaciones=" + referenciaVacaciones 
				+ ", telefonoCasa=" + telefonoCasa + ", telefonoCelular=" + telefonoCelular
				+ ", telefonoTrabajo=" + telefonoTrabajo 
				+ ", email=" + email 
				+ ", indicador="
				+ indicador + "]";
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
	 * @return the direccionDomicilio
	 */
	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}
	/**
	 * @param direccionDomicilio the direccionDomicilio to set
	 */
	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}
	/**
	 * @return the direccionEntrega
	 */
	public String getDireccionEntrega() {
		return direccionEntrega;
	}
	/**
	 * @param direccionEntrega the direccionEntrega to set
	 */
	public void setDireccionEntrega(String direccionEntrega) {
		this.direccionEntrega = direccionEntrega;
	}
	/**
	 * @return the direccionVacaciones
	 */
	public String getDireccionVacaciones() {
		return direccionVacaciones;
	}
	/**
	 * @param direccionVacaciones the direccionVacaciones to set
	 */
	public void setDireccionVacaciones(String direccionVacaciones) {
		this.direccionVacaciones = direccionVacaciones;
	}
	/**
	 * @return the referenciaDomicilio
	 */
	public String getReferenciaDomicilio() {
		return referenciaDomicilio;
	}
	/**
	 * @param referenciaDomicilio the referenciaDomicilio to set
	 */
	public void setReferenciaDomicilio(String referenciaDomicilio) {
		this.referenciaDomicilio = referenciaDomicilio;
	}
	/**
	 * @return the referenciaEntrega
	 */
	public String getReferenciaEntrega() {
		return referenciaEntrega;
	}
	/**
	 * @param referenciaEntrega the referenciaEntrega to set
	 */
	public void setReferenciaEntrega(String referenciaEntrega) {
		this.referenciaEntrega = referenciaEntrega;
	}
	/**
	 * @return the referenciaVacaciones
	 */
	public String getReferenciaVacaciones() {
		return referenciaVacaciones;
	}
	/**
	 * @param referenciaVacaciones the referenciaVacaciones to set
	 */
	public void setReferenciaVacaciones(String referenciaVacaciones) {
		this.referenciaVacaciones = referenciaVacaciones;
	}
	/**
	 * @return the telefonoCasa
	 */
	public String getTelefonoCasa() {
		return telefonoCasa;
	}
	/**
	 * @param telefonoCasa the telefonoCasa to set
	 */
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	/**
	 * @return the telefonoCelular
	 */
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	/**
	 * @param telefonoCelular the telefonoCelular to set
	 */
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	/**
	 * @return the telefonoTrabajo
	 */
	public String getTelefonoTrabajo() {
		return telefonoTrabajo;
	}
	/**
	 * @param telefonoTrabajo the telefonoTrabajo to set
	 */
	public void setTelefonoTrabajo(String telefonoTrabajo) {
		this.telefonoTrabajo = telefonoTrabajo;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
