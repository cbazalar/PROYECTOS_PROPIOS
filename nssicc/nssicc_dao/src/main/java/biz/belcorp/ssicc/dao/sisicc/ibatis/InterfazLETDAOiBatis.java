/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;


import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazLETDAO;


/**
 * 
 * <p>
 * <a href="InterfazLETDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:cbazalar@sigcomt.com">Carlos Bazalar</a>
 * 
 */
@Repository("sisicc.InterfazLETDAO")
public class InterfazLETDAOiBatis extends BaseDAOiBatis implements InterfazLETDAO {

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarCampannaActiva(java.util.Map)
	 */
	public void executeInterfazLETEnviarCampannaActiva(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarCampannaActiva",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarConsultoras(java.util.Map)
	 */
	public void executeInterfazLETEnviarConsultoras(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarConsultoras",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarTipoOperacion(java.util.Map)
	 */
	public void executeInterfazLETEnviarTipoOperacion(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarTipoOperacion",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarRegiones(java.util.Map)
	 */
	public void executeInterfazLETEnviarRegiones(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarRegiones",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarZonas(java.util.Map)
	 */
	public void executeInterfazLETEnviarZonas(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarZonas",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarTerritorios(java.util.Map)
	 */
	public void executeInterfazLETEnviarTerritorios(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarTerritorios",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarZonaConsultora(java.util.Map)
	 */
	public void executeInterfazLETEnviarZonaConsultora(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarZonaConsultora",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarTerritorioConsultora(java.util.Map)
	 */
	public void executeInterfazLETEnviarTerritorioConsultora(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarTerritorioConsultora",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarNuevas(java.util.Map)
	 */
	public void executeInterfazLETEnviarNuevas(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarNuevas",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarConsultoraFactura(java.util.Map)
	 */
	public void executeInterfazLETEnviarConsultoraFactura(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarConsultoraFactura",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarRecomendacion(java.util.Map)
	 */
	public void executeInterfazLETEnviarRecomendacion(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarRecomendacion",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarLideres(java.util.Map)
	 */
	public void executeInterfazLETEnviarLideres(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarLideres",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarPagosCabecera(java.util.Map)
	 */
	public void executeInterfazLETEnviarPagosCabecera(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarPagosCabecera",params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarPagosCabecera1(java.util.Map)
	 */
	public void executeInterfazLETEnviarPagosCabecera1(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarPagosCabecera1",params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarPagosDetalle(java.util.Map)
	 */
	public void executeInterfazLETEnviarPagosDetalle(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarPagosDetalle",params);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarPagosDetalle(java.util.Map)
	 */
	public void executeInterfazLETEnviarPagosDetalle1(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarPagosDetalle1",params);		
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarTarjetasAsociadasCab(java.util.Map)
	 */
	public void executeInterfazLETEnviarTarjetasAsociadasCab(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarTarjetasAsociadasCab",params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#executeInterfazLETEnviarTarjetasAsociadasDeta(java.util.Map)
	 */
	public void executeInterfazLETEnviarTarjetasAsociadasDeta(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazLETSQL.executeInterfazLETEnviarTarjetasAsociadasDeta",params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#deleteInterfazLETRecepcionarCursos(java.util.Map)
	 */
	public void deleteInterfazLETRecepcionarCursos(Map params) {
		getSqlMapClientTemplate().delete("sisicc.InterfazLETSQL.deleteInterfazLETRecepcionarCursos",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazLETDAO#insertInterfazLETRecepcionarCursos(java.util.Map)
	 */
	public void insertInterfazLETRecepcionarCursos(Map params) {
		getSqlMapClientTemplate().insert("sisicc.InterfazLETSQL.insertInterfazLETRecepcionarCursos",params);
	}
	
}