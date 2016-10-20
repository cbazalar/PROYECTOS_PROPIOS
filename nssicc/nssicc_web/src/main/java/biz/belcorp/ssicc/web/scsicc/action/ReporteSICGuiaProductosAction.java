package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSICGuiaProductosForm;


/**
 * The Class ReporteSICFacturacionMatrizAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 16/07/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSICGuiaProductosAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSICGuiaProductosForm form = new ReporteSICGuiaProductosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSICGuiaProductosForm f = (ReporteSICGuiaProductosForm)this.formReporte;
		log.debug(f.getFormatoExportacion());
		if ("XLS".equals(f.getFormatoExportacion())) {
			return "reporteSICGuiaProductos" +  "XLS";
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
		ReporteSICGuiaProductosForm reporteSICForm = (ReporteSICGuiaProductosForm)this.formReporte;
		log.debug(reporteSICForm.getFormatoExportacion());
		
		params.put("codigoPeriodo",reporteSICForm.getCodigoPeriodo());
		
		params.put("titulo", this.getResourceMessage("reporteSICGuiaProductosForm.title")
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
		// Carga de los Periodos
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		// Carga Fecha y Periodo
		ReporteSICGuiaProductosForm f = (ReporteSICGuiaProductosForm)this.formReporte;
        InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
        f.setCodigoPeriodo(service.getPeriodoDefaultByPaisCanal(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(),Constants.CODIGO_CANAL_DEFAULT));
	}
	
}
