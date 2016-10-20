package biz.belcorp.ssicc.service.bas;

import java.util.List;

import biz.belcorp.ssicc.dao.bas.model.BASParametroPais;
import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author yrivas
 *
 */
public interface MantenimientoBASParametroPaisService extends Service{

	
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
