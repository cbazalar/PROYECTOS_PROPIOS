/*
 * Created on 18-nov-2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.BloqueoLider;
import biz.belcorp.ssicc.dao.sisicc.model.BloqueoLiderPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="BloqueoLiderDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface BloqueoLiderDAO extends DAO {
	/**
	 * Obtiene un listado de todos los BloqueoLiders.
	 * 
	 * @param bloqueoLider
	 * @return Lista de objetos BloqueoLider poblados.
	 */
	public List getBloqueoLiders(BloqueoLider bloqueoLider);	
	/**
	 * Obtiene un listado de todos los paises en base a ciertos criterios los
	 * cuales son enviados atra vez de un Map.
	 * 
	 * @param criteria
	 * Objeto Map cuyos atributos son usados como criterios de busqueda.
	 *	 
	 * @return Lista de objetos BloqueoLider poblados.
	 */
	public List getBloqueoLidersByCriteria(Map criteria);
	/**
	 * Obtiene la informacion del bloqueoLider en base a su llave primaria. 
	 * La excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si no es
	 * encontrada.
	 * 
	 * @param primaryKey
	 * Llave primaria del bloqueoLider
	 * 
	 * @return Objeto PAis poblado.
	 */
	public BloqueoLider getBloqueoLider(final BloqueoLiderPK primaryKey);
	/**
	 * Registra la informacin de un nuevo bloqueoLider.
	 * 
	 * @param bloqueoLider
	 * BloqueoLider a ser insertado.
	 * 
	 * @param usuario
	 * Usuario que esta insertando el bloqueoLider.
	 */
	public void insertBloqueoLider(BloqueoLider bloqueoLider, Usuario usuario);
	/**
	 * Actualiza la informacin de un bloqueoLider.
	 *  
	 * @param bloqueoLider
	 * BloqueoLider a ser actualizado.
	 * 
	 * @param usuario
	 * Usuario que esta haciendo la actualizacion.
	 */
	public void updateBloqueoLider(BloqueoLider bloqueoLider, Usuario usuario);
	/**
	 * Elimina un bloqueoLider de la base de datos.
	 * 
	 * @param primaryKey
	 * Llave primaria del bloqueoLider a eliminar.
	 */
	public void removeBloqueoLider(final BloqueoLiderPK primaryKey);
}
