/*
 * Created on 09/10/2006 04:30:00 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarZonasServiceImpl
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
 * <a href="InterfazMYEEnviarZonasServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
@Service("sisicc.interfazMYEEnviarZonasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYEEnviarZonasServiceImpl extends
        BaseInterfazSalidaAbstractService {

    /**
     * Obtiene la data para la Interfaz MYE_Enviar Zonas
     *
     * @param params
     *            Map con los parametros del query
     * @return List con Maps de las filas retornadas
     *
     * @throws InterfazException
     */
    public List readData(Map params) throws InterfazException {
        
        List listZonas = interfazSiCCDAO.getInterfazMYEEnviarZonas(params);        
        
        return listZonas;
    }

}
