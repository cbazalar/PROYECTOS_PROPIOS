package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCCalculoMetasDAO;

/**
 * @author <a href="mailto:avillavicencio@csigcomt.com">AV</a>
 *
 */
@Repository("spusicc.procesoINCCalculoMetasDAO")
public class ProcesoINCCalculoMetasDAOIbatis extends BaseDAOiBatis implements
					ProcesoINCCalculoMetasDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCCalculoMetasDAO#executeCalculoMetas(java.util.Map)
	 */
	public void executeCalculoMetas(Map params) {
		
	
		getSqlMapClientTemplate()
		.update(
				"spusicc.incentivos.ProcesoINCSQL.executeCalculoMetas",
				params);
		
	}

	
}
