package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteCCCRegistroAbonosBoliviaForm;

/**
 * @author Aurelio Oviedo
 *
 */

@ManagedBean
@SessionScoped
public class ReporteCCCRegistroAbonosBoliviaAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteCCCRegistroAbonosBoliviaForm f = new ReporteCCCRegistroAbonosBoliviaForm();
		
		return f;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteCCCRegistroAbonosBoliviaForm f = (ReporteCCCRegistroAbonosBoliviaForm) formReporte;
		
		params.put("codigoPais", this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo());
		params.put("fechaInicio", DateUtil.convertDateToString(DateUtil.getDatePattern(), f.getFechaInicio()));
		params.put("fechaFin", DateUtil.convertDateToString(DateUtil.getDatePattern(), f.getFechaFin()));
		log.debug("Los parametros del Generar en el executeProcess son: " + params.toString());
		
		return params;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteOCSV = true;
		this.mostrarReportePDF = false;
		
		ReporteCCCRegistroAbonosBoliviaForm f = (ReporteCCCRegistroAbonosBoliviaForm) formReporte;
		
		f.setFechaInicio(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
		f.setFechaFin(DateUtil.convertStringToDate(DateUtil.getDatePattern(), DateUtil.convertDateToString(new Date())));
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteCCCRegistroAbonosBoliviaService";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		ReporteCCCRegistroAbonosBoliviaForm f = (ReporteCCCRegistroAbonosBoliviaForm) formReporte;
		
		Date fechaInicio = f.getFechaInicio();
		Date fechaFin = f.getFechaFin();
		String mensaje = "";
		
		try {
			Date fechaEvaluar = DateUtil.addToDate(fechaInicio, Calendar.DATE, 31);
			int indFecha = fechaFin.compareTo(fechaEvaluar);
			
			if(indFecha == 1){
				return mensaje = "La diferencia entre ambas fechas es mayor a 31 días";
			}
			
			if(fechaInicio != null && fechaFin != null){
				if(fechaInicio.compareTo(fechaFin) > 0){
					return getResourceMessage("errors.compare.dates");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
