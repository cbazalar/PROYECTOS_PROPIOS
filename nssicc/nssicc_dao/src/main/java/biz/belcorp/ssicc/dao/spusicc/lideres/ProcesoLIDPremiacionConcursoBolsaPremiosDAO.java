package biz.belcorp.ssicc.dao.spusicc.lideres;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextsapaza
 *
 */
public interface ProcesoLIDPremiacionConcursoBolsaPremiosDAO
		extends DAO {

	/**
	 * Proceso que Genera Solicitudes de premiacion de los concursos con tipo
	 * de premiacion Bolsa de Premios
	 * 
	 * @param params
	 */
	public void executePremiacionConcursoBolsaPremios(Map params);

}

