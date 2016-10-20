package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
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
import biz.belcorp.ssicc.dao.model.ParametroPais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.GenericoService;
import biz.belcorp.ssicc.service.scsicc.ReporteService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;
import biz.belcorp.ssicc.web.scsicc.form.ReporteMAEConsejerasReactivadasForm;

@ManagedBean
@SessionScoped
public class ReporteMAEConsejerasReactivadasAction extends
		BaseReporteAbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6841250864214074144L;
	private String formatoReporte;
	private LabelValue[] siccRegionList = {};
	private LabelValue[] siccZonaList = {};
	private Date fechaini;
	private Date fechafin;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteMAEConsejerasReactivadasForm reporteForm = new ReporteMAEConsejerasReactivadasForm();
		return reporteForm;
	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.info("Entro a ReporteMAEConsejerasReactivadasAction - setViewAttributes");
		}
		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		// -- Parametros
		ReporteMAEConsejerasReactivadasForm reporteMAEConsejerasReactivadasForm = (ReporteMAEConsejerasReactivadasForm) this.formReporte;
		MantenimientoOCRPedidoControlFacturacionService service = (MantenimientoOCRPedidoControlFacturacionService) getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		// -- Logica captura Periodo ---------------------------------

		// -- criteria
		Map criteriaPeriodo = new HashMap();
		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		criteriaPeriodo.put("codigoPais", pais.getCodigo());
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO);
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO);

		// -- Logica
		PedidoControlFacturacion controlFacturacion = service
				.getControlFacturacionById(criteriaPeriodo);
		reporteMAEConsejerasReactivadasForm.setCodigoPeriodo(controlFacturacion
				.getCodigoPeriodo());

		// -- Peticiones
		this.siccRegionList = ajaxService.getRegionesByPaisMarcaCanal(
				pais.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT);

		

		String codPeriodo = controlFacturacion.getCodigoPeriodo();
		String fechaDesde = ajaxService.getFechaInicioPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, codPeriodo);
		String fechaHasta = ajaxService.getFechaFinalPeriodoByPaisMarcaCanal(
				this.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
				Constants.CODIGO_CANAL_DEFAULT, codPeriodo);
		reporteMAEConsejerasReactivadasForm.setFechaInicioD(DateUtil
				.convertStringToDate(fechaDesde));
		reporteMAEConsejerasReactivadasForm.setFechaFinD(DateUtil
				.convertStringToDate(fechaHasta));

		this.fechaini = DateUtil.convertStringToDate(fechaDesde);
		this.fechafin = DateUtil.convertStringToDate(fechaHasta);
		
		log.info("Salio a ReporteMAEConsejerasReactivadasAction - setViewAttributes");

	}


	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.info("Entro a ReporteMAEConsejerasReactivadasAction - prepareParameterMap");
		}

		
		//-- Parametros
		ReporteMAEConsejerasReactivadasForm reporteMAEConsejerasReactivadasForm = (ReporteMAEConsejerasReactivadasForm) this.formReporte;
		ReporteService reporteService = (ReporteService) getBean("scsicc.reporteService");
		
		//-- Formato exportacion
		formatoReporte = reporteMAEConsejerasReactivadasForm.getFormatoExportacion();
		
		//-- Parametros Reporte -------------------------------------
		
		//-- Condicion dinamica
		String condicionRegion = obtieneCondicion(reporteMAEConsejerasReactivadasForm.getCodigoRegion(), "zr.COD_REGI", "'");
		String condicionZona = obtieneCondicion(reporteMAEConsejerasReactivadasForm.getCodigoZona(), "zz.COD_ZONA", "'");
		String condicion = condicionRegion.concat("  ").concat(condicionZona);
		params.put("condicion", condicion);
		
		//-- Otros
		String fechaini=DateUtil.convertDateToString(reporteMAEConsejerasReactivadasForm.getFechaInicioD());
		String fechafin=DateUtil.convertDateToString(reporteMAEConsejerasReactivadasForm.getFechaFinD());
		
		params.put("codigoPeriodo", reporteMAEConsejerasReactivadasForm.getCodigoPeriodo());
		params.put("fechaInicio", fechaini);
		params.put("fechaFin", fechafin);
		
        Map criteria = new HashMap();
        Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
        criteria.put("codigoPais", pais.getCodigo());
        params.put("oidPais", reporteService.getOidString("getOidPaisByCodigoPais", criteria));
        
        
       //INI ECU-SiCC-2015-0036
        ParametroPais paramPais = new ParametroPais();
      	paramPais.setCodigoPais(mPantallaPrincipalBean.getCurrentCountry().getCodigo());
      	paramPais.setCodigoSistema(Constants.MAE_CODIGO_SISTEMA);
      	paramPais.setNombreParametro(Constants.MAE_PARAM_INDICADOR_CAMPOS_ADICIONALES);
      	paramPais.setValorParametro(Constants.NUMERO_UNO);

      	GenericoService genericoService = (GenericoService) getBean("genericoService");
      	List listParametros = genericoService.getParametrosPais(paramPais);

      		if (listParametros != null && listParametros.size() > 0) {
      			params.put("indCamposAdicionales", true);
      		} else {
      			params.put("indCamposAdicionales", false);
      		}
       //FIN ECU-SiCC-2015-0036
        
		
		log.info("Salio a ReporteMAEConsejerasReactivadasAction - prepareParameterMap");
		
		return params;
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteMAEConsejerasReactivadasForm form = (ReporteMAEConsejerasReactivadasForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteMAEConsejerasReactivadasXLS";
		else
			return "reporteMaestroHorizontal";
		

	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return "";
	}

	public void loadZonas(ValueChangeEvent val) {
		if (log.isDebugEnabled()) {
			log.debug("loadZonas");
		}
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

		}else{
			setSiccZonaList(null);
		}

	}

	/**
	 * Metodo para Cambiar el Rango de Fechas
	 * 
	 * @param val
	 * @throws ParseException
	 */
	public void loadFechasPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo");
		}
		ReporteMAEConsejerasReactivadasForm reporteForm = (ReporteMAEConsejerasReactivadasForm) this.formReporte;
		try {
			if (valor.length() > 0) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				String fechaDesde = ajaxService
						.getFechaInicioPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean()
								.getCurrentCountry().getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				String fechaHasta = ajaxService
						.getFechaFinalPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean()
								.getCurrentCountry().getCodigo(),
								Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				reporteForm.setFechaInicioD(DateUtil
						.convertStringToDate(fechaDesde));
				reporteForm.setFechaFinD(DateUtil
						.convertStringToDate(fechaHasta));

				this.fechaini = DateUtil.convertStringToDate(fechaDesde);
				this.fechafin = DateUtil.convertStringToDate(fechaHasta);
			}
		} catch (Exception e) {

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
	 * @return the fechaini
	 */
	public Date getFechaini() {
		return fechaini;
	}

	/**
	 * @param fechaini
	 *            the fechaini to set
	 */
	public void setFechaini(Date fechaini) {
		this.fechaini = fechaini;
	}

	/**
	 * @return the fechafin
	 */
	public Date getFechafin() {
		return fechafin;
	}

	/**
	 * @param fechafin
	 *            the fechafin to set
	 */
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

}
