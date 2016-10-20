package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCClasificarPagosConcursoDAO
		extends DAO {

	/**
	 * Proceso que va a clasificar Pagos Concurso para Consultora
	 * 
	 * @param params
	 */
	public void executeClasificarPagosConcurso(Map params);

}
