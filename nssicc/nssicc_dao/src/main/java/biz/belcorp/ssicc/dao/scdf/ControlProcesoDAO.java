/*
 * Created on 18/05/2006 12:18:18 PM
 * biz.belcorp.ssicc.scdf.dao.ControlProcesoDAO
 */
package biz.belcorp.ssicc.dao.scdf;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * <p>
 * <a href="ControlProcesoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:cahurtado@belcorp.biz">Carlos Hurtado Ramírez </a>
 */
public interface ControlProcesoDAO extends DAO {

    /**
     * Se encarga de ejecutar los procesos de cierre del proceso global que se
     * ejecuta diariamente para un determinado pais.
     * 
     * @param codigoPais codigo del pais cuyos procesos estan siendo cerrados.
     */
    public void executeCierreProcesosDiarios(String codigoPais);

}
