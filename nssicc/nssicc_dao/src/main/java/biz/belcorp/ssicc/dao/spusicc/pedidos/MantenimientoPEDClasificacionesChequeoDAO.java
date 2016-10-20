package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.ClasificacionesChequeo;

public interface MantenimientoPEDClasificacionesChequeoDAO extends DAO{

	/**
	 * retorna todos los tipos cliente de la tabla
	 * @return
	 */
	public List getTipoCliente();

	/**
	 * retorna los sub tipo cliente de acuerdo al oid de tipo cliente
	 * @param oidTipoCliente
	 * @return
	 */
	public List getSubTipoClienteByOidTipoCliente(String oidTipoCliente);

	/**
	 * retorna los tipo clasificacion de acuerdo al oid de sub tipo cliente
	 * @param oidSubTipoCliente
	 * @return
	 */
	public List getTipoClasificacionByOidSubTipoCliente(String oidSubTipoCliente);
	
	/**
	 * retorna las clasificaciones  de acuerdo al oid de tipo de clasificacion
	 * @param oidTipoClasificacion
	 * @return
	 */
	public List getClasificacionByOidTipoClasificacion(String oidTipoClasificacion);

	/**
	 * retorna las clasificaciones de chequeo dependiendo del filtro de la pantalla
	 * @param map
	 * @return
	 */
	public List getClasificacionChequeo(Map map);

	/**
	 * elimina una clasificacion de cheque por los oids
	 * @param map
	 */
	public void deleteClasificacionChequeo(Map map);

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