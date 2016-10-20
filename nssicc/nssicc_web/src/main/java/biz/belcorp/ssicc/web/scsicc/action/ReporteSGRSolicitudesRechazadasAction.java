package biz.belcorp.ssicc.web.scsicc.action;

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
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSGRSolicitudesRechazadasForm;

@ManagedBean
@SessionScoped
public class ReporteSGRSolicitudesRechazadasAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3415122626841563673L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList={};
	private LabelValue[] siccSeccionList={};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSGRSolicitudesRechazadasForm reporteForm = new ReporteSGRSolicitudesRechazadasForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteSGRSolicitudesRechazadasAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		this.siccRegionList = reporteService.getListaGenerico(
				"getRegionesByPais", criteriaOperacion);


		log.debug("Todo OK: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteSGRSolicitudesRechXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteSGRSolicitudesRechazadasForm reporteCOBForm = (ReporteSGRSolicitudesRechazadasForm) this.formReporte;
		formatoReporte = reporteCOBForm.getFormatoExportacion();
		
		String fechaIni="";
		String fechaFin="";
		if(reporteCOBForm.getFechaInicioD()!=null){
			fechaIni=DateUtil.convertDateToString(reporteCOBForm.getFechaInicioD());
			reporteCOBForm.setFechaInicio(fechaIni);
			
		}
		if(reporteCOBForm.getFechaFinalD()!=null){
			fechaFin=DateUtil.convertDateToString(reporteCOBForm.getFechaFinalD());
			reporteCOBForm.setFechaFinal(fechaFin);
		}
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codigoPais = pais.getCodigo();

		String codigoRegion = reporteCOBForm.getCodigoRegion();
		if(StringUtils.isBlank(codigoRegion)){
			codigoRegion=null;
		}
				
		String codigoZona = reporteCOBForm.getCodigoZona();
		if(StringUtils.isBlank(codigoZona)){
			codigoZona=null;
		}

		String codigoSeccion = reporteCOBForm.getCodigoSeccion();
		if(StringUtils.isBlank(codigoSeccion)){
			codigoSeccion=null;
		}

		if(StringUtils.isBlank(fechaIni))
			fechaIni = null;

		if(StringUtils.isBlank(fechaFin))
			fechaFin = null;

		String codigoPeriodoInicio = reporteCOBForm.getCodigoPeriodoInicio();
		if(StringUtils.isBlank(codigoPeriodoInicio))
			codigoPeriodoInicio = null;

		String codigoPeriodoFin = reporteCOBForm.getCodigoPeriodoFin();
		if(StringUtils.isBlank(codigoPeriodoFin))
			codigoPeriodoFin = null;

		params.put("codigoPais", codigoPais);
		params.put("codigoRegion", codigoRegion);
		params.put("codigoZona", codigoZona);
		params.put("codigoSeccion", codigoSeccion);
		params.put("fechaInicio", fechaIni);
		params.put("fechaFin", fechaFin);
		params.put("codigoPeriodoInicio", codigoPeriodoInicio);
		params.put("codigoPeriodoFin", codigoPeriodoFin);
		params.put("NroReporte", "");
		params.put("titulo", this.getResourceMessage("reporteSGRSolicitudesRechazadasForm.title"));
	
		return params;
	}

	public void loadzonas(ValueChangeEvent val) {
		log.debug("loadzonas");

		String valor = (String) val.getNewValue();
		if (valor.trim().length() > 0) {

			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.siccZonaList = aSvc
					.getDesZonasByPaisMarcaCanalRegion(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valor);

		} else {
			setSiccZonaList(null);
			setSiccSeccionList(null);

		}

	}

	//
	public void loadseccion(ValueChangeEvent val) {
		log.debug("loadseccion");
		ReporteSGRSolicitudesRechazadasForm form = (ReporteSGRSolicitudesRechazadasForm) this.formReporte;
		String region = (String) form.getCodigoRegion();
		String zona = (String) val.getNewValue();
		if (region.length() > 0 && zona.length() > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			setSiccSeccionList(aSvc
					.getSeccionesByPaisMarcaCanalRegionZona(
							this.mPantallaPrincipalBean.getCurrentCountry()
									.getCodigo(),
							Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, region, zona));

		} else {
			setSiccSeccionList(null);

		}

	}

	/**
	 * @return
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
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
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccSeccionList
	 */
	public LabelValue[] getSiccSeccionList() {
		return siccSeccionList;
	}

	/**
	 * @param siccSeccionList the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}
	
	

}

