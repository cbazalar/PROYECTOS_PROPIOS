package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazFLXDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz Flexipago.
 * 
 * @author <a href="mailto:itocto@sigcomt.com">Ivan Tocto Jaimes</a>
 */
@Repository("sisicc.interfazFLXDAO")
public class InterfazFLXDAOiBatis extends BaseDAOiBatis implements InterfazFLXDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#insertInterfazAVIRecepcionarConsultorasHabiles(java.util.Map)
	 */
	public void executeInterfazAVIRecepcionarConsultorasHabiles(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazFLXSQL.executeInterfazAVIRecepcionarConsultorasHabiles", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#insertInterfazAVIRecepcionarConsultorasComunicacion(java.util.Map)
	 */
	public void executeInterfazAVIRecepcionarConsultorasComunicacion(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazFLXSQL.executeInterfazAVIRecepcionarConsultorasComunicacion", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnviarResultadoProgramas(java.util.Map)
	 */
	public void executeEnviarResultadoProgramas(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeEnviarResultadoProgramas", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnviarConsultorasObjetadas(java.util.Map)
	 */
	public void executeEnviarConsultorasObjetadas(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeEnviarConsultorasObjetadas", params);
		
	}

	public void executeEnviarInformacionConsultorasHabiles(Map params){
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeEnviarInformacionConsultorasHabiles", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeProcesarFlexipagoGP3(java.util.Map)
	 */
	public void executeProcesarFlexipagoGP3(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeProcesarFlexipagoGP3", params);
	}
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeRecepcionarConsultorasRechazadasWEB(java.util.Map)
	 */
	public void executeRecepcionarConsultorasRechazadasWEB(Map map) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeRecepcionarConsultorasRechazadasWEB", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeRecepcionarConsultorasRecomendadasWEB(java.util.Map)
	 */
	public void executeRecepcionarConsultorasRecomendadasWEB(Map map) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeRecepcionarConsultorasRecomendadasWEB", map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnvioInformacionProcesosComerciales(java.util.Map)
	 */
	public void executeEnvioInformacionProcesosComerciales(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeEnvioInformacionProcesosComerciales", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeRecepcionarVariablesModelo(java.util.Map)
	 */
	public void executeRecepcionarVariablesModelo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeRecepcionarVariablesModelo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeCalculoVariablesModelo(java.util.Map)
	 */
	public void executeCalculoVariablesModelo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeCalculoVariablesModelo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnviarResultadosPrograma(java.util.Map)
	 */
	public void executeEnviarResultadosPrograma(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeEnviarResultadosPrograma", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeCalculoVariablesCuentaCorriente(java.util.Map)
	 */
	public void executeCalculoVariablesCuentaCorriente(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeCalculoVariablesCuentaCorriente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeEnviarVariablesCuentaCorriente(java.util.Map)
	 */
	public void executeEnviarVariablesCuentaCorriente(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeEnviarVariablesCuentaCorriente", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazFLXDAO#executeRecepcionarVariablesVenta(java.util.Map)
	 */
	public void executeRecepcionarVariablesVenta(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazFLXSQL.executeRecepcionarVariablesVenta", params);
	}
	
}