package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICPrecioCeroForm;


/**
 * The Class ReporteSICFacturacionMatrizAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 16/07/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICPrecioCeroAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICPrecioCeroForm form = new ReporteSICPrecioCeroForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICPrecioCeroForm f = (ReporteSICPrecioCeroForm)this.formReporte;
		log.debug(f.getFormatoExportacion());
		if ("XLS".equals(f.getFormatoExportacion())) {
			return "reporteSICPrecioCero" +  "XLS";
		}else{
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSICPrecioCero"+"PDF";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if(log.isDebugEnabled()){
			log.debug("prepareParameterMap");
		}
		ReporteSICPrecioCeroForm reporteSICForm = (ReporteSICPrecioCeroForm)this.formReporte;
		log.debug(reporteSICForm.getFormatoExportacion());
		
		params.put("codigoPeriodo",reporteSICForm.getCodigoPeriodo());
		
		params.put("titulo", this.getResourceMessage("reporteSICPrecioCeroForm.title")
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
		
		this.mostrarReporteXLS = true;		
		// Carga Fecha y Periodo
		ReporteSICPrecioCeroForm f = (ReporteSICPrecioCeroForm)this.formReporte;
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
	}
	
}
