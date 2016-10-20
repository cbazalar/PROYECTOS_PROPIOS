/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazSABDAO;


/**
 * 
 * <p>
 * <a href="InterfazSABDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 * 
 */
@Repository("sisicc.interfazSABDAO")
public class InterfazSABDAOiBatis extends BaseDAOiBatis implements InterfazSABDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazVENDAO#executeInterfazVENEnviarFuenteVentasReal(java.util.Map)
	 */
	public void executeInterfazVENEnviarFuenteVentasReal(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazVENEnviarFuenteVentasReal",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazVENDAO#executeInterfazVENEnviarMaestroRegiones(java.util.Map)
	 */
	public void executeInterfazVENEnviarMaestroRegiones(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazVENEnviarMaestroRegiones",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazVENDAO#executeInterfazVENEnviarMaestroTerritorios(java.util.Map)
	 */
	public void executeInterfazVENEnviarMaestroTerritorios(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazVENEnviarMaestroTerritorios",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazVENDAO#executeInterfazVENEnviarMaestroZonas(java.util.Map)
	 */
	public void executeInterfazVENEnviarMaestroZonas(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazVENEnviarMaestroZonas",params);		
	}

	/* INI SA PER-SiCC-2012-0648 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSABDAO#executeInterfazSABEnviarPedidosTotales(java.util.Map)
	 */
	public void executeInterfazSABEnviarPedidosTotales(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazSABEnviarPedidosTotales",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSABDAO#executeInterfazSABEnviarPedidosPorMarca(java.util.Map)
	 */
	public void executeInterfazSABEnviarPedidosPorMarca(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazSABEnviarPedidosPorMarca",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSABDAO#executeInterfazSABEnviarVentaDiaria(java.util.Map)
	 */
	public void executeInterfazSABEnviarVentaDiaria(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazSABEnviarVentaDiaria",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSABDAO#executeInterfazSABEnviarMaestroTerritorios(java.util.Map)
	 */
	public void executeInterfazSABEnviarMaestroTerritorios(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazSABEnviarMaestroTerritorios",params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSABDAO#executeInterfazSABEnviarDemandaAnormal(java.util.Map)
	 */
	public void executeInterfazSABEnviarDemandaAnormal(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazSABEnviarDemandaAnormal",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSABDAO#executeInterfazSABEnviarTotalesCierre(java.util.Map)
	 */
	public void executeInterfazSABEnviarTotalesCierre(Map params) {
		getSqlMapClientTemplate().
		update("sisicc.InterfazSABSQL.executeInterfazSABEnviarTotalesCierre",params);		
	}
	/* FIN SA PER-SiCC-2012-0648 */
	
}