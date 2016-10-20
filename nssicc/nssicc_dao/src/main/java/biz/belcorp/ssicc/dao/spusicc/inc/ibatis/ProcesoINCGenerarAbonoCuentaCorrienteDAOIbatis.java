package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCGenerarAbonoCuentaCorrienteDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCGenerarAbonoCuentaCorrienteDAO")
public class ProcesoINCGenerarAbonoCuentaCorrienteDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCGenerarAbonoCuentaCorrienteDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCGenerarAbonoCuentaCorrienteDAO#executeGenerarAbonoCuentaCorriente(java.util.Map)
	 */
	public void executeGenerarAbonoCuentaCorriente(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeGenerarAbonoCuentaCorriente",params);
		
	}

}