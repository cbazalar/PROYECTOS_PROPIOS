/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazFACDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz de Facturas Electrnicas.
 * 
 * @author <a href="mailto:sguerra@sigcomt.com">Sebastian Guerra</a>
 */
@Repository("sisicc.InterfazFACDAO")
public class InterfazFACDAOiBatis extends BaseDAOiBatis implements InterfazFACDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFACDAO#executeInterfazFACRecepcionarNotasCreditoCabeceraDocumento(java.util.Map)
	 */
	public void executeInterfazFACRecepcionarNotasCreditoCabeceraDocumento(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFACSQL.executeInterfazFACRecepcionarNotasCreditoCabeceraDocumento", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFACDAO#executeInterfazFACRecepcionarNotasCreditoDetalleDocumento(java.util.Map)
	 */
	public void executeInterfazFACRecepcionarNotasCreditoDetalleDocumento(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFACSQL.executeInterfazFACRecepcionarNotasCreditoDetalleDocumento", params);
	}
	
}
