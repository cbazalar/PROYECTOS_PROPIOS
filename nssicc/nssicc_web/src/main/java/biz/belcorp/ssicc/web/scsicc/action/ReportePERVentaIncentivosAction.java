package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePERVentaIncentivosForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 *
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReportePERVentaIncentivosAction extends BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -2974782389751405523L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePERVentaIncentivosForm form = new ReportePERVentaIncentivosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReportePERVentaIncentivosForm f = (ReportePERVentaIncentivosForm) this.formReporte;
		
		if ("XLS".equals(f.getFormatoExportacion()))
			return "reportePERVentaIncentivosXLS";
		else
			return " ";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if(log.isDebugEnabled()){
			log.debug("setViewAtributes...");
		}
		
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF= false;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReportePERVentaIncentivosForm f = (ReportePERVentaIncentivosForm) this.formReporte;
		
		String fecha1,fecha2;
		fecha1 = DateUtil.getDate(f.getFechaDesdeD());
		fecha2 = DateUtil.getDate(f.getFechaHastaD());
		
		f.setFechaDesde(fecha1);
		f.setFechaHasta(fecha2);

		// params.put("codigoPeriodo",f.getCodigoPeriodo());
		params.put("fechaDesde", f.getFechaDesde());
		params.put("fechaHasta", f.getFechaHasta());
		params.put("titulo", getReportResourceMessage("reportePERVentaIncentivosForm.title"));
		return params;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte...");
		}
		
		ReportePERVentaIncentivosForm form = (ReportePERVentaIncentivosForm)this.formReporte;
		
		Date fecha1D,fecha2D;
		fecha1D = form.getFechaDesdeD();
		fecha2D = form.getFechaHastaD();
		
	    if (fecha2D.compareTo(fecha1D) < 0){
	    	String mensaje =  this.getResourceMessage("reportePERVentaIncentivosForm.error.fecha.Desde.Mayor");
			return mensaje;
	    }
	    
	    return null;
	}
}