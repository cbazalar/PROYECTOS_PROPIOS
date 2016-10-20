package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCCargaPuntajeRetailDAO extends DAO {

	/**
	 * Proceso que Carga Puntaje Retail por Campaa y Fecha de Facturacion
	 * 
	 * @param params
	 */
	public void executeCargaPuntajeRetail(Map params);
	
}
