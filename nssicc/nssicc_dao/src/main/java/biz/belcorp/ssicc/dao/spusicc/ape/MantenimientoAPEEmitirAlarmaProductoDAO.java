package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
/**
 * @author Jose Luis Rodriguez
 *
 */

public interface MantenimientoAPEEmitirAlarmaProductoDAO extends DAO{
	
	/**
	 * Devuelve la lista de productos para enviar por mail
	 * @param criteria
	 * @return
	 */
	public List getEmitirAlarmaProductoList(Map criteria);
	
	/**
	 * Devuelve la lista de correos de los usuarios
	 * @param criteria
	 * @return
	 */
	public List getMailAlarmaProductoList(Map criteria);
	
	/**
	 * Devuele el oid del siguiente periodo ingresado por pantalla
	 * @param criteria
	 * @return
	 */
	public String getOidPeriodoSiguiente(Map criteria);
}
