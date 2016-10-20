package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCActivacionConcursoDAO;

/**
 * @author <a href="mailto:jjrios@csigcomt.com">Jesse James Rios Franco</a>
 *
 */
@Repository("spusicc.procesoINCActivacionConcursoDAO")
public class ProcesoINCActivacionConcursoDAOiBatis extends BaseDAOiBatis implements ProcesoINCActivacionConcursoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCActivacionConcursoDAO#executeActivacionConcurso(java.util.Map)
	 */
	public void executeActivacionConcurso(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeActivacionConcurso",params);
	}

}
