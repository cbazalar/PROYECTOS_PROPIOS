package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import org.apache.commons.lang3.StringUtils;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.LabelValue;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.MantenimientoSTOEstadoEntregaOrdenTransporteService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteSTONovedadesBoletasForm;

/**
 * @author César Estrada
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteSTONovedadesBoletasAction extends BaseReporteAbstractAction
		implements Serializable {

	private String formatoReporte;
	private List siccMarcaList;
	private List stoCompaniasTransporte;
	private List stoTipoOrdenTransporte;
	private List siccRegionesList;
	private LabelValue[] siccZonasList;
	private String tipoReporte;

	/**
	 * @return
	 */
	public String getTipoReporte() {
		return tipoReporte;
	}

	/**
	 * @param tipoReporte
	 */
	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	/**
	 * @return
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return
	 */
	public List getStoCompaniasTransporte() {
		return stoCompaniasTransporte;
	}

	/**
	 * @param stoCompaniasTransporte
	 */
	public void setStoCompaniasTransporte(List stoCompaniasTransporte) {
		this.stoCompaniasTransporte = stoCompaniasTransporte;
	}

	/**
	 * @return
	 */
	public List getStoTipoOrdenTransporte() {
		return stoTipoOrdenTransporte;
	}

	/**
	 * @param stoTipoOrdenTransporte
	 */
	public void setStoTipoOrdenTransporte(List stoTipoOrdenTransporte) {
		this.stoTipoOrdenTransporte = stoTipoOrdenTransporte;
	}

	/**
	 * @return
	 */
	public List getSiccRegionesList() {
		return siccRegionesList;
	}

	/**
	 * @param siccRegionesList
	 */
	public void setSiccRegionesList(List siccRegionesList) {
		this.siccRegionesList = siccRegionesList;
	}

	/**
	 * @return
	 */
	public LabelValue[] getSiccZonasList() {
		return siccZonasList;
	}

	/**
	 * @param siccZonasList
	 */
	public void setSiccZonasList(LabelValue[] siccZonasList) {
		this.siccZonasList = siccZonasList;
	}

	/**
	 * @return
	 */
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	private static final long serialVersionUID = 5452137025798023586L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #devuelveFormReporte()
	 */
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTONovedadesBoletasForm reporteForm = new ReporteSTONovedadesBoletasForm();
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

		if ("XLS".equals(formatoReporte))
			return "reporteSTONoveBole" + this.tipoReporte + "XLS";
		else
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
		return "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see biz.belcorp.ssicc.web.framework.base.action.MBaseSistemaAbstractJSF#
	 * setViewAtributes()ReporteRECMercaderiaSiniestradaAction
	 */

	@Override
	protected void setViewAtributes() throws Exception {
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		ReporteSTONovedadesBoletasForm f = (ReporteSTONovedadesBoletasForm) this.formReporte;
		Map criteria = new HashMap();
		f.setCodigoPais(this.mPantallaPrincipalBean.getCurrentCountry().getCodigo());
		// Carga las regiones
		ReporteService reporteService = (ReporteService) this
				.getBean("scsicc.reporteService");
		criteria.put("codigoPais", f.getCodigoPais());
		criteria.put("codigoUsuario", this.mPantallaPrincipalBean.getCurrentUser().getLogin());
		this.siccRegionesList=reporteService.getListaGenerico("getRegionesByPais", criteria);


		// Carga periodo
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																		// Campanha
																		// Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																			// Campanha
																			// activa
																			// q
																			// se
																			// procesa
																			// actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this
				.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteriaPeriodo);
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());
		// Carga los tipos de orden
		MantenimientoSTOEstadoEntregaOrdenTransporteService service = (MantenimientoSTOEstadoEntregaOrdenTransporteService) getBean("spusicc.mantenimientoSTOEstadoEntregaOrdenTransporteService");
		this.stoTipoOrdenTransporte=service.getTiposOrdenTransporte();
		// Carga las companias de transporte
		ProcesoSTOEjecucionValidacionesService procesoSTOservice = (ProcesoSTOEjecucionValidacionesService) getBean("spusicc.procesoSTOEjecucionValidacionesService");
		this.stoCompaniasTransporte = procesoSTOservice.getCompaniasTransporte();
		
		f.setTipoReporte("P");
		
		log.debug("Todo Ok: Redireccionando");
	}
	
	protected String devuelveBeanReporteService(){
		return "reportes.reporteSTONovedadesBoletasService";
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		ReporteSTONovedadesBoletasForm reporteForm = (ReporteSTONovedadesBoletasForm) this.formReporte;
		this.formatoReporte = reporteForm.getFormatoExportacion();
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		if (reporteForm.getTipoReporte().equals("P")) {
			this.tipoReporte = "Periodo";
		}
		if (reporteForm.getTipoReporte().equals("Z")) {
			this.tipoReporte = "Zona";
			params.put("SUBREPORT_DIR", "subreporteSTONoveBoleZonaXLS"
					+ JASPER_EXTENSION);
			params.put("condicionesZonas", this.obtieneCondicion(
					reporteForm.getZonaList(), "zon.COD_ZONA", "'"));
		}
		if (reporteForm.getTipoReporte().equals("R")) {
			this.tipoReporte = "Region";
			params.put("SUBREPORT_DIR", "subreporteSTONoveBoleRegionXLS"
					+ JASPER_EXTENSION);
		}
		if (reporteForm.getTipoReporte().equals("T")) {
			this.tipoReporte = "CiaTrans";
			params.put("SUBREPORT_DIR", "subreporteSTONoveBoleCiaTransXLS"
					+ JASPER_EXTENSION);
		}
		if (reporteForm.getTipoReporte().equals("TD")) {
			this.tipoReporte = "CiaTransD";
			params.put("SUBREPORT_DIR", "subreporteSTONoveBoleCiaTransDetXLS"
					+ JASPER_EXTENSION);
		}

		params.put("codigoPais", reporteForm.getCodigoPais());
		if (!reporteForm.getCodigoPeriodo().equals(""))
			params.put("codigoPeriodo", reporteForm.getCodigoPeriodo());
		try {
			params.put("oidPeriodo", reporteService.getOidString(
					"getOidPeriodoByCodigoPeriodo", params));
		} catch (Exception e) {
			this.addError("Error : ", this.obtieneMensajeErrorException(e));
		}

		params.put("codigoRegion", reporteForm.getCodigoRegion());
		params.put("tipoOrden", reporteForm.getCodigoTipoOrden());
		params.put("codigoCiaTransporte",
				reporteForm.getCodigoCompaniaTransporte());

		if (reporteForm.getCodigoTipoOrden().equals("BEP")) {
			params.put(
					"titulo",
					this.getResourceMessage("reporteSTONovedadesBoletas.titulo.bep"));
		} else if (reporteForm.getCodigoTipoOrden().equals("BER")) {
			params.put(
					"titulo",
					this.getResourceMessage("reporteSTONovedadesBoletas.titulo.ber"));
		} else {
			params.put("titulo", this
					.getResourceMessage("reporteSTONovedadesBoletas.titulo.br"));
		}
		return params;
	}
	
	/**
	 * 
	 * @param val
	 */
	public void cambiarTipoReporte(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("cambiarTipoReporte...");
		}
		ReporteSTONovedadesBoletasForm reporteForm = (ReporteSTONovedadesBoletasForm) this.formReporte;
		String tipoReporte = (String) val.getNewValue();
		
		reporteForm.setTipoReporte(tipoReporte);
		reporteForm.setCodigoRegion(null);
		reporteForm.setZonaList(null);
		reporteForm.setCodigoCompaniaTransporte(null);
		this.siccZonasList = new LabelValue[]{};
	}

	/**
	 * 
	 * @param val
	 */
	public void cargarZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("cambiarTipoReporte...");
		}
		ReporteSTONovedadesBoletasForm reporteForm = (ReporteSTONovedadesBoletasForm) this.formReporte;
		String codigoRegion = (String) val.getNewValue();
		
		if(!StringUtils.equals(codigoRegion, Constants.TODAS))
		{
			AjaxService ajaxService = (AjaxService)this.getBean("ajaxService");	
			this.siccZonasList = ajaxService.getZonasMultipleByPaisMarcaCanalRegion(reporteForm.getCodigoPais(), Constants.CODIGO_MARCA_DEFAULT, Constants.CODIGO_CANAL_DEFAULT, new String[]{codigoRegion}, ""); 
		}
		else
		{
			this.siccZonasList = new LabelValue[]{};
		}		
	}
	
}