package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEDesactivarEntregaDireccionVacacionesDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoMAEDesactivarEntregaDireccionVacacionesDAO")
public class ProcesoMAEDesactivarEntregaDireccionVacacionesDAOIbatis extends BaseDAOiBatis implements 
		ProcesoMAEDesactivarEntregaDireccionVacacionesDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEDesactivarEntregaDireccionVacacionesDAO#executeActivarEntregaDireccionVacaciones(java.util.Map)
	 */
	public void executeDesactivarEntregaDireccionVacaciones(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeDesactivarEntregaDireccionVacaciones",params);
		
	}

}
