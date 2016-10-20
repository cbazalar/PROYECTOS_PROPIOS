/**
 * 
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.service.SapConnectorService;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;

/**
 * Service de la Interfaz Envio Informacion SAP-FI Pagos Concursos Ventas
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto</a>
 */
@Service("sisicc.interfazSAPFIEnviarInformacionPagosConcursoVentasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAPFIEnviarInformacionPagosConcursoVentasServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazSAFDAO")
	protected InterfazSAFDAO interfazSAFDAO;
	
	@Resource(name="sisicc.sapSafFiConnectorService")
	private SapConnectorService sapConnectorService;
	
	/**
	 * @param interfazSAFDAO the interfazSAFDAO to set
	 */
	public void setInterfazSAFDAO(InterfazSAFDAO interfazSAFDAO) {
		this.interfazSAFDAO = interfazSAFDAO;
	}

	/**
	 * @param sapConnectorService the sapConnectorService to set
	 */
	public void setSapConnectorService(SapConnectorService sapConnectorService) {
		this.sapConnectorService = sapConnectorService;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSAFDAO.executeInterfazSAPFIEnviarInformacionPagosConcursoVentas(params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#afterProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	@Override
	protected void afterProcessInterfaz(InterfazParams interfazParams) throws InterfazException {
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
