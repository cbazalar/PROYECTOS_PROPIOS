package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.dao.sisicc.InterfazRETDAO;
import biz.belcorp.ssicc.dao.sisicc.model.EstructuraArchivo;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazFormat;
import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazRETEnviarPagoComisionesRetailGZServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli </a>
 */
@Service("sisicc.interfazRETEnviarPagoComisionesRetailGZService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazRETEnviarPagoComisionesRetailGZServiceImpl extends
        BaseInterfazSalidaAbstractService {
	
	@Resource(name="sisicc.interfazRETDAO")
	private InterfazRETDAO interfazRETDAO;
    
	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#readData(java.util.Map)
     */
    protected List readData(Map queryParams) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'readData' method "+ queryParams);
        List result = null;
        try {
            result = interfazRETDAO.getInterfazRETEnviarPagoComisionesRetailGZ(queryParams);
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#proccesLine(java.lang.String, java.util.Map)
     */
    protected String proccesLine(String line,Map row) {
    	    String codigoPlanilla = (String)row.get("codigoPlanilla");
    	    int longCampo= codigoPlanilla.length();
    	    line=StringUtils.replace(line,line.substring(0,longCampo),codigoPlanilla);    	    
    	    return line;
        	
    }
    
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazSalidaAbstractService#beforeReadData(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
	 */
	protected void beforeReadData(InterfazParams interfazParams) {
		InterfazFormat interfazFormat = interfazFormatServiceFacade
		.getInterfazFormat(interfazParams.getInterfaz());
		
		List list =interfazFormat.getEstructurasArchivo();
		//campo obtener pos 0
		EstructuraArchivo ea= (EstructuraArchivo)list.get(0);
		int longCampo = ea.getLongitudCampo();		
		interfazParams.getQueryParams().put("longCampoPlanilla",String.valueOf(longCampo));
		
	 	
	}

}
