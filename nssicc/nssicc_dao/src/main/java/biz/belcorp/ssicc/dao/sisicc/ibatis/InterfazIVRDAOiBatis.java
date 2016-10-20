/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazIVRDAO;

/**
 * 
 * <p>
 * <a href="InterfazIVRDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
@Repository("sisicc.interfazIVRDAO")
public class InterfazIVRDAOiBatis extends BaseDAOiBatis implements	InterfazIVRDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#deleteTablaControlIVRCorporativo()
	 */
	public void deleteTablaControlIVRCorporativo() {
		
		getSqlMapClientTemplate().delete("sisicc.InterfazIVRSQL.deleteTablaControlIVRCorporativo",null);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeIVREnviarTablaClientesCorporativo(java.util.Map)
	 */
	public void executeIVREnviarTablaClientesCorporativo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeIVREnviarTablaClientesCorporativo", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaConcursosCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaConcursosCorporativo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaConcursosCorporativo", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaControlCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaControlCorporativo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaControlCorporativo", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaPostVentaCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaPostVentaCorporativo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaPostVentaCorporativo", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaRechazosPostVentaCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaRechazosPostVentaCorporativo(
			Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaRechazosPostVentaCorporativo", params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaLlamadasSalidaCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaLlamadasSalidaCorporativo(
			Map params){
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaLlamadasSalidaCorporativo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaResultadosCampanaCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaResultadosCampanaCorporativo(
			Map params){
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaResultadosCampanaCorporativo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaProgramaDuplaCyzoneCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaProgramaDuplaCyzoneCorporativo(
			Map params){
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaProgramaDuplaCyzoneCorporativo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaMaestroDuplaCyzoneCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaMaestroDuplaCyzoneCorporativo(
			Map params){
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaMaestroDuplaCyzoneCorporativo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaEstadoPedidoCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaEstadoPedidoCorporativo(Map params){
		
		String indNuevaVersion =  (String)params.get("indNuevaVersion");
		if (indNuevaVersion.equals(Constants.SI)){
			getSqlMapClientTemplate().queryForList("sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaEstadoPedidoCorporativo", params);	
		} else {
			getSqlMapClientTemplate().queryForList("sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaEstadoPedidoCorporativoNuevaVersion", params);
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaBonosSalidaCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaBonosSalidaCorporativo(
			Map params){
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaBonosSalidaCorporativo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeIVREnviarTablaCronogramaCorporativo(java.util.Map)
	 */
	public void executeIVREnviarTablaCronogramaCorporativo(
			Map params){
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeIVREnviarTablaCronogramaCorporativo", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarMotivosRechazoCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarMotivosRechazoCorporativo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarMotivosRechazoCorporativo", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaBonosSalidaCorp(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaBonosSalidaCorp(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaBonosSalidaCorp", params);	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaClienteCorporativo(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaClienteCorporativo(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaClienteCorporativo", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaControlCorp(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaControlCorp(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaControlCorp", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaCronogramaActividades(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaCronogramaActividades(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaCronogramaActividades", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaEstadoPedido(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaEstadoPedido(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaEstadoPedido", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarTablaMatrizCampanha(java.util.Map)
	 */
	public void executeInterfazIVREnviarTablaMatrizCampanha(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarTablaMatrizCampanha", params);		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#insertInterfazIVRRecepcionarPedidos(java.util.Map)
	 */
	public void insertInterfazIVRRecepcionarPedidos(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazIVRSQL.insertInterfazIVRRecepcionarPedidos",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeRegistraPedidoIVR(java.util.Map)
	 */
	public void executeRegistraPedidoIVR(Map params) {
		getSqlMapClientTemplate().queryForList("sisicc.InterfazIVRSQL.executeRegistraPedidoIVR", params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazIVRDAO#executeInterfazIVREnviarDataSOA(java.util.Map)
	 */
	public void executeInterfazIVREnviarDataSOA(Map params) {
		getSqlMapClientTemplate().queryForList(
				"sisicc.InterfazIVRSQL.executeInterfazIVREnviarDataSOA", params);			
	}
	
}