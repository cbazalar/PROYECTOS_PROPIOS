package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETDesvinculacionAutomaticaLideresDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoLETDesvinculacionAutomaticaLideresDAO")
public class ProcesoLETDesvinculacionAutomaticaLideresDAOiBatis extends BaseDAOiBatis implements ProcesoLETDesvinculacionAutomaticaLideresDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETDesvinculacionAutomaticaLideresDAO#executeDesvinculacionAutomaticaLideres(java.util.Map)
	 */
	public void executeDesvinculacionAutomaticaLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeDesvinculacionAutomaticaLideres", params);
	}
}