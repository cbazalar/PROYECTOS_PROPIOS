/**
 * 
 */
package biz.belcorp.ssicc.reportes.framework.service;

import java.util.Map;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.framework.Service;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;

/**
 * @author Danny Amaro
 *
 */
public interface BaseReporteInterface extends Service {

	/**
	 * Metodo que se ejecuta antes de la ejecucion Principal de GrabarReporte. 
	 * En este metodo se implementa la logica de existir operaciones extra antes de la ejecucion del reporte
	 * @param reporteParams
	 * @param formReporte
	 * @return
	 * @throws Exception
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception;
	
	/**
	 * @param reporteParams
	 * @param formReporte
	 * @return
	 * @throws Exception
	 */
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception;
	
	/**
	 * @param form
	 */
	public void beforeGrabarReporte(BaseReporteForm form);
	
	/**
	 * @param form
	 */
	public void beforeIniBucleGrabarReporte(BaseReporteForm form);
	
	/**
	 * @param form
	 * @throws Exception TODO
	 */
	public void afterGrabarReporte(BaseReporteForm form) throws Exception;
	
		
	/**
	 * @param form
	 * @return
	 */
	public boolean continueExecuteReporte(BaseReporteForm form);
	
	
	/**
	 * @param parameterMap
	 * @return
	 */
	public Map<String, Object> generarReporteOracle (Map<String, Object> parameterMap);
	
	/**
	 * Devuelve en caso exista el Titulo para el Reporte en Oracle
	 * @param request
	 * @param parameterMap
	 * @return
	 */
	public String obtieneTituloReporteOracle(Map<String, Object> parameterMap) ;
	
    /**
     *  Devuelve key para el Reporte en Oracle
     * @param parameterMap
     * @return
     */
    public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap);
	
    /**
     * Devuelve nombre del Reporte generado en Oracle
     * @param parameterMap
     * @return
     */
    public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap);
    
    /**
	 * Obtiene Tipo de Datos para los campos a generar en el Reporte en Oracle
	 * @param parameterMap
	 * @return
	 */
	public String obtieneTipoDatosReporteOracle(Map<String, Object> parameterMap);
	
	/**
	 * @return
	 */
	public boolean isEjecutarCommitBeforeExecuteReporte();
	
	/**
	 * Metodo que convierte un archivo csv a xlsx.
	 *
	 * @param csvFile the csv file
	 * @param xlsxFile the xlsx file
	 * @throws Exception the exception
	 */
	public void convertircsvToxlsx(String csvFile, String xlsxFile) throws Exception;
	
	/**
	 * Obtiene condicion.
	 *
	 * @param lista the lista
	 * @param parametro the parametro
	 * @param comilla the comilla
	 * @return the string
	 */
	public String obtieneCondicion(String[] lista, String parametro, String comilla);
	
}
