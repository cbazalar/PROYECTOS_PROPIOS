/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarInscritasServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazIVREnviarMaestroClientesServiceImpl.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:majimenez@belcorp.biz">Marco Agurto</a>
 */
@Service("sisicc.interfazIVREnviarMaestroClientesServiceImpl")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazIVREnviarMaestroClientesServiceImpl extends
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
		InterfazPK pk = new InterfazPK(MapUtils.getString(queryParams,
				"codigoPais"),
				MapUtils.getString(queryParams, "codigoSistema"), MapUtils
						.getString(queryParams, "codigoInterfaz"));
		queryParams.put("fechaUltimoProceso", interfazService
				.getFechaUltimoProceso(pk));
		// Obtenemos el codigoISO del idioma del usuario
		queryParams.put("codigoISO", interfazParams.getUsuario().getIdioma()
				.getCodigoISO());

		String codigoPais = (String) queryParams.get("codigoPais");
		String codigoSistema = (String) queryParams.get("codigoSistema");
		String codigoInterfaz = (String) queryParams.get("codigoInterfaz");
		InterfazPK interfazEjecucionPK = new InterfazPK(codigoPais,
				codigoSistema, codigoInterfaz);
		String numeroLote = (String) queryParams.get("numeroLote");
		if (StringUtils.isEmpty(numeroLote)) {
			numeroLote = interfazService.getNumeroLote(interfazEjecucionPK);
			queryParams.put("numeroLote", numeroLote);
		}

		if (log.isDebugEnabled()) {
			log.debug(queryParams);
		}
		return queryParams;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		// TODO Auto-generated method stub
		params.put("codigoTipoCliente", Constants.CODIGO_TIPO_CLIENTE_DEFAULT);
		params.put("codigoTipoBloqueo", Constants.CODIGO_TIPO_BLOQUEO_DEFAULT);
		interfazSiCCDAO.executeInterfazIVREnviarMaestroClientes(params);
	}

}
