/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarInscritasServiceImpl
 */
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
 * <a href="InterfazSAMEnviarReservaPROLServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Sergio Buchelli</a>
 */
@Service("sisicc.interfazSAMEnviarCantidadProductoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAMEnviarCantidadProductoServiceImpl extends	BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazSAMDAO")
	protected InterfazSAMDAO interfazSAMDAO;
	
	@Resource(name="sisicc.sapConnectorService")
	private SapConnectorService sapConnectorService;


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazSAMDAO.executeInterfazSAMEnviarCantidadProducto(params);
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
	        String nombreParametro = MapUtils.getString(queryParams, "nombreParametro");
	        String valorParametro = MapUtils.getString(queryParams, "valorParametro");
	        
	        String nombreParametro2= MapUtils.getString(queryParams, "nombreParametro2");
	        String valorParametro2 = MapUtils.getString(queryParams, "valorParametro2");
	        
	        // Validamos si el parametro que determina si se invoca o no a SAP esta activo
			if (StringUtils.equals(invocarFuncionSAP, Constants.SI)) {
				if (log.isDebugEnabled()) {
					log.debug("Invocando a la funcion SAP: " + funcionSAP);
					log.debug(nombreParametro + "=" + valorParametro);
				}

	            if(StringUtils.isNotBlank(nombreParametro)) {
	                inputParams.put(nombreParametro, valorParametro);
	            }
	            
	            if(StringUtils.isNotBlank(nombreParametro2)) {
	                inputParams.put(nombreParametro2, valorParametro2);
	            }

				try {
					result = sapConnectorService.execute(funcionSAP, inputParams, null);
				} catch (Exception e) {
					log.error("Error al invocar a SAP" + e);
					throw new InterfazException("Error al invocar a SAP"+e);
				}

				if (log.isDebugEnabled()) {
					log.debug("result SAP=" + result);
				}
			} else {
				log.info("La interfaz esta configurada para NO invocar a la funcion SAP");
			}
	    }
}