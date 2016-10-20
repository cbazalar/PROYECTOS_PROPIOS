package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUActualizarParametrosCursoDAO;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */
@Repository("edu.procesoEDUActualizarParametrosCursoDAO")
public class ProcesoEDUActualizarParametrosCursoDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUActualizarParametrosCursoDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUCalificacionAptasDAO#executeActualizarParametrosCurso(java.util.Map)
	 */
	public void executeActualizarParametrosCurso(Map criteria) {
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeActualizarParametrosCurso", criteria);
	}


}
