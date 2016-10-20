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
import biz.belcorp.ssicc.dao.sisicc.InterfazSABDAO;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service InterfazSABEnviarMaestroZonasServiceImpl.
 * 
 * @author <a href="mailto:">Sergio Buchelli</a>
 */
@Service("sisicc.interfazSABEnviarMaestroZonasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSABEnviarMaestroZonasServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazSABDAO")
	private InterfazSABDAO interfazSABDAO;
	
	@Resource(name="sisicc.sapBpsConnectorService")
	private SapConnectorService sapConnectorService;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSABDAO
				.executeInterfazVENEnviarMaestroZonas(params);
	}	

	protected void afterProcessInterfaz(InterfazParams interfazParams)
    throws InterfazException {
		Map result = null;
		Map inputParams = new HashMap();
		Map queryParams = interfazParams.getQueryParams();
		String invocarFuncionSAP = MapUtils.getString(queryParams, "invocarFuncionSAP", Constants.NO);
		String funcionSAP = MapUtils.getString(queryParams, "funcionSAP");
		String nombreParametro = MapUtils.getString(queryParams, "nombreParametro");
		String valorParametro = MapUtils.getString(queryParams, "valorParametro");
 		// Validamos si el parametro que determina si se invoca o no a SAP esta activo
		if (StringUtils.equals(invocarFuncionSAP, Constants.SI)) {
			if (log.isDebugEnabled()) {
				log.debug("Invocando a la funcion SAP: " + funcionSAP);
				log.debug(nombreParametro + "=" + valorParametro);
			}
		
		    if(StringUtils.isNotBlank(nombreParametro)) {
		        inputParams.put(nombreParametro, valorParametro);
		    }
		
			try {
				result = sapConnectorService.execute(funcionSAP, inputParams,
						null);
			} catch (Exception e) {
				log.warn(e.getMessage());
				throw new InterfazException(e.getMessage());
			}
		
			if (log.isDebugEnabled()) {
				log.debug("result SAP=" + result);
			}
		} else {
			log
					.info("La interfaz esta configurada para NO invocar a la funcion SAP");
		}
	}
}