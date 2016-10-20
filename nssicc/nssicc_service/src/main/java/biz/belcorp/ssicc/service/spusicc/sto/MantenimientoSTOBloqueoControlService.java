package biz.belcorp.ssicc.service.spusicc.sto;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextjrios
 */

public interface MantenimientoSTOBloqueoControlService extends Service{

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
	public int insertSTOBloqueoControl(Map criteria);

	/**
	 * borra un registro de bloqueo de control
	 * @param parametros
	 */
	public void deleteBloqueoControl(Map parametros);

	/**
	 * Devuelve el valor del parametro
	 * @param criteria
	 * @return
	 */
	public String getParametroGenericoSistema(Map criteria);

}