package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETResultadoLideresAcumuladoConcuCierreRegionDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoLETResultadoLideresAcumuladoConcuCierreRegionDAO")
public class ProcesoLETResultadoLideresAcumuladoConcuCierreRegionDAOiBatis extends BaseDAOiBatis implements ProcesoLETResultadoLideresAcumuladoConcuCierreRegionDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETResultadoLideresAcumuladoConcuCierreRegionDAO#executeProcesoLETResultadoLideresAcumuladoConcuCierreRegion(java.util.Map)
	 */
	public void executeProcesoLETResultadoLideresAcumuladoConcuCierreRegion(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETResultadoLideresAcumuladoConcuCierreRegion",params);
	}
}