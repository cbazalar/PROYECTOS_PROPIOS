package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEAsignarEstatusClienteDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoMAEAsignarEstatusClienteDAO")
public class ProcesoMAEAsignarEstatusClienteDAOIbatis extends BaseDAOiBatis implements 
				ProcesoMAEAsignarEstatusClienteDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoMAEAsignarEstatusClienteDAO#executeGenerarSolicitudBolsaFaltantes(java.util.Map)
	 */
	public void executeAsignarEstatusCliente(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeAsignarEstatusCliente",params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEAsignarEstatusClienteDAO#executeProcesoMAERevertirEstatusConsultora(java.util.Map)
	 */
	public void executeProcesoMAERevertirEstatusConsultora(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoMAERevertirEstatusConsultora",params);
	}

}

