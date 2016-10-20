package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * Service InterfazDATEnviarIncentivosEstadoConcursoServiceImpl.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
@Service("sisicc.interfazDATEnviarIncentivosEstadoConcursoService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazDATEnviarIncentivosEstadoConcursoServiceImpl extends     BaseInterfazSalidaAbstractService {


    protected List readData(Map queryParams) throws InterfazException {
        if (log.isDebugEnabled())  log.debug("Entering 'readData' method ");
        List result = null;
        try {            
            result = this.interfazSiCCDAO.getInterfazDATEnviarIncentivosEstadoConcurso(queryParams);
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return result;
    }

}