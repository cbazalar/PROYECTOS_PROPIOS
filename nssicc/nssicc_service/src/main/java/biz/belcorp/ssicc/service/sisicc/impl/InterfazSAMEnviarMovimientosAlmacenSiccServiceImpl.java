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
import biz.belcorp.ssicc.dao.sisicc.InterfazSAMDAO;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * 
 * <p>
 * <a href="InterfazSAMEnviarMovimientosAlmacenSiccServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:doliva@belcorp.biz">Dennys Oliva Iriarte</a>
 */
@Service("sisicc.interfazSAMEnviarMovimientosAlmacenSiccService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMEnviarMovimientosAlmacenSiccServiceImpl extends	BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
	
	@Resource(name="sisicc.sapConnectorService")
	private SapConnectorService sapConnectorService;
	
	private int numeroRegistros;
	
	
		
	/**
	 * Devuelve el Map de parametros del query. Sobreescrito con la finalidad de
	 * obtener los 'Parametros de Interaz' configurados en el mantenimiento de
	 * la interfaz
	 * 
	 * @param interfazParams
	 *            parametros de la interfaz
	 * @return Map con parametros del query
	 */
	public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
        if (log.isDebugEnabled()) {
            log.debug("Dentro del metodo 'prepareQueryParams'");
        }
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
						 
		return queryParams;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled())
			log.debug("Entering 'beforeProcessInterfaz' method");
		Map queryParams = super.prepareQueryParams(interfazParams);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
			
		if(numeroRegistros==0){
			interfazSAMDAO.executeInterfazSAMEnviarMovimientosAlmacenSicc(params);
			params.put("resultadoInterfaz","0");
		}
		else
			params.put("resultadoInterfaz","1");
			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void afterProcessInterfaz(InterfazParams interfazParams)	throws InterfazException {
		Map result = null;
        Map inputParams = new HashMap();
        Map queryParams = interfazParams.getQueryParams();
        String invocarFuncionSAP = MapUtils.getString(queryParams, "invocarFuncionSAP", Constants.SI);
        String funcionSAP = MapUtils.getString(queryParams, "funcionSAP");
        String nombreParametro = MapUtils.getString(queryParams, "nombreParametro");
        String valorParametro = MapUtils.getString(queryParams, "valorParametro");
        String nombreParametro2 = MapUtils.getString(queryParams, "nombreParametro2");
        String valorParametro2 = MapUtils.getString(queryParams, "valorParametro2");
        String ejecucionAsincrono = MapUtils.getString(queryParams, "ejecucionAsincronoSAP");
        
        
        // Validamos si el parametro que determina si se invoca o no a SAP esta activo
		if (StringUtils.equals(invocarFuncionSAP, Constants.SI)) {
			if (log.isDebugEnabled()) {
				log.debug("Invocando a la funcion SAP: " + funcionSAP);
				log.debug(nombreParametro + "=" + valorParametro);
				log.debug(nombreParametro2 + "=" + valorParametro2);
			}

            if(StringUtils.isNotBlank(nombreParametro)) {
                inputParams.put(nombreParametro, valorParametro);
            }
                       
            if(StringUtils.isNotBlank(nombreParametro2)) {
                inputParams.put(nombreParametro2, valorParametro2);
            }

            if (StringUtils.equals(ejecucionAsincrono, Constants.SI)) {
                SAPThread sapThread = new SAPThread(funcionSAP, inputParams);
                sapThread.start();
            } else{
				try {
					result = sapConnectorService.execute(funcionSAP, inputParams,null);
					
				} catch (Exception e) {
					log.warn(e.getMessage());
					throw new InterfazException(e.getMessage());
				}
	
				if (log.isDebugEnabled()) {
					log.debug("result SAP=" + result);
				}
            }	
		} else {
			log.info("La interfaz esta configurada para NO invocar a la funcion SAP");
		}
	}
	
	//Clase que ejecutara en forma asincrona el llamado a SAP
    class SAPThread extends Thread {

    	Map result = null;
    	String funcionSAP;
    	Map inputParams;
    	
		public SAPThread(String funcionSAP, Map inputParams) {
			this.funcionSAP = funcionSAP;
			this.inputParams = inputParams;
		}
		
		public void run() {
			log.debug("Invocando a la funcion SAP en forma asincrona :" + funcionSAP);
			
			try {
				result = sapConnectorService.execute(funcionSAP, inputParams, null);
				
			} catch (Exception e) { 
				log.error(e.getMessage());
			}

			if (log.isDebugEnabled()) {
				log.debug("result SAP en forma asincrona=" + result);
			}
		}
    }
}
