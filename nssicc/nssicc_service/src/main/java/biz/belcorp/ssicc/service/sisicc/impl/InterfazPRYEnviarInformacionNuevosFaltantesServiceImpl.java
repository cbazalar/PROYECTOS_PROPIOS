package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.sisicc.InterfazPRYDAO;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service del envio de datos para la Proyeccion Parcial de la Interfaz PRY.
 * 
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Service("sisicc.interfazPRYEnviarInformacionNuevosFaltantesService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazPRYEnviarInformacionNuevosFaltantesServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.sapBwConnectorService")
	private SapConnectorService sapConnectorService;

	@Resource(name="sisicc.interfazPRYDAO")
    private InterfazPRYDAO interfazPRYDAO;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazPRYDAO.executeInterfazPRYEnviarInformacionNuevosFaltantes(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)
			throws InterfazException {
        Map result = null;
		Map inputParams = new HashMap();
        Map queryParams = interfazParams.getQueryParams();
        String invocarFuncionSAP = MapUtils.getString(queryParams, "invocarFuncionSAP", Constants.NO);
        String funcionSAP = MapUtils.getString(queryParams, "funcionSAP");
        String nombreParametroEntrada = MapUtils.getString(queryParams, "nombreParametroEtrada");
        String valorParametroEntrada = MapUtils.getString(queryParams, "valorParametroEntrada");
        String nombreParametroSalida = MapUtils.getString(queryParams, "nombreParametroSalida");
        
        String outputParams[] = {nombreParametroSalida};
        
        // Validamos si el parametro que determina si se invoca o no a SAP esta activo
		if (StringUtils.equals(invocarFuncionSAP, Constants.SI)) {
			if (log.isDebugEnabled()) {
				log.debug("Invocando a la funcion SAP: " + funcionSAP);
				log.debug(nombreParametroEntrada + "=" + valorParametroEntrada);
			}

			inputParams.put(nombreParametroEntrada, valorParametroEntrada);

			try {
				result = sapConnectorService.execute(funcionSAP, inputParams, outputParams);
			} catch (Exception e) {
				log.warn(e.getMessage());
				throw new InterfazException(e.getMessage());
			}

			if (log.isDebugEnabled()) {
				log.debug("result SAP = " + result);
			}
		} else {
			log.info("La interfaz esta configurada para NO invocar a la funcion SAP");
		}
	}
}