 package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENFacturaAuditoriaForm;


/**
 * The Class ReporteVENFacturaAuditoriaAction
 *
 * @autor: Carlos Bazalar
 * @version: 1.0
 */
@ManagedBean
@SessionScoped
public class ReporteVENFacturaAuditoriaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 7680408649987038383L;
	private static final String REPORTE_CONSOLIDADO = "C";
	private String tipoReporte;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENFacturaAuditoriaForm form = new ReporteVENFacturaAuditoriaForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteVENFacturaAuditoriaAction.setViewAtributes' method");
		}		
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		
		ReporteVENFacturaAuditoriaForm f = (ReporteVENFacturaAuditoriaForm) this.formReporte;		
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaDesde(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaDesdeD(new Date(System.currentTimeMillis()));

		f.setFechaHasta(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaHastaD(new Date(System.currentTimeMillis()));
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if (StringUtils.equals(this.tipoReporte, REPORTE_CONSOLIDADO))
			return "reporteVENFacturaAuditoriaConso";
		else
			return "reporteVENFacturaAuditoriaDetal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteVENFacturaAuditoriaForm form = (ReporteVENFacturaAuditoriaForm) this.formReporte;
		Date fecha1D = form.getFechaDesdeD();
		Date fecha2D = form.getFechaHastaD();
		String fecha1 = DateUtil.getDate(form.getFechaDesdeD());
 		String fecha2 = DateUtil.getDate(form.getFechaHastaD());
		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this.getResourceMessage("reporteVENFacturaAuditoriaForm.errors.compare.fecha");
			return mensaje;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReporteVENFacturaAuditoriaForm reporteVENForm = (ReporteVENFacturaAuditoriaForm) this.formReporte;
		this.tipoReporte = reporteVENForm.getTipoReporte();
	   
 		String fecha1 = DateUtil.getDate(reporteVENForm.getFechaDesdeD());
 		String fecha2 = DateUtil.getDate(reporteVENForm.getFechaHastaD());
		reporteVENForm.setFechaDesde(fecha1);
		reporteVENForm.setFechaHasta(fecha2);		
 		
		params.put("fechaDesde", reporteVENForm.getFechaDesde());
		params.put("fechaHasta", reporteVENForm.getFechaHasta());
		
		String titulo = "" ;
		if (StringUtils.equals(this.tipoReporte, REPORTE_CONSOLIDADO))
			titulo = this.getResourceMessage("reporteVENFacturaAuditoriaConso.titulo", new Object[] {fecha1, fecha2});
		else 
			titulo = this.getResourceMessage("reporteVENFacturaAuditoriaDetal.titulo", new Object[] {fecha1, fecha2});
		params.put("titulo", titulo);
	
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		if (StringUtils.equals(this.tipoReporte, REPORTE_CONSOLIDADO))
			reporteService.executeReporteVENFacturaAuditoriaConso(params);
		else
			reporteService.executeReporteVENFacturaAuditoriaDetal(params);
		return params;
	}


	
	
	/* GET - SET */
	
	
	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	
	
}
