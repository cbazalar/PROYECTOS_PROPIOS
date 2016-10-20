package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActivarEntregaDireccionVacacionesDAO;

/**
 * @author peextsapaza
 *
 */
@Repository("spusicc.procesoMAEActivarEntregaDireccionVacacionesDAO")
public class ProcesoMAEActivarEntregaDireccionVacacionesDAOIbatis extends BaseDAOiBatis implements 
		ProcesoMAEActivarEntregaDireccionVacacionesDAO  {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEActivarEntregaDireccionVacacionesDAO#executeActivarEntregaDireccionVacaciones(java.util.Map)
	 */
	public void executeActivarEntregaDireccionVacaciones(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActivarEntregaDireccionVacaciones",params);
		
	}

}
