package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO;

/**
 * Clase de la implementacin de la capa DAO (Data Access Object)
 * 
 * <p>
 * <a href="ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAOIbatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 *         
 */
@Repository("spusicc.procesoLETCalculoPedidoObjetivoSeccionCampaniaDAO")
public class ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAOIbatis extends BaseDAOiBatis implements ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAO#executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)
	 */
	public void executeProcesoLETCalculoPedidoObjetivoSeccionCampania(Map params) {
		log.info("Entro a ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAOIbatis - executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)");
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETCalculoPedidoObjetivoSeccionCampania", params);
		log.info("Salio a ProcesoLETCalculoPedidoObjetivoSeccionCampaniaDAOIbatis - executeProcesoLETCalculoPedidoObjetivoSeccionCampania(java.util.Map)");
	}
	
}
