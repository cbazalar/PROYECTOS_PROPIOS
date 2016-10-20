package biz.belcorp.ssicc.service.spusicc.comision;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.spusicc.cobranzas.model.MinimoNuevas;
import biz.belcorp.ssicc.service.framework.Service;

public interface MantenimientoCOMMinimoNuevasService extends Service {
	
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
	public String insertMinimoNuevas(Map criteria);
	
	
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
	
	

}
