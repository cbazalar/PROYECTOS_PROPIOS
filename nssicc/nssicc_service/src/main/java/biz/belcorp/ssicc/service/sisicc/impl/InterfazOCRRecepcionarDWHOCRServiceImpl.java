package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * Service para la recepcin de DWH OCR en la Interfaz OCR.
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva</a>
 */

public class InterfazOCRRecepcionarDWHOCRServiceImpl extends
		BaseInterfazEntradaAbstractService {

	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		log.debug("Entering 'beforeReadData' method");
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		try {
			interfazSiCCDAO.deleteInterfazOCRRecepcionarDWHOCR();
			interfazSiCCDAO.executeInterfazOCRRecepcionarDWHOCR(map);

		} catch (Exception e) {
			throw new InterfazException(
					"Error al borrar/cargar los registros de la tabla : "
							+ e.getMessage());
		}
	}

}
