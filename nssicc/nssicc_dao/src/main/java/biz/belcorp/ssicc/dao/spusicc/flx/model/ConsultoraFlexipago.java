/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.flx.model;

import java.io.Serializable;

import biz.belcorp.ssicc.dao.framework.model.AuditableBaseObject;

/**
 * @author Ivan Tocto Jaimes
 *
 */
public class ConsultoraFlexipago extends AuditableBaseObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2779354930651055076L;
	private String codigoPais;
	private String codigoCliente;
	private String codigoCampanyaFacturacion;
	private String nombreConsultora;
	private String lineaCredito;
	private String pedidoBase;
	private String campanyaComunicacion;
	private String calificacionExperiencia;
	private String calificacionComportamiento;
	private String flagHabilitado;
	private String flagActivo;
	private String flagComunicado;
	private String flagEnviado;
	private String flagCancelado;
	
	private String codigoMotivo;
	private String numeroContrato;
	
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
	 * @return the codigoCliente
	 */
	public String getCodigoCliente() {
		return codigoCliente;
	}
	/**
	 * @param codigoCliente the codigoCliente to set
	 */
	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}
	/**
	 * @return the codigoCampanyaFacturacion
	 */
	public String getCodigoCampanyaFacturacion() {
		return codigoCampanyaFacturacion;
	}
	/**
	 * @param codigoCampanyaFacturacion the codigoCampanyaFacturacion to set
	 */
	public void setCodigoCampanyaFacturacion(String codigoCampanyaFacturacion) {
		this.codigoCampanyaFacturacion = codigoCampanyaFacturacion;
	}
	/**
	 * @return the nombreConsultora
	 */
	public String getNombreConsultora() {
		return nombreConsultora;
	}
	/**
	 * @param nombreConsultora the nombreConsultora to set
	 */
	public void setNombreConsultora(String nombreConsultora) {
		this.nombreConsultora = nombreConsultora;
	}
	/**
	 * @return the lineaCredito
	 */
	public String getLineaCredito() {
		return lineaCredito;
	}
	/**
	 * @param lineaCredito the lineaCredito to set
	 */
	public void setLineaCredito(String lineaCredito) {
		this.lineaCredito = lineaCredito;
	}
	/**
	 * @return the pedidoBase
	 */
	public String getPedidoBase() {
		return pedidoBase;
	}
	/**
	 * @param pedidoBase the pedidoBase to set
	 */
	public void setPedidoBase(String pedidoBase) {
		this.pedidoBase = pedidoBase;
	}
	/**
	 * @return the campanyaComunicacion
	 */
	public String getCampanyaComunicacion() {
		return campanyaComunicacion;
	}
	/**
	 * @param campanyaComunicacion the campanyaComunicacion to set
	 */
	public void setCampanyaComunicacion(String campanyaComunicacion) {
		this.campanyaComunicacion = campanyaComunicacion;
	}
	/**
	 * @return the calificacionExperiencia
	 */
	public String getCalificacionExperiencia() {
		return calificacionExperiencia;
	}
	/**
	 * @param calificacionExperiencia the calificacionExperiencia to set
	 */
	public void setCalificacionExperiencia(String calificacionExperiencia) {
		this.calificacionExperiencia = calificacionExperiencia;
	}
	/**
	 * @return the calificacionComportamiento
	 */
	public String getCalificacionComportamiento() {
		return calificacionComportamiento;
	}
	/**
	 * @param calificacionComportamiento the calificacionComportamiento to set
	 */
	public void setCalificacionComportamiento(String calificacionComportamiento) {
		this.calificacionComportamiento = calificacionComportamiento;
	}
	/**
	 * @return the flagHabilitado
	 */
	public String getFlagHabilitado() {
		return flagHabilitado;
	}
	/**
	 * @param flagHabilitado the flagHabilitado to set
	 */
	public void setFlagHabilitado(String flagHabilitado) {
		this.flagHabilitado = flagHabilitado;
	}
	/**
	 * @return the flagActivo
	 */
	public String getFlagActivo() {
		return flagActivo;
	}
	/**
	 * @param flagActivo the flagActivo to set
	 */
	public void setFlagActivo(String flagActivo) {
		this.flagActivo = flagActivo;
	}
	/**
	 * @return the flagComunicado
	 */
	public String getFlagComunicado() {
		return flagComunicado;
	}
	/**
	 * @param flagComunicado the flagComunicado to set
	 */
	public void setFlagComunicado(String flagComunicado) {
		this.flagComunicado = flagComunicado;
	}
	/**
	 * @return the flagEnviado
	 */
	public String getFlagEnviado() {
		return flagEnviado;
	}
	/**
	 * @param flagEnviado the flagEnviado to set
	 */
	public void setFlagEnviado(String flagEnviado) {
		this.flagEnviado = flagEnviado;
	}
	/**
	 * @return the flagCancelado
	 */
	public String getFlagCancelado() {
		return flagCancelado;
	}
	/**
	 * @param flagCancelado the flagCancelado to set
	 */
	public void setFlagCancelado(String flagCancelado) {
		this.flagCancelado = flagCancelado;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 100;
		result = prime
				* result
				+ ((calificacionComportamiento == null) ? 0
						: calificacionComportamiento.hashCode());
		result = prime
				* result
				+ ((calificacionExperiencia == null) ? 0
						: calificacionExperiencia.hashCode());
		result = prime
				* result
				+ ((campanyaComunicacion == null) ? 0 : campanyaComunicacion
						.hashCode());
		result = prime
				* result
				+ ((codigoCampanyaFacturacion == null) ? 0
						: codigoCampanyaFacturacion.hashCode());
		result = prime * result
				+ ((codigoCliente == null) ? 0 : codigoCliente.hashCode());
		result = prime * result
				+ ((codigoPais == null) ? 0 : codigoPais.hashCode());
		result = prime * result
				+ ((flagActivo == null) ? 0 : flagActivo.hashCode());
		result = prime * result
				+ ((flagComunicado == null) ? 0 : flagComunicado.hashCode());
		result = prime * result
				+ ((flagEnviado == null) ? 0 : flagEnviado.hashCode());
		result = prime * result
				+ ((flagHabilitado == null) ? 0 : flagHabilitado.hashCode());
		result = prime * result
				+ ((lineaCredito == null) ? 0 : lineaCredito.hashCode());
		result = prime
				* result
				+ ((nombreConsultora == null) ? 0 : nombreConsultora.hashCode());
		result = prime * result
				+ ((pedidoBase == null) ? 0 : pedidoBase.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		ConsultoraFlexipago other = (ConsultoraFlexipago) obj;
		if (calificacionComportamiento == null) {
			if (other.calificacionComportamiento != null)
				return false;
		} else if (!calificacionComportamiento
				.equals(other.calificacionComportamiento))
			return false;
		if (calificacionExperiencia == null) {
			if (other.calificacionExperiencia != null)
				return false;
		} else if (!calificacionExperiencia
				.equals(other.calificacionExperiencia))
			return false;
		if (campanyaComunicacion == null) {
			if (other.campanyaComunicacion != null)
				return false;
		} else if (!campanyaComunicacion.equals(other.campanyaComunicacion))
			return false;
		if (codigoCampanyaFacturacion == null) {
			if (other.codigoCampanyaFacturacion != null)
				return false;
		} else if (!codigoCampanyaFacturacion
				.equals(other.codigoCampanyaFacturacion))
			return false;
		if (codigoCliente == null) {
			if (other.codigoCliente != null)
				return false;
		} else if (!codigoCliente.equals(other.codigoCliente))
			return false;
		if (codigoPais == null) {
			if (other.codigoPais != null)
				return false;
		} else if (!codigoPais.equals(other.codigoPais))
			return false;
		if (flagActivo == null) {
			if (other.flagActivo != null)
				return false;
		} else if (!flagActivo.equals(other.flagActivo))
			return false;
		if (flagComunicado == null) {
			if (other.flagComunicado != null)
				return false;
		} else if (!flagComunicado.equals(other.flagComunicado))
			return false;
		if (flagEnviado == null) {
			if (other.flagEnviado != null)
				return false;
		} else if (!flagEnviado.equals(other.flagEnviado))
			return false;
		if (flagHabilitado == null) {
			if (other.flagHabilitado != null)
				return false;
		} else if (!flagHabilitado.equals(other.flagHabilitado))
			return false;
		if (lineaCredito == null) {
			if (other.lineaCredito != null)
				return false;
		} else if (!lineaCredito.equals(other.lineaCredito))
			return false;
		if (nombreConsultora == null) {
			if (other.nombreConsultora != null)
				return false;
		} else if (!nombreConsultora.equals(other.nombreConsultora))
			return false;
		if (pedidoBase == null) {
			if (other.pedidoBase != null)
				return false;
		} else if (!pedidoBase.equals(other.pedidoBase))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "ConsultoraFlexipago [calificacionComportamiento="
				+ calificacionComportamiento + ", calificacionExperiencia="
				+ calificacionExperiencia + ", campanyaComunicacion="
				+ campanyaComunicacion + ", codigoCampanyaFacturacion="
				+ codigoCampanyaFacturacion + ", codigoCliente="
				+ codigoCliente + ", codigoPais=" + codigoPais
				+ ", flagActivo=" + flagActivo + ", flagComunicado="
				+ flagComunicado + ", flagEnviado=" + flagEnviado
				+ ", flagHabilitado=" + flagHabilitado + ", lineaCredito="
				+ lineaCredito + ", nombreConsultora=" + nombreConsultora
				+ ", pedidoBase=" + pedidoBase + "]";
	}
	/**
	 * @return the numeroContrato
	 */
	public String getNumeroContrato() {
		return numeroContrato;
	}
	/**
	 * @param numeroContrato the numeroContrato to set
	 */
	public void setNumeroContrato(String numeroContrato) {
		this.numeroContrato = numeroContrato;
	}
		

}
