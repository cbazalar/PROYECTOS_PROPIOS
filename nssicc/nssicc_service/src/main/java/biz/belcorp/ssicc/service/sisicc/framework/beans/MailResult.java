package biz.belcorp.ssicc.service.sisicc.framework.beans;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Clase que encapsula el resultado de la ejecucion del envio de correos
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 * 
 */
public class MailResult {

	private boolean completado = false;
	
	private String mensajeError;
	private String correoDestino;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
	
	public boolean isCompletado() {
		return completado;
	}

	public void setCompletado(boolean completado) {
		this.completado = completado;
	}

	/**
	 * @return Returns the mensajeError.
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * @param mensajeError The mensajeError to set.
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * @return the correoDestino
	 */
	public String getCorreoDestino() {
		return correoDestino;
	}

	/**
	 * @param correoDestino the correoDestino to set
	 */
	public void setCorreoDestino(String correoDestino) {
		this.correoDestino = correoDestino;
	}
	
	

	

}
