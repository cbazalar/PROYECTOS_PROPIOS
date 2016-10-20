package biz.belcorp.ssicc.service.sisicc.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.spusicc.reclamos.MantenimientoRECOperacionDAO;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * Service de la Interfaz REC-1 - Productos Reclamados.
 * 
 * @author <a href="mailto:msilva@belcorp.biz">Marco Silva</a>
 * 
 */
@Service("sisicc.interfazRECProductosReclamadosService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRECProductosReclamadosServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.sapConnectorService")
	private SapConnectorService sapConnectorService;
	
	@Resource(name="spusicc.reclamos.mantenimientoRECOperacionDAO")
	MantenimientoRECOperacionDAO mantenimientoRECOperacionDAO;
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO.executeInterfazRECProductosReclamados(params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected Map prepareQueryParams(InterfazParams interfazParams)	throws InterfazException {

		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Date date = new Date(timestamp.getTime() + (timestamp.getNanos() / 1000000));

		Map mapQueryParams = super.prepareQueryParams(interfazParams);
		String numeroLote = interfazParams.getNumeroLote();

		mapQueryParams.put("numeroLote", numeroLote);
		mapQueryParams.put("fechaEnvio", DateUtil.getDateTime(Constants.PATRON_FECHA_AAAAMMDD, date));

		if (log.isDebugEnabled()) {
			log.debug("mapQueryParams en RecService=" + mapQueryParams);
		}
		return mapQueryParams;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {

		log.debug("Entering 'beforeProcessInterfaz' method");
		Map queryParams = interfazParams.getQueryParams();
		mantenimientoRECOperacionDAO.insertRECProductosList(queryParams);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {

        Map result = null;
		Map inputParams = new HashMap();
        Map queryParams = interfazParams.getQueryParams();
        String invocarFuncionSAP = MapUtils.getString(queryParams, "invocarFuncionSAP", Constants.NO);
        String funcionSAP = MapUtils.getString(queryParams, "funcionSAP");
        String nombreParametro = MapUtils.getString(queryParams, "nombreParametro");
        String valorParametro = MapUtils.getString(queryParams, "valorParametro");
        String nombreParametro2 = MapUtils.getString(queryParams, "nombreParametro2");
        String valorParametro2 = MapUtils.getString(queryParams, "valorParametro2");
        
        boolean error = false;
        Exception errorException = new Exception();
        // Validamos si el parametro que determina si se invoca o no a SAP esta activo
		if (StringUtils.equals(invocarFuncionSAP, Constants.SI)) {
			if (log.isDebugEnabled()) {
				log.debug("Invocando a la funcion SAP: " + funcionSAP);
				log.debug(nombreParametro + "=" + valorParametro);
				log.debug(nombreParametro2 + "=" + valorParametro2);
			}

			inputParams.put(nombreParametro, valorParametro);
			inputParams.put(nombreParametro2, valorParametro2);
			
			try {
				result = sapConnectorService.execute(funcionSAP, inputParams,
						null);
			} catch (Exception e) {
				log.warn(e.getMessage());
				error = true;
				errorException = e;
			}
			

			if (log.isDebugEnabled()) {
				log.debug("result SAP=" + result);
			}
		} else {
			log.info("La interfaz esta configurada para NO invocar a la funcion SAP");
		}
		
		this.interfazSiCCService.executeProcesoRECActualizaUnidadesDevueltas02(queryParams);	
		if (error)
			throw new InterfazException(errorException.getMessage());
	}

}
