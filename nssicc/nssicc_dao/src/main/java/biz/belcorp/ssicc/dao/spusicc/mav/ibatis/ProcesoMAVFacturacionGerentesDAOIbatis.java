package biz.belcorp.ssicc.dao.spusicc.mav.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mav.ProcesoMAVFacturacionGerentesDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAVFacturacionGerentesDAO")
public class ProcesoMAVFacturacionGerentesDAOIbatis extends BaseDAOiBatis implements
	ProcesoMAVFacturacionGerentesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mav.dao.ProcesoMAVFacturacionGerentesDAO#executeFacturacionGerentes(java.util.Map)
	 */
	public void executeFacturacionGerentes(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAVSQL.executeFacturacionGerentes", params);
		
	}
}
