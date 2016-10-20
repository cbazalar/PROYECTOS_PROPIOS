/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarVentasPuntajesXCampanyaServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazSICEnviarVentasPuntajesXCampanyaServiceImpl.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius </a>
 */
@Service("sisicc.interfazSICEnviarVentasPuntajesXCampanyaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSICEnviarVentasPuntajesXCampanyaServiceImpl extends
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
		Map criteria = new HashMap();
		criteria.put("numeroConcurso", queryParams.get("codigoConcurso"));
		criteria.put("codigoPeriodo", queryParams.get("codigoPeriodo"));
		String oidParametroGeneral = interfazSiCCDAO.getOidString(
				"getOidConcursoByNumConcursoGenerico", criteria);
		String fechaActual = interfazSiCCDAO.getOidString(
				"getFechaActualBycodigoConcurso", criteria);
		criteria = queryParams;
		String oidMarca = interfazSiCCDAO.getOidGenerico(
				"getOidMarcaByCodigoMarcaGenerico", criteria);
		String oidCanal = interfazSiCCDAO.getOidGenerico(
				"getOidCanalByCodigoCanalGenerico", criteria);
		// Obtenemos el codigoISO del idioma del usuario
		queryParams.put("oidParametroGeneral", oidParametroGeneral);
		queryParams.put("fechaActual", fechaActual);
		queryParams.put("oidMarca", oidMarca);
		queryParams.put("oidCanal", oidCanal);
		queryParams.put("codigoISO", interfazParams.getUsuario().getIdioma()
				.getCodigoISO());
		if (log.isDebugEnabled()) {
			log.debug(queryParams);
		}

		return queryParams;
	}

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.deleteParamTmpInterfaz(params);
		interfazSiCCDAO.inserParamTmpInterfaz(params, "codigoTipoClasificacion",
				Constants.PARM_TIPO_CLASIFICACION, true);
		interfazSiCCDAO.inserParamTmpInterfaz(params, "codigoSubTipoCliente",
				Constants.PARM_SUB_TIPO_CLIENTE, true);
		interfazSiCCDAO.inserParamTmpInterfaz(params, "codigoClasificacion",
				Constants.PARM_CLASIFICACION, true);
		params
				.put("codigoTipoClasificacion",
						Constants.PARM_TIPO_CLASIFICACION);
		params.put("codigoSubTipoCliente", Constants.PARM_SUB_TIPO_CLIENTE);
		params.put("codigoClasificacion", Constants.PARM_CLASIFICACION);
		interfazSiCCDAO.executeInterfazECOEnviarPuntajesXCampanya(params);

	}

}
