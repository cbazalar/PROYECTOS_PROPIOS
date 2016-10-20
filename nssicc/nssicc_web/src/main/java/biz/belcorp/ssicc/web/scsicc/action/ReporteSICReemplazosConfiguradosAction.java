package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICReemplazosConfiguradosForm;


/**
 * The Class ReporteSICFacturacionMatrizAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 16/07/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICReemplazosConfiguradosAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICReemplazosConfiguradosForm form = new ReporteSICReemplazosConfiguradosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICReemplazosConfiguradosForm f = (ReporteSICReemplazosConfiguradosForm)this.formReporte;
		log.debug(f.getFormatoExportacion());
		if ("XLS".equals(f.getFormatoExportacion())) {
			return "reporteSICReemplazosConfiguradosXLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSICReemplazosConfiguradosPDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICReemplazosConfiguradosForm reporteSICForm = (ReporteSICReemplazosConfiguradosForm)this.formReporte;
		log.debug(reporteSICForm.getFormatoExportacion());
		
		params.put("codigoPeriodo", reporteSICForm.getCodigoPeriodo());
		params.put("NroReporte", "");		
		params.put("titulo", this.getResourceMessage("reporteSICReemplazosConfiguradosForm.title"));		
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
		
		this.mostrarReporteXLS = true;		
		// Carga Fecha y Periodo
		ReporteSICReemplazosConfiguradosForm f = (ReporteSICReemplazosConfiguradosForm)this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
	}
	
}
