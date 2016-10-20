package biz.belcorp.ssicc.reportes.framework.service.impl;


import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface;
import biz.belcorp.ssicc.service.framework.impl.BaseService;
import biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams;
import biz.belcorp.ssicc.service.util.ExcelXLSXUtil;

/**
 * Base ActionForm bean. Used to give child classes readable representation of
 * their properties using toString() method.
 * </p>
 * 
 * <p>
 * Also has a validate() method to cancel validation on cancel actions.
 * </p>
 * 
 * <p>
 * <a href="BaseForm.java.html"> <i>View Source </i> </a>
 * </p> 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible </a>
 * @version $Revision: 1.3 $ $Date: 2009/04/15 15:32:57 $
 */

public class BaseReporteInterfaceImpl extends BaseService implements BaseReporteInterface {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8468620648845143814L;
	protected transient final Log log = LogFactory.getLog(getClass());
	
	private  static final String ARCHIVO_REPORT_RESOURCE = "ReportResources";

	public String  nombreReporte;	
	public String  prefijoArchivo; 
	protected boolean ejecutarCommitBeforeExecuteReporte = false;

	public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
    
	/**
	 * @param key
	 * @return Mensaje de reporte resources (Archivo de mensajes .properties)
	 */
	public String getReportResourceMessage(String key){
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getReportResourceMessage' method");
		}
		if (StringUtils.isBlank(key)) return "";
		try {
			ResourceBundle res = ResourceBundle.getBundle(BaseReporteInterfaceImpl.ARCHIVO_REPORT_RESOURCE);					
			String mensaje = (String)res.getObject(key);			
			log.debug("mensaje: " + mensaje);
			return mensaje;
		}
		catch (Exception e) {
			log.error("Error to get Resource Boundle "+e);
			return "";
		}
	}
	
    	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface#beforeExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams beforeExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception{
		return reporteParams;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface#afterExecuteReporte(biz.belcorp.ssicc.service.scsicc.framework.beans.ReporteParams, biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm)
	 */
	public ReporteParams afterExecuteReporte(ReporteParams reporteParams, BaseReporteForm formReporte) throws Exception {
		return reporteParams;
	}
	
	/**
	 * Metodo que se ejecuta antes de la ejecucion Principal de GrabarReporte. 
	 * Dicho metodo puede ser sobreescrito
	 */
	public void beforeGrabarReporte(BaseReporteForm form) {		
				
	}
	
	/**
	 * Metodo que se ejecuta antes de la ejecucion de reportes multiples. 
	 * Dicho metodo puede ser sobreescrito
	 */
	public void beforeIniBucleGrabarReporte(BaseReporteForm form) {
		
	}
	
	/**
	 * Metodo que se ejecuta despues de la ejecucion Principal de GrabarReporte. 
	 * Dicho metodo puede ser sobreescrito 
	 */
	public void afterGrabarReporte(BaseReporteForm form) throws Exception {
		
	}
	
	
	/**
	 * Hook method para la ejecucion del Reporte. Esta implementacion devuelve
	 * siempre true y siempre se ejecuta el Reporte. En caso que el Reporte no
	 * se deba ejecutar debido a algun valor en los parametros se puede
	 * sobrescribir este metodo.
	 * 
	 * @param params
	 *            parametros del Reporte
	 * @return true si se va a ejecutar el Reporte, false en caso de que no se
	 *         ejecute
	 */
	public boolean continueExecuteReporte(BaseReporteForm form) {
		return true;
	}
	
	
	
	/**
	 * Realiza Logica para generar el archivo en el Oracle (Reportes NO JASPER)
	 * @param parameterMap
	 */
	public Map<String, Object> generarReporteOracle (Map<String, Object> parameterMap) {
		return parameterMap;
	}
	
	/**
	 * Devuelve en caso exista el Titulo para el Reporte en Oracle
	 * @param request
	 * @param parameterMap
	 * @return
	 */
	public final String obtieneTituloReporteOracle(Map<String, Object> parameterMap) {
		String titulo = "";
		String key = this.obtieneKeyTituloReporteOracle(parameterMap);
		if (StringUtils.isNotBlank(key))
			titulo = this.getReportResourceMessage(key);
		if (StringUtils.isBlank(titulo)) {
			titulo = this.getKeyMessage(key);
		}
		return titulo;
	}
	
    public String obtieneKeyTituloReporteOracle(Map<String, Object> parameterMap) {
		return null;
	}
    
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface#generarNombreArchivoReporteOracle(java.util.Map)
     */
    public String generarNombreArchivoReporteOracle(Map<String, Object> parameterMap) {
		return null;
	}
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface#obtieneTipoDatosReporteOracle(java.util.Map)
     */
    public String obtieneTipoDatosReporteOracle(Map<String, Object> parameterMap) {
		return null;
	}

    
    /* GET - SET */
    
	/**
	 * @return the ejecutarCommitBeforeExecuteReporte
	 */
	public boolean isEjecutarCommitBeforeExecuteReporte() {
		return ejecutarCommitBeforeExecuteReporte;
	}


	/* (non-Javadoc)
     * @see biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface#csvToxlsx(java.lang.String, java.lang.String)
     */
    public void convertircsvToxlsx(String csvFile, String xlsxFile) throws Exception{
    	ExcelXLSXUtil.convertircsvToxlsx(csvFile, xlsxFile);
    }
    
    /* (non-Javadoc)
     * @see biz.belcorp.ssicc.reportes.framework.service.BaseReporteInterface#obtieneCondicion(java.lang.String[], java.lang.String, java.lang.String)
     */
    public String obtieneCondicion(String[] lista, String parametro,	String comilla) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			for (int i = 0; i < lista.length; i++) {
				String dato = lista[i];
				if (StringUtils.isEmpty(dato) || StringUtils.equals(dato, "Todos"))
					return "";
				if (i == 0)
					resultado = resultado + "(" + comilla + dato + comilla;
				else
					resultado = resultado + "," + comilla + dato + comilla;
			}
			resultado = resultado + ")";
			resultado = " AND " + parametro + " IN " + resultado;
			return resultado;
		}
	}
}