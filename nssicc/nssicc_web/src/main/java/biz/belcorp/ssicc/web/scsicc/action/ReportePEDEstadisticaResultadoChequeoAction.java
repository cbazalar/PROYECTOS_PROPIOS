package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReportePEDEstadisticaResultadoChequeoForm;


/**
 * The Class ReportePEDEstadisticaResultadoChequeoAction.
 *
 * @autor: Hernando Huaman Flores
 * @version: 1.0
 * 07/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReportePEDEstadisticaResultadoChequeoAction extends BaseReporteAbstractAction {
	
	private static final long serialVersionUID = -8510873562337024940L;
	private String formatoReporte;
	private String tipoReporte;
	private List siccRegionList;
	private List<LabelValue> siccZonaList;
	private boolean bRegion;
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReportePEDEstadisticaResultadoChequeoForm form = new ReportePEDEstadisticaResultadoChequeoForm();
		return form;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@SuppressWarnings({ "unchecked" })
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReportePEDEstadisticaResultadoChequeoAction.setViewAtributes' method");
		}
		
		ReportePEDEstadisticaResultadoChequeoForm reporteRECForm = (ReportePEDEstadisticaResultadoChequeoForm) this.formReporte;
		
		this.mostrarReporteOCSV = false;
		this.mostrarReporteXLSX = false;
		this.mostrarReporteCSV = false;
		this.mostrarReportePDF = false;
		this.mostrarReporteXLS = true;
		this.mostrarReporteOJXLSX = false;
		this.mostrarReporteOOXLSX = false;		
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.setSiccRegionList(reporteService.getListaGenerico("getRegionesByPais",criteriaOperacion));
		this.setSiccZonaList(new ArrayList());
		this.setbRegion(true);
		
		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");		
		String codigoPeriodo = service.getPeriodoDefaultByPaisCanal(pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);
		reporteRECForm.setCodigoPeriodo(codigoPeriodo);		
	}
	
	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {
		this.setFormatoReporte(((ReportePEDEstadisticaResultadoChequeoForm)this.formReporte).getFormatoExportacion()); 
		String reporte = "";		
		if ("XLS".equals(getFormatoReporte())){
			if (StringUtils.equals(getTipoReporte(),"R")) {
				reporte= "reportePEDEstadisticaRegionXLS";
			}
			if (StringUtils.equals(getTipoReporte(),"Z")){
				reporte= "reportePEDEstadisticaZonaXLS";
			}
			if (StringUtils.equals(getTipoReporte(),"S")) {
				reporte= "reportePEDEstadisticaSecciXLS";
			}
		} else {
			reporte= "reporteMaestroHorizontal";
		}
		return reporte;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {	
		if ("PDF".equals(getFormatoReporte()))
			return "";
		else
			return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	protected Map prepareParameterMap(Map params) throws Exception {		
		ReportePEDEstadisticaResultadoChequeoForm reporteRECForm = (ReportePEDEstadisticaResultadoChequeoForm) this.formReporte;
		this.setFormatoExportacion(reporteRECForm.getFormatoExportacion());
		setTipoReporte(reporteRECForm.getTipoReporte());		
		params.put("NroReporte", "");		
		params.put("titulo", this.getReportResourceMessage("reportePEDEstadisticaResultadoChequeoForm.titulo"));
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}
	
	/**
	 * Show zonasx region.
	 *
	 * @param val the val
	 */
	public void showZonasxRegion(ValueChangeEvent val){
		if(val.getNewValue() != null) {
			if(log.isDebugEnabled()){
				log.debug("showZonasxRegion:ValueChangeEvent");
			}
			log.debug(val.getNewValue().toString());
			String[] regionListado = new String[1];
			regionListado[0] = (String) val.getNewValue();			
			log.debug(regionListado.length);			
			AjaxService ajax = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(Arrays.asList(ajax.getZonasMultipleByPaisMarcaCanalRegion(this.getmPantallaPrincipalBean().getCurrentCountry().getCodigo(), "T", "VD", regionListado,"T")));	
			this.setbRegion(false);
		} else {
			this.setSiccZonaList(new ArrayList<LabelValue>());
			this.setbRegion(true);
		}
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

	/**
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return the siccRegionList
	 */
	public List getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(List siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public List<LabelValue> getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(List<LabelValue> siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the bRegion
	 */
	public boolean isbRegion() {
		return bRegion;
	}

	/**
	 * @param bRegion the bRegion to set
	 */
	public void setbRegion(boolean bRegion) {
		this.bRegion = bRegion;
	}

	
}