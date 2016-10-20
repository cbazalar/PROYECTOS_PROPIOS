/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
public interface ProcesoPEDEjecutarGPDAO extends DAO {

	/**
	 * Controla la concurrencia del envio con las validaciones
	 * @return
	 */
	public Integer getIndicadorEnvioValidaciones();

	/**
	 * Controla la concurrencia del envio con las validaciones STO
	 * @param criteria 
	 * @return
	 */
	public Integer getIndicadorEnvioValidacionesSTO(Map criteria);

}
