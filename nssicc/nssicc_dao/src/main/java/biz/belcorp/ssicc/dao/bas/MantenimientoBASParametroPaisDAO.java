package biz.belcorp.ssicc.dao.bas;

import java.util.List;

import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * Service con metodos para las consultas invocados por la pantalla de Parametro Pais
 * 
 * <p>
 * <a href="MantenimientoBASParametroPaisDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="">Yahir Rivas Luna</a>
 * 
 */


public interface MantenimientoBASParametroPaisDAO extends DAO {

	/**
	 * Retorna la lista de parametros pais
	 * @param basParametroPais
	 * @return
	 */
	public List getParametroPais(BASParametroPais basParametroPais);
	
	/**
	 * Inserta datos al Parametro Pais.
	 * @param basParametroPais
	 */
	public void insertParametroPais(BASParametroPais basParametroPais);

	/**
	 * Inserta los datos al historcio Parametro Pais.
	 * Se ejecuta al momento que se elimina el registro de la tabla Parametro Pais.
	 * @param basParametroPais
	 */
	public void insertHistoricoParametroPais(BASParametroPais basParametroPais);
	
	/**
	 * Actualiza el registro del Parametro Pais.
	 * @param basParametroPais
	 */
	public void updateParametroPais(BASParametroPais basParametroPais);
	
	/**
	 * Elimina el registro del Parametro Pais.
	 * @param basParametroPais
	 */
	public void deleteParametroPais(BASParametroPais basParametroPais);
}