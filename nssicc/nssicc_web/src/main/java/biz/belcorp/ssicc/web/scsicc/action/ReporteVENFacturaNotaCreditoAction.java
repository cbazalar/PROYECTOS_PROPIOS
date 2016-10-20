/**
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteVENFacturaNotaCreditoForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

/**
 * Clase que controla el Reporte de Reporte RUV Facturas y Notas de Credito	
 * 
 * @author <a href="cdiaz@csigcomt.com">Carlos Diaz Valverde</a>
 */
@ManagedBean
@SessionScoped
public class ReporteVENFacturaNotaCreditoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -7722593099176777931L;
	private String tipoReporte;

	protected BaseReporteForm devuelveFormReporte() throws Exception {		
		ReporteVENFacturaNotaCreditoForm form = new ReporteVENFacturaNotaCreditoForm();
		return form;
	}


	protected String devuelveNombreReporte() throws Exception {	
		
		if(formatoExportacion.equals("XLS") || formatoExportacion.equals("CSV") || formatoExportacion.equals("XLSX")){
			if(tipoReporte.equals("1"))
				return "reporteVENFacturaXLS";
			else if(tipoReporte.equals("2"))
				return "reporteVENNotaCreditoXLS";
			else 
				return "";	
			
		}		
		return "reporteMaestroHorizontal";
		
	}
	

	protected String devuelveNombreSubReporte() throws Exception {		
		if(formatoExportacion.equals("XLS") || formatoExportacion.equals("CSV") || formatoExportacion.equals("XLSX")){
			return "";
		}else{
			if(tipoReporte.equals("1"))
				return "reporteVENFacturaPDF";
			else if(tipoReporte.equals("2"))
				return "reporteVENNotaCreditoPDF";
			else 
				return "";
		}
	}
	

	@Override
	protected void setViewAtributes() throws Exception {		
		if (this.log.isDebugEnabled()) {
            this.log.debug("Entering 'ReporteVENFacturaNotaCreditoAction.setViewAtributes' method");            
        }
		this.mostrarReportePDF=true;
		this.mostrarReporteXLS = true;		
		this.mostrarReporteXLSX = false;
	}	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception{		
		if(log.isDebugEnabled()){
			log.debug("ReporteVENFacturaNotaCreditoAction - prepareParameterMap...");
		}
	
			ReporteVENFacturaNotaCreditoForm form = (ReporteVENFacturaNotaCreditoForm) this.formReporte;;
			ReporteService reporteService = (ReporteService)this.getBeanService("scsicc.reporteService"); 
	
			form.setFormatoExportacion(this.formatoExportacion);
			tipoReporte = form.getTipoReporte();
			
			Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
			String fecha1,fecha2;
			fecha1 = DateUtil.getDate(form.getCalendarInicio());
			fecha2=DateUtil.getDate(form.getCalendarFin());
			form.setFechaInicio(fecha1);
			form.setFechaFin(fecha2);
			Map criteria = params;
			
			criteria.put("codigoPais", pais.getCodigo());		
			form.setOidPais(reporteService.getOidString("getOidPaisByCodigoPais", criteria));
			params.put("oidPais", form.getOidPais());

			params.put("fechaInicio", form.getFechaInicio());
			params.put("fechaFin", form.getFechaFin());

			String titulo = "1".equals(form.getTipoReporte())?"reporteVENFacturaNotaCreditoForm.title.factura":"reporteVENFacturaNotaCreditoForm.title.notaCredito";
			form.setTitulo(this.getResourceMessage(titulo));
			params.put("titulo", form.getTitulo());
			
	
		return params;
	}	

	public String getTipoReporte() {
		return tipoReporte;
	}


	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}
	
	
}
