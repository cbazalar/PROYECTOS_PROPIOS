/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.CronogramaBR;

/**
 * Clase de la declaracin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="MantenimientoRECCronogramaBRDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 * 
 */
public interface MantenimientoRECCronogramaBRDAO extends DAO {

	
	/**
	 * Mtodo que devuelve la lista del Cronograma BR.
	 * @param map
	 * @return
	 */
	public List getCronogramaBRList(Map map);
	
	/**
	 * Mtodo que elimina registros de la tabla INT_REC_CIERR_BOREC. Solo permitir eliminaciones de Periodos aun no cerrados  
	 * @param map
	 */
	public void deleteCronogramaBR(Map map);

	/**
	 * Mtodo que inserta registro en la tabla INT_REC_CIERR_BOREC.
	 * @param map
	 */
	public void insertCronogramaBR(CronogramaBR cronograma, Usuario usuario);
	
	/**
	 * Mtodo que actualiza un registro en la tabla INT_REC_CIERR_BOREC. Solo permitir modificaciones de Periodos aun no cerrados  
	 * @param map
	 * @return
	 */
	public void updateCronogramaBR(CronogramaBR cronograma, Usuario usuario);

	/**
	 * Mtodo que valida si el periodo consultado est activo o cerrado
	 * @param map
	 * @return
	 */
	public int getCronogramaBRValidarPeriodoActivo(Map map);

	/**
	 * Mtodo que valida si el periodo existe o no
	 * @param map
	 * @return
	 */
	public int getCronogramaBRValidarPeriodoExistente(Map map);

	/**
	 * Metodo que obtiene el periodo activo
	 * @param map
	 * @return
	 */
	public int getCronogramaBRPeriodoActivo(Map map);

}
