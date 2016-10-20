/*
 * Created on 18/01/2007 07:20:02 PM
 * biz.belcorp.ssicc.spisicc.service.framework.ProcesoImpresionExecutionService
 */
package biz.belcorp.ssicc.service.spisicc.framework;

import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ProcesoImpresionExecutionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface ProcesoImpresionExecutionService {

    public void executeProcesoImpresion(Map params, Usuario usuario);
    
}
