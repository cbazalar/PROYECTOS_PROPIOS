package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service del Envio de Direccion de Consultoras de la Interfaz GIS.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazGISEnviarDireccionConsultorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazGISEnviarDireccionConsultorasServiceImpl extends
		BaseInterfazSalidaAbstractService {

	/**
	 * Obtiene la data para el Envio de Direccion de Consultoras de la Interfaz
	 * GIS.
	 * 
	 * @param queryParams
	 *            parametros del query.
	 * @return List con Maps con los datos de las direcciones de las Consultoras
	 * @throws InterfazException
	 */
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method ");
		List result = null;
		try {
			result = interfazSiCCService
					.getInterfazGISEnviarDireccionConsultoras(queryParams);
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return result;
	}

	protected Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
		Map queryParams = super.prepareQueryParams(interfazParams);
		queryParams.put("codigoIdioma", interfazParams.getUsuario().getIdioma()
				.getCodigoSiCC());
		return queryParams;
	}

}