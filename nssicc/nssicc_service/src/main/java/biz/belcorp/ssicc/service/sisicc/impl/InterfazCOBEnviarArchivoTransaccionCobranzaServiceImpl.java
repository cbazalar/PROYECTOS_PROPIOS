package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazCOBDAO;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * <p>
 * <a href="InterfazCOBEnviarArchivoTransaccionCobranzaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:nlopez@csigcomt.com">Nicols Lpez</a>
 */
@Service("sisicc.interfazCOBEnviarArchivoTransaccionCobranzaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarArchivoTransaccionCobranzaServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;
	
   
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#adicionarKeyTituloCabecera()
	 */
	protected String adicionarKeyTituloCabecera() {
		return "interfazCOBEnviarArchivoTransaccionCobranzaTituloCabecera";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#prepareQueryParams(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	public Map prepareQueryParams(InterfazParams interfazParams) throws InterfazException {
		if (log.isDebugEnabled()) {
		    log.debug("Dentro del metodo 'prepareQueryParams'");
		}
		// Obtenemos los parametros por defecto de la clase padre
		Map queryParams = super.prepareQueryParams(interfazParams);
		
		String anhio = (String)queryParams.get("anhio");
	    String mes = (String)queryParams.get("mes");
	    String soc = (String)queryParams.get("sociedad");
	    String nombreArchivo = "SC_" + soc + "_01_" + anhio + mes;
	    
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
    	interfazCOBDAO.executeInterfazCOBEnviarArchivoTransaccionCobranza(params);   	
    }
    
}
