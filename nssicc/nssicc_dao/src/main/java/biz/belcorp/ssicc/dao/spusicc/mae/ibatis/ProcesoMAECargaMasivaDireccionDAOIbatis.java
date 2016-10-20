package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaMasivaDireccionDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAECargaMasivaDireccionDAO")
public class ProcesoMAECargaMasivaDireccionDAOIbatis extends BaseDAOiBatis implements
	ProcesoMAECargaMasivaDireccionDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaMasivaDireccionDAO#insertCargaPuntajeConsultora(java.util.Map)
	 */
	public void insertCargaMasivaDireccion(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosMAESQL.insertCargaMasivaDireccion", params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaMasivaDireccionDAO#executeValidarCargaMasivaDireccion(java.util.Map)
	 */
	public void executeValidarCargaMasivaDireccion(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeValidarCargaMasivaDireccion", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaMasivaDireccionDAO#getCargaMasivaDireccionUAList(java.util.Map)
	 */
	public List getCargaMasivaDireccionUAList(Map params) {
		return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getCargaMasivaDireccionUAList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaMasivaDireccionDAO#updateCargaMasivaDireccionUA(java.util.Map)
	 */
	public void updateCargaMasivaDireccionUA(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.updateCargaMasivaDireccionUA", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaMasivaDireccionDAO#getCargaMasivaDireccionList(java.util.Map)
	 */
	public List getCargaMasivaDireccionList(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getCargaMasivaDireccionList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaMasivaDireccionDAO#getCargaMasivaDireccionObsList(java.util.Map)
	 */
	public List getCargaMasivaDireccionObsList(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getCargaMasivaDireccionObsList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaMasivaDireccionDAO#getCargaMasivaDireccionOKList(java.util.Map)
	 */
	public List getCargaMasivaDireccionOKList(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getCargaMasivaDireccionOKList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaMasivaDireccionDAO#updateCargaMasivaDireccionOK(java.util.Map)
	 */
	public void updateCargaMasivaDireccionOK(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.updateCargaMasivaDireccionOK", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaMasivaDireccionDAO#getNumeroCarga()
	 */
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getNumeroCargaDireccion");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaMasivaDireccionDAO#getNumeroLote()
	 */
	public String getNumeroLote() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getNumeroLoteDireccion");	
	}

}

