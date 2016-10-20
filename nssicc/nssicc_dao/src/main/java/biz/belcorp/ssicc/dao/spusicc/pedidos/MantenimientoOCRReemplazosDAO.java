package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextdoliva
 */

public interface MantenimientoOCRReemplazosDAO extends DAO {
	
	/**
	 * Devuelve la lista de reemplazos en base al periodo
	 * @param criteria
	 * @return
	 */
	public List getReemplazosByPeriodo(Map criteria);
	
	/**
	 * Elimina el reemplazo por el oid
	 * @param criteria
	 */
	public void deleteReemplazos(Map criteria);

	/**
	 * Devuelve la informacion del CUV en el periodo
	 * @param criteria
	 * @return
	 */
	public List getInformacionCUV(Map criteria);
	
	/**
	 * Devuelve el CUV en caso ya tenga un Reemplazo
	 * @param criteria
	 * @return
	 */
	public String validaCUVPrincipal(Map criteria);	
	
	/**
	 * Inserta el reemplazo
	 * @param criteria
	 */
	public void insertOCRReemplazos(Map criteria);
	
	/**
	 * actualiza el indicador de actividad en el reemplazo
	 * @param criteria
	 */
	public void updateReemplazos(Map criteria);
}
