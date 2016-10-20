package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ReporteLETResultadosLideresConcursoDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.reporteLETResultadosLideresConcursoDAO")
public class ReporteLETResultadosLideresConcursoDAOiBatis extends BaseDAOiBatis implements ReporteLETResultadosLideresConcursoDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ReporteLETResultadosLideresConcursoDAO#executeGenerarReporteResultadoLider(java.util.Map)
	 */
	public void executeGenerarReporteResultadoLider(Map map) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeGenerarReporteResultadoLider", map);
	}
}