/*
 * Created on 21-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.service;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.BloqueoLider;
import biz.belcorp.ssicc.dao.sisicc.model.BloqueoLiderPK;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BloqueoLiderService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface BloqueoLiderService extends Service {


	/**
	 * Obtiene toda la lista de BloqueoLiders.
	 * 
	 * @param BloqueoLider
	 * 
	 * @return
	 * Lista de todos los BloqueoLiders obtenidos.
	 */
	public List getBloqueoLiders(BloqueoLider BloqueoLider);
	/**
	 * Retorna una lista de BloqueoLiders en base a los parametros a travez del map.
	 * 
	 * @param criteria
	 * Map donde se establcen los criterios para el filtro.
	 * 
	 * @return
	 * Lista de BloqueoLiders obtenidos segun el map.
	 */
	public List getBloqueoLidersByCriteria(Map criteria);
	/**
	 * Obtiene un BloqueoLider en base a su llave primaria.
	 * 
	 * @param primaryKey
	 * Llave primaria del BloqueoLider.
	 * 
	 * @return
	 * BloqueoLider, objeto poblado de tipo BloqueoLider. 
	 */
	public BloqueoLider getBloqueoLider(final BloqueoLiderPK primaryKey);
	/**
	 * Registra la informacin de un nuevo BloqueoLider.
	 * 
	 * @param BloqueoLider
	 * BloqueoLider a insertar.
	 * 
	 * @param usuario
	 * Usuario que esta insertando el BloqueoLider. 
	 */
	public void insertBloqueoLider(BloqueoLider BloqueoLider, Usuario usuario);
	/**
	 * Actualiza la informacin de un BloqueoLider existente.
	 * 
	 * @param BloqueoLider
	 * Informacin del BloqueoLider a actualizar.
	 * 
	 * @param usuario
	 * Usuario que esta realizando la actualizacin.
	 */
	public void updateBloqueoLider(BloqueoLider BloqueoLider, Usuario usuario);
	/**
	 * Elimina logicamente un BloqueoLider en base a su codigo.
	 * 
	 * @param primaryKey
	 * Llave primaria del BloqueoLider a eliminar.
	 * 
	 * @param usuario
	 * Usuario que esta eliminando el BloqueoLider.
	 */
	public void removeBloqueoLider(final BloqueoLiderPK primaryKey, Usuario usuario);
}
