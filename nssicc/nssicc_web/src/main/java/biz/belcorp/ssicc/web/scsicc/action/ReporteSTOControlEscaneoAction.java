package biz.belcorp.ssicc.web.scsicc.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.sisicc.model.Base;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSTOControlEscaneoForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOEstadoEntregaOrdenTransporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteSTOControlEscaneoAction extends BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1435748424609950127L;
	private String formatoReporte;
	private List stoTipoOrdenTransporteList;
	private List stoCompaniaTransporteList;
	private List stoCentroAcopioList;
	private String tipoReporte;
	private String tipoFiltro;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private Boolean cambioTipoReporte;
	private Boolean cambioRegion;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOControlEscaneoForm reporteForm = new ReporteSTOControlEscaneoForm();

		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("ReporteSTOControlEscaneoAction - setViewAtributes");
		}

		this.mostrarReporteXLS = true;

		ReporteSTOControlEscaneoForm f = (ReporteSTOControlEscaneoForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteriaOperacion = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		InterfazSiCCService svc = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");

		// Pais pais = this.getmPantallaPrincipalBean().getCurrentCountry();
		String periodoDefecto = svc.getPeriodoDefaultByPaisCanal(
				pais.getCodigo(), Constants.CODIGO_CANAL_DEFAULT);

		// Carga periodo
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this
				.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteriaPeriodo);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		// Carga los tipos de orden
		MantenimientoSTOEstadoEntregaOrdenTransporteService service = (MantenimientoSTOEstadoEntregaOrdenTransporteService) getBean("spusicc.mantenimientoSTOEstadoEntregaOrdenTransporteService");
		stoTipoOrdenTransporteList = service.getTiposOrdenTransporte();

		// Carga las companias de transporte
		ProcesoSTOEjecucionValidacionesService procesoSTOservice = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		stoCompaniaTransporteList = procesoSTOservice.getCompaniasTransporte();

		// Carga los centros de acopio
		stoCentroAcopioList = procesoSTOservice.getCentrosAcopio();

		criteriaOperacion.put("codigoPais", pais.getCodigo());

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
		this.setCambioRegion(true);
		log.debug("Todo OK: Redireccionando");

	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteSTOControlEscaneoService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSTOControlEscaneoForm form = (ReporteSTOControlEscaneoForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteSTOCtrlEscaneo" + this.tipoFiltro + this.tipoReporte
					+ "XLS";
		else
			return "reporteMaestroVertical";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "reporteSTOCtrlEscaneo" + this.tipoFiltro + this.tipoReporte
				+ "PDF";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("prepareParameterMap...");
		}
		ReporteSTOControlEscaneoForm reporteForm = (ReporteSTOControlEscaneoForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		if (reporteForm.getTipoReporte().equals("P"))
			this.tipoReporte = "Periodo";
		if (reporteForm.getTipoReporte().equals("R"))
			this.tipoReporte = "Region";
		if (reporteForm.getTipoReporte().equals("Z")) {
			this.tipoReporte = "Zona";
			params.put("condicionesZonas", this.obtieneCondicion(
					reporteForm.getZonaList(), "xx.COD_ZONA", "'"));
		}
		if (reporteForm.getTipoReporte().equals("T"))
			this.tipoReporte = "CiaTrans";
		if (reporteForm.getTipoReporte().equals("A"))
			this.tipoReporte = "CentroAcopio";

		if (reporteForm.getCodigoTipoOrden().equals("BEP"))
			this.tipoFiltro = "Ped";
		if (reporteForm.getCodigoTipoOrden().equals("BER"))
			this.tipoFiltro = "PV";

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("codigoPeriodo", reporteForm.getCodigoPeriodo());
		params.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", params));
	
		params.put("codigoRegion", reporteForm.getCodigoRegion());
		params.put("tipoOrden", reporteForm.getCodigoTipoOrden());
		params.put("codigoCiaTransporte",
				reporteForm.getCodigoCompaniaTransporte());
		params.put("codigoCentroAcopio", reporteForm.getCodigoCentroAcopio());
		params.put("titulo",
				getResourceMessage("reporteSTOControlEscaneoForm.title"));
		log.info("Salio a ReporteRECIndFactDevolucionesProductoAction - prepareParameterMap");
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
		String valor = (String) val.getNewValue();
		String[] valores = new String[1];
		valores[0] = valor;
		if (valores.length > 0) {
			AjaxService ajaxService = (AjaxService) getBean("ajaxService");
			this.setSiccZonaList(ajaxService
					.getZonasMultipleByPaisMarcaCanalRegion(this
							.getmPantallaPrincipalBean().getCurrentCountry()
							.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
							Constants.CODIGO_CANAL_DEFAULT, valores,
							Constants.OPCION_TODOS));

		}
	}

	/**
	 * Metodo para Cambiar Tipo de Reporte
	 * 
	 * @param val
	 */
	public void loadTipoReporte(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadTipoReporte");
		}
		String valor = (String) val.getNewValue();
		if (valor.equals("Z")) {
			this.setCambioTipoReporte(true);
			this.setCambioRegion(false);
		} else if (valor.equals("R")) {
			this.setCambioRegion(false);
			this.setCambioTipoReporte(false);
			
		} else {
			this.setCambioTipoReporte(false);
			this.setCambioRegion(true);
			this.setSiccZonaList(null);
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
	 * @return
	 */
	public LabelValue[] getSiccRegionList() {
		return siccRegionList;
	}

	/**
	 * @param siccRegionList
	 */
	public void setSiccRegionList(LabelValue[] siccRegionList) {
		this.siccRegionList = siccRegionList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonaList() {
		return siccZonaList;
	}

	/**
	 * @param siccZonaList
	 */
	public void setSiccZonaList(LabelValue[] siccZonaList) {
		this.siccZonaList = siccZonaList;
	}

	/**
	 * @return the stoTipoOrdenTransporteList
	 */
	public List getStoTipoOrdenTransporteList() {
		return stoTipoOrdenTransporteList;
	}

	/**
	 * @param stoTipoOrdenTransporteList
	 *            the stoTipoOrdenTransporteList to set
	 */
	public void setStoTipoOrdenTransporteList(List stoTipoOrdenTransporteList) {
		this.stoTipoOrdenTransporteList = stoTipoOrdenTransporteList;
	}

	/**
	 * @return the stoCentroAcopioList
	 */
	public List getStoCentroAcopioList() {
		return stoCentroAcopioList;
	}

	/**
	 * @param stoCentroAcopioList
	 *            the stoCentroAcopioList to set
	 */
	public void setStoCentroAcopioList(List stoCentroAcopioList) {
		this.stoCentroAcopioList = stoCentroAcopioList;
	}

	/**
	 * @return the stoCompaniaTransporteList
	 */
	public List getStoCompaniaTransporteList() {
		return stoCompaniaTransporteList;
	}

	/**
	 * @param stoCompaniaTransporteList
	 *            the stoCompaniaTransporteList to set
	 */
	public void setStoCompaniaTransporteList(List stoCompaniaTransporteList) {
		this.stoCompaniaTransporteList = stoCompaniaTransporteList;
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
	 * @return the tipoFiltro
	 */
	public String getTipoFiltro() {
		return tipoFiltro;
	}

	/**
	 * @param tipoFiltro
	 *            the tipoFiltro to set
	 */
	public void setTipoFiltro(String tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	/**
	 * @return the cambioTipoReporte
	 */
	public Boolean getCambioTipoReporte() {
		return cambioTipoReporte;
	}

	/**
	 * @param cambioTipoReporte
	 *            the cambioTipoReporte to set
	 */
	public void setCambioTipoReporte(Boolean cambioTipoReporte) {
		this.cambioTipoReporte = cambioTipoReporte;
	}

	/**
	 * @return the cambioRegion
	 */
	public Boolean getCambioRegion() {
		return cambioRegion;
	}

	/**
	 * @param cambioRegion
	 *            the cambioRegion to set
	 */
	public void setCambioRegion(Boolean cambioRegion) {
		this.cambioRegion = cambioRegion;
	}
}