/*
 * Created on 27/09/2006 05:56:59 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazBELEnviarPorcentajesReferenciaServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;


/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazBELEnviarPorcentajesReferenciaServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazBELEnviarPorcentajesReferenciaService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazBELEnviarPorcentajesReferenciaServiceImpl extends
		BaseInterfazSalidaStoredProcedureAbstractService {

	protected void executeStoreProcedure(Map params) {
		interfazSiCCDAO
				.executeInterfazBELEnviarPorcentajesReferencia(params);
	}

    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
     */
/*	
    protected List readData(Map queryParams) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'readData' method ");

        // Obtenemos la informacin a generar
        List list = null;
        try {
            list = interfazSiCCDAO
                    .getInterfazBELPorcentajesReferencia(queryParams);
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return list;
    }
*/
}
