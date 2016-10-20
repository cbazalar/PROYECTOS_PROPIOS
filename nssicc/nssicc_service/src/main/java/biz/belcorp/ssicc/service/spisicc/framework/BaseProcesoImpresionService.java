/*
 * Created on 18/01/2007 05:24:07 PM
 * biz.belcorp.ssicc.spisicc.service.framework.BaseProcesoImpresionService
 */
package biz.belcorp.ssicc.service.spisicc.framework;

import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;

/**
 * TODO Include class description here.
 * <p>
 * <a href="BaseProcesoImpresionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface BaseProcesoImpresionService {

    public void executeProcesoImpresion(Map params, Usuario usuario);
    
}
