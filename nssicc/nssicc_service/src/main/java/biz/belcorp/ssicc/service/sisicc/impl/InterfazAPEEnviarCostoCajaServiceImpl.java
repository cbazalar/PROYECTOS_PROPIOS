package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazAPEDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * <p>
 * <a href="InterfazAPEEnviarCostoCajaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazAPEEnviarCostoCajaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazAPEEnviarCostoCajaServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {
	
	@Resource(name="sisicc.interfazAPEDAO")
	protected InterfazAPEDAO interfazAPEDAO;
	
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled()) {
		    log.debug("Dentro del metodo 'prepareQueryParams'");
		}
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		String nombreArchivo = MapUtils.getString(queryParams, "nombreArchivo");
		
		Map params = new HashMap();
		params = (Map)(interfazAPEDAO.getPaisMarcaSat(queryParams).get(0));
		
		String codigoMarca   = params.get("codigoMarca").toString();
		String codigoPaisSAT = params.get("codigoPaisSAT").toString();
		
		nombreArchivo = nombreArchivo + codigoPaisSAT + codigoMarca;
		
		queryParams.put("nombreArchivo", nombreArchivo);
		 // Actualizamos tambien el objeto interfaz
        interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
		
		if (log.isDebugEnabled()) {
			log.debug(queryParams);
            log.debug(interfazParams.getInterfaz().getNombreArchivoEntradaSalida());
		}
		
		return queryParams;
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
     */
    protected void executeStoreProcedure(Map params) {
    	interfazAPEDAO.executeInterfazAPEEnviarCostoCaja(params);    	
    }    
    
}
