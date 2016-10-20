package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ProcesoGENGenerarInformacionSolicitudesDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoGENGenerarInformacionSolicitudesDAO")
public class ProcesoGENGenerarInformacionSolicitudesDAOiBatis extends BaseDAOiBatis implements ProcesoGENGenerarInformacionSolicitudesDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoGENGenerarInformacionSolicitudesDAO#executeProcesoGENGenerarInformacionSolicitudes(java.util.Map)
	 */
	public void executeProcesoGENGenerarInformacionSolicitudes(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeProcesoGENGenerarInformacionSolicitudes", params);
	}
}