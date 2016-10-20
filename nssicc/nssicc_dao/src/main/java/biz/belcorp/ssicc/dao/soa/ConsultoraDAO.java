/**
 * 
 */
package biz.belcorp.ssicc.dao.soa;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Danny Amaro
 * 
 */
public interface ConsultoraDAO extends DAO
{
	/**
	 * @param criteria
	 * @return
	 */
	List getObtenerCursosCapacitacion(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getObtenerListaConsultoras(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getObtenerOperaciones(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getObtenerConsultora(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getObtenerPasosVisitaConsul(Map criteria);

	/**
	 * @param criteria
	 * @return
	 */
	List getObtenerPalancaConsultora(Map criteria);
}
