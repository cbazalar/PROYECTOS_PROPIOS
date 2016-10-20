package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ClasificacionesChequeo;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoPEDClasificacionesChequeoService extends Service{

	/**
	 * retorna todos los tipos clientes
	 * @return
	 */
	public List getTipoCliente();

	/**
	 * retorna las clasificaciones de chequeo dependiendo del filtro de la pantalla
	 * @param map
	 * @return
	 */
	public List getClasificacionChequeo(Map map);

	/**
	 * elimina una clasificacion de chequeo por los oids
	 * @param map
	 * @param items 
	 */
	public void deleteClasificacionChequeo(Map map, String[] items);

	/**
	 * inserta una clasificacion de chequeo
	 * @param params
	 */
	public void insertClasificacionChequeo(Map params);

	/**
	 * selecciona una clasificacion de chequepo por sus oids
	 * @param map
	 * @return
	 */
	public ClasificacionesChequeo getClasificacionChequeoObject(Map map);

	/**
	 * actualiza una clasificacion de cheque por medios de los oids guardados antes de ser actualizados
	 * @param params
	 */
	public void updateClasificacionChequeo(Map params);

}