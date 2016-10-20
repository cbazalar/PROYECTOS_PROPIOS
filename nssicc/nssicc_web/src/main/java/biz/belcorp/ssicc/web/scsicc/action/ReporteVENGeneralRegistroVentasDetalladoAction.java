 package biz.belcorp.ssicc.web.scsicc.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENRegistroVentasDetalladoForm;


/**
 * The Class ReporteVENGeneralRegistroVentasDetalladoAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 11/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteVENGeneralRegistroVentasDetalladoAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 4514532487530942046L;
	private String formatoReporte;
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENRegistroVentasDetalladoForm form = new ReporteVENRegistroVentasDetalladoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteVENGeneralRegistroVentasDetalladoAction.setViewAtributes' method");
		}		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteVENRegistroVentasDetalladoForm f = (ReporteVENRegistroVentasDetalladoForm) this.formReporte;		
		f.setFlagExcel(false);
		f.setFlagPdf(false);
		
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		HashMap criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoParametro", Constants.PARAM_REPOR_BTN);
		
		String valParam = reporteService.getParamReporGener(criteria);
		log.debug("valParam"+valParam);
		
		if (valParam.equals(Constants.PARAM_REPOR_BTN_PDF))			
			f.setFlagPdf(true);
		if (valParam.equals(Constants.PARAM_REPOR_BTN_XLS))
			f.setFlagExcel(true);
		if (valParam.equals(Constants.PARAM_REPOR_BTN_AMB)){			
			f.setFlagExcel(true);
			f.setFlagPdf(true);
		}
		if (valParam.equals(Constants.PARAM_REPOR_BTN_NIN)){			
			f.setFlagExcel(false);
			f.setFlagPdf(false);
		}		
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		f.setFechaDesde(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaDesdeD(new Date(System.currentTimeMillis()));

		f.setFechaHasta(sdf.format(new Date(System.currentTimeMillis())));
		f.setFechaHastaD(new Date(System.currentTimeMillis()));
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte)) {
			return "reporteVENRegistroVentasDetalladoNotasCreditoXLS" ;
		} else if ("XLS".equals(formatoReporte)) {
			return "reporteVENRegistroVentasDetalladoVentasXLS";
		} else if ("XLS2".equals(formatoReporte)) {
			return "reporteVENRegistroVentasDetalladoEsquemasXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteVENRegistroVentasDetalladoEsquemasPDF";
	}
	
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteVENRegistroVentasDetalladoForm form = (ReporteVENRegistroVentasDetalladoForm) this.formReporte;
		Date fecha1D = form.getFechaDesdeD();
		Date fecha2D = form.getFechaHastaD();
		if (fecha2D.compareTo(fecha1D) < 0) {
			String mensaje = this.getResourceMessage("reporteVENRegistroVentasDetalladoForm.errors.compare.fecha");
			return mensaje;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
/*		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}*/
		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ReporteVENRegistroVentasDetalladoForm reporteVENForm = (ReporteVENRegistroVentasDetalladoForm) this.formReporte;
		Map criteria = params;		
		this.formatoReporte = reporteVENForm.getFormatoExportacion();

		params.put("NroReporte", "");
		String nombreEmpresa = ""; 
		String codigoPais = reporteVENForm.getCodigoPais(); 
		if (codigoPais.equals(Constants.PAIS_ECL)) {
			nombreEmpresa = this.getReportResourceMessage("reporte.maestro.ebelParis");
		} else {
			nombreEmpresa = this.getReportResourceMessage("reporte.maestro.cetco");
		}

		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		
		params.put("codigoPaisLbel", pais.getCodigo().substring(0,2)+ Constants.FIN_CODIGO_PAIS_LBEL);
		params.put("codigoPais", pais.getCodigo());				
		String oidPais = reporteService.getOidString("getOidPaisByCodigoPais",criteria);		
 		params.put("oidPais", oidPais);
 		
 		String fecha1 = DateUtil.getDate(reporteVENForm.getFechaDesdeD());
 		String fecha2 = DateUtil.getDate(reporteVENForm.getFechaHastaD());
		reporteVENForm.setFechaDesde(fecha1);
		reporteVENForm.setFechaHasta(fecha2);		
 		
		params.put("fechaDesde", reporteVENForm.getFechaDesde());
		params.put("fechaHasta", reporteVENForm.getFechaHasta());
				
		params.put("superiorIzquierda", nombreEmpresa);
		params.put("titulo", this.getReportResourceMessage("reporteVENGeneralRegistroVentasDetalladoForm.titulo")
				+ "\n("
				+ this.getReportResourceMessage("reporteVENGeneralRegistroVentasDetalladoForm.desde")
				+ reporteVENForm.getFechaDesde()
				+ " "
				+ this.getReportResourceMessage("reporteVENGeneralRegistroVentasDetalladoForm.al") 
				+ " " + reporteVENForm.getFechaHasta() + ")");
		
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	
	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

}
