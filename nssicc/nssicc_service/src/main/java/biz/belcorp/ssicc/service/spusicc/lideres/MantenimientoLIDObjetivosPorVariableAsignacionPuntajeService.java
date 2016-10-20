package biz.belcorp.ssicc.service.spusicc.lideres;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * 
 * <p>
 * <a href="MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService.java.html"> <i>View
 * Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:etovar@csigcomt.com">Eder Tovar
 *         </a>
 */

public interface MantenimientoLIDObjetivosPorVariableAsignacionPuntajeService extends Service{

	/**
	 * Metodo que obtiene la lista de tipos de asignacion.
	 * @return Lista de tipo de asignacion
	 */
	public List getTipoAsignacionPuntajeList();
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
	public boolean insertObjetivoAsignacionPuntaje(Map criteria);
	/**
	 * Actualiza un Objetivo de asginacion Puntaje
	 * @param criteria
	 */	
	public boolean udpateObjetivoAsignacionPuntaje(Map criteria);
	/**
	 * Elimina un Objetivo de asginacion Puntaje
	 * @param criteria
	 */
	public boolean deleteObjetivoAsignacionPuntaje(Map criteria);
	
}
