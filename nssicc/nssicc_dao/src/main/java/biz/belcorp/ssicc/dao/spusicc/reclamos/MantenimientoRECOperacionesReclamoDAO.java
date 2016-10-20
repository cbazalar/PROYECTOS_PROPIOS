package biz.belcorp.ssicc.dao.spusicc.reclamos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.OperacionReclamo;
import biz.belcorp.ssicc.dao.spusicc.reclamos.model.TipoOperacion;

/**
 * @author <a href="aoviedo@sigcomt.com">Aurelio Oviedo</a>
 *
 */
public interface MantenimientoRECOperacionesReclamoDAO extends DAO {

	/**
	 * @param criteria
	 * Devuelve las operaciones de Reclamo
	 */
	public List getOperacionesReclamoList(Map criteria);
	
	/**
	 * Devuelve los Motivos de Bloqueo
	 * @param criteria
	 * @return
	 */
	public List getMotivosBloqueoList(Map criteria);
	
	/**
	 * Devuelve los Movimientos de Almacen
	 * @param criteria
	 * @return
	 */
	public List getMovimientosAlmacenList(Map criteria);
	
	/**
	 * Devuelve los datos de la Operacion Reclamo
	 * @param criteria
	 * @return
	 */
	public OperacionReclamo getOperacionReclamo(Map criteria);
	
	/**
	 * Verifica si existe Operacion Reclamo by Codigo
	 * @param criteria
	 * @return
	 */
	public int getExisteOperacionReclamoByCodigo(Map criteria);
	
	/**
	 * Obtiene el siguiente OID a insertar de la Operacion Reclamo
	 * @param criteria
	 * @return
	 */
	public int getNextOidOperacionReclamo(Map criteria);
	
	/**
	 * Inserta una Operacion Reclamo
	 * @param operacionReclamo
	 */
	public void insertOperacionReclamo(OperacionReclamo operacionReclamo);
	
	/**
	 * Actualiza una Operacion Reclamo
	 * @param operacionReclamo
	 */
	public void updateOperacionReclamo(OperacionReclamo operacionReclamo);
	
	/**
	 * Elimina una Operacion Reclamo
	 * @param id
	 */
	public void deleteOperacionReclamo(String id);
	
	/**
	 * Devuelve los Motivos de Rechazo/Desbloqueo
	 * @param criteria
	 * @return
	 */
	public List getMotivosRechazoDesbloqueoList(Map criteria);
	
	/**
	 * Obtiene el siguiente OID a insertar del Tipo de Operacion
	 * @param criteria
	 * @return
	 */
	public int getNextOidTipoOperacion(Map criteria);
	
	/**
	 * Verifica si existe Tipo Operacion by Codigo
	 * @param criteria
	 * @return
	 */
	public int getExisteTipoOperacionByCodigo(Map criteria);
	
	/**
	 * Devuelve los datos del Tipo Operacion
	 * @param criteria
	 * @return
	 */
	public List getTipoOperacionListByOperacion(Map criteria);
	
	/**
	 * Inserta un Tipo Operacion
	 * @param tipoOperacion
	 */
	public void insertTipoOperacion(TipoOperacion tipoOperacion);
	
	/**
	 * Actualiza un Tipo Operacion
	 * @param tipoOperacion
	 */
	public void updateTipoOperacion(TipoOperacion tipoOperacion);
	
	/**
	 * Elimina un Tipo Operacion
	 * @param id
	 */
	public void deleteTipoOperacion(Map criteria);
	
	/**
	 * Devuelve los Tipo de Solicitud
	 * @return
	 */
	public List getTiposSolicitudList();
	
	/**
	 * Devuelve los Tipo de Solicitud Genera
	 * @return
	 */
	public List getTiposSolicitudGeneraList();
	
	/**
	 * Devuelve los Almacenes
	 * @return
	 */
	public List getAlmacenList(Map criteria);
}