/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * @author <a href="mailto:llizana@belcorp.biz">Leonardo Lizana</a>
 * 
 * 
 * */

public class InterfazOCRRecepcionarArriboZonasServiceImpl extends
		BaseInterfazEntradaAbstractService {

	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		try {
			interfazSiCCDAO.executeInterfazOCRRecepcionarArriboZonas(map);

		} catch (Exception e) {
			throw new InterfazException(
					"Error al borrar/cargar los registros de la tabla temporal: "
							+ e.getMessage());
		}

	}

}
