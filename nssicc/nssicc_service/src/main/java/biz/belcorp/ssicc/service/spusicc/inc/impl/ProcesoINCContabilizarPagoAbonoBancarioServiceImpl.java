package biz.belcorp.ssicc.service.spusicc.inc.impl;

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
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazProcesoAbstractService;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCContabilizarPagoAbonoBancarioDAO;

/**
 * Service que va a contabilizar Pago Abono Bancario
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Service("spusicc.procesoINCContabilizarPagoAbonoBancarioService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class ProcesoINCContabilizarPagoAbonoBancarioServiceImpl extends
	BaseInterfazProcesoAbstractService {
	                                     
	@Resource(name="spusicc.procesoINCContabilizarPagoAbonoBancarioDAO")
	private ProcesoINCContabilizarPagoAbonoBancarioDAO procesoINCContabilizarPagoAbonoBancarioDAO;
	
	@Resource(name="sisicc.sapConnectorService")
	private SapConnectorService sapConnectorService;

	/**
	 * @param procesoINCContabilizarPagoAbonoBancarioServiceDAO the procesoINCContabilizarPagoAbonoBancarioServiceDAO to set
	 */
	public void setProcesoINCContabilizarPagoAbonoBancarioDAO(
			ProcesoINCContabilizarPagoAbonoBancarioDAO procesoINCContabilizarPagoAbonoBancarioDAO) {
		this.procesoINCContabilizarPagoAbonoBancarioDAO = procesoINCContabilizarPagoAbonoBancarioDAO;
		
	}
	
	/**
	 * @param sapConnectorService
	 */
	public void setSapConnectorService(SapConnectorService sapConnectorService) {
		this.sapConnectorService = sapConnectorService;
	}
	
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
		procesoINCContabilizarPagoAbonoBancarioDAO.executeContabilizarPagoAbonoBancario(params);
	}	
	
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

			try {
				result = sapConnectorService.execute(funcionSAP, inputParams,null);
				
			} catch (Exception e) {
				log.warn(e.getMessage());
				throw new InterfazException(e.getMessage());
			}

			if (log.isDebugEnabled()) {
				log.debug("result SAP=" + result);
			}
		} else {
			log.info("La interfaz esta configurada para NO invocar a la funcion SAP");
		}
	}
	
}
