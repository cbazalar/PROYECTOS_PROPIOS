package biz.belcorp.ssicc.dao.spusicc.let.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.let.ProcesoLETActualizacionClasificacionLideresDAO;

/**
 * @author Jesse James Rios Franco
 *
 */
@Repository("spusicc.procesoLETActualizacionClasificacionLideresDAO")
public class ProcesoLETActualizacionClasificacionLideresDAOiBatis extends BaseDAOiBatis implements ProcesoLETActualizacionClasificacionLideresDAO{

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETActualizacionClasificacionLideresDAO#executeProcesoLETActualizacionClasificacionLideres(java.util.Map)
	 */
	public void executeProcesoLETActualizacionClasificacionLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETActualizacionClasificacionLideres", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.let.dao.ProcesoLETActualizacionClasificacionLideresDAO#executeProcesoLETProcesarClasificacionLideres(java.util.Map)
	 */
	public void executeProcesoLETProcesarClasificacionLideres(Map params) {
		getSqlMapClientTemplate().update("spusicc.let.ProcesosLETSQL.executeProcesoLETProcesarClasificacionLideres", params);
	}
}