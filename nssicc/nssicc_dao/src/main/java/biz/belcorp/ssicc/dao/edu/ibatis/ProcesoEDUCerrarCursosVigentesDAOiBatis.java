package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUCerrarCursosVigentesDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Repository("edu.procesoEDUCerrarCursosVigentesDAO")
public class ProcesoEDUCerrarCursosVigentesDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUCerrarCursosVigentesDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDAO#executeCerrarCursosVigentes(java.util.Map)
	 */
	public void executeCerrarCursosVigentes(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeCerrarCursosVigentes", criteria);
	}


}
