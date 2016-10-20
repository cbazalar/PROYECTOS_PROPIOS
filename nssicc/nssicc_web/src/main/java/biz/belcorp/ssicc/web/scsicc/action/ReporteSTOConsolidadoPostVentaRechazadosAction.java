package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOConsolidadoPostVentaRechazadosForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTOConsolidadoPostVentaRechazadosAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = 3884970801334242434L;
	private String formatoReporte;
	private String tipoReporte;
	private List siccMarcaList;
	private List siccOperacionesList;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private String STO_TIPO_DOCUMENTO_CABECERA;
	private String STO_TIPO_DOCUMENTO_DETALLE;

	/**
	 * Cargar Zonas
	 * 
	 * @param val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		try {
			ReporteSTOConsolidadoPostVentaRechazadosForm form = (ReporteSTOConsolidadoPostVentaRechazadosForm) this.formReporte;
			String[] regiones = (String[]) val.getNewValue();
			if (!val.equals(null) && regiones.length > 0) {
				AjaxService aSvc = (AjaxService) getBean("ajaxService");
				this.setSiccZonaList(aSvc
						.getZonasMultipleByPaisMarcaCanalRegion(
								form.getCodigoPais(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, regiones,
								Constants.FORMATO_TOTAL));
				form.setZonaList(null);
			} else {
				this.siccZonaList = null;
				form.setZonaList(null);
			}
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveBeanReporteService()
	 */
	protected String devuelveBeanReporteService() {
		return "reportes.reporteSTOConsolidadoPostVentaRechazadosService";
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
		ReporteSTOConsolidadoPostVentaRechazadosForm form = new ReporteSTOConsolidadoPostVentaRechazadosForm();
		return form;
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
		if (this.tipoReporte.equals(Constants.STO_TIPO_REPORTE_CABECERA))
			if ("XLS".equals(formatoReporte))
				return "reporteSTOConsolidadoPostVentaRechazadosCabeceraXLS";

		if (this.tipoReporte.equals(Constants.STO_TIPO_REPORTE_DETALLE))
			if ("XLS".equals(formatoReporte))
				return "reporteSTOConsolidadoPostVentaRechazadosDetalleXLS";

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
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #setValidarReporte()
	 */
	@Override
	public String setValidarReporte() {
		ReporteSTOConsolidadoPostVentaRechazadosForm form = (ReporteSTOConsolidadoPostVentaRechazadosForm) this.formReporte;
		String mensaje = null;
		if (form.getCodigoPeriodoInicial().length() > 0
				&& form.getCodigoPeriodoFinal().length() > 0) {
			int codperini = Integer.parseInt(form.getCodigoPeriodoInicial());
			int codperfin = Integer.parseInt(form.getCodigoPeriodoFinal());
			if (codperfin < codperini) {
				mensaje = "La Campaña Inicial debe ser menor o igual a la Campaña Final";
			}
		}
		return mensaje;
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
		ReporteSTOConsolidadoPostVentaRechazadosForm reporteSTOForm = (ReporteSTOConsolidadoPostVentaRechazadosForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		String fecha1, fecha2;
		fecha1 = DateUtil.getDate(reporteSTOForm.getFechaInicioD());
		fecha2 = DateUtil.getDate(reporteSTOForm.getFechaFinD());
		reporteSTOForm.setFechaInicio(fecha1);
		reporteSTOForm.setFechaFin(fecha2);
		
		String descripcionRegionList = descripcionMultipleLista(reporteSTOForm.getRegionList(), this.siccRegionList);
		String descripcionZonaList = descripcionMultipleLista(reporteSTOForm.getZonaList(), this.siccZonaList);
		String descripcionCodOperacionList = descripcionMultipleLista(reporteSTOForm.getCodOperacionList(), this.siccOperacionesList);
		if(descripcionRegionList.equals("")){
			descripcionRegionList="Todos";
		}
		if(descripcionZonaList.equals("")){
			descripcionZonaList="Todos";
		}
		if(descripcionCodOperacionList.equals("")){
			descripcionCodOperacionList="Todos";
		}
		
		reporteSTOForm.setDescripcionCodOperacionList(descripcionCodOperacionList);
		reporteSTOForm.setDescripcionRegionList(descripcionRegionList);
		reporteSTOForm.setDescripcionZonaList(descripcionZonaList);
		
		params.put("descripcionRegionList", descripcionRegionList);
		params.put("descripcionZonaList", descripcionZonaList);
		params.put("descripcionCodOperacionList", descripcionCodOperacionList);
		
		String[] limpia = {};
		if (reporteSTOForm.getZonaList().length > 0) {
			if(reporteSTOForm.getZonaList()[0].equals("")){
				reporteSTOForm.setZonaList(limpia);
				String valor = "Todos";
				String[] arreglo = new String[1];
				arreglo[0] = valor;
				reporteSTOForm.setZonaList(arreglo);
			}			
		}
		this.formatoReporte = reporteSTOForm.getFormatoExportacion();
		this.tipoReporte = reporteSTOForm.getTipoReporte();
		boolean valor1 = reporteSTOForm.getRegionList() == null;
		boolean valor2 = StringUtils.equals(StringUtils.substring(reporteSTOForm.getDescripcionRegionList(), 0, 5),"Todos");


		// -- Saco listas --------------------------------------------
		params.put(
				"regionList",
				(valor1 || valor2 ? new ArrayList() : Arrays.asList(reporteSTOForm.getRegionList())));

		params.put(
				"zonaList",
				(reporteSTOForm.getZonaList() == null || StringUtils.equals(
						StringUtils.substring(
								reporteSTOForm.getDescripcionZonaList(), 0, 5),
						"Todos")) ? new ArrayList() : Arrays
						.asList(reporteSTOForm.getZonaList()));

		params.put(
				"codOperacionList",
				(reporteSTOForm.getCodOperacionList() == null || StringUtils.equals(
						StringUtils.substring(
								reporteSTOForm.getDescripcionCodOperacionList(),
								0, 5), "Todos")) ? new ArrayList() : Arrays
						.asList(reporteSTOForm.getCodOperacionList()));

		// -- Saco oid periodos --------------------------------------
		params.put("codigoPeriodoInicial",
				reporteSTOForm.getCodigoPeriodoInicial());
		if (!("".equals(reporteSTOForm.getCodigoPeriodoInicial().trim()))) {

			Map criteria = null;
			int oid_periodo = 0;

			criteria = new HashMap();
			criteria.put("codigoPeriodo",
					reporteSTOForm.getCodigoPeriodoInicial());
			oid_periodo = reporteService.getOidPeriodo(criteria);
			params.put("oidPeriodoInicial", oid_periodo);

			criteria = new HashMap();
			criteria.put("codigoPeriodo",
					reporteSTOForm.getCodigoPeriodoFinal());
			oid_periodo = reporteService.getOidPeriodo(criteria);
			params.put("oidPeriodoFinal", oid_periodo);

		}

		// -- saco otros ---------------------------------------------
		params.put("numLote", reporteSTOForm.getNumeroLote());
		params.put("fechaInicio", reporteSTOForm.getFechaInicio());
		params.put("fechaFin", reporteSTOForm.getFechaFin());
		params.put("fechaProceso", reporteSTOForm.getFechaProceso());
		return params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Map criteriaOperacion = new HashMap();

		criteriaOperacion.put("codigoPais", pais.getCodigo());

		this.siccMarcaList = reporteService.getListaGenerico("getMarcaProdu",
				criteriaOperacion);
		this.siccOperacionesList = interfazSiCCService
				.getOperacionesByCodigoPais(criteriaOperacion);

		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteriaOperacion);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		ReporteSTOConsolidadoPostVentaRechazadosForm reporteSTOForm = (ReporteSTOConsolidadoPostVentaRechazadosForm) this.formReporte;
		reporteSTOForm.setCodigoPais(pais.getCodigo());

		this.STO_TIPO_DOCUMENTO_CABECERA = Constants.STO_TIPO_REPORTE_CABECERA;
		this.STO_TIPO_DOCUMENTO_DETALLE = Constants.STO_TIPO_REPORTE_DETALLE;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String periodo = sdf.format(new Date(System.currentTimeMillis()));
		// periodoActual = this.mPantallaPrincipalBean.getAnyoActualperiodo();
		reporteSTOForm.setCodigoPeriodoInicial(periodo);
		if (StringUtils.isEmpty(reporteSTOForm.getCodigoPeriodoInicial()))
			reporteSTOForm.setCodigoPeriodoInicial(periodo);
		reporteSTOForm.setCodigoPeriodoFinal(periodo);
		if (StringUtils.isEmpty(reporteSTOForm.getCodigoPeriodoFinal()))
			reporteSTOForm.setCodigoPeriodoFinal(periodo);
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
	 * @return the tipoReporte
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 *            the tipoReporte to set
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
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
	 * @return the siccOperacionesList
	 */
	public List getSiccOperacionesList() {
		return siccOperacionesList;
	}

	/**
	 * @param siccOperacionesList
	 *            the siccOperacionesList to set
	 */
	public void setSiccOperacionesList(List siccOperacionesList) {
		this.siccOperacionesList = siccOperacionesList;
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
	 * @return the sTO_TIPO_DOCUMENTO_CABECERA
	 */
	public String getSTO_TIPO_DOCUMENTO_CABECERA() {
		return STO_TIPO_DOCUMENTO_CABECERA;
	}

	/**
	 * @param sTO_TIPO_DOCUMENTO_CABECERA
	 *            the sTO_TIPO_DOCUMENTO_CABECERA to set
	 */
	public void setSTO_TIPO_DOCUMENTO_CABECERA(
			String sTO_TIPO_DOCUMENTO_CABECERA) {
		STO_TIPO_DOCUMENTO_CABECERA = sTO_TIPO_DOCUMENTO_CABECERA;
	}

	/**
	 * @return the sTO_TIPO_DOCUMENTO_DETALLE
	 */
	public String getSTO_TIPO_DOCUMENTO_DETALLE() {
		return STO_TIPO_DOCUMENTO_DETALLE;
	}

	/**
	 * @param sTO_TIPO_DOCUMENTO_DETALLE
	 *            the sTO_TIPO_DOCUMENTO_DETALLE to set
	 */
	public void setSTO_TIPO_DOCUMENTO_DETALLE(String sTO_TIPO_DOCUMENTO_DETALLE) {
		STO_TIPO_DOCUMENTO_DETALLE = sTO_TIPO_DOCUMENTO_DETALLE;
	}
}