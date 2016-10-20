package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
 * TODO Include class description here.
 * 
 * <p>
 * <a href="InterfazLLIDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 * 
 */
public interface InterfazLLIDAO extends DAO {

	/**
	 * Proceso que ejecuta Envo Ventas por Periodo
	 * @param params
	 */
	void executeInterfazLLIEnviarVentaPeriodo(Map params);
	
	/**
	 * Proceso que ejecuta Envo Ventas por Facturacion Acumulada
	 * @param params
	 */
	void executeInterfazLLIEnviarVentaRealDiariaAcumulada(Map params);
	
}
