/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;

import biz.belcorp.ssicc.dao.sisicc.model.Delimitador;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="DelimitadorService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface DelimitadorService extends Service {

    /**
     * Obtiene un listado de todos los delimitadores
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
