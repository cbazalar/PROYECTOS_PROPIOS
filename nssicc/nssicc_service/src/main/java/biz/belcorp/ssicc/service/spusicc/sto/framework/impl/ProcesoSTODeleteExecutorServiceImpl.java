package biz.belcorp.ssicc.service.spusicc.sto.framework.impl;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOExecutorAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * Implementacion de BaseProcesoSTOExecutorAbstractService que utiliza un Map con las
 * implementaciones especificas de las Acciones STO inyectados mediante
 * Spring. Nuevas Acciones requeriran que se agregue la referencia al Map de
 * implementaciones en el 'applicationContext-service-spusicc-impl.xml'.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTODeleteExecutorServiceImpl extends BaseProcesoSTOExecutorAbstractService {
	
	/**
	 * @param documentoSTOParams
	 * @return
	 */
	@Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	protected void execute(DocumentoSTOParams documentoSTOParams) throws Exception {
		log.warn("Iniciando la Eliminacion");
		if (documentoSTOParams.getStoList()!=null){
			Map queryParams = documentoSTOParams.getQueryParams();    	
			List  list = documentoSTOParams.getStoList();
			procesoSTOService.updateDocumentoForDelete(list,queryParams);
			BaseProcesoSTOService stoImpl = getSTOImplementation(documentoSTOParams);
			stoImpl.deleteDocumentoSTO(documentoSTOParams);
		}
	}

}
