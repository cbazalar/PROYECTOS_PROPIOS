/**
 * 
 */
package biz.belcorp.ssicc.web._ejemplos;

import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteEVIMicaRecepcionPedidosZonaForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * @author Danny Amaro
 *
 */
@ManagedBean
@SessionScoped
public class EjemploReporteAction2 extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -3663077356420883237L;
	
	/* Seteo del form del reporte.
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		ReporteEVIMicaRecepcionPedidosZonaForm reporteForm = new ReporteEVIMicaRecepcionPedidosZonaForm();
		return reporteForm;
	}
	
	/* Seteo del reporte base.
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {		
		return "reporteMaestroHorizontal";
	}
	
	/* Seteo del subreporte
	 * (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseSubReporteAbstractAction#devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {		
		return "reporteEVIMicaRecepcionPedidosZonaPDF";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'EjemploReporteAction2.setViewAtributes' method");            
        }       
		//Seteo de valores por default de nuevos registros
		ReporteEVIMicaRecepcionPedidosZonaForm reporteForm = (ReporteEVIMicaRecepcionPedidosZonaForm)this.formReporte;
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
		
		ReporteEVIMicaRecepcionPedidosZonaForm reporteForm = (ReporteEVIMicaRecepcionPedidosZonaForm)this.formReporte;
				
		reporteForm.setCodigoCanal("VD");
		reporteForm.setCodigoMarca("EB");
		reporteForm.setCodigoPais("PE");
		reporteForm.setCodigoPeriodo("201111");
		//reporteForm.setCodigoRegion("10");
				
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'prepareParameterMap' method");
		}
		
		reporteForm.setTitulo(this.getReportResourceMessage("reporteEVIMicaRecepcionPedidosZonaForm.titulo")				
				+ " "
				+ DateUtil.getDate(new Date())
				+ "\n"
				+ this.getReportResourceMessage("reporteEVIMicaRecepcionPedidosZonaForm.campana") 
				+ " " + reporteForm.getCodigoPeriodo());
		
		reporteForm.setNroReporte("");
				
		return params;
	}
	
}
