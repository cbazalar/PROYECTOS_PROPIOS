package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteRECCuadreSAPForm;


@ManagedBean
@SessionScoped
public class ReporteRECCuadreSAPAction extends BaseReporteAbstractAction {

	@Override
	protected void setViewAtributes() throws Exception {
		
		this.mostrarReporteXLS = true;
		
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'setViewAtributes' method");
		}
		
	}	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECCuadreSAPForm reporteForm = new ReporteRECCuadreSAPForm();

		return reporteForm;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteRECCuadreSAPForm form = (ReporteRECCuadreSAPForm)this.formReporte;
		
		if ("XLS".equals(form.getFormatoExportacion())){
			return "reporteRECCuadreSAPXLS";
		}else{
			return "reporteMaestroVertical";
		}		
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		ReporteRECCuadreSAPForm form = (ReporteRECCuadreSAPForm)this.formReporte;
		
		if ("PDF".equals(form.getFormatoExportacion())){
			return "reporteRECCuadreSAPPDF";
		}else{
			return "";
		}		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		
		ReporteRECCuadreSAPForm form = (ReporteRECCuadreSAPForm)this.formReporte;
		
		params.put("titulo",getResourceMessage("reporteRECCuadreSAPForm.titulo"));
		
		form.setBeforeExecuteReporte(true);
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteRECCuadreSAPService";
	}
	
	
}
