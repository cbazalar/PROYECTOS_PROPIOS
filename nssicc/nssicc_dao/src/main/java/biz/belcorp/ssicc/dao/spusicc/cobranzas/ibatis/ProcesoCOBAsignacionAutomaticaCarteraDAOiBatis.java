package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBAsignacionAutomaticaCarteraDAO;

/**
 * Implementacion del DAO que ejecutara la Asignacion de Cartera
 * <p>
 * <a href="ProcesoCOBAsignacionAutomaticaCarteraDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:jflorencio@belcorp.biz">Jorge Florencio Arias </a>
 */
@Repository("spusicc.procesoCOBAsignacionAutomaticaCarteraDAO")
public class ProcesoCOBAsignacionAutomaticaCarteraDAOiBatis extends BaseDAOiBatis implements ProcesoCOBAsignacionAutomaticaCarteraDAO {


	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionAutomaticaCarteraDAO#executeAsignacionAutomaticaCartera(java.util.Map)
	 */
	public void executeAsignacionAutomaticaCartera(Map criteria) {
		getSqlMapClientTemplate().update(
				"sisicc.ProcesosCOBSQL.executeAsignacionAutomaticaCartera", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionAutomaticaCarteraDAO#getEnvioMailsAsignacionAutomaticaCarteraParams(java.util.Map)
	 */
	public List getEnvioMailsAsignacionAutomaticaCarteraParams(Map criteria){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getEnvioMailsAsignacionAutomaticaCarteraParams",criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionAutomaticaCarteraDAO#getCarterasAsignadasProceso(java.util.Map)
	 */
	public List getCarterasAsignadasProceso(){
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getCarterasAsignadasProceso");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionAutomaticaCarteraDAO#getEnvioMailsAsignacionCarteraParams(java.util.Map)
	 */
	public List getEnvioMailsAsignacionCarteraParams(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getEnvioMailsAsignacionCarteraParams",criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionAutomaticaCarteraDAO#getListaCobradores(java.util.Map)
	 */
	public List getListaCobradores(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getListaCobradores", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionAutomaticaCarteraDAO#getListaCuerpoEmailCobrador(java.util.Map)
	 */
	public List getListaCuerpoEmailCobrador(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getListaCuerpoEmailCobrador", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionAutomaticaCarteraDAO#getListaCuerpoEmailSupervisor(java.util.Map)
	 */
	public List getListaCuerpoEmailSupervisor(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getListaCuerpoEmailSupervisor", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBAsignacionAutomaticaCarteraDAO#getListaSupervisores(java.util.Map)
	 */
	public List getListaSupervisores(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.getListaSupervisores", criteria);
	}
	
}
