package biz.belcorp.ssicc.dao.spusicc.sto;


import java.util.List;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.sto.model.ParametroValidacion;


/**
 * Service con metodos para las consultas invocados por la pantalla de Parametro Validacion
 * 
 * <p>
 * <a href="MantenimientoSTOParametroValidacionDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 */
public interface MantenimientoSTOParametroValidacionDAO extends DAO {

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
	 * Inserta los datos al historcio Parametro Validacion.
	 * Se ejecuta al momento que se elimina el registro de la tabla Parametro Validacion.
	 * @param parametroValidacion
	 */
	public void insertHistoricoParametroValidacion(ParametroValidacion parametroValidacion);
	
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