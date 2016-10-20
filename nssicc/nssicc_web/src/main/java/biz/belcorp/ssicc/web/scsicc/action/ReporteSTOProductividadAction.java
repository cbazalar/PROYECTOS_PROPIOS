//TODO Migrar al framework de reportes
package biz.belcorp.ssicc.web.scsicc.action;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import biz.belcorp.ssicc.dao.Constants;
import biz.belcorp.ssicc.dao.model.Pais;
import biz.belcorp.ssicc.dao.spusicc.pedidos.model.PedidoControlFacturacion;
import biz.belcorp.ssicc.dao.util.DateUtil;
import biz.belcorp.ssicc.reportes.framework.bean.BaseReporteForm;
import biz.belcorp.ssicc.reportes.web.scsicc.form.ReporteSTOProductividadForm;
import biz.belcorp.ssicc.service.AjaxService;
import biz.belcorp.ssicc.service.sisicc.InterfazSiCCService;
import biz.belcorp.ssicc.service.spusicc.pedidos.MantenimientoOCRPedidoControlFacturacionService;
import biz.belcorp.ssicc.service.spusicc.sto.ProcesoSTOEjecucionValidacionesService;
import biz.belcorp.ssicc.web.framework.base.action.BaseReporteAbstractAction;

@ManagedBean
@SessionScoped
public class ReporteSTOProductividadAction extends BaseReporteAbstractAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7521118584500251560L;
	private String formatoReporte;
	private List stoTipoDocumentoList;
	private Date fechaini;
	private Date fechafin;

	@Override
	protected BaseReporteForm devuelveFormReporte() throws Exception {
		ReporteSTOProductividadForm reporteForm = new ReporteSTOProductividadForm();
		return reporteForm;
	}

	protected String devuelveBeanReporteService() {
		return "reportes.reporteSTOProductividadService";
	}

	@Override
	protected String devuelveNombreReporte() throws Exception {
		ReporteSTOProductividadForm form = (ReporteSTOProductividadForm) this.formReporte;
		log.debug(form.getFormatoExportacion());
		if ("XLS".equals(formReporte.getFormatoExportacion()))
			return "reporteSTOProductividadXLS";
		else
			return "reporteMaestroHorizontal";
	}

	@Override
	protected String devuelveNombreSubReporte() throws Exception {
		return " ";
	}

	@Override
	protected Map prepareParameterMap(Map params) throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'prepareParameterMap' method");
		}
		ReporteSTOProductividadForm reporteForm = (ReporteSTOProductividadForm) this.formReporte;
		formatoReporte = reporteForm.getFormatoExportacion();
		params.put("codigoPais", reporteForm.getCodigoPais());
		params.put("tipoDocumento", reporteForm.getTipoDocumento());
		params.put("periodo", reporteForm.getCodigoPeriodo());
		params.put("fechaDesde", reporteForm.getFechaDesde());
		params.put("fechaHasta", reporteForm.getFechaHasta());
		params.put("titulo",
				getResourceMessage("reporteSTOProductividadForm.title"));

		return params;

	}

	@Override
	protected void setViewAtributes() throws Exception {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'view' method");
		}

		this.mostrarReporteXLS = true;
		this.mostrarReportePDF = false;

		Pais pais = this.mPantallaPrincipalBean.getCurrentCountry();
		String codpais = pais.getCodigo();

		Map criteria = new HashMap();

		criteria.put("codigoPais", codpais);
		criteria.put("codigoUsuario", this.mPantallaPrincipalBean
				.getCurrentUser().getLogin());
		ReporteSTOProductividadForm reporteForm = (ReporteSTOProductividadForm) this.formReporte;
		// Carga Fecha
		InterfazSiCCService service = (InterfazSiCCService) this
				.getBean("sisicc.interfazSiCCService");
		// Carga Periodo
		Map criteriaPeriodo = new HashMap();
		criteriaPeriodo.put("codigoPais", codpais);
		criteriaPeriodo.put("estadoCampanha", Constants.NUMERO_CERO); // Indica Campanha Activa
		criteriaPeriodo.put("indicadorActiva", Constants.ESTADO_ACTIVO); // Indica Campanha activa q se procesa actualmente

		MantenimientoOCRPedidoControlFacturacionService serviceFact = (MantenimientoOCRPedidoControlFacturacionService) this
				.getBean("spusicc.pedidos.mantenimientoOCRPedidoControlFacturacionService");
		PedidoControlFacturacion controlFacturacion = serviceFact
				.getControlFacturacionById(criteriaPeriodo);

		reporteForm.setCodigoPeriodo(controlFacturacion.getCodigoPeriodo());

		// Carga lista de documentos
		ProcesoSTOEjecucionValidacionesService procesoSTOService = (ProcesoSTOEjecucionValidacionesService) this
				.getBean("spusicc.procesoSTOEjecucionValidacionesService");

		stoTipoDocumentoList = procesoSTOService
				.getTiposDocumentosSTO(criteria);
		
		
		
		AjaxService ajaxService = (AjaxService) getBean("ajaxService");
		String fechaDesde = ajaxService
				.getFechaInicioPeriodoByPaisMarcaCanal(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, controlFacturacion.getCodigoPeriodo());
		String fechaHasta = ajaxService
				.getFechaFinalPeriodoByPaisMarcaCanal(this
						.getmPantallaPrincipalBean().getCurrentCountry()
						.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
						Constants.CODIGO_CANAL_DEFAULT, controlFacturacion.getCodigoPeriodo());
		reporteForm.setFechaDesdeD(DateUtil.convertStringToDate(fechaDesde));
		reporteForm.setFechaHastaD(DateUtil.convertStringToDate(fechaHasta));
		
		this.fechaini=DateUtil.convertStringToDate(fechaDesde);
		this.fechafin=DateUtil.convertStringToDate(fechaHasta);
	
		
		
      

	}
	
	/**
	 * Metodo para Cambiar el Rango de Fechas
	 * @param val
	 * @throws ParseException 
	 */
	public void loadFechasPeriodo(String valor) {
		if (log.isDebugEnabled()) {
			log.debug("loadFechasPeriodo");
		}
		ReporteSTOProductividadForm reporteForm = (ReporteSTOProductividadForm) this.formReporte;
		try {
			if (valor.length() > 0) {
				AjaxService ajaxService = (AjaxService) getBean("ajaxService");
				String fechaDesde = ajaxService
						.getFechaInicioPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				String fechaHasta = ajaxService
						.getFechaFinalPeriodoByPaisMarcaCanal(this
								.getmPantallaPrincipalBean().getCurrentCountry()
								.getCodigo(), Constants.CODIGO_MARCA_DEFAULT,
								Constants.CODIGO_CANAL_DEFAULT, valor);
				reporteForm.setFechaDesdeD(DateUtil.convertStringToDate(fechaDesde));
				reporteForm.setFechaHastaD(DateUtil.convertStringToDate(fechaHasta));
				
				this.fechaini=DateUtil.convertStringToDate(fechaDesde);
				this.fechafin=DateUtil.convertStringToDate(fechaHasta);
			}
		}
		catch (Exception e) {
			
		}
		
	}

	public String setValidarReporte() {
		ReporteSTOProductividadForm form = (ReporteSTOProductividadForm) this.formReporte;
		String fechaIni =DateUtil.convertDateToString(fechaini);
		String fechaFin =DateUtil.convertDateToString(fechafin);
		if (fechaini.compareTo(form.getFechaDesdeD()) ==1 || fechafin.compareTo(form.getFechaDesdeD())<0) {
			String mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaInicio")+fechaIni+" - "+fechaFin;
			return mensaje;
		} else if (fechafin.compareTo(form.getFechaHastaD())<0 || fechaini.compareTo(form.getFechaHastaD()) ==1) {
			String mensaje = this.getResourceMessage("errors.compare.campaigns.periodo.fechaFin")+fechaIni+" - "+fechaFin;
			return mensaje;
		}

		return null;
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
	 * @return the stoTipoDocumentoList
	 */
	public List getStoTipoDocumentoList() {
		return stoTipoDocumentoList;
	}

	/**
	 * @param stoTipoDocumentoList
	 *            the stoTipoDocumentoList to set
	 */
	public void setStoTipoDocumentoList(List stoTipoDocumentoList) {
		this.stoTipoDocumentoList = stoTipoDocumentoList;
	}

	/**
	 * @return the fechaini
	 */
	public Date getFechaini() {
		return fechaini;
	}

	/**
	 * @param fechaini the fechaini to set
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
	 * @param fechafin the fechafin to set
	 */
	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}
	
	

}
