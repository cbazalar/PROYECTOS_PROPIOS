/*
 * Created on 05-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.sisicc.model.TipoFormatoArchivo;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="TipoFormatoArchivoDAO.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface TipoFormatoArchivoDAO extends DAO {
    /**
     * Obtiene un listado de todos los tipos de formato de archivo existentes.
     */
    public List getTiposFormatoArchivo(TipoFormatoArchivo formato);

    /**
     * Obtiene un tipo de formato en base al codigo.
     * 
     * @param codigo
     * codigo del formato.
     * 
     * @return
     * Objeto Formato poblado.
     */
    public TipoFormatoArchivo getTipoFormatoArchivo(String codigo);

}
