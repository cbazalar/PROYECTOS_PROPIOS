package biz.belcorp.ssicc.service.spusicc.car;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * TODO Include class description here.
 * <p>
 * <a href="MantenimientoCARNivelRiesgoSeccionService.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza </a>
 */
public interface MantenimientoCARNivelRiesgoSeccionService extends Service {

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
	 * Actualiza un registro en la entidad Nivel Riesgo x Seccion
	 * 
	 * @param params
	 */
	public void updateNivelRiesgoSeccion(Map params);
	
}
