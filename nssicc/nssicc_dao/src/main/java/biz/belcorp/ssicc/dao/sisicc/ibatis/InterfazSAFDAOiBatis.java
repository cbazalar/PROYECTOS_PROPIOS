/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc.ibatis;

import java.util.Map;

import org.springframework.stereotype.Repository;

import biz.belcorp.ssicc.dao.framework.ibatis.BaseDAOiBatis;
import biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO;

/**
 * 
 * <p>
 * <a href="InterfazSAFDAOiBatis.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
@Repository("sisicc.interfazSAFDAO")
public class InterfazSAFDAOiBatis extends BaseDAOiBatis implements	InterfazSAFDAO {

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFEnviarFacturacion(java.util.Map)
	 */
	public void executeInterfazSAFEnviarFacturacion(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFEnviarFacturacion",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFEnviarCabeceraColombia(java.util.Map)
	 */
	public void executeInterfazSAFEnviarCabeceraColombia(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFEnviarCabeceraColombia",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFEnviarDetalleColombia(java.util.Map)
	 */
	public void executeInterfazSAFEnviarDetalleColombia(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFEnviarDetalleColombia",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFEnviarComisionEjecutiva(java.util.Map)
	 */
	public void executeInterfazSAFEnviarComisionEjecutiva(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFEnviarComisionEjecutiva",params);
	}
	
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFEnviarCargaSAPFICorporativo(java.util.Map)
     */
    public void executeInterfazSAFEnviarCargaSAPFICorporativo(Map params) {
    	getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFEnviarCargaSAPFICorporativo",params);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFProcesoSAPCargaFinancieroCorporativo(java.util.Map)
     */
    public void executeInterfazSAFProcesoSAPCargaFinancieroCorporativo(Map params) {
    	getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFProcesoSAPCargaFinancieroCorporativo",params);
    }
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFEnviarSAPFICorporativo(java.util.Map)
	 */
	public void executeInterfazSAFEnviarSAPFICorporativo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFEnviarSAPFICorporativo",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFProcesoSAPFinancieroCorporativo(java.util.Map)
	 */
	public void executeInterfazSAFProcesoSAPFinancieroCorporativo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFProcesoSAPFinancieroCorporativo",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFReprocesoEnviarSAPFICorporativo(java.util.Map)
	 */
	public void executeInterfazSAFReprocesoEnviarSAPFICorporativo(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFReprocesoEnviarSAPFICorporativo",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#deleteSAFReprocesoEnviarSAPFICorporativo(java.util.Map)
	 */
	public void deleteSAFReprocesoEnviarSAPFICorporativo(Map params) {
		getSqlMapClientTemplate().delete(
				"sisicc.InterfazSAFSQL.deleteSAFReprocesoEnviarSAPFICorporativo", params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFReporteCabecera(java.util.Map)
	 */
	public void executeInterfazSAFReporteCabecera(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFReporteCabecera",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFReporteDetalle(java.util.Map)
	 */
	public void executeInterfazSAFReporteDetalle(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFReporteDetalle",params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFReporteResumen(java.util.Map)
	 */
	public void executeInterfazSAFReporteResumen(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFReporteResumen",params);
	}
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAFReporteResumen(java.util.Map)
	 */
	public void executeInterfazSAFPagosLet(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAFPagosLet",params);
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAPFIEnviarInformacionSociasEmpresarias(java.util.Map)
	 */
	public void executeInterfazSAPFIEnviarInformacionSociasEmpresarias(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAPFIEnviarInformacionSociasEmpresarias",params);
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO#executeInterfazSAPEnviarCobranzaxEtapas(java.util.Map)
	 */
	public void executeInterfazSAPEnviarCobranzaxEtapas(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAPEnviarCobranzaxEtapas",params);
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO#executeInterfazSAPEnviarFechaCierreFacturacion(java.util.Map)
	 */
	public void executeInterfazSAPEnviarFechaCierreFacturacion(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAPEnviarFechaCierreFacturacion",params);
		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.dao.sisicc.InterfazSAFDAO#getCampoPeriodoActualSAP()
	 */
	public String getCampoPeriodoActualSAP() {
		return (String) getSqlMapClientTemplate().queryForObject("sisicc.InterfazSAFSQL.getCampoPeriodoActualSAP");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.sisicc.dao.InterfazSAFDAO#executeInterfazSAPFIEnviarInformacionPagosConcursoVentas(java.util.Map)
	 */
	public void executeInterfazSAPFIEnviarInformacionPagosConcursoVentas(Map params) {
		getSqlMapClientTemplate().update("sisicc.InterfazSAFSQL.executeInterfazSAPFIEnviarInformacionPagosConcursoVentas",params);
	}
	
}