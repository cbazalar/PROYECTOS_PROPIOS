package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCAplicarRequisitosPremiacionZonaDAO extends DAO {

	/**
	 * Proceso de Aplicar requisitos premiacion a Consultoras en Cierre de Zona
	 * 
	 * @param params
	 */
	public void executeAplicarRequisitosPremiacionZona(Map params);
	
}