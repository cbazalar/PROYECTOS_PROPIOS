package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.spusicc.fdv.ProcesoFDVClusterizacionService;
import biz.belcorp.ssicc.service.util.ConstantsList;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteFDVFuerzaDeVentaForm;

/**
 * @author Jose Pulido
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteFDVFuerzaDeVentaAction extends BaseReporteAbstractAction
		implements Serializable {

	private static final long serialVersionUID = 6467992741175535675L;
	private String formatoReporte;
	private List siccAnioList;
	private LabelValue[] siccProcesoList = {};
	private List siccPeriodoList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	
	private LabelValue[] siccRegionInicialList = {};
	private LabelValue[] siccZonaInicialList = {};

	/**
	 * @return
	 */
	public LabelValue[] getSiccProcesoList() {
		return siccProcesoList;
	}

	/**
	 * @param siccProcesoList
	 */
	public void setSiccProcesoList(LabelValue[] siccProcesoList) {
		this.siccProcesoList = siccProcesoList;
	}

	/**
	 * @return
	 */
	public List getSiccAnioList() {
		return siccAnioList;
	}

	/**
	 * @param siccAnioList
	 */
	public void setSiccAnioList(List siccAnioList) {
		this.siccAnioList = siccAnioList;
	}

	/**
	 * @return
	 */
	public List getSiccPeriodoList() {
		return siccPeriodoList;
	}

	/**
	 * @param siccPeriodoList
	 */
	public void setSiccPeriodoList(List siccPeriodoList) {
		this.siccPeriodoList = siccPeriodoList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
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
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {

		ProcesoFDVClusterizacionService procesoService = (ProcesoFDVClusterizacionService) getBean("spusicc.procesoFDVClusterizacionService");
		ReporteFDVFuerzaDeVentaForm reporteForm = new ReporteFDVFuerzaDeVentaForm();
		String codigoPais = mPantallaPrincipalBean.getCurrentCountry()
				.getCodigo();
		reporteForm.setCodigoPais(codigoPais);
		
		this.mostrarReportePDF = false;
		this.mostrarReporteZIP = true;

		this.siccAnioList = procesoService
				.getAnyosProcesosDistribucionRealizadaByPais(codigoPais);

		reporteForm.getIdioma().setCodigoISO(
				mPantallaPrincipalBean.getCurrentIdioma().getCodigoISO());
		this.siccPeriodoList = ConstantsList.getProcessPeriodListFDV();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroVertical";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ProcesoFDVClusterizacionService procesoService = (ProcesoFDVClusterizacionService) getBean("spusicc.procesoFDVClusterizacionService");

		ReporteFDVFuerzaDeVentaForm reporteFDVForm = (ReporteFDVFuerzaDeVentaForm) this.formReporte;

		// Cargamos los subreportes

		ClassPathResource resource1 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO
						+ "subReporteFDVFuerzaDeVentaPromedios"
						+ JASPER_EXTENSION);
		ClassPathResource resource2 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO
						+ "subReporteFDVFuerzaDeVentaSemaforos"
						+ JASPER_EXTENSION);

		params.put(
				"SUBREPORT_DIR1",
				(JasperReport) JRLoader.loadObject(this.getClass()
						.getClassLoader().getResource(resource1.getPath())));
		params.put(
				"SUBREPORT_DIR2",
				(JasperReport) JRLoader.loadObject(this.getClass()
						.getClassLoader().getResource(resource2.getPath())));

		// cargamos los demas parametros
		params.put("reporteEmpaquetado", Constants.SI);
		params.put("codigoProceso", reporteFDVForm.getCodigoProceso());
		params.put("anyo", reporteFDVForm.getAnyoProceso());
		params.put("periodo", reporteFDVForm.getPeriodoAnyoProceso());
		params.put("nombreProceso", descripcionSimpleLista(reporteFDVForm.getCodigoProceso(), this.siccProcesoList));

		// Cargamos las listas de regiones y zonas
		List regionesZonas = new ArrayList();

		AjaxService ajax = (AjaxService) getBean("ajaxService");
		if (StringUtils.isBlank(reporteFDVForm.getCodigoRegion())) {
			params.put("flagGenerarConsolidado", Constants.SI);
			params.put("flagGenerarConsolidadoRegion", Constants.SI);

			// Cargamos todas las regiones con todas las zonas
			LabelValue[] regiones = ajax.getRegionesFDVByProceso(reporteFDVForm
					.getCodigoProceso());

			if (regiones != null && regiones.length > 0) {
				// Cargamos cada una de las zonas de cada una de las regiones
				for (int i = 0; i < regiones.length; i++) {
					cargarDatosZonas(ajax, regionesZonas,
							reporteFDVForm.getCodigoProceso(),
							regiones[i].getValue());
				}
			}
		} else {
			if (StringUtils.isBlank(reporteFDVForm.getCodigoZona())) {
				params.put("flagGenerarConsolidadoRegion", Constants.SI);

				// Cargamos todas las zonas de la region seleccionada
				cargarDatosZonas(ajax, regionesZonas,
						reporteFDVForm.getCodigoProceso(),
						reporteFDVForm.getCodigoRegion());
			} else {
				// Cargamos solo la region y zona seleccionada
				Map regzon = new HashMap();
				regzon.put("codigoRegion", reporteFDVForm.getCodigoRegion());
				regzon.put("codigoZona", reporteFDVForm.getCodigoZona());

				regionesZonas.add(regzon);
			}
		}
		params.put(Constants.REPORTE_REGION_ZONA_MAP_LIST, regionesZonas);
		//

		params.put(
				"parametroAlertaCapPupPpuRojo",
				procesoService.getValorParametroProceso(
						reporteFDVForm.getCodigoProceso(),
						Constants.CODIGO_PARAMETRO_ALERTA_REPORTE_CAPITALIZACION_PUP_PPU_ROJO));
		params.put(
				"parametroAlertaCapPupPpuAmbar",
				procesoService.getValorParametroProceso(
						reporteFDVForm.getCodigoProceso(),
						Constants.CODIGO_PARAMETRO_ALERTA_REPORTE_CAPITALIZACION_PUP_PPU_AMBAR));
		params.put(
				"parametroAlertaActRojo",
				procesoService.getValorParametroProceso(
						reporteFDVForm.getCodigoProceso(),
						Constants.CODIGO_PARAMETRO_ALERTA_REPORTE_ACTIVIDAD_ROJO));
		params.put(
				"parametroAlertaActAmbar",
				procesoService.getValorParametroProceso(
						reporteFDVForm.getCodigoProceso(),
						Constants.CODIGO_PARAMETRO_ALERTA_REPORTE_ACTIVIDAD_AMBAR));

		String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/") + "/resources/images/";
		
		params.put("rutaImagenVerde", path + getReportResourceMessage("reporteFDVFuerzaDeVentaForm.image.semaforo.verde"));
		params.put("rutaImagenAmbar", path + getReportResourceMessage("reporteFDVFuerzaDeVentaForm.image.semaforo.ambar"));
		params.put("rutaImagenRojo", path + getReportResourceMessage("reporteFDVFuerzaDeVentaForm.image.semaforo.rojo"));

		params.put("titulo", getReportResourceMessage("reporteFDVFuerzaDeVentaForm.titulo"));
		
		params.put("nombreRegion", (StringUtils.isBlank(reporteFDVForm.getCodigoRegion()) ? "Todas" : reporteFDVForm.getCodigoRegion()));
		params.put("nombreZona", (StringUtils.isBlank(reporteFDVForm.getCodigoZona()) ? "Todas" : reporteFDVForm.getCodigoZona()));

		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteFDVFuerzaDeVentaForm reporteForm = new ReporteFDVFuerzaDeVentaForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteFDVFuerzaDeVentaPDF";
	}

	/**
	 * Obtener Lista de Zonas
	 * 
	 * @param val
	 */

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @param ajax
	 * @param regionesZonas
	 * @param codigoProceso
	 * @param codigoRegion
	 */
	private void cargarDatosZonas(AjaxService ajax, List regionesZonas,
			String codigoProceso, String codigoRegion) {
		try {
			LabelValue[] zonas = ajax.getZonasFDVByProcesoRegion(codigoRegion,
					codigoProceso);

			if (zonas != null && zonas.length > 0) {
				// Cargamos los datos en la lista
				for (int j = 0; j < zonas.length; j++) {
					Map regzon = new HashMap();
					regzon.put("codigoRegion", codigoRegion);
					regzon.put("codigoZona", zonas[j].getValue());

					regionesZonas.add(regzon);
				}
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}

	}

	/**
	 * Carga proceso
	 * 
	 * @param val
	 */
	public void loadProcesoPaisAnyoPeriodo(ValueChangeEvent val) {
		try {
			log.debug(">>loadProcesoPaisAnyoPeriodo...");
			
			ReporteFDVFuerzaDeVentaForm form = (ReporteFDVFuerzaDeVentaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String Anio = "";
			
			this.siccProcesoList = null;
			this.siccRegionList = null;
			this.siccZonaList = null;
			form.setCodigoProceso("");
			form.setCodigoRegion("");
			form.setCodigoZona("");
			
			if(val.getNewValue() != null){
				Anio = val.getNewValue().toString();
				form.setAnyoProceso(Anio);
			}else{
				form.setAnyoProceso(Anio);
			}

			this.siccProcesoList = aSvc.getProcesosFDVByPaisAnyoPeriodo(form.getCodigoPais(), form.getAnyoProceso(), form.getPeriodoAnyoProceso());

			return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}
	}

	/**
	 * Carga proceso
	 * 
	 * @param val
	 */
	public void loadProcesoPaisAnyoPeriodo2(ValueChangeEvent val) {
		try {
			log.debug(">>loadProcesoPaisAnyoPeriodo2...");
			
			ReporteFDVFuerzaDeVentaForm form = (ReporteFDVFuerzaDeVentaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String periodo = "";
			
			this.siccProcesoList = null;
			this.siccRegionList = null;
			this.siccZonaList = null;
			form.setCodigoProceso("");
			form.setCodigoRegion("");
			form.setCodigoZona("");
			
			if(val.getNewValue() != null){
				periodo = val.getNewValue().toString();
				form.setPeriodoAnyoProceso(periodo);
			}else{
				form.setPeriodoAnyoProceso(periodo);
			}

			this.siccProcesoList = aSvc.getProcesosFDVByPaisAnyoPeriodo(form.getCodigoPais(), form.getAnyoProceso(), form.getPeriodoAnyoProceso());

			return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}
	}
	
	/**
	 * Carga regiones
	 * 
	 * @param val
	 */
	public void loadRegionProceso(ValueChangeEvent val) {
		try {
			log.debug(">>loadRegionProceso...");
			
			ReporteFDVFuerzaDeVentaForm form = (ReporteFDVFuerzaDeVentaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String procesoSelect = "";
			
			this.siccRegionList = null;
			this.siccZonaList = null;
			form.setCodigoRegion("");
			form.setCodigoZona("");
			
			if(val.getNewValue() != null){
				procesoSelect = val.getNewValue().toString();
				form.setCodigoProceso(procesoSelect);
			}else{
				form.setCodigoProceso(procesoSelect);
			}
						
			this.siccRegionInicialList = aSvc.getRegionesFDVByProceso(form.getCodigoProceso());
			
			if(this.siccRegionInicialList != null){
				this.siccRegionList = new LabelValue[this.siccRegionInicialList.length + 1];
				this.siccRegionList[0] = new LabelValue("Todas", "");
				
				for (int i = 0; i < this.siccRegionInicialList.length; i++) {
					this.siccRegionList[i + 1] = this.siccRegionInicialList[i];
				}
			}else{
				this.siccRegionList = new LabelValue[1];
				this.siccRegionList[0] = new LabelValue("Todas", "");
			}
			
			return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}
	}

	/**
	 * Carga zona
	 * 
	 * @param val
	 */
	public void loadZonaRegion(ValueChangeEvent val) {
		try {
			log.debug(">>loadZonaRegion...");
			
			ReporteFDVFuerzaDeVentaForm form = (ReporteFDVFuerzaDeVentaForm) this.formReporte;
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			
			String regionSelect = "";
			
			this.siccZonaList = null;
			form.setCodigoZona("");
			
			if(val.getNewValue() != null){
				regionSelect = val.getNewValue().toString();
				form.setCodigoRegion(regionSelect);
			}else{
				form.setCodigoRegion(regionSelect);
			}

			this.siccZonaInicialList = aSvc.getZonasFDVByProcesoRegion(form.getCodigoRegion(), form.getCodigoProceso());
			
			if(this.siccZonaInicialList != null){
				this.siccZonaList = new LabelValue[this.siccZonaInicialList.length + 1];
				this.siccZonaList[0] = new LabelValue("Todas", "");
				
				for (int i = 0; i < this.siccZonaInicialList.length; i++) {
					this.siccZonaList[i + 1] = this.siccZonaInicialList[i];
				}
			}else{
				this.siccZonaList = new LabelValue[1];
				this.siccZonaList[0] = new LabelValue("Todas", "");
			}

			return;
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
			return;
		}

	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		if(log.isDebugEnabled()){
			log.debug("setValidarReporte...");
		}
		
		ReporteFDVFuerzaDeVentaForm form = (ReporteFDVFuerzaDeVentaForm) this.formReporte;
		
		if(StringUtils.isBlank(form.getAnyoProceso())){
			return "'Año' es un campo requerido";
		}
		
		if(StringUtils.isBlank(form.getPeriodoAnyoProceso())){
			return "'Periodo' es un campo requerido";
		}
		
		if(StringUtils.isBlank(form.getCodigoProceso())){
			return "'Proceso' es un campo requerido";
		}
		
		return super.setValidarReporte();
	}

	/**
	 * @return the siccRegionInicialList
	 */
	public LabelValue[] getSiccRegionInicialList() {
		return siccRegionInicialList;
	}

	/**
	 * @param siccRegionInicialList the siccRegionInicialList to set
	 */
	public void setSiccRegionInicialList(LabelValue[] siccRegionInicialList) {
		this.siccRegionInicialList = siccRegionInicialList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaInicialList
	 */
	public LabelValue[] getSiccZonaInicialList() {
		return siccZonaInicialList;
	}

	/**
	 * @param siccZonaInicialList the siccZonaInicialList to set
	 */
	public void setSiccZonaInicialList(LabelValue[] siccZonaInicialList) {
		this.siccZonaInicialList = siccZonaInicialList;
	}
}