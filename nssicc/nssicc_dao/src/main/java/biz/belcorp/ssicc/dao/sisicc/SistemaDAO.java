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
import biz.belcorp.ssicc.dao.sisicc.model.Sistema;
import biz.belcorp.ssicc.dao.sisicc.model.SistemaPK;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="SistemaDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:itocto@belcorp.biz">Victorino Ivan Tocto Jaimes</a>
 * 
 */

public interface SistemaDAO extends DAO {
	/**
	 * Obtiene un listado de todos los Sistemas.
	 * 
	 * @param sistema
	 * @return Lista de objetos Sistema poblados.
	 */
	public List getSistemas(Sistema sistema);	
	/**
	 * Obtiene un listado de todos los paises en base a ciertos criterios los
	 * cuales son enviados atra vez de un Map.
	 * 
	 * @param criteria
	 * Objeto Map cuyos atributos son usados como criterios de busqueda.
	 *	 
	 * @return Lista de objetos Sistema poblados.
	 */
	public List getSistemasByCriteria(Map criteria);
	
	public List getSistemasByCriteria2(Map criteria) ;
	
	/**
	 * Obtiene la informacion del sistema en base a su llave primaria. 
	 * La excepcion ObjectRetrievalFailureException Runtime Exception es lanzada si no es
	 * encontrada.
	 * 
	 * @param primaryKey
	 * Llave primaria del sistema
	 * 
	 * @return Objeto PAis poblado.
	 */
	public Sistema getSistema(final SistemaPK primaryKey);
	/**
	 * Registra la informacin de un nuevo sistema.
	 * 
	 * @param sistema
	 * Sistema a ser insertado.
	 * 
	 * @param usuario
	 * Usuario que esta insertando el sistema.
	 */
	public void insertSistema(Sistema sistema, Usuario usuario);
	/**
	 * Actualiza la informacin de un sistema.
	 *  
	 * @param sistema
	 * Sistema a ser actualizado.
	 * 
	 * @param usuario
	 * Usuario que esta haciendo la actualizacion.
	 */
	public void updateSistema(Sistema sistema, Usuario usuario);
	/**
	 * Elimina un sistema de la base de datos.
	 * 
	 * @param primaryKey
	 * Llave primaria del sistema a eliminar.
	 */
	public void removeSistema(final SistemaPK primaryKey);
}
