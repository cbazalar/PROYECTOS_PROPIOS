package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */

public interface ProcesoPEJCalculoVariablesSeccionCampanaDAO extends DAO{

	/**
	 * Ejecuta el proceso de calculo de variables por seccion y campaña
	 * @param params
	 */
	public void executeProcesoPEJCalculoVariablesSeccionCampaña(Map params);

	/**
	 * Valida que la regin que se este intentando procesar ya no halla sido procesada anteriormente
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoRegion
	 * @return
	 */
	public Integer validarRegionProcesada(String codigoPais,String codigoPeriodo, String codigoRegion);

}