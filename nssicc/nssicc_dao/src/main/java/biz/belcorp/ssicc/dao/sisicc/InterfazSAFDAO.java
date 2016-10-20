/**
 * 
 */
package biz.belcorp.ssicc.dao.sisicc;

import java.util.Map;

import biz.belcorp.ssicc.dao.framework.DAO;

/**
  * <p>
 * <a href="InterfazSAFDAO.java.html"> <i>View Source</i> </a>
 * </p>
 * 
 * @author <a href="mailto:jcairampoma@belcorp.biz">Jose Cairampoma</a>
 * 
 */
public interface InterfazSAFDAO extends DAO {

	public void executeInterfazSAFEnviarFacturacion(Map params);

	public void executeInterfazSAFEnviarCabeceraColombia(Map params);
	
	public void executeInterfazSAFEnviarDetalleColombia(Map params);
	
	/**
	 * Ejecuta procedure correspondiente a Interfaz SAF-4
	 * @param params
	 */
	public void executeInterfazSAFEnviarComisionEjecutiva(Map params);
	
	/**
	 * Ejecuta procedure correspondiente a Interfaz IMP-231 (Carga SAPFI Corporativo)
	 * @param params
	 */
	public void executeInterfazSAFEnviarCargaSAPFICorporativo(Map params);
	
	
	/**
	 * Ejecuta procedure correspondiente a Interfaz IMP-241 (Carga Financiero Corporativo)
	 * @param params
	 */
	public void executeInterfazSAFProcesoSAPCargaFinancieroCorporativo(Map params);
	
	/**
	 * Ejecuta procedure correspondiente a Interfaz IMP-232 (Interfaz SAPFI Corporativo)
	 * @param params
	 */
	public void executeInterfazSAFEnviarSAPFICorporativo(Map params);
	
	/**
	 * Ejecuta procedure correspondiente a Interfaz IMP-242 (Proceso Financiero Corporativo)
	 * @param params
	 */
	public void executeInterfazSAFProcesoSAPFinancieroCorporativo(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazSAFReprocesoEnviarSAPFICorporativo(Map params);
	
	/**
	 * 
	 * @param params
	 */
	public void deleteSAFReprocesoEnviarSAPFICorporativo(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazSAFReporteCabecera(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazSAFReporteDetalle(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazSAFReporteResumen(Map params);
	/**
	 * @param params
	 */
	public void  executeInterfazSAFPagosLet(Map params);
	
	/**
	 * Execute interfaz sapfi enviar informacion socias empresarias.
	 *
	 * @param params the params
	 */
	public void executeInterfazSAPFIEnviarInformacionSociasEmpresarias(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazSAPEnviarCobranzaxEtapas(Map params);
	
	/**
	 * @param params
	 */
	public void executeInterfazSAPEnviarFechaCierreFacturacion(Map params);
	
	public String getCampoPeriodoActualSAP();
	
	/**
	 * 
	 * @param params
	 */
	public void executeInterfazSAPFIEnviarInformacionPagosConcursoVentas(Map params);
	
}
