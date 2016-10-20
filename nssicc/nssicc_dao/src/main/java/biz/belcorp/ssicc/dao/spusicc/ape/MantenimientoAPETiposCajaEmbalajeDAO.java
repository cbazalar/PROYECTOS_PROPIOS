package biz.belcorp.ssicc.dao.spusicc.ape;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.ape.model.TiposCajaEmbalaje;

/**
 * @author Nicols Lpez
 *
 */
public interface MantenimientoAPETiposCajaEmbalajeDAO extends DAO {
	
	/**
	 * Recupera la lista de Tipos de Embalaje Consulta por Filtros
	 * 
	 * @param criteria
	 * @return
	 */
	
	public List getTipoCajaEmbalajeList(Map criteria);
	
	/**
	 * Insertar Tipos de Caja Embalaje
	 * @param criteria
	 */
	public void insertarTipoCajaEmbalaje(Map criteria);
	
	/**
	 * Elimina un Tipo de Caja Embalaje
	 * @param criteria
	 */
	public void deleteTiposCajaEmbalaje(Map criteria);
	
	/**
	 * Actualiza el Tipo de Caja Embalaje
	 * @param criteria
	 */
	public void updateTipoCajaEmbalaje(Map criteria);
	
	/**
	 * @param criteria
	 * @return Next Oid Tipo Caja Embalaje
	 */
	public String getNextOidTipoCajaEmbalaje(Map criteria);
	
	/**
	 * @param criteria
	 * @return El objeto Tipos Caja Embalaje
	 */
	public TiposCajaEmbalaje getTiposCajaEmbalajeObject(Map criteria);
	
	/**
	 * @param criteria
	 * @return El Codigo de Tipo de Caja Embalaje
	 */
	public String getObtenerCodigoTipoCaja(Map criteria);
	
}
