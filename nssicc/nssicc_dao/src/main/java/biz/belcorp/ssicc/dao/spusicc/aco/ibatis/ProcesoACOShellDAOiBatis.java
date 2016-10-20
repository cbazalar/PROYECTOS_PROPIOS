package biz.belcorp.ssicc.dao.spusicc.aco.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.aco.ProcesoACOShellDAO;
/**
 * <p>
 * <a href="ProcesoACOShellDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli</a>
 */
@Repository("spusicc.procesoACOShellDAO")
public class ProcesoACOShellDAOiBatis extends BaseDAOiBatis implements ProcesoACOShellDAO {

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.aco.dao.ProcesoACOShellDAO#executeProcesoShell(java.util.Map)
	 */
	public void executeProcesoShell(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.aco.ProcesosACOSQL.executeProcesoShell", criteria);
	}
}
