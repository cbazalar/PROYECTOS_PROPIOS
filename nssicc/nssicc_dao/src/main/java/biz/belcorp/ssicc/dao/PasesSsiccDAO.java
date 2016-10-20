/*
 * Created on 25/02/2005 12:28:50 PM biz.belcorp.ssicc.dao.RolDAO
 */
package biz.belcorp.ssicc.dao;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="PasesSsiccDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="">Jose Luis Rodriguez </a>
 */
public interface PasesSsiccDAO extends DAO {

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