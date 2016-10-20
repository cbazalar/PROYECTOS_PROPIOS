/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * DAO de la Interfaz de Facturas Electrnicas.
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
public interface InterfazFACDAO extends DAO {

	/**
	 * Permite recepcionar el documento de la cabecera de notas de credito
	 * @param params
	 */
	public void executeInterfazFACRecepcionarNotasCreditoCabeceraDocumento(Map params);

	/**
	 * Permite recepcionar el documento del detalle de notas de credito
	 * @param map
	 */
	public void executeInterfazFACRecepcionarNotasCreditoDetalleDocumento(Map params);

}
