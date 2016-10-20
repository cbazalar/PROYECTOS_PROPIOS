package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarInformacionBasicaDAO;

/**
 * @author Ivan Tocto
 *
 */
@Repository("spusicc.procesoMAEActualizarInformacionBasicaDAO")
public class ProcesoMAEActualizarInformacionBasicaDAOIbatis extends BaseDAOiBatis implements ProcesoMAEActualizarInformacionBasicaDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEActualizarInformacionDAO#executeActualizarInformacionBasica(java.util.Map)
	 */
	public void executeActualizarInformacionBasica(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarInformacionBasica",params);
		
	}

}
