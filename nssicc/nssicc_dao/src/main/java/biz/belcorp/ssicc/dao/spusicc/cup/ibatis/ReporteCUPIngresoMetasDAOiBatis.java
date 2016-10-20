package biz.belcorp.ssicc.dao.spusicc.cup.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cup.ReporteCUPIngresoMetasDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.reporteCUPIngresoMetasDAO")
public class ReporteCUPIngresoMetasDAOiBatis extends BaseDAOiBatis implements ReporteCUPIngresoMetasDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.nvs.dao.ReporteCUPIngresoMetasDAO#executeReporteNVSIngresoMetas(java.util.Map)
	 */
	public void executeReporteCUPIngresoMetas(Map params) {
		getSqlMapClientTemplate().update("spusicc.GenericoCUPSQL.executeReporteCUPIngresoMetas",params);
	}
}