package biz.belcorp.ssicc.web.scsicc.action;

import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.edu.model.EmpresaComercializadora;
import biz.belcorp.ssicc.dao.edu.model.RegionCursoCapacitacion;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteEDUCronogramaDictadoForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUCursoCapacitacionService;
import biz.belcorp.ssicc.service.edu.MantenimientoEDUGenericoService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteEDUCronogramaDictadoAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -245016251525034494L;
	private String formatoReporte;
	private List eduEmpresaComercializadoraList;
	private LabelValue[] eduRegionesCursosList = {};
	private LabelValue[] eduParametrosZonaList = {};

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteEDUCronogramaDictadoForm reporteForm = new ReporteEDUCronogramaDictadoForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteEDUCronogramaDictadoAction - setViewAtributes");
		}

		ReporteEDUCronogramaDictadoForm f = (ReporteEDUCronogramaDictadoForm) this.formReporte;
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		f.setCodigoPais(pais.getCodigo());

		/* Obteniendo Lista de empresas */
		List resultado = obtenerListaEmpresa(pais.getCodigo(), null);
		this.eduEmpresaComercializadoraList = resultado;

		/* Inicializamos la Empresa, siempre despues de LoadCombos */
		List listaEmpresa = resultado;
		if ((listaEmpresa != null) && (listaEmpresa.size() > 0)) {
			EmpresaComercializadora empresa = new EmpresaComercializadora();
			empresa = (EmpresaComercializadora) listaEmpresa.get(0);
			f.setCodigoEmpresa(empresa.getCodigoEmpresa());
		}
		this.eduRegionesCursosList = ajaxService.getRegionesEDUByPaisEmpresa(
				f.getCodigoPais(), f.getCodigoEmpresa());

		String codRegion0 = getEduRegionesCursosList()[0].getValue();
		this.eduParametrosZonaList = ajaxService.getZonaByPaisEmpresaRegion(
				f.getCodigoPais(), f.getCodigoEmpresa(), codRegion0);
	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteEDUCronogramaDictadoService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteEDUCronogramaDictado";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		String descripcion = new String();
		ReporteEDUCronogramaDictadoForm reporteForm = (ReporteEDUCronogramaDictadoForm) this.formReporte;

		/* Obteniendo descripcion de Empresa */
		List resultado = obtenerListaEmpresa(reporteForm.getCodigoPais(),
				reporteForm.getCodigoEmpresa());
		if (resultado != null && resultado.size() > 0) {
			EmpresaComercializadora empresa = (EmpresaComercializadora) resultado
					.get(0);
			descripcion = empresa.getDescripcion();
			params.put("superiorIzquierda", descripcion);
		}

		/* Obteniendo Instructora */
		RegionCursoCapacitacion region = new RegionCursoCapacitacion();
		List listaRegion = obtenerListaRegion(reporteForm, region);
		if (listaRegion != null && listaRegion.size() > 0) {
			region = (RegionCursoCapacitacion) listaRegion.get(0);
		}
		String nombreInstructora = region.getDescripcionInstructora();

		/* Colocando valores al Reporte */
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		params.put("codigoUsuario", usuario.getLogin());
		params.put("NroReporte", "");
		params.put("titulo",
				getResourceMessage("reporteEDUCronogramaDictadoForm.titulo")
						+ region.getDescripcionRegion());
		params.put("codigoInstructora", region.getCodigoInstructora());
		params.put("nombreInstructora", nombreInstructora);
		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("codigoEmpresa", reporteForm.getCodigoEmpresa());
		params.put("codigoRegion", reporteForm.getCodigoRegion());
		params.put("codigoZona", reporteForm.getCodigoZona());
		params.put("campanhaProceso", reporteForm.getCampanhaProceso());
		String campanhaProcesoMostrar = reporteForm.getCampanhaProceso();
		campanhaProcesoMostrar = campanhaProcesoMostrar.substring(0, 4) + "-"
				+ campanhaProcesoMostrar.substring(4);
		params.put("campanhaProcesoMostrar", campanhaProcesoMostrar);

		/* Pasando subreportes */
		String subReporteDir1, subReporteDir2;
		subReporteDir1 = "subreporteEDUCronogramaDictadoCurso";
		subReporteDir2 = "subreporteEDUCronogramaDictadoLocal";

		ClassPathResource resource = new ClassPathResource(Constants.JASPER_DIRECTORIO + subReporteDir1
				+ JASPER_EXTENSION);
		ClassPathResource resource1 = new ClassPathResource(Constants.JASPER_DIRECTORIO + subReporteDir2
				+ JASPER_EXTENSION);

		params.put("SUBREPORT_DIR1",
				(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource.getPath() )));
		params.put("SUBREPORT_DIR2",
				(JasperReport) JRLoader.loadObject(this.getClass().getClassLoader().getResource(resource1.getPath() )));

		return params;
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {

		if (val != null) {
			String region = (String) val.getNewValue();
			ReporteEDUCronogramaDictadoForm f = (ReporteEDUCronogramaDictadoForm) this.formReporte;
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");

			setEduParametrosZonaList(ajaxService
					.getZonaByPaisEmpresaRegion(this.mPantallaPrincipalBean
							.getCurrentCountry().getCodigo(), f
							.getCodigoEmpresa(), region));
		}else{
			setEduParametrosZonaList(null);
		}

	}

	/**
	 * Obtiene Lista de Empresas
	 * 
	 * @param request
	 * @param codigoPais
	 * @param codigoEmpresa
	 * @return
	 */
	private List obtenerListaEmpresa(String codigoPais, String codigoEmpresa) {
		EmpresaComercializadora parametroEmpresa = new EmpresaComercializadora();
		parametroEmpresa.setCodigoPais(codigoPais);
		parametroEmpresa.setCodigoEmpresa(codigoEmpresa);
		parametroEmpresa.setEstadoRegistro(Constants.ESTADO_ACTIVO);
		MantenimientoEDUGenericoService siccService = (MantenimientoEDUGenericoService) getBean("edu.mantenimientoEDUGenericoService");
		List resultado = siccService
				.getEmpresasComercializadorasByPais(parametroEmpresa);
		return resultado;
	}

	/**
	 * Obtiene Lista de Regiones
	 * 
	 * @param reporteForm
	 * @param region
	 * @return
	 */
	private List obtenerListaRegion(
			ReporteEDUCronogramaDictadoForm reporteForm,
			RegionCursoCapacitacion region) {
		region.setCodigoPais(reporteForm.getCodigoPais());
		region.setCodigoEmpresa(reporteForm.getCodigoEmpresa());
		region.setCodigoRegion(reporteForm.getCodigoRegion());
		MantenimientoEDUCursoCapacitacionService cursoService = (MantenimientoEDUCursoCapacitacionService) getBean("edu.mantenimientoEDUCursoCapacitacionService");

		List listaRegion = cursoService.getRegion(region);
		return listaRegion;
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

}
