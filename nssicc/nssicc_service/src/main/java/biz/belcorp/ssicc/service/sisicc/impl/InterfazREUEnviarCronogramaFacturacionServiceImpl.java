/*
 * Created on 26/09/2006 09:53:35 AM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazREUEnviarCronogramaFacturacionServiceImpl
 */
package biz.belcorp.ssicc.service.sisicc.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import biz.belcorp.ssicc.service.sisicc.framework.exception.InterfazException;
import biz.belcorp.ssicc.service.sisicc.framework.impl.BaseInterfazSalidaAbstractService;

/**
 * TODO Include class description here.
 * <p>
 * <a href="InterfazREUEnviarCronogramaFacturacionServiceImpl.java.html">
 * <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazREUEnviarCronogramaFacturacionService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazREUEnviarCronogramaFacturacionServiceImpl extends
        BaseInterfazSalidaAbstractService {

    /*
     * (non-Javadoc)
     * 
     * @see biz.belcorp.ssicc.sisicc.service.BaseInterfazSalidaAbstractService#readData(java.util.Map)
     */
    protected List readData(Map queryParams) throws InterfazException {
        if (log.isDebugEnabled())
            log.debug("Entering 'readData' method ");
        List listCronogramaFacturacion = null;
        try {
            listCronogramaFacturacion = this.interfazSiCCDAO
                    .getInterfazREUCronogramaFacturacion(queryParams);
        } catch (Exception e) {
            log.error("Error al leer los datos: " + e.getMessage());
            throw new InterfazException(e.getMessage());
        }
        return listCronogramaFacturacion;
    }

}
