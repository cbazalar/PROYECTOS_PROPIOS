package biz.belcorp.ssicc.web.scsicc.action;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.model.Usuario;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteLETLideresDesvinculadasForm;

/**
 * @author César Estrada
 * @company Sigcomt
 * 
 */
@ManagedBean
@SessionScoped
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ReporteLETLideresDesvinculadasAction extends
		BaseReporteAbstractAction implements Serializable {

	private String formatoReporte;
	private List siccMarcaList;
	private List siccCanalList;

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
	public List getSiccMarcaList() {
		return siccMarcaList;
	}

	/**
	 * @param siccMarcaList
	 */
	public void setSiccMarcaList(List siccMarcaList) {
		this.siccMarcaList = siccMarcaList;
	}

	/**
	 * @return
	 */
	public List getSiccCanalList() {
		return siccCanalList;
	}

	/**
	 * @param siccCanalList
	 */
	public void setSiccCanalList(List siccCanalList) {
		this.siccCanalList = siccCanalList;
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
		ReporteLETLideresDesvinculadasForm reporteForm = new ReporteLETLideresDesvinculadasForm();
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

		if ("XLS".equals(formatoReporte) || ("XLS".equals(formatoReporte)))
			return "reporteLETLideresDesvinculadasXLS";
		else
			return "";
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
		
		log.debug("ReporteLETLideresDesvinculadasAction - setViewAttributes");


		Usuario usuario = this.mPantallaPrincipalBean.getCurrentUser();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ReporteLETLideresDesvinculadasForm f = (ReporteLETLideresDesvinculadasForm) this.formReporte;
		f.reset();
		InterfazSiCCService interfazSiCCService = (InterfazSiCCService) getBean("sisicc.interfazSiCCService");
		
		this.siccMarcaList=interfazSiCCService.getMarcas();
		
		this.siccCanalList=interfazSiCCService
				.getCanalesByCodigoISO(usuario.getIdioma().getCodigoISO());
		

		f.setCodigoMarca(Constants.CODIGO_MARCA_DEFAULT);
		f.setCodigoCanal(Constants.CODIGO_CANAL_DEFAULT);

		f.setCodigoPais(pais.getCodigo());

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("estadoCampanha", Constants.NUMERO_CERO); // Indica
																// Campanha
																// Activa
		criteria.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica
																	// Campanha
																	// activa q
																	// se
																	// procesa
																	// actualmente

		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteria);

		// Carga de PeriodoProceso y Fecha Facturacion
		f.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		log.debug("Todo Ok: Redireccionando");

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction
	 * #prepareParameterMap()
	 */
	protected Map prepareParameterMap(Map params) throws Exception {

		log.debug("ReporteLETLideresDesvinculadasAction - prepareParameterMap");

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();

		ReporteLETLideresDesvinculadasForm f = (ReporteLETLideresDesvinculadasForm) this.formReporte;

		this.formatoReporte = f.getFormatoExportacion();

		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");

		Map criteria = new HashMap();
		criteria.put("codigoPais", pais.getCodigo());
		criteria.put("codigoPeriodo", f.getCodigoPeriodo());
		criteria.put("codigoMarca", f.getCodigoMarca());
		criteria.put("codigoCanal", f.getCodigoCanal());

		params.put("oidPais", Integer.valueOf(reporteService.getOidString(
				"getOidPaisByCodigoPais", criteria)));
		params.put("oidPeriodo", Integer.valueOf(reporteService.getOidString(
				"getOidPeriodoByCodigoPeriodo", criteria)));
		params.put("codigoPeriodo", f.getCodigoPeriodo());
		params.put("oidMarca", Integer.valueOf(reporteService.getOidString(
				"getOidMarcaByCodigoMarca", criteria)));
		params.put("oidCanal", Integer.valueOf(reporteService.getOidString(
				"getOidCanalByCodigoCanal", criteria)));
		return params;

	}
}