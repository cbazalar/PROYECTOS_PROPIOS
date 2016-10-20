package biz.belcorp.ssicc.dao.spusicc.inc;

import java.util.List;
import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoINCCargaOrdenesRetailDAO extends DAO {

	/**
	 * Verifica si ya se realizo la carga de ordenes retail para una determinada Campaa
	 * 
	 * @param params
	 */
	public boolean existeCargaOrdenesRetailProcesado(Map criteria);

	/**
	 * Proceso que Carga Ordenes Retail por Campaa para proceso de Calculo de Puntos.
	 * 
	 * @param params
	 */
	public void executeCargaOrdenesRetail(Map params);

	/**
	 * @param criteria
	 * @return
	 */
	public List getCargasEjecutadasECM (Map criteria);

	/**
	 * Proceso que Anula una Carga Ordenes Retail por Campaa.
	 * 
	 * @param params
	 */
	public void executeAnularCargaOrdenesRetail(Map params);
	
}
