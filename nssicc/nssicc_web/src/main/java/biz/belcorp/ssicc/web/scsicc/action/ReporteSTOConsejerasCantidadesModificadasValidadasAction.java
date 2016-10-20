package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOLevantamientoErroresValidacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTOConsejerasCantidadesModificadasValidadasForm;

@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes","unchecked" })
public class ReporteSTOConsejerasCantidadesModificadasValidadasAction extends
		BaseReporteAbstractAction implements Serializable {

	private static final long serialVersionUID = -2128645586330911891L;
	private String formatoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private List STO_HORAS_CARGA_LIST = new ArrayList();
	private List STO_HORAS_PROCESO_LIST = new ArrayList();

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#setViewAtributes()
	 */
	@Override
	protected void setViewAtributes() throws Exception {
		log.debug("Executing action : setViewAttributes.");
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Map criteriaOperacion = new HashMap();
		criteriaOperacion.put("codigoPais", pais.getCodigo());
		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoUsuario", usuario.getLogin());

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		ProcesoSTOLevantamientoErroresValidacionService procesoSTOLevantamientoErroresValidacionService = (ProcesoSTOLevantamientoErroresValidacionService) getBean("spusicc.procesoSTOLevantamientoErroresValidacionService");

		// Carga la lista de Regiones
		List listaRegiones = new ArrayList();
		listaRegiones = reporteService.getListaGenerico("getRegionesByPais",
				criteria);
		this.siccRegionList = new LabelValue[listaRegiones.size()];
		int z = 0;
		for (Object object : listaRegiones) {
			LabelValue labelValue = new LabelValue();
			labelValue.setLabel(((Base) object).getDescripcion());
			labelValue.setValue(((Base) object).getCodigo());
			this.getSiccRegionList()[z] = labelValue;
			z++;
		}
		ReporteSTOConsejerasCantidadesModificadasValidadasForm form = (ReporteSTOConsejerasCantidadesModificadasValidadasForm) this.formReporte;
		criteria.put("codigoParametro", Constants.STO_INTERVALO_CARGA_STO);
		List listaHorasCarga = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		STO_HORAS_CARGA_LIST = listaHorasCarga;
		criteria.put("codigoParametro", Constants.STO_INTERVALO_PROCESO_STO);
		List listaHorasProceso = procesoSTOLevantamientoErroresValidacionService
				.getListaHoras(criteria);
		STO_HORAS_PROCESO_LIST = listaHorasProceso;
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaInicio = new Date(System.currentTimeMillis());
        Date fechaFin = new Date(System.currentTimeMillis());
	             
		form.setFechaInicio(sdf.format(fechaInicio));
		form.setFechaInicioProcesoD(fechaInicio);
		try {
			fechaFin= DateUtil.addToDate(fechaFin, Calendar.DATE, 1);
		} catch (Exception e) {
			fechaFin = new Date(System.currentTimeMillis());
		}		
		form.setFechaFinProceso(sdf.format(fechaFin));		
		form.setFechaFinProcesoD(fechaFin);
		form.setHoraInicioProceso("00:00");
		form.setHoraFinProceso("00:00");
		form.setCodigoPais(pais.getCodigo());
		
		this.siccZonaList = new LabelValue[1];
		LabelValue labelValue = new LabelValue();
		labelValue.setLabel("Todos");
		labelValue.setValue("");
		this.siccZonaList[0] = labelValue;
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveFormReporte()
	 */
	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOConsejerasCantidadesModificadasValidadasForm form = new ReporteSTOConsejerasCantidadesModificadasValidadasForm();
		return form; 
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreReporte()
	 */
	@Override
	protected String devuelveNombreReporte() throws Exception {
		if ("XLS".equals(formatoReporte))
			return "reporteSTOConsejerasCantidadesModificadasValidadasXLS";
		else
			return "reporteMaestroHorizontal";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#devuelveNombreSubReporte()
	 */
	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	/* (non-Javadoc)
	 * @see biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction#prepareParameterMap(java.util.Map)
	 */
	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		ReporteSTOConsejerasCantidadesModificadasValidadasForm reporteSTOForm = (ReporteSTOConsejerasCantidadesModificadasValidadasForm) this.formReporte;
		formatoReporte = reporteSTOForm.getFormatoExportacion();
		
		String fecha1 = DateUtil.getDate(reporteSTOForm.getFechaInicioD());
 		String fecha2 = DateUtil.getDate(reporteSTOForm.getFechaFinD());
 		String fecha3 = DateUtil.getDate(reporteSTOForm.getFechaInicioProcesoD());
 		String fecha4 = DateUtil.getDate(reporteSTOForm.getFechaFinProcesoD());
 		
 		reporteSTOForm.setFechaInicio(fecha1);
 		reporteSTOForm.setFechaFin(fecha2);
 		reporteSTOForm.setFechaInicioProceso(fecha3);
 		reporteSTOForm.setFechaFinProceso(fecha4);
		
		String condicionZonas = obtieneCondicion(reporteSTOForm.getZonaList(),
				"S.COD_ZONA", "'");
		String condicionRegion = obtieneCondicion(
				reporteSTOForm.getRegionList(), "S.COD_REGI", "'");

		Map criteria = params;

		criteria.put("codigoPeriodo", reporteSTOForm.getCodigoPeriodo());

		String fechaInicio = reporteSTOForm.getFechaInicio() + " "
				+ reporteSTOForm.getHoraInicioCarga();
		String fechaFin = reporteSTOForm.getFechaFin() + " "
				+ reporteSTOForm.getHoraFinCarga();
		String fechaInicioProceso = reporteSTOForm.getFechaInicioProceso()
				+ " " + reporteSTOForm.getHoraInicioProceso();
		String fechaFinProceso = reporteSTOForm.getFechaFinProceso() + " "
				+ reporteSTOForm.getHoraFinProceso();

		if (fechaInicio.compareToIgnoreCase("") == 0)
			fechaInicio = null;
		else
			fechaInicio = fechaInicio.trim();

		if (fechaFin.compareToIgnoreCase("") == 0)
			fechaFin = null;
		else
			fechaFin = fechaFin.trim();

		if (fechaInicioProceso.compareToIgnoreCase("") == 0)
			fechaInicioProceso = null;
		else
			fechaInicioProceso = fechaInicioProceso.trim();

		if (fechaFinProceso.compareToIgnoreCase("") == 0)
			fechaFinProceso = null;
		else
			fechaFinProceso = fechaFinProceso.trim();

		String codigoPeriodo = reporteSTOForm.getCodigoPeriodo();
		if (codigoPeriodo.compareToIgnoreCase("") == 0)
			codigoPeriodo = null;
		String codigoCliente = reporteSTOForm.getCodigoCliente();
		if (codigoCliente.compareToIgnoreCase("") == 0)
			codigoCliente = null;

		String numLote = reporteSTOForm.getNumeroLote();
		if (numLote.compareToIgnoreCase("") == 0)
			numLote = null;

		params.put("codigoPeriodo", codigoPeriodo);
		params.put("numLote", numLote);
		params.put("codigoCliente", codigoCliente);
		params.put("condicionZonas", condicionZonas);
		params.put("condicionRegion", condicionRegion);
		params.put("fechaInicio", fechaInicio);
		params.put("fechaFin", fechaFin);
		params.put("fechaFinProceso", fechaFinProceso);
		params.put("fechaInicioProceso", fechaInicioProceso);
		params.put("NroReporte", "");
		params.put(
				"titulo",
				getReportResourceMessage("reporteSTOConsejerasCantidadesModificadasValidadasForm.title"));
		return params;
	}

	/**
	 * Show zonasx region.
	 * 
	 * @param val
	 *            the val
	 */
	public void showZonasxRegion(ValueChangeEvent val) {
		log.debug(">>showZonasxRegion ");
		log.debug("val: " + val.getNewValue().toString());
		ReporteSTOConsejerasCantidadesModificadasValidadasForm form = (ReporteSTOConsejerasCantidadesModificadasValidadasForm) this.formReporte;
		String[] regiones = (String[]) val.getNewValue();
		if (!val.equals(null) && regiones.length > 0) {
			AjaxService aSvc = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(aSvc.getZonasMultipleByPaisMarcaCanalRegion(
					form.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT,
					Constants.CODIGO_CANAL_DEFAULT, regiones,
					Constants.FORMATO_TOTAL));
			form.setZonaList(null);
		} else {
			this.siccZonaList = null;
			form.setZonaList(null);
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
	 * @return the sTO_HORAS_CARGA_LIST
	 */
	public List getSTO_HORAS_CARGA_LIST() {
		return STO_HORAS_CARGA_LIST;
	}

	/**
	 * @param sTO_HORAS_CARGA_LIST the sTO_HORAS_CARGA_LIST to set
	 */
	public void setSTO_HORAS_CARGA_LIST(List sTO_HORAS_CARGA_LIST) {
		STO_HORAS_CARGA_LIST = sTO_HORAS_CARGA_LIST;
	}

	/**
	 * @return the sTO_HORAS_PROCESO_LIST
	 */
	public List getSTO_HORAS_PROCESO_LIST() {
		return STO_HORAS_PROCESO_LIST;
	}

	/**
	 * @param sTO_HORAS_PROCESO_LIST the sTO_HORAS_PROCESO_LIST to set
	 */
	public void setSTO_HORAS_PROCESO_LIST(List sTO_HORAS_PROCESO_LIST) {
		STO_HORAS_PROCESO_LIST = sTO_HORAS_PROCESO_LIST;
	}
}