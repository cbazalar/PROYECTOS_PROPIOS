package biz.belcorp.ssicc.web.edu.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.edu.form.ReporteEDUResumenProgramadasPlanillaForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.service.edu.ProcesoEDUCalificacionAptasAutomaticaService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped 
public class ReporteEDUResumenProgramadasPlanillaAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -227035293872628825L;

	private String formatoReporte;
	private List eduEmpresaComercializadoraList;
	private String empresaDefault;
	private LabelValue[] eduRegionesCursosList;
	private LabelValue[] eduParametrosZonaList;

	public String getFormatoReporte() {
		return formatoReporte;
	}

	public void setFormatoReporte(String formatoReporte) {
		this.formatoReporte = formatoReporte;
	}

	/**
	 * @return the eduEmpresaComercializadoraList
	 */
	public List getEduEmpresaComercializadoraList() {
		return eduEmpresaComercializadoraList;
	}

	/**
	 * @param eduEmpresaComercializadoraList
	 *            the eduEmpresaComercializadoraList to set
	 */
	public void setEduEmpresaComercializadoraList(
			List eduEmpresaComercializadoraList) {
		this.eduEmpresaComercializadoraList = eduEmpresaComercializadoraList;
	}

	public String getEmpresaDefault() {
		return empresaDefault;
	}

	public void setEmpresaDefault(String empresaDefault) {
		this.empresaDefault = empresaDefault;
	}

	/**
	 * @return the eduRegionesCursosList
	 */
	public LabelValue[] getEduRegionesCursosList() {
		return eduRegionesCursosList;
	}

	/**
	 * @param eduRegionesCursosList
	 *            the eduRegionesCursosList to set
	 */
	public void setEduRegionesCursosList(LabelValue[] eduRegionesCursosList) {
		this.eduRegionesCursosList = eduRegionesCursosList;
	}

	/**
	 * @return the eduParametrosZonaList
	 */
	public LabelValue[] getEduParametrosZonaList() {
		return eduParametrosZonaList;
	}

	/**
	 * @param eduParametrosZonaList
	 *            the eduParametrosZonaList to set
	 */
	public void setEduParametrosZonaList(LabelValue[] eduParametrosZonaList) {
		this.eduParametrosZonaList = eduParametrosZonaList;
	}

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteEDUResumenProgramadasPlanillaForm reporteForm = new ReporteEDUResumenProgramadasPlanillaForm();
		return reporteForm;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		if ("PDF".equals(formatoReporte))
			return "reporteEDUResumenProgramadasPlanillaPDF";
		else
			return "";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteEDUResumenProgramadasPlanillaForm reporteForm = (ReporteEDUResumenProgramadasPlanillaForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		params.put("NroReporte", "");
		params.put(
				"titulo",
				getResourceMessage("reporteEDUResumenProgramadasPlanillaForm.titulo"));
		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("codigoEmpresa", reporteForm.getCodigoEmpresa());
		params.put("campanhaProceso", reporteForm.getCampanhaProceso());
		params.put("codigoRegion", reporteForm.getCodigoRegion());
		params.put("codigoZona", reporteForm.getCodigoZona());

		return params;
	}

	@Override
	protected void setViewAtributes() throws Exception {

		ReporteEDUResumenProgramadasPlanillaForm f = (ReporteEDUResumenProgramadasPlanillaForm) this.formReporte;
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();

		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setCodigoPais(pais.getCodigo());
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		eduEmpresaComercializadoraList = siccService
				.getEmpresasComercializadorasByPais(parametroEmpresa);

		/* Inicializamos la Empresa, siempre despues de LoadCombos */

		List listaEmpresa = eduEmpresaComercializadoraList;

		if ((listaEmpresa != null) && (listaEmpresa.size() > 0)) {
			EmpresaComercializadora empresa = new EmpresaComercializadora();
			empresa = (EmpresaComercializadora) listaEmpresa.get(0);
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());
		}

		eduRegionesCursosList = ajaxService.getRegionesEDUByPaisEmpresa(
				f.getCodigoPais(), f.getCodigoEmpresa());
		f.setCampanhaProceso(getCampanhaProceso(f.getCodigoPais(),
				f.getCodigoEmpresa()));

		String region0 = getEduRegionesCursosList()[0].getValue();
		this.eduParametrosZonaList = ajaxService.getZonaByPaisEmpresaRegion(
				f.getCodigoPais(), f.getCodigoEmpresa(), region0);

	}

	private String getCampanhaProceso(String codigoPais, String codigoEmpresa) {
		// Obteniendo Campaña de Proceso
		ProcesoEDUCalificacionAptasAutomaticaService service = (ProcesoEDUCalificacionAptasAutomaticaService) getBean("edu.procesoEDUCalificacionAptasAutomaticaService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", codigoPais);
		criteria.put("codigoEmpresa", codigoEmpresa);
		String codigoPeriodo = service.getCampannaActualProceso(criteria);
		return codigoPeriodo;
	}

	public void loadCargarDatos(ValueChangeEvent value) {
		if (log.isDebugEnabled()) {
			log.debug("loadCargarDatos");
		}

		try {
			String valor = (String) value.getNewValue();
			if (valor.trim().length() > 0) {
				ReporteEDUResumenProgramadasPlanillaForm reporteForm = (ReporteEDUResumenProgramadasPlanillaForm) this.formReporte;
				getCampanhaProceso(reporteForm.getCodigoPais(),	reporteForm.getCodigoEmpresa());
				
				AjaxService ajaxService = (AjaxService) this.getBeanService("ajaxService");
				eduRegionesCursosList = ajaxService.getRegionesEDUByPaisEmpresa( reporteForm.getCodigoPais(), reporteForm.getCodigoEmpresa());
				this.eduParametrosZonaList = ajaxService.getZonaByPaisEmpresaRegion(reporteForm.getCodigoPais(), reporteForm.getCodigoEmpresa(),
						eduRegionesCursosList[0].getValue());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void loadZonas(ValueChangeEvent value) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		String valor = (String) value.getNewValue();
		valor.trim().length();
		ReporteEDUResumenProgramadasPlanillaForm reporteForm = (ReporteEDUResumenProgramadasPlanillaForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) this
				.getBeanService("ajaxService");
		this.eduParametrosZonaList = ajaxService.getZonaByPaisEmpresaRegion(reporteForm.getCodigoPais(), reporteForm.getCodigoEmpresa(),
				valor);
	}

	@Override
	protected String devuelveBeanReporteService() {
		return "reportes.reporteEDUResumenProgramadasPlanillaService";
	}
}
