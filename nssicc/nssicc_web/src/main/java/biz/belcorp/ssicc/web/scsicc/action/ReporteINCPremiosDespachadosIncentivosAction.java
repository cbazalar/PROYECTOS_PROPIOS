package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteINCPremiosDespachadosIncentivosForm;

/**
 * The Class ReporteINCPremiosDespachadosIncentivosAction.
 *
 * @author Belcorp
 * @version 1.0
 * 11/11/2014
 */
@ManagedBean
@SessionScoped
public class ReporteINCPremiosDespachadosIncentivosAction extends BaseReporteAbstractAction { 

	private static final long serialVersionUID = 1L;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCPremiosDespachadosIncentivosForm reporteForm = new ReporteINCPremiosDespachadosIncentivosForm();
		return reporteForm;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formReporte.getFormatoExportacion())) {
			return "reporteINCPremiosDespachadosIncentivosXLS";
		}else {
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
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCPremiosDespachadosIncentivosAction.setViewAtributes' method");
		}
		
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		
		ReporteINCPremiosDespachadosIncentivosForm f = (ReporteINCPremiosDespachadosIncentivosForm) this.formReporte;
		
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		
		f.setCodigoPais(pais.getCodigo());
				
		log.debug("Todo Ok: Redireccionando");
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception{
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCPremiosDespachadosIncentivosAction.prepareParameterMap' method");
		}
		
		ReporteINCPremiosDespachadosIncentivosForm reporteForm = (ReporteINCPremiosDespachadosIncentivosForm) this.formReporte;
		
		reporteForm.setBeforeExecuteReporte(true);
		
		params.put("NroReporte", " ");
		params.put("fechaInicio", DateUtil.convertDateToString(DateUtil.getDatePattern(), reporteForm.getFechaInicioD()));
		params.put("fechaFin", DateUtil.convertDateToString(DateUtil.getDatePattern(), reporteForm.getFechaFinD()));
		
		if(StringUtils.isNotBlank(reporteForm.getCodigoSAP())){
			params.put("codigoSAP", " and cod_sap = '" + reporteForm.getCodigoSAP() + "' ");
		}else{
			params.put("codigoSAP", "");
		}
		
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
		
		ReporteINCPremiosDespachadosIncentivosForm form = (ReporteINCPremiosDespachadosIncentivosForm) this.formReporte;
		
		if(form.getFechaInicioD()!=null && form.getFechaFinD()!=null){
			if(form.getFechaInicioD().compareTo(form.getFechaFinD())>0){
				this.setMensajeAlertaDefault(this.getResourceMessage("errors.compare.dates"));
				return this.getMensajeAlertaDefault();
			}
		}
		
		return null;
	}
}