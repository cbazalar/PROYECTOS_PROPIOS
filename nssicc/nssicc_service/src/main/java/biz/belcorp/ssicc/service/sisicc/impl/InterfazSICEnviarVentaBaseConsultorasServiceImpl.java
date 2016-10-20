/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarVentaBaseConsultorasServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSICEnviarVentaBaseConsultorasServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius </a>
 */
@Service("sisicc.interfazSICEnviarVentaBaseConsultorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSICEnviarVentaBaseConsultorasServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	/**
	 * Devuelve el Map de parametros del query. Sobreescrito con la finalidad de
	 * obtener los 'Parametros de Interaz' configurados en el mantenimiento de
	 * la interfaz
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Map con parametros del query
	 */
	public Map prepareQueryParams(InterfazParams interfazParams)
			throws InterfazException {
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);

		// Obtenemos el codigoISO del idioma del usuario
		queryParams.put("codigoISO", interfazParams.getUsuario().getIdioma()
				.getCodigoISO());
		if (log.isDebugEnabled()) {
			log.debug(queryParams);
		}
		return queryParams;
	}

	protected void beforeProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
		try {
			Map mapQueryParams = (Map) interfazParams.getQueryParams();

			interfazSiCCDAO.deleteVentaBaseConsultoras();
			String codigoPeriodo = interfazSiCCDAO.getOidString(
					"getCodigoPeriodoByConcurso", mapQueryParams);
			mapQueryParams.put("codigoPeriodo", codigoPeriodo);
			// mapQueryParams.get("codigoPeriodo")
			interfazSiCCDAO.insertVentaBaseConsultoras(mapQueryParams);

		} catch (Exception e) {
			throw new InterfazException(
					"Error al borrar o insertar los registros de la tabla temporal: "
							+ e.getMessage());
		}

	}

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazECOEnviarVentaBaseConsultoras(params);
	}

	/**
	 * Obtiene la data para la interfaz
	 * 
	 * @param queryParams
	 *            parametros del query.
	 * @return List con Maps de las filas resultado.
	 * @throws InterfazException
	 */
	/*
	 * protected List readData(Map queryParams) throws InterfazException { if
	 * (log.isDebugEnabled()) log.debug("Entering 'readData' method "); //
	 * Obtenemos la informacin List listVentaBaseConsultoras = null; try {
	 * listVentaBaseConsultoras = this.interfazSiCCDAO
	 * .getInterfazSICVentaBaseConsultorasList(queryParams); } catch (Exception
	 * e) { log.error("Error al leer los datos: " + e.getMessage()); throw new
	 * InterfazException(e.getMessage()); } return listVentaBaseConsultoras; }
	 */

}
