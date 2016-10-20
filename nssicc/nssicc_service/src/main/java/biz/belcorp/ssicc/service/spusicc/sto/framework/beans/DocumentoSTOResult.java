package biz.belcorp.ssicc.service.spusicc.sto.framework.beans;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;

/**
 * @author Jose Cairampoma
 *
 */
public class DocumentoSTOResult {
	private String numeroProceso;

	private TipoDocumentoDigitado tipoDocumentoDigitado;

	private String mensaje = "";

	private int registrosProcesados = 0;

	private boolean completado = false;
	/**
	 * @param numeroProceso
	 * @param tipoDocumentoDigitado
	 */
	public DocumentoSTOResult(String numeroProceso,	TipoDocumentoDigitado tipoDocumentoDigitado) {
		super();
		this.numeroProceso = numeroProceso;
		this.tipoDocumentoDigitado = tipoDocumentoDigitado;
	}
	
	/**
	 * @param documentoSTOParams
	 */
	public DocumentoSTOResult(DocumentoSTOParams documentoSTOParams) {
		this.numeroProceso = documentoSTOParams.getHistoricoTipoDocumento().getNumeroProceso();
		this.tipoDocumentoDigitado = documentoSTOParams.getTipoDocumentoDigitado();
	}

	/**
	 * @return the numeroProceso
	 */
	public String getNumeroProceso() {
		return numeroProceso;
	}

	/**
	 * @param numeroProceso the numeroProceso to set
	 */
	public void setNumeroProceso(String numeroProceso) {
		this.numeroProceso = numeroProceso;
	}

	/**
	 * @return the tipoDocumentoDigitado
	 */
	public TipoDocumentoDigitado getTipoDocumentoDigitado() {
		return tipoDocumentoDigitado;
	}

	/**
	 * @param tipoDocumentoDigitado the tipoDocumentoDigitado to set
	 */
	public void setTipoDocumentoDigitado(TipoDocumentoDigitado tipoDocumentoDigitado) {
		this.tipoDocumentoDigitado = tipoDocumentoDigitado;
	}

	/**
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * @return the registrosProcesados
	 */
	public int getRegistrosProcesados() {
		return registrosProcesados;
	}

	/**
	 * @param registrosProcesados the registrosProcesados to set
	 */
	public void setRegistrosProcesados(int registrosProcesados) {
		this.registrosProcesados = registrosProcesados;
	}

	/**
	 * @return the completado
	 */
	public boolean isCompletado() {
		return completado;
	}

	/**
	 * @param completado the completado to set
	 */
	public void setCompletado(boolean completado) {
		this.completado = completado;
	}
	
	/**	  
	 * @param exception
	 */
	public void updateSTOResultOnException(Exception exception) {
    	this.completado = false;
    	this.mensaje = exception.getMessage();
	}
	
	/**
	 * @param registrosProcesados
	 */
	public void updateSTOResultOnSuccess(int registrosProcesados) {		
		this.completado = true;
		this.mensaje = Constants.INTERFAZSICC_ARCHIVO_ENVIADOGENERICO;
		this.registrosProcesados = registrosProcesados;
	}
}
