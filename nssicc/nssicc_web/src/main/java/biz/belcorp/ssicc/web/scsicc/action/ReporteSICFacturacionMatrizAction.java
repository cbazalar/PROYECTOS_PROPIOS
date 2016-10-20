package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICFacturacionMatrizForm;


/**
 * The Class ReporteSICFacturacionMatrizAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 16/07/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICFacturacionMatrizAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICFacturacionMatrizForm form = new ReporteSICFacturacionMatrizForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICFacturacionMatrizForm f = (ReporteSICFacturacionMatrizForm)this.formReporte;
		log.debug(f.getFormatoExportacion());
		if ("XLS".equals(f.getFormatoExportacion())) {
			return "reporteSICFacturacionMatrizXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSICFacturacionMatrizForm f = (ReporteSICFacturacionMatrizForm)this.formReporte;
		log.debug(f.getFormatoExportacion());
		if ("PDF".equals(f.getFormatoExportacion()) || "VPDF".equals(f.getFormatoExportacion())){
			return "reporteSICFacturacionMatrizPDF";
		}else{
			return "";			
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICFacturacionMatrizForm reporteSICForm = (ReporteSICFacturacionMatrizForm)this.formReporte;
		log.debug(reporteSICForm.getFormatoExportacion());
		
		Map criteria = params;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",
				criteria);
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());

		String oidPeriodo = reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria);
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());

		params.put("NroReporte", "");
		params.put("oidPais", oidPais);
		params.put("oidPeriodo", oidPeriodo);
		params.put("oidPeriodoAnterior", "");
		params.put("desPeriodoAnterior", "");
		params.put("titulo", this.getResourceMessage("reporteSICFacturacionMatrizForm.titulo")
				+ "\n"
				+ this.getResourceMessage("reporte.generico.periodo")
				+ ": " + reporteSICForm.getCodigoPeriodo());
		return params;
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes");
		}
		
		// Carga de los Periodos
		this.mostrarReporteXLS = true;
		ReporteSICFacturacionMatrizForm reporteSICForm = (ReporteSICFacturacionMatrizForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);
		String periodoActual = reporteService.getStringGenerico("getPeriodoByFechaActual", criteriaOperacion);
		reporteSICForm.setCodigoPeriodo(periodoActual);
	}
	
}
