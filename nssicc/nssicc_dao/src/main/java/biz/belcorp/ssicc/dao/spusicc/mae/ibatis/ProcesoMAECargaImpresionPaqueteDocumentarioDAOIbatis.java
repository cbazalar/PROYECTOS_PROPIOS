package biz.belcorp.ssicc.dao.spusicc.mae.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.spusicc.mae.ProcesoMAECargaImpresionPaqueteDocumentarioDAO;

/**
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 *
 */
@Repository("spusicc.procesoMAECargaImpresionPaqueteDocumentarioDAO")
public class ProcesoMAECargaImpresionPaqueteDocumentarioDAOIbatis extends BaseDAOiBatis implements
	ProcesoMAECargaImpresionPaqueteDocumentarioDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAECargaImpresionPaqueteDocumentarioDAO#insertCargaImpresionPaqueteDocumentario(java.util.Map)
	 */
	public void insertCargaImpresionPaqueteDocumentario(Map params) {
		getSqlMapClientTemplate().insert(
				"spusicc.ProcesosMAESQL.insertCargaImpresionPaqueteDocumentario", params);

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ProcesoMAECargaImpresionPaqueteDocumentarioDAO#executeInsertarCargaImpresionPaqueteDocumentario(java.util.Map)
	 */
	public void executeInsertarCargaImpresionPaqueteDocumentario(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeInsertarCargaImpresionPaqueteDocumentario", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaImpresionPaqueteDocumentarioDAO#executeValidarCargaImpresionPaqueteDocumentario(java.util.Map)
	 */
	public void executeValidarCargaImpresionPaqueteDocumentario(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeValidarCargaImpresionPaqueteDocumentario", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaImpresionPaqueteDocumentarioDAO#getCargaImpresionPaqueteDocumentarioList(java.util.Map)
	 */
	public List getCargaImpresionPaqueteDocumentarioList(Map params) {
		 return getSqlMapClientTemplate().queryForList("spusicc.ProcesosMAESQL.getCargaImpresionPaqueteDocumentarioList", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaImpresionPaqueteDocumentarioDAO#executeActualizarCargaImpresionPaqueteDocumentario(java.util.Map)
	 */
	public void executeActualizarCargaImpresionPaqueteDocumentario(Map params) {
		getSqlMapClientTemplate().update("spusicc.ProcesosMAESQL.executeActualizarCargaImpresionPaqueteDocumentario", params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.spusicc.mae.dao.ibatis.ProcesoMAECargaImpresionPaqueteDocumentarioDAO#getNumeroCarga()
	 */
	public String getNumeroCarga() {
		return (String)getSqlMapClientTemplate().queryForObject("spusicc.ProcesosMAESQL.getNumeroCargaImpresion");
	}

}
