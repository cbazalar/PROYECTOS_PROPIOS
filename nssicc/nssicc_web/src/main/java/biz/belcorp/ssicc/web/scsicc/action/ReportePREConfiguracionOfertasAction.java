package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePREConfiguracionOfertasForm;


/**
 * The Class ReportePREConfiguracionOfertasAction.
 *
 */
@ManagedBean
@SessionScoped
public class ReportePREConfiguracionOfertasAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = -4976076480723396167L;
	private static final String TIPO_REPORTE_CONCURSO = "C";
	private String tipoReporte;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePREConfiguracionOfertasForm form = new ReportePREConfiguracionOfertasForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePREConfiguracionOfertasForm f = (ReportePREConfiguracionOfertasForm)this.formReporte;
		log.debug(f.getFormatoExportacion());
		return "reportePREConfiguracionOfertas" +  this.tipoReporte + "XLS";
		
		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";			
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReportePREConfiguracionOfertasForm reporteSICForm = (ReportePREConfiguracionOfertasForm)this.formReporte;
		params.put("codigoPeriodo",reporteSICForm.getCodigoPeriodo());
		this.tipoReporte = reporteSICForm.getTipoReporte();
		return params;
	}
	
	
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParamsBeforeExecute(java.util.Map, biz.belcorp.ssicc.service.sisicc.framework.form.BaseForm)
	 */
	@Override
	protected Map<String, Object> prepareParamsBeforeExecute(
			Map<String, Object> params, BaseForm form) throws Exception {
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		if (this.tipoReporte.equals(TIPO_REPORTE_CONCURSO)) 
			reporteService.executeReporteConfiguracionOfertasConcurso(params);
		else
			reporteService.executeReporteConfiguracionOfertasN(params);
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
		this.mostrarReportePDF = false;
		this.ignorarCeldaBorder = false;
		
		// Carga Fecha y Periodo
		ReportePREConfiguracionOfertasForm f = (ReportePREConfiguracionOfertasForm)this.formReporte;
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
	}
	
}
