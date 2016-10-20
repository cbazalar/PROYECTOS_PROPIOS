/*
 * Created on 03/10/2006 12:05:40 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazCOBEnviarDatosInfocorpServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.beans.InterfazParams;
import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaStoredProcedureAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazCOBEnviarDatosInfocorpServiceImpl.java.html"> <i>View Source
 * </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cmarius@belcorp.biz">Carla Marius </a>
 */
@Service("sisicc.interfazCOBEnviarDatosInfocorpService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazCOBEnviarDatosInfocorpServiceImpl extends
        BaseInterfazSalidaStoredProcedureAbstractService {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.service.framework.BaseInterfazAbstractService#beforeProcessInterfaz(biz.belcorp.ssicc.sisicc.service.framework.beans.InterfazParams)
     */
    protected void beforeProcessInterfaz(InterfazParams interfazParams)
            throws InterfazException {
        log.debug("Dentro del metodo 'beforeProcessInterfaz'");
        // Ejecutamos el procedimiento encargado de refrescar la informacion de
        // infocorp
        interfazSiCCDAO.executeInterfazCOBCargarDatosInfocorp(interfazParams
                .getQueryParams());
    }

    protected void executeStoreProcedure(Map params) {
        interfazSiCCDAO.executeInterfazCOBEnviarDatosInfocorp(params);
    }

}
