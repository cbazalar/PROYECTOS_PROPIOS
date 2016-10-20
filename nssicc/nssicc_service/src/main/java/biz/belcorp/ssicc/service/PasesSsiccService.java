/*
 * Created on 20/04/2005 10:15:02 AM biz.belcorp.ssicc.service.RolService
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PasesSsiccService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez</a>
 */
public interface PasesSsiccService extends Service {

    /**
     * Obtiene los nombres de los esquemas de base de datos de los paises  
     * @return
     */
    public List getListaPaisMarca();
    
    /**
     * Obtiene la lista de Resultado de Log de Errores
     * @param criteria
     * @return
     */
    public List getResultadoLogErrores(Map criteria);
    
    /**
     * Obtiene la lista de Resultado de Ejecucion
     * @param criteria
     * @return
     */
    public List getResultadoEjecucion(Map criteria);
}
