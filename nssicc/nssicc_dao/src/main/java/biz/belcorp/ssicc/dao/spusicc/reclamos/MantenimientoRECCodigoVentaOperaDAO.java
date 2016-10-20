/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author 
 * Cristhian Roman
 */
public interface MantenimientoRECCodigoVentaOperaDAO extends DAO {

	/**
	 * Metodo que devuelve la lista de tipos de ofertas
	 * @param map
	 * @return
	 */
	public List getTipoOfertaList(Map map);
	
	/**
	 * Metodo que devuelve la lista de catalogos
	 * @param map
	 * @return
	 */
	public List getCodigoCatalogoList(Map map);
	
	/**
	 * Metodo para devolver la lista de codigos de venta
	 * @param map
	 * @return
	 */
	public List getCodigoVentaOperaList(Map map);
	
	/**
	 * Metodo que elimina los registros de la tabla REC_EXCEP_CODIG_VENTA_OPERA
	 * @param map
	 */
	public void deleteCodigoVentaOpera(Map map);
	
	/**
	 * Metodo que inserta en la tabla REC_EXCEP_CODIG_VENTA_OPERA
	 * @param map
	 */
	public void insertCodigoVentaOpera(Map map);
	
	/**
	 * Metodo que actualiza los campos de la tabla REC_EXCEP_CODIG_VENTA_OPERA
	 * @param map
	 */
	public void updateCodigoVentaOpera(Map map);
	
	/**
	 * Metodo que valida que no exista el cdigo de venta en el mdulo
	 * @param criteria
	 * @return
	 */
	public int getValidaCodigoVentaOpera(Map criteria);
}
