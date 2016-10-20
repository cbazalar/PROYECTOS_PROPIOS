package biz.belcorp.ssicc.dao.spusicc.pedidos;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author peextjcairampoma
 *
 */
public interface ProcesoOCRActualizacionMatrizFacturacionDAO extends DAO {
	
	/**
	 * @param params
	 */
	public void executeOCRActualizacionMatrizFacturacion(Map params);

	
}
