package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICBoletaConsultoraForm;


/**
 * The Class ReporteSICFacturacionMatrizAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 16/07/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICBoletaConsultoraAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICBoletaConsultoraForm form = new ReporteSICBoletaConsultoraForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICBoletaConsultoraForm f = (ReporteSICBoletaConsultoraForm)this.formReporte;
		log.debug(f.getFormatoExportacion());
		if ("XLS".equals(f.getFormatoExportacion())) {
			return "reporteSICBoletaConsultora" +  "XLS";
		}else{
			return "reporteMaestroHorizontal";
		}
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
		ReporteSICBoletaConsultoraForm reporteSICForm = (ReporteSICBoletaConsultoraForm)this.formReporte;
		log.debug(reporteSICForm.getFormatoExportacion());
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Usuario usuario = this.getmPantallaPrincipalBean().getCurrentUser(); 
		
		Map criteria = params;
		criteria.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		criteria.put("codigoIso", usuario.getIdioma().getCodigoISO());	
		log.debug("usuario.getIdioma().getCodigoISO-------"+usuario.getIdioma().getCodigoISO());
		
		params.put("oidPeriodo",reporteService
				.getOidString("getDesPerioByOidPerio", criteria));
				
		params.put("oidIdioma",reporteService
				.getOidString("getOidIdiomaByCodIso", criteria));
		params.put("codigoCliente",reporteSICForm.getCodigoCliente());
				
		log.debug("getOidIdiomaByCodIso::"+reporteService
				.getOidString("getOidIdiomaByCodIso", criteria));
		params.put("titulo", this.getResourceMessage("reporteSICBoletaConsultoraForm.title")
				+ "\n" + "Campaña: "+reporteSICForm.getCodigoPeriodo()
				);
		
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
		ReporteSICBoletaConsultoraForm reporteSICForm = (ReporteSICBoletaConsultoraForm)this.formReporte;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;		
		// Carga Fecha y Periodo
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        reporteSICForm.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
        reporteSICForm.setCodigoCliente("");
	}
	
}
