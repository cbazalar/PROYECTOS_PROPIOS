/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.Delimitador;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="DelimitadorDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface DelimitadorDAO extends DAO {
    /**
     * Obtiene un listado de todas las Interfaces
     */
    public List getDelimitadores(Delimitador delimitador);
    
    /**
     * Obtiene un delimitador por codigo.
     * @param codigo
     * codigo del delimitaor
     * 
     * @return
     * Objeto Delimitador, poblado.
     */
    public Delimitador getDelimitador(String codigo);
}
