/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.Formato;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="FormatoDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface FormatoDAO extends DAO {
    /**
     * Obtiene un listado de todas las Interfaces
     */
    public List getFormatos(Formato formato);

    /**
     * Obtiene un Formato en base al codigo.
     * 
     * @param codigo
     * codigo del formato.
     * 
     * @return
     * Objeto Formato poblado.
     */
    public Formato getFormato(String codigo);
}
