package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;


/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCAplicarRequisitosPremiacionDAO extends DAO {

	/**
	 * Proceso de Aplicar requisitos premiacion a Consultoras
	 * 
	 * @param params
	 */
	public void executeAplicarRequisitosPremiacion(Map params);
	
}

