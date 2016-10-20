package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface MantenimientoINCBloqueoPremiosDAO extends DAO {	

	/**
	 * retorna la lista de Concursos para Bloqueo de Premios
	 * 
	 * @return
	 */
	public List getConcursosBloqueoPremios(Map criteria);

	/**
	 * Retorna la lista de premios de un determinado Concurso
	 *  
	 * @param codigoConcurso
	 * @return
	 */
	public List getPremiosxConcurso(Map criteria);
	
	/**
	 * retorna la lista de regiones que manejaran Bloqueo de Premios
	 * 
	 * @return
	 */
	public List getRegionesBloqueoPremios();
	
	/**
	 *  retorna la lista de Bloqueo de Premios 
	 *  
	 * @param params
	 * @return
	 */
	public List getBloqueoPremios(Map criteria);
	
	/**
	 * Inserta registro a la entidad Bloqueo de Premios x Concurso
	 * 
	 * @param params
	 */
	public void insertBloqueoPremios(Map params);
	
	/**
	 * Verifica si ya existe un premio registrado como Bloqueo
	 * 
	 * @return
	 */	
	public boolean existeBloqueoPremio(Map criteria);
	
	/**
	 * Actualiza registro a la entidad Bloqueo de Premios x Concurso
	 * 
	 * @param params
	 */
	public void updateBloqueoPremios(Map params);
	
	/**
	 * Elimina registro a la entidad Bloqueo de Premios x Concurso
	 * 
	 * @param calificacionComisionDetalle
	 */
	public void deleteBloqueoPremios(Map params);
	
}
