package biz.belcorp.ssicc.dao.edu.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.edu.ProcesoEDUAuditoriaDAO;
import biz.belcorp.ssicc.dao.edu.model.Auditoria;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;

/**
 * @author <a href="mailto:sbuchelli@belcorp.biz">Sergio Buchelli Silva</a>
 *
 */
@Repository("edu.procesoEDUAuditoriaDAO")
public class ProcesoEDUAuditoriaDAOiBatis extends 
	BaseDAOiBatis implements ProcesoEDUAuditoriaDAO {
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.edu.dao.ProcesoEDUAuditoriaDAO#executeActualizarParametrosCurso(java.util.Map)
	 */
	public void executeAuditoria(Map criteria) {
		logger.debug("executeAuditoria");
		getSqlMapClientTemplate().update(
				"edu.ProcesosEDUSQL.executeAuditoria", criteria);
		
		
	}

	public List getListAuditoria(Auditoria auditoria) {
		logger.debug("getListAuditoria");
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getListAuditoria",
				auditoria);
		return resultado;
	}

	public List getListDetalleAuditoria(Auditoria auditoria) {
		logger.debug("getListDetalleAuditoria");
		List resultado = getSqlMapClientTemplate().queryForList(
				"edu.ProcesosEDUSQL.getListDetalleAuditoria",
				auditoria);
		return resultado;
	}


}
