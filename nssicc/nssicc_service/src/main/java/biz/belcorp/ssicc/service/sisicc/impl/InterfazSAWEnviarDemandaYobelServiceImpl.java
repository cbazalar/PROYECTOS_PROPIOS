/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazSICEnviarInscritasServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazSAWDAO;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * 
 * <p>
 * <a href="InterfazSAWEnviarReservaMAVServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:croman@belcorp.biz">Cristhian Roman</a>
 */
@Service("sisicc.interfazSAWEnviarDemandaYobelService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazSAWEnviarDemandaYobelServiceImpl extends	BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazSAWDAO")
	protected InterfazSAWDAO interfazSAWDAO;
	
	
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
		
		return queryParams;
	}
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		
		interfazSAWDAO.executeInterfazSAWEnviarDemandaYobel(params);

	}
	


	
}
