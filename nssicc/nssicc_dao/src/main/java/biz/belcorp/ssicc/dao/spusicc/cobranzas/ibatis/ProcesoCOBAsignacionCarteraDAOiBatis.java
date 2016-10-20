package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBAsignacionCarteraDAO;

/**
 * Implementacion del DAO que ejecutara la Asignacion de Cartera
 * <p>
 * <a href="ProcesoCOBAsignacionCarteraDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
@Repository("spusicc.procesoCOBAsignacionCarteraDAO")
public class ProcesoCOBAsignacionCarteraDAOiBatis extends BaseDAOiBatis implements ProcesoCOBAsignacionCarteraDAO {


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionCarteraDAO#executeAsignacionCartera(java.util.Map)
	 */
	public void executeAsignacionCartera(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOBSQL.executeAsignacionCartera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionCarteraDAO#getEnvioMailsAsignacionCarteraParams(java.util.Map)
	 */
	public List getEnvioMailsAsignacionCarteraParams(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getEnvioMailsAsignacionCarteraParams",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionCarteraDAO#getCarterasAsignadasProceso(java.util.Map)
	 */
	public List getCarterasAsignadasProceso(){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCarterasAsignadasProceso");
	}
	
}
