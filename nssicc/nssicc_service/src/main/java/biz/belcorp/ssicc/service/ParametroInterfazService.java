/*
 * Created on 12-dic-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.InterfazPK;
import biz.belcorp.ssicc.dao.sisicc.model.ParametroInterfaz;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="ParametroInterfazService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface ParametroInterfazService extends Service {
	/**
	 * Inserta un nuevo parametro a una interfaz.
	 * 
	 * @param parametroInterfaz
	 * Parametro a insertar
	 */
	public void insertParametroInterfaz(ParametroInterfaz parametroInterfaz, Usuario usuario);
	
	/**
	 * Elimina todos los parametros de una interfaz.
	 * 
	 * @param interfazPK
	 * Llave primaria de la interfaz.
	 */
	public void removeParametrosByPKInterfaz(InterfazPK interfazPK);
	
	/**
	 * Obtiene Todos los parametros de una interfaz.
	 * 
	 * @param interfazPK
	 * Llave primaria de la interfaz.
	 * 
	 * @return
	 * Lista de objetos ParametroInterfaz, poblados.
	 */
	public List getParametrosByPKInterfaz(InterfazPK interfazPK);

	/**
	 * Obtiene un parametro en base a un criterio.
	 * 
	 * @param criteria
	 * Criterio de busqueda.
	 * 
	 * @return
	 * Objeto de tipo ParametroInterfaz, poblado.
	 */
	public ParametroInterfaz getParametroByCriteria(ParametroInterfaz criteria);
}
