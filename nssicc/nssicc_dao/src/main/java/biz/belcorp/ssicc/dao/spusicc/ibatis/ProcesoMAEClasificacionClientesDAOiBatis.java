package biz.belcorp.ssicc.dao.spusicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.ProcesoMAEClasificacionClientesDAO;

@Repository("spusicc.procesoMAEClasificacionClientesDAO")
public class ProcesoMAEClasificacionClientesDAOiBatis extends BaseDAOiBatis
		implements ProcesoMAEClasificacionClientesDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoMAEClasificacionClientesDAO#executeProcesarClasificacion(java.util.Map)
	 */
	public void executeProcesarClasificacion(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeClasificacionClientes", criteria);
        return;
	}

	/**
	 * @param criteria
	 */
	public void executeProcesarClasificacionLove(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeClasificacionLoveClientes", criteria);
        return;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoMAEClasificacionClientesDAO#executeActualizarDatosClientes(java.util.Map)
	 */
	public void executeActualizarDatosClientes(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarDatosClientes", criteria);
        return;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoMAEClasificacionClientesDAO#executeValidacionClientes(java.util.Map)
	 */
	public void executeValidacionClientes(Map criteria) {
        getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeValidacionClientes", criteria);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.dao.ProcesoMAEClasificacionClientesDAO#executeInactivarConsultora2campanas(java.util.Map)
	 */
	public void executeInactivarConsultora2campanas(Map criteria) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeInactivarConsultora2campanas", criteria);
	}
	
}

