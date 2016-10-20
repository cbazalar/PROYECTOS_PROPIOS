package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazPOLDAO;


/**
 * 
 * <p>
 * <a href="InterfazPOLDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:dtorres@sigcomt.com">Diego Torres Loyola</a>
 * 
 */
@Repository("sisicc.interfazPOLDAO")
public class InterfazPOLDAOiBatis extends BaseDAOiBatis implements InterfazPOLDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazPOLDAO#insertInterfazLETRecepcionarCursos(java.util.Map)
	 */
	public void executeInterfazPOLEnviarConsultora(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnvioConsultora",params);
	}

	public void executeInterfazPOLEnviarRecomendaciones(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarRecomendaciones",params);
	}

	public void executeInterfazPOLEnviarZonas(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarZonas",params);
	}

	public void executeInterfazPOLEnviarEstadisticoPorZona(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarEstadisticoPorZona",params);
	}

	public void executeInterfazPOLEnviarConsolidadoPedidos(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarConsolidadoPedidos",params);
	}

	public void executeInterfazPOLEnviarMatrizFacturacion(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarMatrizFacturacion",params);
	}

	public void executeInterfazPOLEnviarFueraCaja(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarFueraCaja",params);
	}

	public void executeInterfazPOLEnviarCargosCuentaCorriente(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarCargosCuentaCorriente",params);
	}

	public void executeInterfazPOLEnviarAbonosCuentaCorriente(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarAbonosCuentaCorriente",params);
	}

	public void executeInterfazPOLEnviarCantidadInteresesProcesados(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarCantidadInteresesProcesados",params);
	}
	
	public void executeInterfazPOLEnviarArchivoControlModuloLocal(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazPOLSQL.executeInterfazPOLEnviarArchivoControlModuloLocal",params);		
	}
	
	
}
