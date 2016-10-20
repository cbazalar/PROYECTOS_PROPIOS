/**
 * 
 */
package biz.belcorp.ssicc.dao.spusicc.sto.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.sto.ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAO;

/**
 * @author itocto
 *
 */
@Repository("spusicc.procesoSTOEnviarInformeEstatusSolicitudesCreditoDAO")
public class ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAOiBatis extends BaseDAOiBatis implements ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAO#getCorreo(java.util.Map)
	 */
	public String getCorreo(Map params) {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosSTOSQL.getCorreoEnviarInformeEstatusSolicitudesCredito", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAO#getRegiones(java.util.Map)
	 */
	public List getRegiones(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getRegionesEnviarInformeEstatusSolicitudesCredito", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.sto.dao.ProcesoSTOEnviarInformeEstatusSolicitudesCreditoDAO#getZonas(java.util.Map)
	 */
	public List getZonas(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosSTOSQL.getZonasEnviarInformeEstatusSolicitudesCredito", params);
	}

}
