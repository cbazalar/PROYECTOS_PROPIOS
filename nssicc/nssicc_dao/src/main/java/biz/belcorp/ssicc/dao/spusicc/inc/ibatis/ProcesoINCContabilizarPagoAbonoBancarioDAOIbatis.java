package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCContabilizarPagoAbonoBancarioDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoINCContabilizarPagoAbonoBancarioDAO")
public class ProcesoINCContabilizarPagoAbonoBancarioDAOIbatis extends BaseDAOiBatis implements
	ProcesoINCContabilizarPagoAbonoBancarioDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCContabilizarPagoAbonoBancarioDAO#executeContabilizarPagoAbonoBancario(java.util.Map)
	 */
	public void executeContabilizarPagoAbonoBancario(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeContabilizarPagoAbonoBancario",params);
		
	}

}
