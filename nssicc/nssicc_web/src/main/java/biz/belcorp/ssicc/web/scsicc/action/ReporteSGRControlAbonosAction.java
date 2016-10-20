package biz.belcorp.ssicc.web.scsicc.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSGRControlAbonosForm;


/**
 * The Class ReporteSGRControlAbonosAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 11/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteSGRControlAbonosAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = 6815680992363510261L;
	private String formatoReporte;

	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSGRControlAbonosForm form = new ReporteSGRControlAbonosForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteCCCBoletaDepositoAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;	
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception { 
		this.formatoReporte = ((ReporteSGRControlAbonosForm)this.formReporte).getFormatoExportacion(); 
		if ("XLS".equals(formatoReporte)) {
			return "reporteSGRControlAbonosXLS";
		} else {
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
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteSGRControlAbonosForm form = (ReporteSGRControlAbonosForm) this.formReporte;
		Date fecha1D = form.getFechaInicioD();
		Date fecha2D = form.getFechaFinalD();
		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this.getResourceMessage("reporteSGRControlAbonosForm.errors.compare.fecha");
			return mensaje;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSGRControlAbonosService";
	}	

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSGRControlAbonosForm reporteCOBForm = (ReporteSGRControlAbonosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		formatoReporte = reporteCOBForm.getFormatoExportacion();

		Map criteria = params;
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		String codigoPais = pais.getCodigo();
		criteria.put("codigoPais", codigoPais);
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais", criteria);
		
		String fecha1 = DateUtil.getDate(reporteCOBForm.getFechaInicioD());
 		String fecha2 = DateUtil.getDate(reporteCOBForm.getFechaFinalD());
 		reporteCOBForm.setFechaInicio(fecha1);
 		reporteCOBForm.setFechaFinal(fecha2);	
 		
		String fechaInicio = reporteCOBForm.getFechaInicio();
		String fechaFin = reporteCOBForm.getFechaFinal();

		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
		params.put("oidPais", oidPais);
		params.put("codigoPais", codigoPais);
		params.put("fechaInicio", fechaInicio);
		params.put("fechaFin", fechaFin);
		params.put("NroReporte", "");
		params.put("titulo", getResourceMessage("reporteSGRControlAbonosForm.title"));

		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}		

}