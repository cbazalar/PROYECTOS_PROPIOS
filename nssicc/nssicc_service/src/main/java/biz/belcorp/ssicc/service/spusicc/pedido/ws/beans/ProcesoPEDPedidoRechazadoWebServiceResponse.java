package biz.belcorp.ssicc.service.spusicc.pedido.ws.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProcesoPEDPedidoRechazadoWebServiceResponse implements Serializable {

	private static final long serialVersionUID = 6222431448834869834L;
	
	private String accion;
	private Integer oidSicc;
	private String consultora;
	private String periodo;
	private String deuda;
	private String flagDetalle;
	private String flagAlternativos;
	private String flagErrores;
	private String flagMensajes;
	private String mensajeError;
	
	private ProcesoPEDPedidoDetalleRechazadoWebServiceResponse[] detalle;
	private ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse[] alternativos;
	private ProcesoPEDPedidoErroresRechazadoWebServiceResponse[] errores;
	private ProcesoPEDPedidoMensajesRechazadoWebServiceResponse[] mensajes;
	
	public ProcesoPEDPedidoRechazadoWebServiceResponse() {
		this.accion = "";
		this.oidSicc = 0;
		this.consultora = "";
		this.periodo = "";
		this.deuda = "";
		this.flagDetalle = "";
		this.flagAlternativos = "";
		this.flagErrores = "";
		this.flagMensajes = "";
		this.detalle = null;
		this.alternativos = null;
		this.errores = null;
		this.mensajes = null;
		this.mensajeError = "";
	}

	public ProcesoPEDPedidoRechazadoWebServiceResponse(
			String accion,
			Integer oidSicc,
			String consultora,
			String periodo,
			String deuda,
			String flagDetalle,
			String flagAlternativos,
			String flagErrores,
			String flagMensajes,
			ProcesoPEDPedidoDetalleRechazadoWebServiceResponse[] detalle,
			ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse[] alternativos,
			ProcesoPEDPedidoErroresRechazadoWebServiceResponse[] errores,
			ProcesoPEDPedidoMensajesRechazadoWebServiceResponse[] mensajes,
			String mensajeError) {
		this.accion = accion;
		this.oidSicc = oidSicc;
		this.consultora = consultora;
		this.periodo = periodo;
		this.deuda = deuda;
		this.flagDetalle = flagDetalle;
		this.flagAlternativos = flagAlternativos;
		this.flagErrores = flagErrores;
		this.flagMensajes = flagMensajes;
		this.detalle = detalle;
		this.alternativos = alternativos;
		this.errores = errores;
		this.mensajes = mensajes;
		this.mensajeError = mensajeError;
	}

	/**
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * @return the oidSicc
	 */
	public Integer getOidSicc() {
		return oidSicc;
	}

	/**
	 * @param oidSicc the oidSicc to set
	 */
	public void setOidSicc(Integer oidSicc) {
		this.oidSicc = oidSicc;
	}

	/**
	 * @return the consultora
	 */
	public String getConsultora() {
		return consultora;
	}

	/**
	 * @param consultora the consultora to set
	 */
	public void setConsultora(String consultora) {
		this.consultora = consultora;
	}

	/**
	 * @return the periodo
	 */
	public String getPeriodo() {
		return periodo;
	}

	/**
	 * @param periodo the periodo to set
	 */
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	/**
	 * @return the flagDetalle
	 */
	public String getFlagDetalle() {
		return flagDetalle;
	}

	/**
	 * @param flagDetalle the flagDetalle to set
	 */
	public void setFlagDetalle(String flagDetalle) {
		this.flagDetalle = flagDetalle;
	}

	/**
	 * @return the flagAlternativos
	 */
	public String getFlagAlternativos() {
		return flagAlternativos;
	}

	/**
	 * @param flagAlternativos the flagAlternativos to set
	 */
	public void setFlagAlternativos(String flagAlternativos) {
		this.flagAlternativos = flagAlternativos;
	}

	/**
	 * @return the flagErrores
	 */
	public String getFlagErrores() {
		return flagErrores;
	}

	/**
	 * @param flagErrores the flagErrores to set
	 */
	public void setFlagErrores(String flagErrores) {
		this.flagErrores = flagErrores;
	}

	/**
	 * @return the flagMensajes
	 */
	public String getFlagMensajes() {
		return flagMensajes;
	}

	/**
	 * @param flagMensajes the flagMensajes to set
	 */
	public void setFlagMensajes(String flagMensajes) {
		this.flagMensajes = flagMensajes;
	}

	/**
	 * @return the detalle
	 */
	public ProcesoPEDPedidoDetalleRechazadoWebServiceResponse[] getDetalle() {
		return detalle;
	}

	/**
	 * @param detalle the detalle to set
	 */
	public void setDetalle(ProcesoPEDPedidoDetalleRechazadoWebServiceResponse[] detalle) {
		this.detalle = detalle;
	}

	/**
	 * @return the alternativos
	 */
	public ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse[] getAlternativos() {
		return alternativos;
	}

	/**
	 * @param alternativos the alternativos to set
	 */
	public void setAlternativos(ProcesoPEDPedidoAlternativosRechazadoWebServiceResponse[] alternativos) {
		this.alternativos = alternativos;
	}

	/**
	 * @return the errores
	 */
	public ProcesoPEDPedidoErroresRechazadoWebServiceResponse[] getErrores() {
		return errores;
	}

	/**
	 * @param errores the errores to set
	 */
	public void setErrores(ProcesoPEDPedidoErroresRechazadoWebServiceResponse[] errores) {
		this.errores = errores;
	}

	/**
	 * @return the mensajes
	 */
	public ProcesoPEDPedidoMensajesRechazadoWebServiceResponse[] getMensajes() {
		return mensajes;
	}

	/**
	 * @param mensajes the mensajes to set
	 */
	public void setMensajes(ProcesoPEDPedidoMensajesRechazadoWebServiceResponse[] mensajes) {
		this.mensajes = mensajes;
	}

	/**
	 * @return the mensajeError
	 */
	public String getMensajeError() {
		return mensajeError;
	}

	/**
	 * @param mensajeError the mensajeError to set
	 */
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}

	/**
	 * @return the deuda
	 */
	public String getDeuda() {
		return deuda;
	}

	/**
	 * @param deuda the deuda to set
	 */
	public void setDeuda(String deuda) {
		this.deuda = deuda;
	}
}