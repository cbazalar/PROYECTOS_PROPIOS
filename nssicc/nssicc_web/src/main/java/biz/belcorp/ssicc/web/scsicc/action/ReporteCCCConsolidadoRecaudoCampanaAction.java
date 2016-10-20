package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCConsolidadoRecaudoCampanaForm;

/**
 * @author <a href="mailto:aoviedo@sigcomt.com">Aurelio Oviedo</a>
 */

@ManagedBean
@SessionScoped
public class ReporteCCCConsolidadoRecaudoCampanaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCConsolidadoRecaudoCampanaForm f = new ReporteCCCConsolidadoRecaudoCampanaForm();
		
		return f;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCConsolidadoRecaudoCampanaService";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteCCCConsolidadoRecaudoCampanaForm f = (ReporteCCCConsolidadoRecaudoCampanaForm) formReporte;
		
		if("XLS".equals(f.getFormatoExportacion())){
			return "reporteCCC" + f.getTipoReporte() + "RecaudoCampanaXLS";
		}else{
			return "";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCConsolidadoRecaudoCampanaForm f = (ReporteCCCConsolidadoRecaudoCampanaForm) this.formReporte;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String fechaDesde = sdf.format(f.getFechaDesde());
		String fechaHasta = sdf.format(f.getFechaHasta());
		params.put("tipoReporte", f.getTipoReporte());
		params.put("fechaDesde",fechaDesde);
		params.put("fechaHasta", fechaHasta);
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		
		ReporteCCCConsolidadoRecaudoCampanaForm f = (ReporteCCCConsolidadoRecaudoCampanaForm) this.formReporte;
		
		f.setFechaDesde(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
		f.setFechaHasta(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		ReporteCCCConsolidadoRecaudoCampanaForm f = (ReporteCCCConsolidadoRecaudoCampanaForm) formReporte;
		
		Date fechaDesde = f.getFechaDesde();
		Date fechaHasta = f.getFechaHasta();
		
		if(fechaDesde != null && fechaHasta != null){
			if(fechaDesde.compareTo(fechaHasta) > 0){
				return "'Fecha Desde' debe ser menor o igual a 'Fecha Hasta'";
			}
		}
		
		return null;
	}
}