package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETDespachoPremiosDAO;

/**
 * <p>
 * <a href="ProcesoLETDespachoPremiosDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 * @author Aurelio Oviedo
 *         
 */
@Repository("spusicc.procesoLETDespachoPremiosDAO")
public class ProcesoLETDespachoPremiosDAOiBatis extends BaseDAOiBatis implements ProcesoLETDespachoPremiosDAO{
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETDespachoPremiosDAO#executeProcesoLETDespachoPremios(java.util.Map)
	 */
	public void executeProcesoLETDespachoPremios(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETDespachoPremios", params);
	}
}