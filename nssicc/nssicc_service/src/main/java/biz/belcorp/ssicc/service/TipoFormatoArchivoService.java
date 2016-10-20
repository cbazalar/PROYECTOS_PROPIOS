/*
 * Created on 05-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;

import biz.belcorp.ssicc.dao.sisicc.model.TipoFormatoArchivo;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="TipoFormatoArchivoService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface TipoFormatoArchivoService extends Service {
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
