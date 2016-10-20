package biz.belcorp.ssicc.service.spusicc.sto.framework.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOExecutorAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * Implementacion de ProcesoSTOExcecutionServiceImpl que utiliza un Map con las
 * implementaciones especificas de los Documentos STO SiCC inyectados mediante
 * Spring. Nuevos documentos requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service-spusicc-impl.xml'.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTORejectExecutorServiceImpl extends BaseProcesoSTOExecutorAbstractService {
	
	/**
	 * @param documentoSTOParams
	 * @return
	 */
	protected void execute(DocumentoSTOParams documentoSTOParams) throws Exception{
		BaseProcesoSTOService stoImpl = getSTOImplementation(documentoSTOParams);
		stoImpl.rejectDocumentoSTO(documentoSTOParams);
	}

}
