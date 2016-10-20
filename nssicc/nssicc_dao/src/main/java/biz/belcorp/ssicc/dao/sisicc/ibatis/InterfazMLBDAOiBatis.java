package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazMLBDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz My LBEL.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("sisicc.interfazMLBDAO")
public class InterfazMLBDAOiBatis extends BaseDAOiBatis implements
		InterfazMLBDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarConcursos(java.util.Map)
	 */
	public void executeInterfazMLBEnviarConcursos(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarConcursos", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarNiveles(java.util.Map)
	 */
	public void executeInterfazMLBEnviarNiveles(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarNiveles", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarPremios(java.util.Map)
	 */
	public void executeInterfazMLBEnviarPremios(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarPremios", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarDescripcionPremios(java.util.Map)
	 */
	public void executeInterfazMLBEnviarDescripcionPremios(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarDescripcionPremios", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarZonas(java.util.Map)
	 */
	public void executeInterfazMLBEnviarZonas(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarZonas", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarPremiosAsignados(java.util.Map)
	 */
	public void executeInterfazMLBEnviarPremiosAsignados(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarPremiosAsignados", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarPremiosDespachados(java.util.Map)
	 */
	public void executeInterfazMLBEnviarPremiosDespachados(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarPremiosDespachados", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarPuntajes(java.util.Map)
	 */
	public void executeInterfazMLBEnviarPuntajes(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarPuntajes", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarRecomendaciones(java.util.Map)
	 */
	public void executeInterfazMLBEnviarRecomendaciones(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarRecomendaciones", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMLBDAO#executeInterfazMLBEnviarArchivoControl(java.util.Map)
	 */
	public void executeInterfazMLBEnviarArchivoControl(Map params) {
		
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarArchivoControlLock", params);
		getSqlMapClientTemplate().update(
				"sisicc.InterfazMLBSQL.executeInterfazMLBEnviarArchivoControl", params);
		
	}
	
}
