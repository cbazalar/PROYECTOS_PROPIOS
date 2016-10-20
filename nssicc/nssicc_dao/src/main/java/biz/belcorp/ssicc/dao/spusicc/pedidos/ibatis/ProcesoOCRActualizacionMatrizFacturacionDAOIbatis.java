/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.pedidos.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.pedidos.ProcesoOCRActualizacionMatrizFacturacionDAO;

/**
 * @author peextjcairampoma
 *
 */
@Repository("spusicc.procesoOCRActualizacionMatrizFacturacionDAO")
public class ProcesoOCRActualizacionMatrizFacturacionDAOIbatis extends BaseDAOiBatis implements ProcesoOCRActualizacionMatrizFacturacionDAO {
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.pedidos.dao.ProcesoOCRActualizacionMatrizFacturacionDAO#executeOCRActualizacionMatrizFacturacion(java.util.Map)
	 */
	public void executeOCRActualizacionMatrizFacturacion(Map params){
			
			getSqlMapClientTemplate().update("spusicc.pedidos.PedidosSQL.executeOCRActualizacionMatrizFacturacion",params);
				
	}
	
}
