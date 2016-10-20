package biz.belcorp.ssicc.service.spusicc.sto;


import java.util.List;

import biz.belcorp.ssicc.dao.spusicc.sto.model.ParametroValidacion;
import biz.belcorp.ssicc.service.framework.Service;


/**
 * Service con metodos para las consultas invocados por la pantalla de Parametro Validacion
 * 
 * <p>
 * <a href="MantenimientoSTOParametroValidacionService.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 */
public interface MantenimientoSTOParametroValidacionService extends Service {

	/**
	 * Retorna la lista de parametros validacion
	 * @param parametroValidacion
	 * @return
	 */
	public List getParametroValidacion(ParametroValidacion parametroValidacion);
	
	/**
	 * Inserta datos al Parametro Validacion.
	 * @param parametroValidacion
	 */
	public void insertParametrValidacion(ParametroValidacion parametroValidacion);

	/**
	 * Actualiza el registro del Parametro Validacion.
	 * @param parametroValidacion
	 */
	public void updateParametroValidacion(ParametroValidacion parametroValidacion);
	
	/**
	 * Elimina el registro del Parametro Validacion.
	 * @param parametroValidacion
	 */
	public void deleteParametroValidacion(ParametroValidacion parametroValidacion);
}