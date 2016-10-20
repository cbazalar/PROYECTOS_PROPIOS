package biz.belcorp.ssicc.dao.spusicc.cobranzas.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.cobranzas.ProcesoCOBGeneracionReportesFFVVFTPDAO;

/**
 * <p>
 * <a href="ProcesoCOBGeneracionReportesFFVVFTPDAOiBatis.java.html"> <i>View Source </i> </a>
 * </p>
 * 
 *  @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */
@Repository("spusicc.procesoCOBGeneracionReportesFFVVFTPDAO")
public class ProcesoCOBGeneracionReportesFFVVFTPDAOiBatis extends BaseDAOiBatis implements ProcesoCOBGeneracionReportesFFVVFTPDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBGeneracionReportesFFVVFTPDAO#executeGenerarDataFFVVFTP(java.util.Map)
	 */
	public void executeGenerarDataFFVVFTP(Map criteria) {
		
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.executeGenerarDataGerentesFFVV", criteria);
		getSqlMapClientTemplate().update("sisicc.ProcesosCOBSQL.executeGenerarDataFFVVFTP", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBGeneracionReportesFFVVFTPDAO#obtenerRegionesA(java.lang.String)
	 */
	public List obtenerRegionesA(String codigoUsuario) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.obtenerRegionesA", codigoUsuario);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.cobranzas.dao.ProcesoCOBGeneracionReportesFFVVFTPDAO#obtenerZonasA(java.util.Map)
	 */
	public List obtenerZonasA(Map criteria) {
		return getSqlMapClientTemplate().queryForList("sisicc.ProcesosCOBSQL.obtenerZonasA", criteria);
	}
}