package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Calendar;
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
 * @author Jesse James Rios Franco
 *
 */
@Service("sisicc.interfazCOBEnviarRecuperacionCobranzaPorCobradorService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarRecuperacionCobranzaPorCobradorServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService{
	
	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaStoredProcedureAbstractService#executeStoreProcedure(java.util.Map)
	 */
	protected void executeStoreProcedure(Map params) {
		interfazCOBDAO.executeInterfazCOBEnviarRecuperacionCobranzaPorCobrador(params);
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
	    
	    String codigoSistema = (String)queryParams.get("codigoSistema");
	    String paisMarca = (String)queryParams.get("codigoPais");
	    String anhio = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	    String mes = (Calendar.getInstance().get(Calendar.MONTH) + 1) < 10 ? "0"+String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1) : String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1);
	    String dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) < 10 ? "0"+String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) : String.valueOf(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
	    String hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 10 ? "0"+String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) : String.valueOf(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
	    String minutos = Calendar.getInstance().get(Calendar.MINUTE) < 10 ? "0"+String.valueOf(Calendar.getInstance().get(Calendar.MINUTE)) : String.valueOf(Calendar.getInstance().get(Calendar.MINUTE));
	    
	    String nombreArchivo = codigoSistema+"_"+paisMarca+"_"+anhio+mes+dia+"_"+hora+minutos;
	    queryParams.put("nombreArchivo", nombreArchivo);
		
	    // Actualizamos tambien el objeto interfaz
        interfazParams.getInterfaz().setNombreArchivoEntradaSalida(nombreArchivo);
       
        if (log.isDebugEnabled()) {
			log.debug(queryParams);
            log.debug(interfazParams.getInterfaz().getNombreArchivoEntradaSalida());
		}
        
		return queryParams;
	}
}