package biz.belcorp.ssicc.web.spusicc.sto.form;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.web.framework.base.form.BaseEditForm;

public class MantenimientoSTOValidacionForm  extends BaseEditForm{

	/**
	 * JPPS
	 */
	
	private static final long serialVersionUID = -963537902601841689L;
	private String codigoPais;
	private String tipoDocumento;
	private String descripcionDocumento;
	private String codigoValidacion;
	private String descripcionValidacion;
	private String procesoValidacion;
	private String procesoAprobar;
	private String procesoDesaprobar;
	private String motivoRechazo;
	private String mensajeRechazo;
	
	private String indicadorGestionable;
	private String indicadorContinuacion;
	private String indicadorRechazoAutomatico;
	private String indicadorHistoricoExcepciones;
	private String indicadorRechazoGestion;
	private String indicadorModificacionConsultora;
	private String indicadorExcepcionValidacion;
	private String indicadorMensajeAdicionalError;
	private String indicadorVisualizarMotivoGestion;
	private String indicadorForzarValidacion;
	
	
	
	public void reset() {
		this.codigoValidacion = "";
		this.descripcionValidacion = "";
		this.procesoValidacion = "";
		this.procesoAprobar = "";
		this.procesoDesaprobar = "";
		this.motivoRechazo = "";
		this.mensajeRechazo = "";
		this.indicadorGestionable = Constants.NUMERO_CERO;
		this.indicadorContinuacion = Constants.NO;	
		this.indicadorRechazoAutomatico = Constants.NUMERO_CERO;
		this.indicadorHistoricoExcepciones = Constants.NUMERO_CERO;
		this.indicadorRechazoGestion = Constants.NUMERO_CERO;
		this.indicadorModificacionConsultora = Constants.NUMERO_CERO;
		this.indicadorExcepcionValidacion = Constants.NO;		
		this.indicadorMensajeAdicionalError = Constants.NO;
		this.indicadorVisualizarMotivoGestion = Constants.NO;
		this.indicadorForzarValidacion = Constants.NO;
	}

	/**
	 * @return the codigoPais
	 */
	public String getCodigoPais() {
		return codigoPais;
	}

	/**
	 * @param codigoPais the codigoPais to set
	 * @struts.validator type="required"
	 */
	public void setCodigoPais(String codigoPais) {
		this.codigoPais = codigoPais;
	}

	/**
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * @param tipoDocumento the tipoDocumento to set
	 * @struts.validator type="required"
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * @return the codigoValidacion
	 */
	public String getCodigoValidacion() {
		return codigoValidacion;
	}

	/**
	 * @param codigoValidacion the codigoValidacion to set
	 * @struts.validator type="required"
	 */
	public void setCodigoValidacion(String codigoValidacion) {
		this.codigoValidacion = codigoValidacion;
	}

	/**
	 * @return the descripcionValidacion
	 */
	public String getDescripcionValidacion() {
		return descripcionValidacion;
	}

	/**
	 * @param descripcionValidacion the descripcionValidacion to set
	 */
	public void setDescripcionValidacion(String descripcionValidacion) {
		this.descripcionValidacion = descripcionValidacion;
	}

	/**
	 * @return the procesoValidacion
	 */
	public String getProcesoValidacion() {
		return procesoValidacion;
	}

	/**
	 * @param procesoValidacion the procesoValidacion to set
	 */
	public void setProcesoValidacion(String procesoValidacion) {
		this.procesoValidacion = procesoValidacion;
	}

	/**
	 * @return the procesoAprobar
	 */
	public String getProcesoAprobar() {
		return procesoAprobar;
	}

	/**
	 * @param procesoAprobar the procesoAprobar to set
	 */
	public void setProcesoAprobar(String procesoAprobar) {
		this.procesoAprobar = procesoAprobar;
	}

	/**
	 * @return the procesoDesaprobar
	 */
	public String getProcesoDesaprobar() {
		return procesoDesaprobar;
	}

	/**
	 * @param procesoDesaprobar the procesoDesaprobar to set
	 */
	public void setProcesoDesaprobar(String procesoDesaprobar) {
		this.procesoDesaprobar = procesoDesaprobar;
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
	 * @return the mensajeRechazo
	 */
	public String getMensajeRechazo() {
		return mensajeRechazo;
	}

	/**
	 * @param mensajeRechazo the mensajeRechazo to set
	 */
	public void setMensajeRechazo(String mensajeRechazo) {
		this.mensajeRechazo = mensajeRechazo;
	}

	/**
	 * @return the indicadorGestionable
	 */
	public String getIndicadorGestionable() {
		return indicadorGestionable;
	}

	/**
	 * @param indicadorGestionable the indicadorGestionable to set
	 */
	public void setIndicadorGestionable(String indicadorGestionable) {
		this.indicadorGestionable = indicadorGestionable;
	}

	/**
	 * @return the indicadorContinuacion
	 */
	public String getIndicadorContinuacion() {
		return indicadorContinuacion;
	}

	/**
	 * @param indicadorContinuacion the indicadorContinuacion to set
	 */
	public void setIndicadorContinuacion(String indicadorContinuacion) {
		this.indicadorContinuacion = indicadorContinuacion;
	}

	/**
	 * @return the indicadorRechazoAutomatico
	 */
	public String getIndicadorRechazoAutomatico() {
		return indicadorRechazoAutomatico;
	}

	/**
	 * @param indicadorRechazoAutomatico the indicadorRechazoAutomatico to set
	 */
	public void setIndicadorRechazoAutomatico(String indicadorRechazoAutomatico) {
		this.indicadorRechazoAutomatico = indicadorRechazoAutomatico;
	}

	/**
	 * @return the indicadorHistoricoExcepciones
	 */
	public String getIndicadorHistoricoExcepciones() {
		return indicadorHistoricoExcepciones;
	}

	/**
	 * @param indicadorHistoricoExcepciones the indicadorHistoricoExcepciones to set
	 */
	public void setIndicadorHistoricoExcepciones(
			String indicadorHistoricoExcepciones) {
		this.indicadorHistoricoExcepciones = indicadorHistoricoExcepciones;
	}

	/**
	 * @return the indicadorRechazoGestion
	 */
	public String getIndicadorRechazoGestion() {
		return indicadorRechazoGestion;
	}

	/**
	 * @param indicadorRechazoGestion the indicadorRechazoGestion to set
	 */
	public void setIndicadorRechazoGestion(String indicadorRechazoGestion) {
		this.indicadorRechazoGestion = indicadorRechazoGestion;
	}

	/**
	 * @return the indicadorModificacionConsultora
	 */
	public String getIndicadorModificacionConsultora() {
		return indicadorModificacionConsultora;
	}

	/**
	 * @param indicadorModificacionConsultora the indicadorModificacionConsultora to set
	 */
	public void setIndicadorModificacionConsultora(
			String indicadorModificacionConsultora) {
		this.indicadorModificacionConsultora = indicadorModificacionConsultora;
	}

	/**
	 * @return the indicadorExcepcionValidacion
	 */
	public String getIndicadorExcepcionValidacion() {
		return indicadorExcepcionValidacion;
	}

	/**
	 * @param indicadorExcepcionValidacion the indicadorExcepcionValidacion to set
	 */
	public void setIndicadorExcepcionValidacion(String indicadorExcepcionValidacion) {
		this.indicadorExcepcionValidacion = indicadorExcepcionValidacion;
	}

	/**
	 * @return the indicadorMensajeAdicionalError
	 */
	public String getIndicadorMensajeAdicionalError() {
		return indicadorMensajeAdicionalError;
	}

	/**
	 * @param indicadorMensajeAdicionalError the indicadorMensajeAdicionalError to set
	 */
	public void setIndicadorMensajeAdicionalError(
			String indicadorMensajeAdicionalError) {
		this.indicadorMensajeAdicionalError = indicadorMensajeAdicionalError;
	}

	/**
	 * @return the indicadorVisualizarMotivoGestion
	 */
	public String getIndicadorVisualizarMotivoGestion() {
		return indicadorVisualizarMotivoGestion;
	}

	/**
	 * @param indicadorVisualizarMotivoGestion the indicadorVisualizarMotivoGestion to set
	 */
	public void setIndicadorVisualizarMotivoGestion(
			String indicadorVisualizarMotivoGestion) {
		this.indicadorVisualizarMotivoGestion = indicadorVisualizarMotivoGestion;
	}

	/**
	 * @return the indicadorForzarValidacion
	 */
	public String getIndicadorForzarValidacion() {
		return indicadorForzarValidacion;
	}

	/**
	 * @param indicadorForzarValidacion the indicadorForzarValidacion to set
	 */
	public void setIndicadorForzarValidacion(String indicadorForzarValidacion) {
		this.indicadorForzarValidacion = indicadorForzarValidacion;
	}

	/**
	 * @return the descripcionDocumento
	 */
	public String getDescripcionDocumento() {
		return descripcionDocumento;
	}

	/**
	 * @param descripcionDocumento the descripcionDocumento to set
	 */
	public void setDescripcionDocumento(String descripcionDocumento) {
		this.descripcionDocumento = descripcionDocumento;
	}
	
}
