package biz.belcorp.ssicc.dao.spusicc.comision;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 *
 */
public interface ProcesoCOMCierreProcesosCampannaDAO extends DAO {

	/**
	 * Actualiza Cierre Proceso Campaa Comision
	 * @param criteria
	 * @throws Exception
	 */
	public void executeCierreProcesosCampanna(Map criteria)throws Exception;

	
}

