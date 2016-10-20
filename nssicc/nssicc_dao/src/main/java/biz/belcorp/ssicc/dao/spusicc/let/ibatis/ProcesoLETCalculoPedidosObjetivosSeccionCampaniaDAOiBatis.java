/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAO;

/**
 * Proceso que realiza el calculo de pedidos Objetvos por Seccion y Campania
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Danny Amaro
 *         
 */
@Repository("spusicc.procesoLETCalculoPedidosObjetivosSeccionCampaniaDAO")
public class ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAOiBatis extends BaseDAOiBatis implements ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAO#executeProcesoLETCalculoPedidosObjetivosSeccionCampania(java.util.Map)
	 */
	public void executeProcesoLETCalculoPedidosObjetivosSeccionCampania(Map params) {
		log.info("Entro a ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAOiBatis - executeProcesoLETCalculoPedidosObjetivosSeccionCampania(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoPedidosObjetivosSeccionCampania", params);
		log.info("Salio a ProcesoLETCalculoPedidosObjetivosSeccionCampaniaDAOiBatis - executeProcesoLETCalculoPedidosObjetivosSeccionCampania(java.util.Map)");
		
	}

}
