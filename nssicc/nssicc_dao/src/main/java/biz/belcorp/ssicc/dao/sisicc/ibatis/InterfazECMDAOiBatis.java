package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazECMDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz de Cuenta Corriente.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
@Repository("sisicc.interfazECMDAO")
public class InterfazECMDAOiBatis extends BaseDAOiBatis implements
	InterfazECMDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazECMDAO#executeInterfazECMEnviarMatrizProductosOfertaCumpleanhos(java.util.Map)
	 */
	public void executeInterfazECMEnviarMatrizProductosOfertaCumpleanhos(
			Map params) {
		
		getSqlMapClientTemplate().update(
				"sisicc.InterfazECMSQL.executeInterfazECMEnviarMatrizProductosOfertaCumpleanhos", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazECMDAO#executeInterfazECMEnviarClientes(java.util.Map)
	 */
	public void executeInterfazECMEnviarClientes(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazECMSQL.executeInterfazECMEnviarClientes", params);
	}
}