package biz.belcorp.ssicc.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;


/**
 * 
 * @author <a href="mailto:cbazalar@belcorp.biz">Carlos Bazalar La Rosa</a>
 *
 */

@ManagedBean
@SessionScoped
public class CopyOfReporteINCConsultorasPuntajeUbicacionAction extends BaseReporteAbstractAction{

	private static final long serialVersionUID = -347731473531399862L;
	
	private List incConcursosList;
	private String formatoReporte;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCForm reporteForm = new ReporteINCForm();
		return reporteForm;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)){
			return "reporteINCConsultorasPuntajeUbicacionXLS";
		}
		if ( ("PDF".equals(formatoReporte)) || ("VPDF".equals(formatoReporte)) ){ 
			return "reporteINCConsultorasPuntajeUbicacionPDF";
		}
		return "reporteMaestroHorizontal";
	}
	
	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return "";
	}
	
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCConsultorasPuntajeUbicacionAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		
		ReporteService reporteService = (ReporteService)getBean("scsicc.reporteService");
		setIncConcursosList(reporteService.getListaGenerico("getOidConcursosList", ((Map)new HashMap())));
	}
	
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCConsultorasPuntajeUbicacionAction.prepareParameterMap' method");
		}
		ReporteINCForm f = (ReporteINCForm)this.formReporte;
		
		this.formatoReporte = f.getFormatoExportacion();
		
		f.setTitulo(this.getResourceMessage("reporteINCConsultorasPuntajeUbicacionForm.title"));
		f.setBeforeExecuteReporte(true);
		params.put("titulo", f.getTitulo());
		
		params.put("oidConcurso", Integer.valueOf(f.getCodigoConcurso()));
		params.put("titulo", f.getTitulo());
		
		return params;
	}

	public List getIncConcursosList() {
		return incConcursosList;
	}

	public void setIncConcursosList(List incConcursosList) {
		this.incConcursosList = incConcursosList;
	}	
}