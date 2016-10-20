package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.MinimoNuevas;

public interface MantenimientoCOMMinimoNuevasDAO extends DAO {
	
	/**
	 * retorna la lista de Minima Nuevas
	 * @param criteria
	 * @return
	 */
	public List getMinimoNuevasList(Map criteria);
	
	/**
	 * retorna el objeto Minimo Nuevas
	 * @param criteria
	 * @return
	 */
	public MinimoNuevas getMinimoNuevas(Map criteria);
	
	/**
	 * Inserta en la tabla COM_MINIM_NUEVA
	 * @param criteria
	 * @return
	 */
	public void insertMinimoNuevas(Map criteria);
	
	
	/**
	 * Actualiza los campos de la tabla COM_MINIM_NUEVA
	 * @param criteria
	 * @return
	 */
	public void updateMinimoNuevas(Map criteria);
	
	/**
	 * Elimina la data de la tabla COM_MINIM_NUEVA por el OID
	 * @param criteria
	 * @return
	 */
	public void deleteMinimoNuevas(Map criteria);
	
	/**
	 * Validar que en la entidad Mnimo de Nuevas no exista 
	 * otro registro con el cdigo de Regin ingresado
	 * @param criteria
	 * @return
	 */
	public List getValidarMinimoNuevasRegion(Map criteria);
	
	/**
	 * Validar que en la entidad Mnimo de Nuevas no exista 
	 * otro registro con el cdigo de Zonas ingresado
	 * @param criteria
	 * @return
	 */
	public List getValidarMinimoNuevasZonas(Map criteria);
	
	public List getMinimoNuevasDuplicada(Map criteria);
}
