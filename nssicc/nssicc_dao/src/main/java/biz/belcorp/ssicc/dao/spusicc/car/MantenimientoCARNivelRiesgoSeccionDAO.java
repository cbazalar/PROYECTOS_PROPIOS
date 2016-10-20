package biz.belcorp.ssicc.dao.spusicc.car;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface MantenimientoCARNivelRiesgoSeccionDAO extends DAO {
	
	/**
	 * Obtiene la lista de Secciones, indicando quienes tienen asociado Nivel Riesgo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getNivelRiesgoSeccionList(Map criteria);

	/**
	 * Obtiene la lista de Niveles de Riesgo
	 * 
	 * @param criteria
	 * @return
	 */
	public List getNivelesRiesgos(Map criteria);

	/**
	 * Valida si ya esta registrado el Niveles de Riesgo x Seccion
	 * 
	 * @param criteria
	 * @return
	 */
	public boolean existeNivelRiesgoSeccion(Map params);
	
	/**
	 * Inserta un nuevo registro en la entidad Nivel Riesgo x Seccion
	 * 
	 * @param params
	 */
	public void insertNivelRiesgoSeccion(Map params);
	
	/**
	 * Actualiza un registro en la entidad Nivel Riesgo x Seccion
	 * 
	 * @param params
	 */
	public void updateNivelRiesgoSeccion(Map params);
	
}
