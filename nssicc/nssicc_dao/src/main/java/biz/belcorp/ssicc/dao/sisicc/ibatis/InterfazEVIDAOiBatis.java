package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazEVIDAO;

/**
 * Implementacion iBatis del DAO de la Interfaz Ejecutivo Virtual.
 * 
 * @author <a href="mailto:lshimokawa@belcorp.biz">Lennon Shimokawa</a>
 */
@Repository("sisicc.interfazEVIDAO")
public class InterfazEVIDAOiBatis extends BaseDAOiBatis implements
		InterfazEVIDAO {

	public void updateInterfazEVIRecepcionarOCSaldoDeudor(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.updateInterfazEVIRecepcionarOCSaldoDeudor",
				params);
	}

	public void actualizaOCSConsultorasInactivas(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.actualizaOCSConsultorasInactivas",
				params);
	}
	
	public void insertInterfazEVIRecepcionarOC(Map params) {
		getSqlMapClientTemplate().insert(
				"sisicc.InterfazSQL.insertInterfazEVIRecepcionarOC", params);
	}

	public List getInterfazEVIRecepcionarPrimerosPedidos(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazEVIRecepcionarPrimerosPedidos",
				params);
	}

	public void insertInterfazEVIRecepcionarPrimerosPedidos(Map params) {
		getSqlMapClientTemplate()
				.insert(
						"sisicc.InterfazSQL.insertInterfazEVIRecepcionarPrimerosPedidos",
						params);
	}

	public void updateInterfazEVIRecepcionarPrimerosPedidos(Map params) {
		getSqlMapClientTemplate()
				.insert(
						"sisicc.InterfazSQL.updateInterfazEVIRecepcionarPrimerosPedidos",
						params);
	}

	public void cargarResumenesPrefacturacion() {
		log.info("Iniciando cargarResumenesPrefacturacion");
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.cargarResumenesPrefacturacion", null);
		log.info("Finalizado cargarResumenesPrefacturacion");
	}

	public void cargarFacturacionCabecera(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.cargarFacturacionCabecera", params);
	}

	public void cargarFacturacionDetalle(Map params) {
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.cargarFacturacionDetalle", params);
	}

	public List getInterfazEVIPrefacturacionCabecera(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazEVIPrefacturacionCabecera",
				params);
	}

	public List getInterfazEVIPrefacturacionDetalle(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazEVIPrefacturacionDetalle",
				params);
	}

	public List getInterfazEVIFacturacionCabecera(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazEVIFacturacionCabecera", params);
	}

	public List getInterfazEVIFacturacionDetalle(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazEVIFacturacionDetalle", params);
	}

	public List getInterfazEVIConsultoraCDR(Map params) {
		return getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazSQL.getInterfazEVIConsultoraCDR", params);
	}

	public void executeInterfazEVIConsultoraPuntaje(Map params) {
		if (log.isDebugEnabled()) {
			log.debug("Iniciando executeInterfazEVIConsultoraPuntaje, params="
					+ params);
		}
		getSqlMapClientTemplate().update(
				"sisicc.InterfazSQL.executeInterfazEVIConsultoraPuntaje",
				params);
	}
}