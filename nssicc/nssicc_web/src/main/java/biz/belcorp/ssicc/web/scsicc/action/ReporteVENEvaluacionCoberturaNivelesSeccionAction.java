package biz.belcorp.ssicc.web.scsicc.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteVENEvaluacionCoberturaNivelesSeccionForm;

/**
 * The Class ReporteVENEvaluacionCoberturaNivelesSeccionAction.
 * 
 * @autor: Hernando Huaman Flores
 * @version: 1.0 11/11/2014
 */
@SuppressWarnings("rawtypes")
@ManagedBean
@SessionScoped
public class ReporteVENEvaluacionCoberturaNivelesSeccionAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = 9089545372110234269L;
	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;
	private LabelValue[] siccRegionList;
	private LabelValue[] siccZonaList;
	private List siccAnioList;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteVENEvaluacionCoberturaNivelesSeccionForm form = new ReporteVENEvaluacionCoberturaNivelesSeccionForm();
		return form;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteVENEvaluacionCoberturaNivelesSeccionAction.setViewAtributes' method");
		}		
		this.mostrarReportePDF = true;
		this.mostrarReporteXLS = true;	

		InterfazSiCCService service = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccMarcaList = service.getMarcas();
		this.siccCanalList = service.getCanalesByCodigoISO(usuario.getIdioma()
				.getCodigoISO());
		ReporteVENEvaluacionCoberturaNivelesSeccionForm reporteVENForm = (ReporteVENEvaluacionCoberturaNivelesSeccionForm) this.formReporte;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String anio = sdf.format(new Date(System.currentTimeMillis()));
		reporteVENForm.setAnio(anio);
		reporteVENForm.setCodigoPais(pais.getCodigo());
		reporteVENForm.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);
		this.siccAnioList = reporteService.getListaGenerico("getAniosCraPerio",
				criteriaOperacion);
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		this.siccRegionList = ajaxService
				.getRegionesByPaisLet(pais.getCodigo());
		log.debug("Todo Ok: Redireccionando");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		this.formatoReporte = ((ReporteVENEvaluacionCoberturaNivelesSeccionForm) this.formReporte)
				.getFormatoExportacion();
		if ("XLS".equals(formatoReporte)) {
			return "reporteVENEvaluacionCoberturaNivelesSeccionXLS";
		} else {
			return "reporteMaestroHorizontal";
		}
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
		if ("PDF".equals(formatoReporte)) {
			return "reporteVENEvaluacionCoberturaNivelesSeccionPDF";
		}
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteVENEvaluacionCoberturaNivelesSeccionService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteVENEvaluacionCoberturaNivelesSeccionForm reporteVENForm = (ReporteVENEvaluacionCoberturaNivelesSeccionForm) this.formReporte;
		formatoReporte = reporteVENForm.getFormatoExportacion();

		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO
						+ "subReporteVENEvaluacionCoberturaNivelesSeccion"
						+ JASPER_EXTENSION);

		params.put(
				"SUBREPORT_DIR1",
				(JasperReport) JRLoader.loadObject(this.getClass()
						.getClassLoader().getResource(resource.getPath())));

		params.put(
				"NroReporte",
				getReportResourceMessage("reporteVENEvaluacionCoberturaNivelesSeccionForm.numero.reporte"));
		params.put(
				"titulo",
				getReportResourceMessage("reporteVENEvaluacionCoberturaNivelesSeccionForm.titulo"));
		log.debug("Imprimiendo parámetros");
		log.debug(params);
		log.debug("Fin parámetros");
		return params;
	}

	/**
	 * Carga Combo de Regiones por Marca
	 * 
	 * @param val
	 */
	public void loadRegionesMarca(ValueChangeEvent val) {
		
			log.debug(">>loadRegionesMarca...");
			String valor = val.getNewValue().toString();

			ReporteVENEvaluacionCoberturaNivelesSeccionForm form = (ReporteVENEvaluacionCoberturaNivelesSeccionForm) this.formReporte;
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			if (valor.equals(Constants.CODIGO_MARCA_DEFAULT)
					&& form.getCodigoCanal().equals(
							Constants.CODIGO_CANAL_DEFAULT)) {
				this.siccRegionList = ajaxService.getRegionesByPaisLet(form
						.getCodigoPais());
			} else {
				this.setSiccRegionList(ajaxService
						.getRegionesByPaisMarcaCanalDetalle(
								form.getCodigoPais(), valor,
								form.getCodigoCanal(),
								Constants.CODIGO_MARCA_DEFAULT));
			}
			form.setCodigoRegion(null);
			setSiccZonaList(null);
			form.setCodigoZona(null);
	
	}

	/**
	 * Carga Combo de Regiones por Canal
	 * 
	 * @param val
	 */
	public void loadRegionesCanal(ValueChangeEvent val) {
	
			log.debug(">>loadRegionesMarca...");
			String valor = val.getNewValue().toString();

			ReporteVENEvaluacionCoberturaNivelesSeccionForm form = (ReporteVENEvaluacionCoberturaNivelesSeccionForm) this.formReporte;
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			if (form.getCodigoMarca().equals(Constants.CODIGO_MARCA_DEFAULT)
					&& valor.equals(Constants.CODIGO_CANAL_DEFAULT)) {
				this.siccRegionList = ajaxService.getRegionesByPaisLet(form
						.getCodigoPais());
			} else {
				this.setSiccRegionList(ajaxService
						.getRegionesByPaisMarcaCanalDetalle(
								form.getCodigoPais(), valor,
								form.getCodigoCanal(),
								Constants.CODIGO_MARCA_DEFAULT));
			}
			form.setCodigoRegion(null);
			setSiccZonaList(null);
			form.setCodigoZona(null);
		
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		
			if (log.isDebugEnabled()) {
				log.debug("loadZonas");
			}
			if (!val.equals(null)) {
				String valor = (String) val.getNewValue();
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(ajaxService.getZonasByPaisRegionLet(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), valor));
			} else
				setSiccZonaList(null);
		
	}

	/**
	 * @return the formatoReporte
	 */
	public String getFormatoReporte() {
		return formatoReporte;
	}

	/**
	 * @param formatoReporte
	 *            the formatoReporte to set
	 */
	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the siccMarcaList
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 *            the siccMarcaList to set
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return the siccCanalList
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 *            the siccCanalList to set
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
	}

	/**
	 * @return the siccRegionList
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 *            the siccRegionList to set
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return the siccZonaList
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 *            the siccZonaList to set
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the siccAnioList
	 */
	public List getSiccAnioList() {
		return siccAnioList;
	}

	/**
	 * @param siccAnioList
	 *            the siccAnioList to set
	 */
	public void setSiccAnioList(List siccAnioList) {
		this.siccAnioList = siccAnioList;
	}

}
