package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.inc.MantenimientoINCHabilitacionConcursoCargaPuntajeService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteINCProgramaReconocimientoForm;


@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteINCProgramaReconocimientoAction extends	BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;
	
	private List incentivosConcursosReconocidosList;
	
	/** The formato reporte. */
	private String formatoReporte;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCProgramaReconocimientoForm reporteForm = new ReporteINCProgramaReconocimientoForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteINCProgramaXLS";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {		
		return "";
	}

		
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCProgramaReconocimientoAction.setViewAtributes' method");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		
		ReporteINCProgramaReconocimientoForm f = (ReporteINCProgramaReconocimientoForm) this.formReporte;
		MantenimientoINCHabilitacionConcursoCargaPuntajeService service = (MantenimientoINCHabilitacionConcursoCargaPuntajeService) getBean("spusicc.mantenimientoINCHabilitacionConcursoCargaPuntajeService");
		//cargando en session la lista de programas de reconocimiento
		setIncentivosConcursosReconocidosList(service.getListConcursoReconocido());
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		 
		f.setCodigoPeriodo(periodo);
		
		//-- Valores x defecto Puntaje minimo y maximo
		f.setPuntajeMinimo("0");
		f.setPuntajeMaximo("9999999");
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCProgramaReconocimientoAction.prepareParameterMap' method");
		}
		
		ReporteService reporteService = (ReporteService) this.getBean("scsicc.reporteService");
		ReporteINCProgramaReconocimientoForm reporteForm = (ReporteINCProgramaReconocimientoForm) this.formReporte;
		
		this.formatoReporte = this.formatoExportacion;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		String codigoPais = pais.getCodigo();
		String oidPeriodo = reporteService.getOidString("getOidPeriodoByCodigoPeriodo", params);;
		int puntajeMinimo = 0;
		int puntajeMaximo = 9999999;
		
		
		
		//-- Validar valores de Puntaje
		if(reporteForm.getPuntajeMinimo().trim().length()>0)
			puntajeMinimo = Integer.valueOf(reporteForm.getPuntajeMinimo()).intValue();
		if(reporteForm.getPuntajeMaximo().trim().length()>0)
			puntajeMaximo = Integer.valueOf(reporteForm.getPuntajeMaximo()).intValue();
		log.info("Envio de Puntajes: ".concat("Puntaje Minimo="+puntajeMinimo).concat(" - Puntaje Maximo="+puntajeMaximo));
		
		//-- Activar el generador de tabs XLS
		this.setGenerateTabsXLS(true);
		
		params.put("codigoPais", codigoPais);
		params.put("numeroConcurso", reporteForm.getNumConcurso());
		params.put("codigoPeriodo", reporteForm.getCodigoPeriodo());
		params.put("oidPeriodo",oidPeriodo);
		params.put("puntajeMinimo",puntajeMinimo);
		params.put("puntajeMaximo",puntajeMaximo);
		params.put("formatoExportacion", this.formatoExportacion);
		
		return params;
	}

	/**
	 * @return
	 */
	public List getIncentivosConcursosReconocidosList() {
		return incentivosConcursosReconocidosList;
	}

	/**
	 * @param incentivosConcursosReconocidosList
	 */
	public void setIncentivosConcursosReconocidosList(
			List incentivosConcursosReconocidosList) {
		this.incentivosConcursosReconocidosList = incentivosConcursosReconocidosList;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCProgramaReconocimientoService";
	}
}