/*
 * Created on 18/05/2006 12:22:44 PM
 * biz.belcorp.ssicc.scdf.service.ControlProcesoService
 */
package biz.belcorp.ssicc.service.scdf;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlProcesoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramrez </a>
 */
public interface ControlProcesoService extends Service {

    
    public void executeCierreProcesosDiarios(String codigoPais);
    
}
