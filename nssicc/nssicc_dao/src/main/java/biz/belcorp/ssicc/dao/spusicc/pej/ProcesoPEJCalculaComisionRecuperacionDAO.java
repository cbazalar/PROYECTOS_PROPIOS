package biz.belcorp.ssicc.dao.spusicc.pej;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author Jesse James Rios Franco
 *
 */
public interface ProcesoPEJCalculaComisionRecuperacionDAO extends DAO{

	/**
	 * Valida si la region ya fue calculada
	 * @param codigoPais
	 * @param codigoPeriodo
	 * @param codigoRegion
	 * @return
	 */
	public Integer validaRegionComisionCalculada(String codigoPais,String codigoPeriodo, String codigoRegion);

	/**
	 * Ejecuta el proceso de calculo de comision de recuperacion
	 * @param params
	 */
	public void executeProcesoPEJCalculaComisionRecuperacion(Map params);

}
