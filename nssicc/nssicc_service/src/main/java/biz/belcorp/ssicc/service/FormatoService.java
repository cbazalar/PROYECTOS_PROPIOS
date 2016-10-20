/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;

import biz.belcorp.ssicc.dao.sisicc.model.Formato;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="FormatoService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface FormatoService extends Service {
	
    /**
     * Obtiene un listado de todos los Formatos
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
