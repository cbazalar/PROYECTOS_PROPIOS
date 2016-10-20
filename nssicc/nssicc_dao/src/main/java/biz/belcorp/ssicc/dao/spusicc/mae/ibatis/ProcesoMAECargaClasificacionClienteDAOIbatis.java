package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaClasificacionClienteDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAECargaClasificacionClienteDAO")
public class ProcesoMAECargaClasificacionClienteDAOIbatis extends BaseDAOiBatis implements
	ProcesoMAECargaClasificacionClienteDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaClasificacionClienteDAO#insertCargaPuntajeConsultora(java.util.Map)
	 */
	public void insertCargaClasificacionCliente(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosMAESQL.insertCargaClasificacionCliente", params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaClasificacionClienteDAO#executeValidarCargaClasificacionClientes(java.util.Map)
	 */
	public void executeValidarCargaClasificacionClientes(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeValidarCargaClasificacionClientes", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaClasificacionClienteDAO#getCargaClasificacionClientesList(java.util.Map)
	 */
	public List getCargaClasificacionClientesList(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getCargaClasificacionClientesList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaClasificacionClienteDAO#executeActualizarCargaClasificacionClientes(java.util.Map)
	 */
	public void executeActualizarCargaClasificacionClientes(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarCargaClasificacionClientes", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaClasificacionClienteDAO#getNumeroCarga()
	 */
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getNumeroCarga");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaClasificacionClienteDAO#getNumeroLote()
	 */
	public String getNumeroLote() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getNumeroLote");	
	}

}
