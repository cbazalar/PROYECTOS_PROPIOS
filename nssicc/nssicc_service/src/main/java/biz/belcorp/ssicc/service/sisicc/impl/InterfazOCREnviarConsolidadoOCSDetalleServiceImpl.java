package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service del Envio del Consolidado de OCS Detalle de la Interfaz OCR.
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 */
@Service("sisicc.interfazOCREnviarConsolidadoOCSDetalleService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazOCREnviarConsolidadoOCSDetalleServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazOCREnviarOCSDetalle(params);
	}
/*	
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method ");
		List result = null;
		try {
			result = interfazSiCCDAO
					.getInterfazOCRConsolidadoOCSDetalle(queryParams);
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return result;
	}
*/	
}