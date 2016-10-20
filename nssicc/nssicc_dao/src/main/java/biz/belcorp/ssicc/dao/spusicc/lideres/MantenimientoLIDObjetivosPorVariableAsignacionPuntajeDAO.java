package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * 
 * <p>
 * <a href="MantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:etovar@csigcomt.com">Eder Tovar
 *         </a>
 */

public interface MantenimientoLIDObjetivosPorVariableAsignacionPuntajeDAO extends DAO {
	
	
	/**
	 * Metodo que obtiene la lista de tipos de asignacion.
	 * @return Lista de tipo de asignacion
	 */
	public List getTipoAsignacionList();
	
	/**
	 * Metodo que obtiente la lista de los objetivos de asignacin puntaje que coinciden
	 * con los parametros de bsqueda
	 * @param criteria
	 * @return Lista de ObjetivosAsignacionPuntaje
	 */
	public List getObjetivoAsignacionPuntajeList(Map criteria);
	
	/**
	 * Registra un nuevo Objetivo de asginacion Puntaje
	 * @param criteria
	 */	
	public void insertObjetivoAsignacionPuntaje(Map criteria);
	
	/**
	 * Actualiza un Objetivo de asginacion Puntaje
	 * @param criteria
	 */	
	public void udpateObjetivoAsignacionPuntaje(Map criteria);
	
	/**
	 * Mtodo que obtiene la cantidad de cruces que existe entre el periodo ingresado por parametro 
	 * y los rangos de periodos ya registrados en el sistema.
	 * @param criteria
	 */
	public Long getCantCrucesRangoPeriodo(Map criteria);
	
	/**
	 * Elimina un Objetivo de asginacion Puntaje
	 * @param criteria
	 */
	public boolean deleteObjetivoAsignacionPuntaje(Map criteria);
	
}
