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
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteRECListadoRecAreasForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteRECListadoRecAreasAction extends BaseReporteAbstractAction {

	private static final long serialVersionUID = 8917347265349778332L;
	private String formatoReporte;
	private String operacion;
	private List operaciones;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private LabelValue[] siccOperacionesList = {};
	private LabelValue[] siccTipoOperacionList = {};

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteRECListadoRecAreasForm reporteForm = new ReporteRECListadoRecAreasForm();
		return reporteForm;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		String codpais = pais.getCodigo();
		criteriaOperacion.put("codigoPais", codpais);

		this.operaciones = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int i = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[i] = labelValue;
			i++;
		}

		List listaOperaciones = new ArrayList();
		listaOperaciones = this.operaciones;
		this.siccOperacionesList = new LabelValue[listaOperaciones.size()];
		int j = 0;
		for (Object object : listaOperaciones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccOperacionesList()[j] = labelValue;
			j++;
		}
		ReporteRECListadoRecAreasForm form = (ReporteRECListadoRecAreasForm) this.formReporte;
		form.setOidIdiomaIso(usuario.getIdioma().getCodigo());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	public String setValidarReporte() {
		ReporteRECListadoRecAreasForm form = (ReporteRECListadoRecAreasForm) this.formReporte;
		int codperini = Integer.parseInt(form.getCodigoPeriodoInicial());
		int codperfin = Integer.parseInt(form.getCodigoPeriodoFinal());
		if (codperfin < codperini) {
			String mensaje = this
					.getResourceMessage("reporteRECIndFactTransaccionesForm.errorInicioMayor");
			return mensaje;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReporteRECListadoRecAreasForm reporteRECForm = (ReporteRECListadoRecAreasForm) this.formReporte;
		String descripcionRegionList = null;
		String descripcionZonaList = null;
		String descripcionOperacionList = null;
		String descripcionTipoOperacionList = null;

		if (reporteRECForm.getRegionList().length > 0) {
			descripcionRegionList = descripcionMultipleLista(
					reporteRECForm.getRegionList(), this.siccRegionList);

		}
		if (reporteRECForm.getZonaList().length > 0) {
			descripcionZonaList = descripcionMultipleLista(
					reporteRECForm.getZonaList(), this.siccZonaList);

		}
		if (reporteRECForm.getOperacionList().length > 0) {
			descripcionOperacionList = descripcionMultipleLista(
					reporteRECForm.getOperacionList(), this.siccOperacionesList);

		}
		if (reporteRECForm.getOperacionList().length > 0) {
			descripcionTipoOperacionList = descripcionMultipleLista(
					reporteRECForm.getTipoOperacionList(),
					this.siccTipoOperacionList);

		}

		String[] limpia = {};
		if (reporteRECForm.getZonaList().length > 0) {
			if (reporteRECForm.getZonaList()[0].equals("")) {
				reporteRECForm.setZonaList(limpia);
				String valor = "Todos";
				String[] arreglo = new String[1];
				arreglo[0] = valor;
				reporteRECForm.setZonaList(arreglo);
			}
		}

		if (reporteRECForm.getOperacionList().length > 0) {
			if (reporteRECForm.getOperacionList()[0].equals("")) {
				reporteRECForm.setOperacionList(limpia);
				String valor = "Todos";
				String[] arreglo = new String[1];
				arreglo[0] = valor;
				reporteRECForm.setOperacionList(arreglo);
			}
		}

		if (reporteRECForm.getRegionList().length > 0) {
			if (reporteRECForm.getRegionList()[0].equals("")) {
				reporteRECForm.setRegionList(limpia);
				String valor = "Todos";
				String[] arreglo = new String[1];
				arreglo[0] = valor;
				reporteRECForm.setOperacionList(arreglo);
			}
		}

		if (reporteRECForm.getTipoOperacionList().length > 0) {
			if (reporteRECForm.getTipoOperacionList()[0].equals("")) {
				reporteRECForm.setTipoOperacionList(limpia);
				String valor = "Todos";
				String[] arreglo = new String[1];
				arreglo[0] = valor;
				reporteRECForm.setTipoOperacionList(arreglo);
			}
		}

		if (StringUtils.isBlank(descripcionRegionList)) {
			descripcionRegionList = "Todos";

		}
		if (StringUtils.isBlank(descripcionZonaList)) {
			descripcionZonaList = "Todos";
		}
		if (StringUtils.isBlank(descripcionOperacionList)) {
			descripcionOperacionList = "Todos";
		}
		if (StringUtils.isBlank(descripcionTipoOperacionList)) {
			descripcionTipoOperacionList = "Todos";
		}

		reporteRECForm.setDescripcionOperacionList(descripcionOperacionList);
		reporteRECForm.setDescripcionRegionList(descripcionRegionList);
		reporteRECForm.setDescripcionZonaList(descripcionZonaList);
		reporteRECForm
				.setDescripcionTipoOperacionList(descripcionTipoOperacionList);

		params.put("descripcionRegionList", descripcionRegionList);
		params.put("descripcionZonaList", descripcionZonaList);
		params.put("descripcionOperacionList", descripcionOperacionList);
		params.put("descripcionTipoOperacionList", descripcionTipoOperacionList);
		reporteRECForm.setFormatoExportacion(this.formatoExportacion);
		this.formatoReporte = reporteRECForm.getFormatoExportacion()
				+ reporteRECForm.getTipoReporte();

		this.operacion = this.obtieneLista(reporteRECForm.getOperacionList());
		if (!this.operacion.equals(""))
			params.put("operacion", this.operacion);
		if (StringUtils.equals(reporteRECForm.getTipoPeriodo(), "0")) {
			params.put("periodoReferenciaInicial",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoReferenciaFinal",
					reporteRECForm.getCodigoPeriodoFinal());
			params.put("periodoRegistroInicial", null);
			params.put("periodoRegistroFinal", null);
		} else {
			params.put("periodoRegistroInicial",
					reporteRECForm.getCodigoPeriodoInicial());
			params.put("periodoRegistroFinal",
					reporteRECForm.getCodigoPeriodoFinal());
			params.put("periodoReferenciaInicial", null);
			params.put("periodoReferenciaFinal", null);
		}

		params.put(
				"operacionList",
				(reporteRECForm.getOperacionList() == null || StringUtils
						.equals(StringUtils.substring(
								reporteRECForm.getDescripcionOperacionList(),
								0, 5), "Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getOperacionList()));

		params.put(
				"tipoOperacionList",
				(reporteRECForm.getTipoOperacionList() == null || StringUtils
						.equals(StringUtils.substring(reporteRECForm
								.getDescripcionTipoOperacionList(), 0, 5),
								"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getTipoOperacionList()));

		params.put(
				"zonaList",
				(reporteRECForm.getZonaList() == null || StringUtils.equals(
						StringUtils.substring(
								reporteRECForm.getDescripcionZonaList(), 0, 5),
						"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getZonaList()));
		params.put(
				"regionList",
				(reporteRECForm.getRegionList() == null || StringUtils.equals(
						StringUtils
								.substring(reporteRECForm
										.getDescripcionRegionList(), 0, 5),
						"Todos")) ? new ArrayList() : Arrays
						.asList(reporteRECForm.getRegionList()));

		params.put(
				"NroReporte",
				getReportResourceMessage("reporteRECListadoRecAreasForm.numero.reporte"));
		params.put("titulo",
				getReportResourceMessage("reporteRECListadoRecAreasForm.title"));
		this.setGenerateTabsXLS(false);
		return params;

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
		ReporteRECListadoRecAreasForm form = (ReporteRECListadoRecAreasForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS0".equals(formatoReporte))
			return "reporteRECListadoRecAreasxPeriodoXLS";
		else if ("XLS1".equals(formatoReporte))
			return "reporteRECListadoRecAreasxZonaXLS";
		else
			return "reporteMaestroHorizontal";

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
		ReporteRECListadoRecAreasForm form = (ReporteRECListadoRecAreasForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("PDF0".equals(this.formReporte.getFormatoExportacion()))
			return "reporteRECListadoRecAreasxPeriodoPDF";
		else if ("PDF1".equals(formatoReporte))
			return "reporteRECListadoRecAreasxZonaPDF";
		else
			return "reporteRECListadoRecAreasxZonaPDF";

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteRECListadoRecAreasService";
	}

	/**
	 * Metodo para obtener Lista de Zonas
	 * 
	 * @param val
	 */

	/**
	 * @param val
	 */
	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
		try {
			String[] valor = (String[]) val.getNewValue();
			if (valor.length > 0) {
				AjaxService ajax = (AjaxService) getBean("ajaxService");

				this.siccZonaList = ajax
						.getZonasMultipleByPaisMarcaCanalRegion(
								this.mPantallaPrincipalBean.getCurrentCountry()
										.getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor,
								Constants.FORMATEAR_TODOS);

			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	/**
	 * Metodo para obtener Lista de Tipo Operaciones
	 * 
	 * @param val
	 */
	public void loadTipoOperaciones(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoOperaciones");
		}
		try {
			String[] valor = (String[]) val.getNewValue();
			ArrayList valorArray = new ArrayList<String>(Arrays.asList(valor));

			if (valorArray.size() > 0) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				setSiccTipoOperacionList(ajaxService
						.getTiposOperaByOperaDesList(this
								.getmPantallaPrincipalBean()
								.getCurrentCountry().getCodigo(), valorArray,
								Constants.OPCION_TODOS));
			} else {
				setSiccTipoOperacionList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

	}

	// public void loadTipoOperacion(ValueChangeEvent val) {
	// if (log.isDebugEnabled()) {
	// log.debug("loadTipoOperacion");
	// }
	// String[] valor0 = (String[]) val.getNewValue();
	// String valor = valor0[0];
	//
	// if (valor.length() > 0) {
	// AjaxService ajax = (AjaxService) getBean("ajaxService");
	//
	// this.siccTipoOperacionList = ajax
	// .getTiposOperaByOperaByReporte(this.mPantallaPrincipalBean
	// .getCurrentCountry().getCodigo(), valor, "");
	// return;
	// }
	//
	// }

	/**
	 * @param lista
	 * @return
	 */
	private String obtieneLista(String[] lista) {
		if (lista == null || lista.length == 0)
			return "";
		else {
			String resultado = "";
			String dato = "";
			for (int i = 0; i < lista.length; i++) {
				for (int j = 0; j < this.operaciones.size(); j++) {
					Base item = (Base) operaciones.get(j);
					if (item.getCodigo().equals(lista[i])) {
						dato = item.getDescripcion();
						break;
					}
				}
				resultado += dato + "-";
			}
			resultado = resultado.substring(0, resultado.length() - 1);
			return resultado;
		}
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
	 * @return the operacion
	 */
	public String getOperacion() {
		return operacion;
	}

	/**
	 * @param operacion
	 *            the operacion to set
	 */
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}

	/**
	 * @return the operaciones
	 */
	public List getOperaciones() {
		return operaciones;
	}

	/**
	 * @param operaciones
	 *            the operaciones to set
	 */
	public void setOperaciones(List operaciones) {
		this.operaciones = operaciones;
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
	 * @return the siccOperacionesList
	 */
	public LabelValue[] getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 *            the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(LabelValue[] siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
	}

	/**
	 * @return the siccTipoOperacionList
	 */
	public LabelValue[] getSiccTipoOperacionList() {
		return siccTipoOperacionList;
	}

	/**
	 * @param siccTipoOperacionList
	 *            the siccTipoOperacionList to set
	 */
	public void setSiccTipoOperacionList(LabelValue[] siccTipoOperacionList) {
		this.siccTipoOperacionList = siccTipoOperacionList;
	}

}
