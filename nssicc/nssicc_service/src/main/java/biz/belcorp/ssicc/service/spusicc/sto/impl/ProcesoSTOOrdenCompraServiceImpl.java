/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * @author Jose Cairampoma
 * 
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOOrdenCompraServiceImpl extends BaseProcesoSTOAbstractService {
	
	/**
	 * @param documentoSTOParams
	 */
	public void executeDeleteDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		procesoSTODAO.executeDeletePedido(documentoSTOParams.getQueryParams());
	};
	
	
	/**
	 * Metodo ejecutado despues de rechazar los documentos
	 * @param documentoSTOParams
	 */
	protected void afterRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterRejectDocumentoSTO' method");
		Map queryParams = documentoSTOParams.getQueryParams();
		procesoSTODAO.updateRechazoOCC(queryParams);
		
	}
	
	/**
	 * Metodo ejecutado despues de rechazar los documentos
	 * @param documentoSTOParams
	 */
	protected void afterRecoverRejectDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		if (log.isDebugEnabled())
			log.debug("Entering 'afterRejectDocumentoSTO' method");
		Map queryParams = documentoSTOParams.getQueryParams();
		procesoSTODAO.executeRecoverRejectOCC(queryParams);
		
	}
	
	
	
}
