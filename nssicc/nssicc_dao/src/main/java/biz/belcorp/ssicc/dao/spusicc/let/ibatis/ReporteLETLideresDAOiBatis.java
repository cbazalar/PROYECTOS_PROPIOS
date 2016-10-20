package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ReporteLETLideresDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.reporteLETLideresDAO")
public class ReporteLETLideresDAOiBatis extends BaseDAOiBatis implements ReporteLETLideresDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ReporteLETLideresDAO#executeGenerarReporteLideres(java.util.Map)
	 */
	public void executeGenerarReporteLideres(Map map) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeGenerarReporteLideres", map);
	}

}