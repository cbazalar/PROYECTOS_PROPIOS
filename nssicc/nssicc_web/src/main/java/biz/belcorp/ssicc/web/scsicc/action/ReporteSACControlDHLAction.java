package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSACControlDHLForm;


/**
 * The Class ConsultaOCRPedidosDuplicadosAction.
 *
 * @autor: Belcorp
 * @version: 1.0
 * 03/09/2014
 */
@ManagedBean
@SessionScoped
public class ReporteSACControlDHLAction extends BaseReporteAbstractAction {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSACControlDHLForm form = new ReporteSACControlDHLForm();
		return form;
	}
	
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSACControlDHLForm form = (ReporteSACControlDHLForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		String valor = "";
		
		if ("XLS".equals(form.getFormatoExportacion())) {			
			valor =  "reporteSACControlDHLXLS";
		}else{
			valor = "reporteMaestroHorizontal";
		}
		return valor;
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteSACControlDHLForm form = (ReporteSACControlDHLForm)this.formReporte;
		log.debug(form.getFormatoExportacion());
		
		String valor = "";
		
		if ("PDF".equals(form.getFormatoExportacion())) {			
			valor =  "reporteSACControlDHLPDF";
		}else{
			valor = "";
		}
		return valor;
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
		
        ReporteSACControlDHLForm form = (ReporteSACControlDHLForm)this.formReporte;
		form.setCodigoPais(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());		
	}


	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSACControlDHLForm form = (ReporteSACControlDHLForm)this.formReporte;
		
		Map criteria = params;
		criteria.put("codigoPais", form.getCodigoPais());
		criteria.put("periodo", form.getCodigoPeriodo());
		
		if(form.getFechaFacturacionDate()!=null){
			form.setFechaFacturacion(DateUtil.convertDateToString(Constants.DEFAULT_DATE_FORMAT, form.getFechaFacturacionDate()));
			criteria.put("fechaFacturacion", form.getFechaFacturacion());
		}		
				
		criteria.put("titulo", this.getResourceMessage("reporteSACControlDHLForm.titulo"));
		return criteria;
	}
	
}
