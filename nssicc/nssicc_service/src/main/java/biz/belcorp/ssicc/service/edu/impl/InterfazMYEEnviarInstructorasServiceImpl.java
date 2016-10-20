/*
 * Created on 09/10/2006 04:27:06 PM
 * biz.belcorp.ssicc.sisicc.service.impl.InterfazMYEEnviarRegionesServiceImpl
 */
package biz.belcorp.ssicc.service.edu.impl;

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
 * <a href="InterfazMYEEnviarInstructorasServiceImpl.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa </a>
 */
@Service("sisicc.interfazMYEEnviarInstructorasService")
@Transactional(propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
public class InterfazMYEEnviarInstructorasServiceImpl extends
        BaseInterfazSalidaAbstractService {

    /**
     * Obtiene la data para la Interfaz MYE_Enviar Instructoras
     *
     * @param params
     *            Map con los parametros del query
     * @return List con Maps de las filas retornadas
     *
     * @throws InterfazException
     */
    public List readData(Map params) throws InterfazException {
        
        List lista = interfazSiCCDAO.getInterfazMYEEnviarInstructoras(params);        
        
        return lista;
    }
    
}
