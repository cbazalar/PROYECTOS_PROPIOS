package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAEEliminarClasificacionClienteDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAEEliminarClasificacionClienteDAO")
public class ProcesoMAEEliminarClasificacionClienteDAOIbatis extends BaseDAOiBatis implements
ProcesoMAEEliminarClasificacionClienteDAO {

	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAEEliminarClasificacionClienteDAO#eliminarClasificacionCliente(java.util.Map)
	 */
	public void eliminarClasificacionCliente(Map params) {
		getSqlMapClientTemplate().delete(
				"spusicc.ProcesosMAESQL.eliminarClasificacionCliente", params);

		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAEEliminarClasificacionClienteDAO#getNroConsultorasClasificacion(java.util.Map)
	 */
	public String getNroConsultorasClasificacion(Map params) {
		return (String) getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getNroConsultorasClasificacion",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAEEliminarClasificacionClienteDAO#insertClasificacionClienteHistorico(java.util.Map)
	 */
	public void insertClasificacionClienteHistorico(Map params) {
		getSqlMapClientTemplate().insert("spusicc.ProcesosMAESQL.insertClasificacionClienteHistorico", params);
	}

}
