/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * @author USER
 *
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOPostVentaServiceImpl extends BaseProcesoSTOAbstractService {
	
	/**
	 * @param documentoSTOParams
	 */
	public void executeDeleteDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		procesoSTODAO.executeEliminarCDR(documentoSTOParams.getQueryParams());
	};
}
