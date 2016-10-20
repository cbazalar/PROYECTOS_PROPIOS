package biz.belcorp.ssicc.web.scsicc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.ClassPathResource;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteINCConsolidadoPremioDespachadoForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteINCConsolidadoPremioDespachadoAction extends
		BaseReporteAbstractAction {

	private static final long serialVersionUID = -1404210653478550884L;

	private List listaConcurso;
	private List listaMarca;
	private List listaCanal;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteINCConsolidadoPremioDespachadoForm reporteForm = new ReporteINCConsolidadoPremioDespachadoForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreReporte()
	 */
	protected String devuelveNombreReporte() throws Exception {
		if (this.formatoExportacion.equals("XLS")) {
			return "reporteINCConsolidadoPremioDespachadoXLS";
		}
		return "reporteMaestroVertical";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveNombreSubReporte()
	 */
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteINCConsolidadoPremioDespachadoPDF";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanConstructorService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteINCConsolidadoPremioDespachadoService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanMailService()
	 */
	protected String devuelveBeanMailService() {
		return "scsicc.mailReporteGenerarConsolidadoPremioDespachadoService";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (this.log.isDebugEnabled()) {
			this.log.debug("Entering 'ReporteINCConsolidadoPremioDespachadoAction.setViewAtributes' method");
		}

		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		criteriaOperacion.put("codigoMarca", Constants.CODIGO_MARCA_DEFAULT);
		criteriaOperacion.put("codigoCanal", Constants.CODIGO_CANAL_DEFAULT);

		ReporteService reporteService = (ReporteService) this
				.getBeanService("scsicc.reporteService");

		this.listaMarca = (List) reporteService.getMarcas();
		this.listaCanal = (List) reporteService
				.getCanalesByCodigoISO(this.mPantallaPrincipalBean
						.getCurrentUser().getIdioma().getCodigoISO());
		this.listaConcurso = (List) reporteService.getListaGenerico(
				"getConcursosVigentesCerradosByPaisMarcaCanal",
				criteriaOperacion);

		this.mostrarReporteXLS = true;
		this.mostrarReporteMailXLS = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'ReporteINCConsolidadoPremioDespachadoAction.prepareParameterMap' method");
		}
		ReporteINCConsolidadoPremioDespachadoForm form = (ReporteINCConsolidadoPremioDespachadoForm) this.formReporte;
		form.setFormatoExportacion(this.formatoExportacion);
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		ClassPathResource resource = new ClassPathResource(
				Constants.JASPER_DIRECTORIO
						+ "subReporteINCConsolidadoPremioDespachados"
						+ JASPER_EXTENSION);
		ClassPathResource resource1 = new ClassPathResource(
				Constants.JASPER_DIRECTORIO
						+ "subReporteINCConsolidadoPremioPendiente"
						+ JASPER_EXTENSION);

		params.put(
				"SUBREPORT_DIR1",
				(JasperReport) JRLoader.loadObject(this.getClass()
						.getClassLoader().getResource(resource.getPath())));
		params.put(
				"SUBREPORT_DIR2",
				(JasperReport) JRLoader.loadObject(this.getClass()
						.getClassLoader().getResource(resource1.getPath())));

		if (contiene(form.getListConcursos(), "Todos")) {
			form.setConcursos("");
			form.setSoloActivos("and vc.vico_oid_vige_conc in (1,6)");
			params.put("concursos", "");
			params.put("soloActivos", "and vc.vico_oid_vige_conc in (1,6)");
		} else if (contiene(form.getListConcursos(), "Solo Activos")) {
			form.setConcursos("");
			form.setSoloActivos("and vc.vico_oid_vige_conc in (1) and cpg.ind_acti =1");
			params.put("concursos", "");
			params.put("soloActivos",
					"and vc.vico_oid_vige_conc in (1) and cpg.ind_acti =1");
		} else {
			form.setConcursos(obtieneCondicion(form.getListConcursos(),
					"cpg.oid_para_gral", "'"));
			form.setSoloActivos("and vc.vico_oid_vige_conc in (1,6)");
			params.put(
					"concursos",
					obtieneCondicion(form.getListConcursos(),
							"cpg.oid_para_gral", "'"));
			params.put("soloActivos", "and vc.vico_oid_vige_conc in (1,6)");
		}

		Map criteria = new HashMap();
		criteria.put("codigoPais", this.mPantallaPrincipalBean
				.getCurrentCountry().getCodigo());
		form.setOidPais(reporteService.getOidString("getOidPaisByCodigoPais",
				criteria));
		params.put("oidPais", form.getOidPais());

		if ((form.getCodigoMarca() != null)
				&& (!form.getCodigoMarca().equals("Todos"))) {
			criteria.put("codigoMarca", form.getCodigoMarca());
			params.put(
					"oidMarca",
					"and cpg.marc_oid_marc ="
							+ reporteService.getOidString(
									"getOidMarcaByCodigoMarca", criteria));
		} else {
			params.put("oidMarca", "");
		}

		if ((form.getCodigoCanal() != null)
				&& (!form.getCodigoCanal().equals("Todos"))) {
			criteria.put("codigoCanal", form.getCodigoCanal());
			params.put(
					"oidCanal",
					"and cpg.cana_oid_cana ="
							+ reporteService.getOidString(
									"getOidCanalByCodigoCanal", criteria));
		} else {
			params.put("oidCanal", "");
		}

		form.setTitulo(this
				.getReportResourceMessage("reporteINCConsolidadoPremioDespachadoForm.title"));
		params.put("titulo", form.getTitulo());

		return params;

	}

	/**
	 * Metodo que retorna el valor contenido en una lista
	 * 
	 * @param listConcursos
	 * @param string
	 * @return
	 */
	private boolean contiene(String[] lista, String dato) {
		for (int i = 0; i < lista.length; i++) {
			if (StringUtils.equals(lista[i], dato))
				return true;
		}
		return false;
	}

	/**
	 * @return the listaConcurso
	 */
	public List getListaConcurso() {
		return listaConcurso;
	}

	/**
	 * @param listaConcurso
	 *            the listaConcurso to set
	 */
	public void setListaConcurso(List listaConcurso) {
		this.listaConcurso = listaConcurso;
	}

	/**
	 * @return the listaMarca
	 */
	public List getListaMarca() {
		return listaMarca;
	}

	/**
	 * @param listaMarca
	 *            the listaMarca to set
	 */
	public void setListaMarca(List listaMarca) {
		this.listaMarca = listaMarca;
	}

	/**
	 * @return the listaCanal
	 */
	public List getListaCanal() {
		return listaCanal;
	}

	/**
	 * @param listaCanal
	 *            the listaCanal to set
	 */
	public void setListaCanal(List listaCanal) {
		this.listaCanal = listaCanal;
	}

	public String getFormatoExportacion() {
		return formatoExportacion;
	}

	public void setFormatoExportacion(String formatoExportacion) {
		this.formatoExportacion = formatoExportacion;
	}

}
