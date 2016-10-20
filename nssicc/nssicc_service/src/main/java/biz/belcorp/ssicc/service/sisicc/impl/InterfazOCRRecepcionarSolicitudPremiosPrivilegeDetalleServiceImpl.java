/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazEntradaAbstractService;

/**
 * @author llizana@belcorp.biz Leonardo Lizana
 * 
 * 
 */

public class InterfazOCRRecepcionarSolicitudPremiosPrivilegeDetalleServiceImpl
		extends BaseInterfazEntradaAbstractService {

	protected void beforeReadData(InterfazParams interfazParams)
			throws InterfazException {
		Map map = interfazParams.getQueryParams();
		map.put("nombreArchivo", interfazParams.getTempName());

		try {
			interfazSiCCDAO
					.executeInterfazOCRRecepcionarSolicitudPremiosPrivilegeDetalle(map);

		} catch (Exception e) {
			throw new InterfazException(
					"Error al borrar/cargar los registros de la tabla temporal: "
							+ e.getMessage());
		}

	}

}
