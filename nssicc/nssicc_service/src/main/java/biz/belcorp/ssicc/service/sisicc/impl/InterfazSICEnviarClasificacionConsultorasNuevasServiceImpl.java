package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service del Envio de Personalizacion de Sobres de la Interfaz Privilege.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Service("sisicc.interfazSICEnviarClasificacionConsultorasNuevasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSICEnviarClasificacionConsultorasNuevasServiceImpl extends
		BaseInterfazSalidaAbstractService {

	/**
	 * Obtiene la data para la Personalizacion de Sobres de la Interfaz
	 * Privilege.
	 * 
	 * @param queryParams
	 *            parametros del query.
	 * @return List con Maps de las filas de la actualizacion de tipos de
	 *         clientes.
	 * @throws InterfazException
	 */
	protected List readData(Map queryParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'readData' method ");
		List result = null;
		try {
			// Obtengo el periodo a evaluar
			String periodoEvaluar = this.interfazSiCCDAO
					.getInterfazPRIPeriodoEvaluar(queryParams);

			if (periodoEvaluar == null)
				throw new InterfazException(
						"El periodo a evaluar no existe para el periodo y el numero de campaas a evaluar seleccionados.");

			// Obtengo los codigos de tipos de clasificacion
			Map criteria = new HashMap();
			criteria.put("codigoPais", queryParams.get("codigoPais"));

			criteria.put("codigoClasificacion", "1");
			String codigoTipoClasificacionNuevo = this.interfazSiCCDAO
					.getInterfazSICTipoClasificacion(criteria);

			criteria.put("codigoClasificacion", "2");
			String codigoTipoClasificacionAntiguo = this.interfazSiCCDAO
					.getInterfazSICTipoClasificacion(criteria);

			// Agrego los parametros necesarios para el query
			queryParams.put("periodoEvaluar", periodoEvaluar);
			queryParams.put("codigoTipoClasificacionNuevo",
					codigoTipoClasificacionNuevo);
			queryParams.put("codigoTipoClasificacionAntiguo",
					codigoTipoClasificacionAntiguo);

			result = this.interfazSiCCDAO
					.getInterfazSICEnviarClasificacionConsultorasNuevas(queryParams);
		} catch (Exception e) {
			log.error("Error al leer los datos: " + e.getMessage());
			throw new InterfazException(e.getMessage());
		}
		return result;
	}
}