package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazAVIDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz Asistente Virtual.
 * 
 * @author <a href="mailto:sapaza@belcorp.biz">Sergio Apaza</a>
 */
@Repository("sisicc.interfazAVIDAO")
public class InterfazAVIDAOiBatis extends BaseDAOiBatis implements InterfazAVIDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#getInterfazAVIPrefacturacionCabecera(java.util.Map)
	 */
	public List getInterfazAVIPrefacturacionCabecera(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazAVIPrefacturacionCabecera",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#getInterfazAVIPrefacturacionDetalle(java.util.Map)
	 */
	public List getInterfazAVIPrefacturacionDetalle(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazAVIPrefacturacionDetalle",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#cargarFacturacionCabecera(java.util.Map)
	 */
	public void cargarFacturacionCabecera(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.cargarAVIFacturacionCabecera", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#cargarFacturacionDetalle(java.util.Map)
	 */
	public void cargarFacturacionDetalle(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.cargarAVIFacturacionDetalle", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#getInterfazAVIFacturacionCabecera(java.util.Map)
	 */
	public List getInterfazAVIFacturacionCabecera(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazAVIFacturacionCabecera", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#getInterfazAVIFacturacionDetalle(java.util.Map)
	 */
	public List getInterfazAVIFacturacionDetalle(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazAVIFacturacionDetalle", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#getInterfazAVIConsultoraCDRCabecera(java.util.Map)
	 */
	public List getInterfazAVIConsultoraCDRCabecera(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazAVIConsultoraCDRCabecera", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#getInterfazAVIConsultoraCDRDetalle(java.util.Map)
	 */
	public List getInterfazAVIConsultoraCDRDetalle(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazAVIConsultoraCDRDetalle", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIConsultoraCDRDetalle(java.util.Map)
	 */
	public void executeInterfazAVIConsultoraCDRDetalle(Map params) {
		if (log.isDebugEnabled()) {
			log.debug("Iniciando executeInterfazAVIConsultoraCDRDetalle, params="
					+ params);
		}
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazAVIConsultoraCDRDetalle",
				params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIConsultoraPuntaje(java.util.Map)
	 */
	public void executeInterfazAVIConsultoraPuntaje(Map params) {
		if (log.isDebugEnabled()) {
			log.debug("Iniciando executeInterfazAVIConsultoraPuntaje, params="
					+ params);
		}
		getSqlMapClientTemplate().update(
				"sisicc.InterfazAVISQL.executeInterfazAVIConsultoraPuntaje",
				params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#getInterfazAVITerritorio(java.util.Map)
	 */
	public List getInterfazAVITerritorio(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazAVITerritorio", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVISaldosConsultora(java.util.Map)
	 */
	public void executeInterfazAVISaldosConsultora(Map params) {
		if (log.isDebugEnabled()) {
			log.debug("Iniciando executeInterfazAVISaldosConsultora, params="
					+ params);
		}
		getSqlMapClientTemplate().update(
				"sisicc.InterfazAVISQL.executeInterfazAVISaldosConsultora",
				params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIConsultorasBloqueada(java.util.Map)
	 */
	public void executeInterfazAVIConsultorasBloqueada(Map params) {
		log.info("Entro a InterfazAVIDAOiBatis - executeInterfazAVIConsultorasBloqueada(Map)");
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIConsultorasBloqueada", params);
		log.info("Salio a InterfazAVIDAOiBatis - executeInterfazAVIConsultorasBloqueada(Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVITiposBloqueo(java.util.Map)
	 */
	public void executeInterfazAVITiposBloqueo(Map params) {
		log.info("Entro a InterfazAVIDAOiBatis - executeInterfazAVITiposBloqueo(Map)");
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVITiposBloqueo", params);
		log.info("Salio a InterfazAVIDAOiBatis - executeInterfazAVITiposBloqueo(Map)");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIEnvioMetasLogro(java.util.Map)
	 */
	public void executeInterfazAVIEnvioMetasLogro(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIEnvioMetasLogro", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIEnvioSecciones(java.util.Map)
	 */
	public void executeInterfazAVIEnvioSecciones(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIEnvioSecciones", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIEnvioVentaGanancia(java.util.Map)
	 */
	public void executeInterfazAVIEnvioVentaGanancia(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIEnvioVentaGanancia", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIEnvioVentaMarca(java.util.Map)
	 */
	public void executeInterfazAVIEnvioVentaMarca(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIEnvioVentaMarca", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIEnviarIndicadoresGGZZ(java.util.Map)
	 */
	public void executeInterfazAVIEnviarIndicadoresGGZZ(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIEnviarIndicadoresGGZZ", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIEnviarInformacionAdicional(java.util.Map)
	 */
	public void executeInterfazAVIEnviarInformacionAdicional(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIEnviarInformacionAdicional", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#insertInterfazAVIRecepcionarLogros(java.util.Map)
	 */
	public void insertInterfazAVIRecepcionarLogros(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazAVISQL.insertInterfazAVIRecepcionarLogros", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeValidacionRecepcionAsistenteVirtual(java.util.Map)
	 */
	public void executeValidacionRecepcionAsistenteVirtual(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeValidacionRecepcionAsistenteVirtual", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#updateInterfazAVIRecepcionarLogros(java.util.Map)
	 */
	public void updateInterfazAVIRecepcionarLogros(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.updateInterfazAVIRecepcionarLogros", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIEnviarTiposLogros(java.util.Map)
	 */
	public void executeInterfazAVIEnviarTiposLogros(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIEnviarTiposLogros", params);
	}
	
	/* INI SA PER-SiCC-2012-0344 */
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#getListTiposVisita()
	 */
	public List getListTiposVisita() {
		return getSqlMapClientTemplate().queryForList("sisicc.InterfazAVISQL.getListTiposVisita");		
	}
	/* FIN SA PER-SiCC-2012-0344 */
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIPrefacturacionCabecera(java.util.Map)
	 */
	public void executeInterfazAVIPrefacturacionCabecera(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIPrefacturacionCabecera", params);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIPrefacturacionDetalle(java.util.Map)
	 */
	public void executeInterfazAVIPrefacturacionDetalle(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIPrefacturacionDetalle", params);		
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIConsultoraCDRCabecera(java.util.Map)
	 */
	public void executeInterfazAVIConsultoraCDRCabecera(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIConsultoraCDRCabecera", params);	
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazAVIDAO#executeInterfazAVIEnviarTerritorio(java.util.Map)
	 */
	public void executeInterfazAVIEnviarTerritorio(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazAVISQL.executeInterfazAVIEnviarTerritorio", params);	
	}
}