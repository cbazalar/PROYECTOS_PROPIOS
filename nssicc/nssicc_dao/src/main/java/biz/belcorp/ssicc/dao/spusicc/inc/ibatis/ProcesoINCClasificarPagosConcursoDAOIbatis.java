package biz.belcorp.ssicc.dao.spusicc.inc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.inc.ProcesoINCClasificarPagosConcursoDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoINCClasificarPagosConcursoDAO")
public class ProcesoINCClasificarPagosConcursoDAOIbatis extends BaseDAOiBatis implements 
				ProcesoINCClasificarPagosConcursoDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.inc.dao.ProcesoINCClasificarPagosConcursoDAO#executeClasificarPagosConcurso(java.util.Map)
	 */
	public void executeClasificarPagosConcurso(Map params) {
		getSqlMapClientTemplate().update("spusicc.incentivos.ProcesoINCSQL.executeClasificarPagosConcurso",params);
		
	}

}