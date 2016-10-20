package biz.belcorp.ssicc.dao.spusicc.mav;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
public interface ProcesoMAVFacturacionGerentesDAO extends DAO {

	/**
	 * Ejecura el proceso de Facturacion Gerentes MAV
	 * 
	 * @param params
	 */
	void executeFacturacionGerentes(Map params);
	
}

