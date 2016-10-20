/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazCOBEnviarCobranzaSaldoPendienteServiceImpl
 */
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
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOBEnviarCobranzaPeriodoZonaServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
@Service("sisicc.interfazCOBEnviarCobranzaPeriodoZonaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarCobranzaPeriodoZonaServiceImpl extends BaseInterfazSalidaStoredProcedureAbstractService {

	@Resource(name="sisicc.interfazCOBDAO")
	protected InterfazCOBDAO interfazCOBDAO;
	
   

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#adicionarKeyTituloCabecera()
	 */
	protected String adicionarKeyTituloCabecera() {
		//return "interfazCOBEnviarCobranzaSaldoPendienteTituloCabecera";
		return "interfazCOBEnviarCobranzaPeriodoZonaTituloCabecera";
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
		
		String anho = (String)queryParams.get("anho");
	    String mes = (String)queryParams.get("mes");
	    String soc = (String)queryParams.get("sociedad");
	    
	    String lsAnhio = "";
	    String lsMes = "";
	    
	    if (mes.equals("01")){
	    	lsAnhio = String.valueOf((Integer.parseInt(anho)-1));
	    	lsMes = "12";
	    }else{
	    	lsAnhio = anho;
	    	lsMes = String.valueOf((Integer.parseInt(mes) - 1));
	    	
	    	if (lsMes.length()==1){
	    		lsMes = "0" + lsMes;
	    	}
	    }
	    
	    String nombreArchivo = "SC_" + soc + "_03_" + lsAnhio + lsMes;
	    	
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
    	log.debug("Dentro del metodo 'executeStoreProcedure'");
    	interfazCOBDAO.executeInterfazCOBEnviarCobranzaPeriodoZona(params);
    }
}