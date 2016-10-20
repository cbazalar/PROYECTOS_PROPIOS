package biz.belcorp.ssicc.service.spusicc.sto.framework;

import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

public interface BaseProcesoSTOService {

	/**
	 * Ejecuta las Validaciones del Documento Digitado dado los parametros
	 * 
	 * @param documentoSTOParams
	 */
	public void executeValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception;

	/**
	 * Aprueba que la lista de documentos pasen la validacion a pesar de haber
	 * tenido errores al ejecutarse
	 * 
	 * @param documentoSTOParams
	 */
	public void approveValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception;

	/**
	 * Desaprueba que la lista de documentos pasen la validacion a pesar de
	 * haber tenido errores al ejecutarse
	 * 
	 * @param documentoSTOParams
	 */
	public void disapproveValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception;

	/**
	 * Pasa a los Historicos los documentos
	 * 
	 * @param documentoSTOParams
	 * @return
	 */
	public void executeHistoricoDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception;

	/**
	 * Rechazo los Documentos de la lista
	 * 
	 * @param documentoSTOParams
	 * @return
	 */
	public void rejectDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception;

	
	/**
	 * Elimina pedidos dados los parametros
	 * 
	 * @param documentoSTOParams
	 */
	public void deleteDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception;
	
	/**
	 * @param documentoSTOParams
	 */
	public void recoverRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams) throws Exception;



}
