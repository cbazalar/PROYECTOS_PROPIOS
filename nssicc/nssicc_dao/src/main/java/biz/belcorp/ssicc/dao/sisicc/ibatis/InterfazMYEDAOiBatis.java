package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz de MYE.
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 */
@Repository("sisicc.interfazMYEDAO")
public class InterfazMYEDAOiBatis extends BaseDAOiBatis implements InterfazMYEDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#deleteInterfazMYERecepcionarActualizacionDatosConsultora()
	 */
	public void deleteInterfazMYERecepcionarActualizacionDatosConsultora() {
		getSqlMapClientTemplate().delete("sisicc.InterfazMYESQL.deleteInterfazMYERecepcionarActualizacionDatosConsultora",null);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#executeInterfazMYEProcesarActualizacionDatosConsultora(java.util.Map)
	 */
	public void executeInterfazMYEProcesarActualizacionDatosConsultora(Map map) {
		getSqlMapClientTemplate().update("sisicc.InterfazMYESQL.executeInterfazMYEProcesarActualizacionDatosConsultora",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#executeInterfazMYERecepcionarActualizacionDatosConsultora(java.util.Map)
	 */
	public void executeInterfazMYERecepcionarActualizacionDatosConsultora(Map map) {
		getSqlMapClientTemplate().update("sisicc.InterfazMYESQL.executeInterfazMYERecepcionarActualizacionDatosConsultora",map);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#executeInterfazMYEEnviarFaltantesAnunciadosLimiteVenta(java.util.Map)
	 */
	public void executeInterfazMYEEnviarFaltantesAnunciadosLimiteVenta(Map queryParams) {

		if (log.isDebugEnabled()) {
			log.debug(queryParams.get("codigoPais"));
			log.debug(queryParams.get("codigoSistema"));
			log.debug(queryParams.get("codigoInterfaz"));
			log.debug(queryParams.get("nombreArchivo"));
			log.debug(queryParams.get("codigoPeriodo"));
		}

		getSqlMapClientTemplate()
				.update("sisicc.InterfazMYESQL.executeInterfazMYEEnviarFaltantesAnunciadosLimiteVenta",
						queryParams);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#executeInterfazMYEEnviarClientesWeb(java.util.Map)
	 */
	public void executeInterfazMYEEnviarClientesWeb(Map params) {
		getSqlMapClientTemplate()
		.update("sisicc.InterfazMYESQL.executeInterfazMYEEnviarClientesWeb",
				params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#executeInterfazMYEEnviarMaestrosIncobrables(java.util.Map)
	 */
	public void executeInterfazMYEEnviarMaestrosIncobrables(Map params) {
		getSqlMapClientTemplate()
		.update("sisicc.InterfazMYESQL.executeInterfazMYEEnviarMaestrosIncobrables",
				params);		
	}			
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#executeInterfazMYERecepcionarActivacionFlexipagoWeb(java.util.Map)
	 */
	public void executeInterfazMYERecepcionarActivacionFlexipagoWeb(Map params) {
		getSqlMapClientTemplate()
		.update("sisicc.InterfazMYESQL.executeInterfazMYERecepcionarActivacionFlexipagoWeb",
				params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#executeInterfazMYERecepcionarActivacionFlexipagoWebFLX(java.util.Map)
	 */
	public void executeInterfazMYERecepcionarActivacionFlexipagoWebFLX(Map params) {
		getSqlMapClientTemplate()
		.update("sisicc.InterfazMYESQL.executeInterfazMYERecepcionarActivacionFlexipagoWebFLX",
				params);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#getExisteMaestroClientes(java.util.Map)
	 */
	public int getExisteMaestroClientes(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazMYESQL.getExisteMaestroClientes", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#updateInterfazMYERecepcionarClientesIPUnica(java.util.Map)
	 */
	public void updateInterfazMYERecepcionarClientesIPUnica(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazMYESQL.updateInterfazMYERecepcionarClientesIPUnica", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#updateInterfazMYERecepcionarClientesIPUnica2(java.util.Map)
	 */
	public void updateInterfazMYERecepcionarClientesIPUnica2(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazMYESQL.updateInterfazMYERecepcionarClientesIPUnica2", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#getExisteTipoLogro(java.util.Map)
	 */
	@Override
	public int getExisteTipoLogro(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazMYESQL.getExisteTipoLogro", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#getExisteConsultoraMaestro(java.util.Map)
	 */
	@Override
	public int getExisteConsultoraMaestro(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazMYESQL.getExisteConsultoraMaestro", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#getCampañaPrimerUltimoPedidoConsultora(java.util.Map)
	 */
	@Override
	public List getCampañaPrimerUltimoPedidoConsultora(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList("sisicc.InterfazMYESQL.getCampañaPrimerUltimoPedidoConsultora", criteria);
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#getCampañasInicioFinLogroConsultora(java.util.Map)
	 */
	@Override
	public List getCampañasInicioFinLogroConsultora(Map criteria) {
		List resultado = getSqlMapClientTemplate().queryForList("sisicc.InterfazMYESQL.getCampañasInicioFinLogroConsultora", criteria);
    	return resultado;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#getIndicadorNuevasLogro(java.util.Map)
	 */
	@Override
	public int getIndicadorNuevasLogro(Map criteria) {
		return(Integer)getSqlMapClientTemplate().queryForObject("sisicc.InterfazMYESQL.getIndicadorNuevasLogro", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#insertInterfazMYERecepcionarMetas(java.util.Map)
	 */
	@Override
	public void insertInterfazMYERecepcionarMetas(Map criteria) {
		getSqlMapClientTemplate().insert("sisicc.InterfazMYESQL.insertInterfazMYERecepcionarMetas", criteria);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazMYEDAO#updateInterfazMYERecepcionarMetas(java.util.Map)
	 */
	@Override
	public void updateInterfazMYERecepcionarMetas(Map criteria) {
		getSqlMapClientTemplate().update("sisicc.InterfazMYESQL.updateInterfazMYERecepcionarMetas", criteria);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazMYEDAO#executeInterfazMYERecepcionarPremiosWeb(java.util.Map)
	 */
	public void executeInterfazMYERecepcionarPremiosWeb(Map params) {
		getSqlMapClientTemplate()
		.update("sisicc.InterfazMYESQL.executeInterfazMYERecepcionarPremiosWeb",
				params);		
	}
	
}