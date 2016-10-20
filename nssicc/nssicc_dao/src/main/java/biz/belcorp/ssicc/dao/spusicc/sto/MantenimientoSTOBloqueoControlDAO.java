package biz.belcorp.ssicc.dao.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextjrios
 */

public interface MantenimientoSTOBloqueoControlDAO extends DAO{
	
	/**
	 * retorna la lista de bloqueos de control
	 * @param criteria
	 * @return
	 */
	public List getBloqueoControlList(Map criteria);
	
	/**
	 * inserta un registro de bloqueo de control
	 * @param criteria
	 */
	public void insertSTOBloqueoControl(Map criteria);
	
	/**
	 * borra un registro de bloqueo de control
	 * @param selectedItems
	 */
	public void deleteBloqueoControl(Map criteria);

	/**
	 * Actualiza el estado de registro a 0(Inactivo)
	 * @param criteria
	 */
	public void updateEstadoInactivoBloqueoControl(Map criteria);

	/**
	 * Devuelve el valor del parametro
	 * @param criteria
	 * @return
	 */
	public String getParametroGenericoSistema(Map criteria);

}