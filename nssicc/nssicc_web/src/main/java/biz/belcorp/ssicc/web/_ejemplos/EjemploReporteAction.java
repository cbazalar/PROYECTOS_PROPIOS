/**
 * 
 */
package biz.belcorp.ssicc.web._ejemplos;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteGEOClientesZonasTerritoriosPendientesForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author Danny Amaro
 *
 */


@ManagedBean
@SessionScoped
public class EjemploReporteAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = -9174060614247975931L;


	/* Seteo del form del reporte.
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = new ReporteGEOClientesZonasTerritoriosPendientesForm();
		return reporteForm;
	}
	
	/* Seteo del reporte base.
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {	
				
		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = (ReporteGEOClientesZonasTerritoriosPendientesForm)this.formReporte;
		String formatoReporte = reporteForm.getFormatoExportacion();
		
		if(formatoReporte.equals("XLS") || formatoReporte.equals("CSV")){
			return "reporteGEOClientesZonasTerritoriosPendientesXLS";
		}
		
		return "reporteMaestroHorizontal";
		
	}
	
	/* Seteo del subreporte
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseSubReporteAbstractAction#devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {		
		
		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = (ReporteGEOClientesZonasTerritoriosPendientesForm)this.formReporte;
		String formatoReporte = reporteForm.getFormatoExportacion();
		
		if(formatoReporte.equals("XLS") || formatoReporte.equals("CSV")){
			return "";
		}
				
		return "reporteGEOClientesZonasTerritoriosPendientesPDF";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'EjemploReporteAction.setViewAtributes' method");            
        }       
		//Seteo de valores por default de nuevos registros
		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = (ReporteGEOClientesZonasTerritoriosPendientesForm)this.formReporte;
		
		/** TODO **/
		/**
		 * Logica de seteo de valores iniciales del Form
		 * 
		 * Aqui se debe setear los valores del form de filtro que generan el report
		 * p.e selects(listas de objetos), valores por default en los campos etc.
		 */
		
        this.formReporte = reporteForm;
	}	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception{		
		if (log.isDebugEnabled()) {
			log.debug("Entering 'EjemploReporteAction2.prepareParameterMap' method");
		}
		
		/** TODO **/		
		ReporteGEOClientesZonasTerritoriosPendientesForm reporteForm = (ReporteGEOClientesZonasTerritoriosPendientesForm)this.formReporte;
					
		reporteForm.setCodigoPais("PE");
		reporteForm.setFechaDesde("01/01/2010");
		reporteForm.setFechaHasta("06/06/2012");
		reporteForm.setTitulo(this.getReportResourceMessage("reporteGEOClientesZonasTerritoriosPendientesForm.titulo"));				
		reporteForm.setNroReporte(this.getReportResourceMessage("reporteGEOClientesZonasTerritoriosPendientesForm.numero.reporte"));
				
		return params;
	}

}
