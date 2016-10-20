/**
 * 
 */
package biz.belcorp.ssicc.service.spusicc.sto.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.spusicc.sto.model.TipoDocumentoDigitado;
import biz.belcorp.ssicc.service.spusicc.sto.framework.BaseProcesoSTOAbstractService;
import biz.belcorp.ssicc.service.spusicc.sto.framework.beans.DocumentoSTOParams;

/**
 * @author USER
 *
 */

@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoSTOActualizacionDatosServiceImpl extends BaseProcesoSTOAbstractService {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.framework.BaseProcesoSTOAbstractService#beforeProcessValidacionDocumentoSTO(biz.belcorp.ssicc.spusicc.sto.framework.beans.DocumentoSTOParams)
	 */
	public void beforeProcessValidacionDocumentoSTO(DocumentoSTOParams documentoSTOParams){
		
		if (log.isDebugEnabled())
			log.debug("Entering 'beforeProcessValidacionDocumentoSTO' method");		
		
		Map criteria = new HashMap();		
		
		TipoDocumentoDigitado tipoDocumentoDigitado = documentoSTOParams.getTipoDocumentoDigitado();
		criteria.put("codigoPais", tipoDocumentoDigitado.getCodPais());
				
		procesoSTODAO.executePulirDataActualizada(criteria);
				
	}




	
}
