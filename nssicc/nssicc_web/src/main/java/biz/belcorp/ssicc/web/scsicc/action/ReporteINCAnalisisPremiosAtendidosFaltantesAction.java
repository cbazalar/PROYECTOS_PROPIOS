/*
 * 
 */
package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteINCAnalisisPremiosAtendidosFaltantesForm;

// TODO: Auto-generated Javadoc
/**
 * The Class ReporteINCAnalisisPremiosAtendidosFaltantesAction.
 *
 * @author Belcorp
 * @version 1.0
 * 11/11/2014
 */
@ManagedBean
@SessionScoped
public class ReporteINCAnalisisPremiosAtendidosFaltantesAction extends BaseReporteAbstractAction { 

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCAnalisisPremiosAtendidosFaltantesForm reporteForm = new ReporteINCAnalisisPremiosAtendidosFaltantesForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formReporte.getFormatoExportacion())) {
			return "reporteINCAnalisisPremioXLS";
		} 
		else {
			return "reporteMaestroHorizontal";
		} 
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {	
		if (("PDF".equals(formReporte.getFormatoExportacion()))||("VPDF".equals(formReporte.getFormatoExportacion())))
			return "reporteINCAnalisisPremioPDF";
		return "";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCAnalisisPremiosAtendidosFaltantesAction.setViewAtributes' method");
		}
		
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		ReporteINCAnalisisPremiosAtendidosFaltantesForm f = (ReporteINCAnalisisPremiosAtendidosFaltantesForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		f.setPeriodoInicio(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		f.setPeriodoFin(service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT));
		
		List listRango = reporteService.getRangoFechaPeriodo(f.getPeriodoInicio());
		if(listRango.size()>0){
			Map m = (Map)listRango.get(0);
			f.setFechaInicio((String)m.get("fechaInicial"));
			f.setFechaDateInicio(DateUtil.convertStringToDate(f.getFechaInicio()));
			f.setFechaFin((String)m.get("fechaFinal"));
			f.setFechaDateFin(DateUtil.convertStringToDate(f.getFechaFin()));
		}
		
		log.debug("Todo Ok: Redireccionando");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCAnalisisPremiosAtendidosFaltantesAction.prepareParameterMap' method");
		}
		ReporteINCAnalisisPremiosAtendidosFaltantesForm reporteForm = (ReporteINCAnalisisPremiosAtendidosFaltantesForm) this.formReporte;
		
		reporteForm.setTitulo(this.getReportResourceMessage("reporteINCAnalisisPremiosAtendidosFaltantesForm.titulo"));
		reporteForm.setBeforeExecuteReporte(true);
		params.put("NroReporte", " ");
		params.put("titulo", reporteForm.getTitulo());
		
		params.put("fechaInicio", DateUtil.convertDateToString(DateUtil.getDatePattern(), reporteForm.getFechaDateInicio()));
		params.put("fechaFin", DateUtil.convertDateToString(DateUtil.getDatePattern(), reporteForm.getFechaDateFin()));
		
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte");
		}
		ReporteINCAnalisisPremiosAtendidosFaltantesForm form = (ReporteINCAnalisisPremiosAtendidosFaltantesForm) this.formReporte;
		if(form.getFechaDateInicio()!=null && form.getFechaDateFin()!=null){
			if(form.getFechaDateInicio().compareTo(form.getFechaDateFin())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		return null;
	}
}

