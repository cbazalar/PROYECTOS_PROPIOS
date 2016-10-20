package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEActualizarClasificacionesFlexipagoDAO;

/**
 * @author ghuertasa
 *
 */
@Repository("spusicc.procesoMAEActualizarClasificacionesFlexipagoDAO")
public class ProcesoMAEActualizarClasificacionesFlexipagoDAOIbatis extends BaseDAOiBatis implements 
ProcesoMAEActualizarClasificacionesFlexipagoDAO {

	/**
	 * Proceso que actualiza clasificacion flexipago
	 * 
	 * @param params
	 */
	public void executeActualizarClasificacionesFlexipago(Map params){
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarClasificacionesFlexipago",params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEActualizarClasificacionesFlexipagoDAO#executeActualizarClasificacionNuevasDcto(java.util.Map)
	 */
	public void executeActualizarClasificacionNuevasDcto(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarClasificacionNuevasDcto",params);
	}

}
