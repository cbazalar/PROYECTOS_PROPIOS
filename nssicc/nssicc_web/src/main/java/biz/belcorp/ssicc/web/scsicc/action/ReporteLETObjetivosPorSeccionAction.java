package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.lec.MantenimientoLECProgramaCorporativoService;
import biz.belcorp.ssicc.service.spusicc.pej.MantenimientoPEJProgramaEjecutivasService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETObjetivosPorSeccionForm;

@ManagedBean
@SessionScoped
public class ReporteLETObjetivosPorSeccionAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6079085404175506811L;
	private String formatoReporte;
	private List siccRegionList;
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccSeccionList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteLETObjetivosPorSeccionForm reporteForm = new ReporteLETObjetivosPorSeccionForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteLETObjetivosPorSeccionAction - setViewAtributes");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteLETObjetivosPorSeccionForm f = (ReporteLETObjetivosPorSeccionForm) this.formReporte;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		MantenimientoPEJProgramaEjecutivasService service = (MantenimientoPEJProgramaEjecutivasService) getBean("spusicc.mantenimientoPEJProgramaEjecutivasService");
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		Map result = service.getPeriodoDefault();

		String codigoPeriodo = (String) result.get("codigoPeriodo");
		f.setCampanyaObjetivo(codigoPeriodo);

		Map map1 = lecService.getEncontrarProgramaLecCorporativo(f
				.getCampanyaObjetivo());

		f.setCodigoPrograma(map1.get("codigoPrograma") == null ? "" : map1.get(
				"codigoPrograma").toString());
		f.setDescripcionPrograma(map1.get("descPrograma") == null ? "" : map1
				.get("descPrograma").toString());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		this.siccRegionList = reporteService.getListaGenerico("getRegionesPEJ",
				criteria);

		// loadRegiones();

		log.debug("Todo Ok: Redireccionando");

	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteLETObjetivosPorSeccionForm form = (ReporteLETObjetivosPorSeccionForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteLETObjetivosPorSeccionXLS";
		else
			return " ";

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {

		return null;

	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteLETObjetivosPorSeccionForm reporteForm = (ReporteLETObjetivosPorSeccionForm) this.formReporte;

		String regionList[] = { reporteForm.getCodigoRegion() };
		String zonaList[] = { reporteForm.getCodigoZona() };
		String seccionList[] = { reporteForm.getCodigoSeccion() };

		// Actualizamos el programa con la campaña que viene por parametro
		MantenimientoLECProgramaCorporativoService lecService = (MantenimientoLECProgramaCorporativoService) getBean("spusicc.mantenimientoLECProgramaCorporativoService");
		Map map1 = lecService.getEncontrarProgramaLecCorporativo(reporteForm
				.getCampanyaObjetivo());

		reporteForm.setCodigoPrograma(map1.get("codigoPrograma") == null ? ""
				: map1.get("codigoPrograma").toString());
		reporteForm
				.setDescripcionPrograma(map1.get("descPrograma") == null ? ""
						: map1.get("descPrograma").toString());

		String condicionRegion = this.obtieneCondicion(regionList, "resp.cod_regi", "'");
		String condicionZonas = this.obtieneCondicion(zonaList, "resp.cod_zona", "'");
		String condicionSeccion = this.obtieneCondicion(seccionList, "resp.cod_secc", "'");
		
		String condicion = condicionRegion + condicionZonas + condicionSeccion;
		params.put("condicion", condicion);

		params.put("titulo", this
				.getResourceMessage("reporteLETObjetivosPorSeccionForm.titulo"));
		params.put("NroReporte", "");

		return params;
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

		String value = (String) val.getNewValue();
		ReporteLETObjetivosPorSeccionForm reporteForm = (ReporteLETObjetivosPorSeccionForm) this.formReporte;

		if (value == null) {
			setSiccSeccionList(null);
			reporteForm.setCodigoSeccion("");
		}
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		setSiccZonaList(ajaxService.getZonasRegionPEJ(value));


	}

	/**
	 * Metodo para obtener Seccion por Zona
	 * 
	 * @param val
	 */
	public void loadSeccionxZona(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadSeccionxZona");
		}
		ReporteLETObjetivosPorSeccionForm reporteForm = (ReporteLETObjetivosPorSeccionForm) this.formReporte;

		String zona = (String) val.getNewValue();
		if (zona == null) {
			reporteForm.setCodigoSeccion("");
		}
		AjaxService aSvc = (AjaxService) getBean("ajaxService");
		setSiccSeccionList(aSvc.getSeccionesZonaPEJ(zona));


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
	 * @param siccRegionList
	 *            the siccRegionList to set
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
	 * @param siccZonaList
	 *            the siccZonaList to set
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
	 * @param siccSeccionList
	 *            the siccSeccionList to set
	 */
	public void setSiccSeccionList(LabelValue[] siccSeccionList) {
		this.siccSeccionList = siccSeccionList;
	}

}
