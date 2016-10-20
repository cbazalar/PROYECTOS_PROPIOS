package biz.belcorp.ssicc.service.spusicc.pedidos;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.service.framework.Service;

/**
 * @author peextcroman
 */

public interface MantenimientoOCRPreAlternativosAutomaticosService extends Service {	     

	
	
	/**
	 * Devuelve la lista de alternativos automaticos
	 * @param criteria
	 * @return
	 */
	public List getListaAlternativosAutomaticos(Map criteria);
	
	/**
	 * Elimina alternativos automaticos
	 * @param criteria
	 */
	public void deleteAlternativosAutomaticos(Map criteria);
	
	
	/**
	 * Inserta alternativos automaticos
	 * @param criteria
	 */
	public void insertPreAlternativosAutomaticos(Map criteria);
	
	/**
	 * Proceso que crea ofertas alternativas
	 * @param criteria
	 */
	public void executeProcesoCrearOfertas(Map criteria);
	
	
}